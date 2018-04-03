package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Flugzeug;

/**
 * Session Bean implementation class FlugzeugHandler. <br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Flugzeug.
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
	/**
	 * Methode, die ein Flugzeug anlegt.
	 * @param hersteller Angabe des Herstellers des Flugzeugs
	 * @param typ Angabe des Typs des Flugzeugs
	 * @param sitzplaetze Angabe der Sitzplaetze des Flugzeugs
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 */
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
	/**
	 * gibt alle Flugzeuge aus. <br>
	 * Beispiel: "1. Flugzeug: Airbus A380-800 (853 Sitzplätze)" <br>
	 * Der Default Wert mit der flugzeugid=1 wird nicht ausgegeben. <br>
	 * Der erste angezeigt Eintrag ist der zweite in der Datenbank. (Es wird auch mit 1. Flugzeug begonnen)
	 * @return gibt eine List<String> aus. Beispiel siehe Beschreibung der Methode.
	 */
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
	/**
	 * Methode, die ein Flugzeug einem Flug zuordnet. <br>
	 * Zu beachten ist, dass ein Flugzeug nur genau einem Flug zugeordnet werden kann!
	 * @param flugzeugString String im Format der Rückgabe der Methode FlugzeugHandler.getAllFlugzeuge()
	 * @param flugString String im Format der Rückgabe der Methode FlugHandler.getAllFluege()
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 */
	public String assignFlugzeugToFlug(String flugzeugString, String flugString) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		String[] flugzeugArrayString = flugzeugString.split("\\.");
		int flugzeugid = Integer.parseInt(flugzeugArrayString[0]);
		flugzeugid+=1;
		
		String[] flugArrayString = flugString.split("\\:");
		String flugid = flugArrayString[0];
		
		Query queryFlug = em.createNamedQuery("Flug.findbyID").setParameter("id", flugid);
		List<Flug> flugliste = new ArrayList<Flug>();
		for (Object o : queryFlug.getResultList()) {
			flugliste.add((Flug) o);
		}
		for(Flug flug : flugliste) {
			if(flug.getFlugzeugid()==flugzeugid&&!flug.getFlugid().equals(flugid)) {
				em.close();
				return ErrorHandler.FLUGZEUGBEREITSZUGEORDNET;
			}
			if(flug.getFlugzeugid()==flugzeugid&&flug.getFlugid().equals(flugid)) {
				em.close();
				return ErrorHandler.FLUGZEUGDIESEMFLUGZUGEORDNET;
			}
		}
		
		Flug flug = new Flug();
		flug = flugliste.get(0);
		flug.setFlugzeugid(flugzeugid);
		em.merge(flug);
		em.getTransaction().commit();
		em.close();
		return SuccessHandler.FLUGZEUGZUORDNUNG;
	}

}
