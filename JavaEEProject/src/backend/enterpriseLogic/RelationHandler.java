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
 * Session Bean implementation class RelationHandler
 */
@Stateless
@LocalBean
public class RelationHandler extends DatabaseHandler {

	public RelationHandler() {
		super();
	}

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
