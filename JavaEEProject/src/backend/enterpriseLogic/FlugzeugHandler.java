package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Flugzeug;

/**
 * Session Bean implementation class FlugzeugHandler
 */
@Stateless
@LocalBean
public class FlugzeugHandler extends DatabaseHandler {

	/**
	 * Default constructor.
	 */
	public FlugzeugHandler() {
		super();
	}

	public String createFlugzeug(String hersteller, String typ, int sitzplaetze) {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		if (hersteller == null || typ == null || hersteller.isEmpty() || typ.isEmpty()) {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		Flugzeug flugzeug = new Flugzeug(hersteller, typ, sitzplaetze);
		em.persist(flugzeug);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.FLUGZEUGANLAGE;
	}

	public List<String> getAllFlugzeuge() {
		List<String> flugzeugliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Flugzeug.findAll");
		List<Flugzeug> flugzeuglisteDB = new ArrayList<Flugzeug>();
		for (Object o : query.getResultList()) {
			flugzeuglisteDB.add((Flugzeug) o);
		}
		if (!flugzeuglisteDB.isEmpty()) {
			for (Flugzeug flugzeug : flugzeuglisteDB) {
				if (flugzeug.getFlugzeugid() != 1) {
					int id = flugzeug.getFlugzeugid() - 1;
					flugzeugliste.add(id + ". Flugzeug: " + flugzeug.getHersteller() + " "
							+ flugzeug.getTyp() + " (" + flugzeug.getSitzplaetze() + " Sitzplätze)");
				}

			}
		}
		em.close();
		return flugzeugliste;
	}

	public String assignFlugzeugToFlug(int flugzeugid, String flugid) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query queryFlug = em.createNamedQuery("Flug.findbyID").setParameter("id", flugid);
		List<Flug> flugliste = new ArrayList<Flug>();
		for (Object o : queryFlug.getResultList()) {
			flugliste.add((Flug) o);
		}
		Flug flug = new Flug();
		flug = flugliste.get(0);
		flug.setFlugzeugid(flugzeugid);
		em.merge(flug);
		em.getTransaction().commit();
		em.close();
		return SuccessHandler.FLUGZEUGANLAGE;
	}

}
