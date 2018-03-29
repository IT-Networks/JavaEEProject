package backend.enterpriseLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Relation;

/**
 * Session Bean implementation class FlugHandler
 */
@Stateless
@LocalBean
public class FlugHandler {
	EntityManagerFactory emf = null;
	EntityManager em = null;
    /**
     * Default constructor. 
     */
    public FlugHandler() {
		emf = Persistence.createEntityManagerFactory("JavaEEProject");
    }
    
    public String createFlug(Date abflug, Date ankunft, int relationid, double preis) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Relation> relationliste = new ArrayList<Relation>();
		Query query = em.createNamedQuery("Relation.findbyID").setParameter("id", relationid);
		for (Object o : query.getResultList()) {
			relationliste.add((Relation) o);
		}
		if(relationliste.isEmpty()) {
			return ErrorHandler.RELATIONNICHTVORHANDEN;
		}
		Flug flug = new Flug();
		flug.setAbflug(abflug);
		flug.setAnkunft(ankunft);
		flug.setPreis(BigDecimal.valueOf(preis));
		Relation r = new Relation();
		r.setRelationid(relationid);
		flug.setRelation(r);
		em.persist(flug);
		em.getTransaction().commit();
		
		em.close();
    	return "";
    }

}
