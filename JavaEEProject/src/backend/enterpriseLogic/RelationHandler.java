package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import backend.entities.Flughafen;
import backend.entities.Relation;

/**
 * Session Bean implementation class RelationHandler
 */
@Stateless
@LocalBean
public class RelationHandler implements RelationHandlerLocal {

	/**
	 * Default constructor.
	 */
	public RelationHandler() {
	}

	
	public String createRelation(String startort, String zielort) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEEProject");

		EntityManager em = emf.createEntityManager();

		Relation relation = new Relation();

		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flughafen.findAll");

		Query query1 = em.createNamedQuery("Relation.findAll");
		List<Relation> relationliste = new ArrayList<Relation>();
		
		for(Object o : query1.getResultList()) {
			relationliste.add((Relation) o);
		}
		for (Relation relationschleife : relationliste) {
			Flughafen forstartort = relationschleife.getFlughafen1();
			Flughafen forzielort = relationschleife.getFlughafen2();
			if (forstartort.getFlughafenid().equals(startort) && forzielort.getFlughafenid().equals(zielort)) {
				return ErrorHandler.RELATIONSCHONVORHANDEN;
			}
		}
		List<Flughafen> flughafenliste = new ArrayList<Flughafen>();
		for(Object o : query.getResultList()) {
			flughafenliste.add((Flughafen) o);
		}
		for (Flughafen flughafen : flughafenliste) {
			if (flughafen.getFlughafenid().equals(startort)) {
				relation.setFlughafen1(flughafen);
			}
			if (flughafen.getFlughafenid().equals(zielort)) {
				relation.setFlughafen2(flughafen);
			}
		}
		if (relation.getFlughafen1() == null) {
			return ErrorHandler.STARTORTNICHTGEFUNDEN;
		}
		if (relation.getFlughafen2() == null) {
			return ErrorHandler.ZIELORTNICHTGEFUNDEN;
		}

		em.persist(relation);
		em.getTransaction().commit();

		em.close();
		return "Erfolgreiche Anlage der Relation!";

	}

}
