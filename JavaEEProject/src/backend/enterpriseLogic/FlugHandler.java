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
 * Session Bean implementation class FlugHandler. <br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Flug.
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
	/**
	 * Methode, die eine Flug anlegt. <br>
	 * Anfangs werden die Felder flugzeugid und mahlzeitid auf die default Felder der Tabellen Flugzeug und Mahlzeit belegt (jeweils die ID=1). <br>
	 * Die FlugID setzt sich nach dem folgenden Muster zusammen: MH %relationid%/%flugnummer% (Die Flugnummer wird bei Neuanlage um 1 erhöht). <br>
	 * Folglich hat der erste Flug der Relation 1  die FlugID MH1/1 der zweite Flug MH1/2 usw. <br>
	 * 
	 * @param abflug muss im Format EEE MMM dd HH:mm:ss zzz yyyy  (z.B. Tue Apr 17 17:46:00 CEST 2018) sein
	 * @param relationString String im Format der Rückgabe der Methode FlugHandler.getAllRelationen()
	 * @param preis Angabe des Preises für den Flug
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 * @throws ParseException kann bei einem falsch formatieren abflug-String auftreten.
	 */
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
	/**
	 * gibt alle Flüge aus. Beispiel:  "MH1/1: Abflug: 2018-26-01 01:26, Ankunft: 2018-26-01 01:26 (Preis: 25.00 €)"
	 * 
	 * @return ibt eine List<String> aus. Beispiel siehe Beschreibung der Methode.
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
	/**
	 * Ausgabe des Status eines Flugs zu einer gegebeben Uhrzeit
	 * @param aktuellString Übergabe eines Zeipunkts im Format: EEE MMM dd HH:mm:ss zzz yyyy  (z.B. Tue Apr 17 17:46:00 CEST 2018)
	 * @param flugString
	 * @return String im Format der Rückgabe der Methode FlugHandler.getAllFluege()
	 * @throws ParseException kann bei einem falsch formatieren akutellString auftreten.
	 */
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
	/**
	 * Methode, die für die createFlug Methode aus der Abflugszeit und der Flugzeit der Relation die Ankunftszeit errechnet.
	 * @param abflugszeit Abflugszeit des Flugs
	 * @param flugzeit Flugzeit der zugehörigen Relation
	 * @return Ausgabe der Ankunftszeit des Flugs
	 */
	private Date calculateAnkunftszeit(Date abflugszeit, Time flugzeit) {
		Date ankunftszeit = new Date();
		String[] time = flugzeit.toString().split(":");
		int hours = Integer.parseInt(time[0]);
		int minuts = Integer.parseInt(time[1]);
		ankunftszeit = DateUtils.addHours(abflugszeit, hours);
		ankunftszeit = DateUtils.addMinutes(ankunftszeit, minuts);
		return ankunftszeit;
	}
	/**
	 * interne Klasse, um die Status eines Flugs fest zu definieren.
	 *
	 */
	public final class Status {
		public static final String DEPARTURED = "departured";
		public static final String SCHEDULED = "scheduled";
		public static final String LANDED = "landed";
		public static final String DELAYED = "delayed";
		public static final String CANCELED = "canceled";
	}

}
