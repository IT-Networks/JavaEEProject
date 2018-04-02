package backend.enterpriseLogic;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;

import backend.entities.Flug;
import backend.entities.Relation;

/**
 * Session Bean implementation class FlugHandler
 */
@Stateless
@LocalBean
public class FlugHandler extends DatabaseHandler {
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");

	/**
	 * Default constructor.
	 */
	public FlugHandler() {
		super();
	}

	public String createFlug(String abflug, String relationString, double preis) throws ParseException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		String[] arrayString = relationString.split("\\.");
		int relationid = Integer.parseInt(arrayString[0]);

		List<Relation> relationliste = new ArrayList<Relation>();
		Query queryRelation = em.createNamedQuery("Relation.findbyID").setParameter("id", relationid);
		for (Object o : queryRelation.getResultList()) {
			relationliste.add((Relation) o);
		}
		if (relationliste.isEmpty()) {
			em.close();
			return ErrorHandler.RELATIONNICHTVORHANDEN;
		}
		Relation relation = relationliste.get(0);

		int flugnummer = 1;
		Query queryFlug = em.createNamedQuery("Flug.findbyRelationID").setParameter("id", relation);
		for (int i = 0; i < queryFlug.getResultList().size(); i++) {
			flugnummer += 1;
		}
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date abflugszeit = dateFormat.parse(abflug);
		Date ankunftszeit = calculateAnkunftszeit(abflugszeit, relation.getFlugzeit());
		Flug flug = new Flug();
		flug.setFlugid("MH" + relationid + "/" + flugnummer);
		flug.setAbflug(abflugszeit);
		flug.setAnkunft(ankunftszeit);
		flug.setPreis(BigDecimal.valueOf(preis));
		flug.setFlugzeugid(1);
		flug.setMahlzeitid(1);
		flug.setRelation(relation);

		em.persist(flug);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.FLUGANLAGE;
	}

	public List<String> getAllRelationen() {
		List<String> relationenliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Relation.findAll");
		List<Relation> relationenlisteDB = new ArrayList<Relation>();
		for (Object o : query.getResultList()) {
			relationenlisteDB.add((Relation) o);
		}
		if (!relationenlisteDB.isEmpty()) {
			for (Relation relation : relationenlisteDB) {
				relationenliste.add(relation.getRelationid() + ". Relation: Startort: " + relation.getStartort()
						+ ", Zielort: " + relation.getZielort() + " (" + relation.getDistanz() + " km, "
						+ relation.getFlugzeit() + " Stunden)");
			}
		}
		return relationenliste;
	}

	/**
	 * not implemented
	 * 
	 * @return
	 */
	public List<String> getAllFluege() {
		List<String> flugliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flug.findAll");
		List<Flug> fluglisteDB = new ArrayList<Flug>();
		for (Object o : query.getResultList()) {
			fluglisteDB.add((Flug) o);
		}
		if (!fluglisteDB.isEmpty()) {
			for (Flug flug : fluglisteDB) {
				String abflug = dateFormat.format(flug.getAbflug());
				String ankunft = dateFormat.format(flug.getAnkunft());
				flugliste.add(flug.getFlugid() + ": Abflug: " + abflug + ", Ankunft: " + ankunft + " (Preis: "
						+ flug.getPreis() + " €)");
			}
		}
		return flugliste;
	}

	public String getFlugStatus(String aktuellString, String flugString) throws ParseException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
		String[] arrayString = flugString.split("\\:");
		String flugid = arrayString[0];
		Query queryFlug = em.createNamedQuery("Flug.findbyID").setParameter("id", flugid);
		Flug flug = (Flug) queryFlug.getResultList().get(0);
		Date abflugszeit = flug.getAbflug();
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date aktuell = dateFormat.parse(aktuellString);
		if (randomNum == 0) {
			em.close();
			return Status.CANCELED;
		}
		if (randomNum == 1) {
			em.close();
			return Status.DELAYED;
		}
		if (abflugszeit.before(aktuell)||abflugszeit.equals(aktuell)) {
			em.close();
			return Status.DEPARTURED;
		}
		if (abflugszeit.after(aktuell)) {
			Date grenze = DateUtils.addHours(aktuell, 6);
			if (abflugszeit.before(grenze)) {
				em.close();
				return Status.LANDED;
			}
			em.close();
			return Status.SCHEDULED;
		}
		em.close();
		return ErrorHandler.STATUSNICHTSETZBAR;
	}

	private Date calculateAnkunftszeit(Date abflugszeit, Time flugzeit) {
		Date ankunftszeit = new Date();
		String[] time = flugzeit.toString().split(":");
		int hours = Integer.parseInt(time[0]);
		int minuts = Integer.parseInt(time[1]);
		ankunftszeit = DateUtils.addHours(abflugszeit, hours);
		ankunftszeit = DateUtils.addMinutes(ankunftszeit, minuts);
		return ankunftszeit;
	}

	private final class Status {
		public static final String DEPARTURED = "departured";
		public static final String SCHEDULED = "scheduled";
		public static final String LANDED = "landed";
		public static final String DELAYED = "delayed";
		public static final String CANCELED = "canceled";
	}

}
