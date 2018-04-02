package backend.enterpriseLogic;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;

import backend.models.FlugModel;
import backend.entities.Flug;
import backend.entities.Flughafen;
import backend.entities.Mahlzeit;
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
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
 
	public List<FlugModel> getAllFlugModels(){
		List<FlugModel> flugModelListe = new ArrayList<FlugModel>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query queryFlug = em.createNamedQuery("Flug.findAll");
		List<Flug> fluglisteDB = new ArrayList<Flug>();
		for(Object o : queryFlug.getResultList()) {
			fluglisteDB.add((Flug) o);
		}
		for(Flug flug : fluglisteDB) {
			Query queryRelation = em.createNamedQuery("Relation.findbyID").setParameter("id", flug.getRelation().getRelationid());
			Relation relation = (Relation) queryRelation.getResultList().get(0);
			Query queryFlughafen = em.createNamedQuery("Flughafen.findStartUndZiel").setParameter("start", relation.getStartort()).setParameter("ziel", relation.getZielort());
			Flughafen startFlughafen = (Flughafen) queryFlughafen.getResultList().get(0);
			Flughafen zielFlughafen = (Flughafen) queryFlughafen.getResultList().get(1);
			Query queryMahlzeit = em.createNamedQuery("Mahlzeit.findbyID").setParameter("id", flug.getMahlzeitid());
			Mahlzeit mahlzeit = (Mahlzeit) queryMahlzeit.getResultList().get(0);
			BuchungHandler bH = new BuchungHandler();
			flugModelListe.add(new FlugModel(startFlughafen.getName(), zielFlughafen.getName(), mahlzeit.getName(), flug.getFlugid(), bH.getBuchungenbyFlug(flug.getFlugid()+":")));
		}
		return flugModelListe;
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

}
