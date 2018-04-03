package backend.enterpriseLogic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flughafen;
import backend.entities.Relation;

/**
 * Session Bean implementation class RelationHandler<br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Relation.
 */
@Stateless
@LocalBean
public class RelationHandler extends DatabaseHandler {

	public RelationHandler() {
		super();
	}
	/**
	 * Methode, die eine Relation anlegt. <br>
	 * Startort und Zielort werden als Flughafennamen übergeben, aber mit der flughafenid gespeichert!
	 * @param startort Übergabe des Startflughafennamens
	 * @param zielort Übergabe des Zielflughafennamens
	 * @param flugzeit String flugzeit im Format: "hh:mm:ss"
	 * @param distanz Entfernung zwischen den beiden Flughäfen
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 */
	public String createRelation(String startort, String zielort, String flugzeit, int distanz) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flughafen.findAll");
		List<Flughafen> flughafenliste = new ArrayList<Flughafen>();
		for (Object o : query.getResultList()) {
			flughafenliste.add((Flughafen) o); 
		}
		String startid = "";
		String zielid = "";
		for (Flughafen f : flughafenliste) {
			if (f.getName().equals(startort)) {
				startid = f.getFlughafenid();
			}
			if (f.getName().equals(zielort)) {
				zielid = f.getFlughafenid();
			}
		}

		// List<Flughafen> flughafenliste = new ArrayList<Flughafen>();
		List<Relation> relationliste = new ArrayList<Relation>();
		Query queryRelation = em.createNamedQuery("Relation.findRelations").setParameter("start", startid)
				.setParameter("ziel", zielid);
		for (Object o : queryRelation.getResultList()) {
			relationliste.add((Relation) o);
		}
		if (!relationliste.isEmpty()) {
			em.close();
			return ErrorHandler.RELATIONSCHONVORHANDEN;
		}

		Relation relation = new Relation();
		for (Flughafen flughafen : flughafenliste) {
			if (flughafen.getFlughafenid().equals(startid)) {
				relation.setStartort(flughafen.getFlughafenid());
			}
			if (flughafen.getFlughafenid().equals(zielid)) {
				relation.setZielort(flughafen.getFlughafenid());
			}
		}
		if (relation.getStartort() == null) {
			em.close();
			return ErrorHandler.STARTORTNICHTGEFUNDEN;
		}
		if (relation.getZielort() == null) {
			em.close();
			return ErrorHandler.ZIELORTNICHTGEFUNDEN;
		}
		relation.setFlugzeit(Time.valueOf(flugzeit));
		relation.setDistanz(distanz);
		em.persist(relation);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.RELATIONANLAGE;

	}
	/**
	 * gibt alle Relationen aus. Beispiel:  "1. Relation: Startort: FRA, Zielort: BOM (1500 km, 10:30:00 Stunden)"
	 * @return gibt eine List<String> aus. Beispiel siehe Beschreibung der Methode.
	 */
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
	 * Methode, die alle Flughafennamen aus der Tabelle Flughafen ausgibt.
	 * @return List<String> mit allen Flughafennamen.
	 */
	public List<String> getAllFlughafennamen() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flughafen.findAll");
		List<Flughafen> flughafenliste = new ArrayList<Flughafen>();
		List<String> alleFlughafennamen = new ArrayList<String>();
		for (Object o : query.getResultList()) {
			flughafenliste.add((Flughafen) o);

		}

		for (Flughafen f : flughafenliste) {
			alleFlughafennamen.add(f.getName());
		}
		return alleFlughafennamen;
	}

}
