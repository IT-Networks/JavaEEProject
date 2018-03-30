package backend.enterpriseLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Relation;

/**
 * Session Bean implementation class FlugHandler
 */
@Stateless
@LocalBean
public class FlugHandler extends DatabaseHandler{

    /**
     * Default constructor. 
     */
    public FlugHandler() {
		super();
    }
    
    public String createFlug(Date abflug, Date ankunft, int relationid, double preis) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Relation> relationliste = new ArrayList<Relation>();
		Query queryRelation = em.createNamedQuery("Relation.findbyID").setParameter("id", relationid);
		for (Object o : queryRelation.getResultList()) {
			relationliste.add((Relation) o);
		}
		if(relationliste.isEmpty()) {
			em.close();
			return ErrorHandler.RELATIONNICHTVORHANDEN;
		}
		int flugnummer = 1;
		Query queryFlug = em.createNamedQuery("Flug.findbyRelationID").setParameter("id", relationid);
		for (Object o : queryFlug.getResultList()) {
			flugnummer+=1;
		}
		
		Flug flug = new Flug();
		flug.setFlugid("MH"+relationid+"/"+flugnummer);
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
    
    public List<String> getAllRelationen() {
		List<String> relationenliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Relation.findAll");
		List<Relation> relationenlisteDB = new ArrayList<Relation>();
		for (Object o : query.getResultList()) {
			relationenlisteDB.add((Relation) o);
		}
		if(!relationenlisteDB.isEmpty()) {
			for (Relation relation : relationenlisteDB) {
				relationenliste.add(relation.getRelationid() + ". Relation: Startort: " + relation.getStartort() + ", Zielort: "
						+ relation.getZielort() + " (" + relation.getDistanz() + " km, " + relation.getFlugzeit() + " Stunden)");
			}
		}
		return relationenliste;
    }

}
