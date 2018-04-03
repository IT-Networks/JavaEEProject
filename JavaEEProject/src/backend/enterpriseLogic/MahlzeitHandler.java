package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Mahlzeit;

/**
 * Session Bean implementation class MahlzeitHandler <br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Mahlzeit.
 */
@Stateless
@LocalBean
public class MahlzeitHandler extends DatabaseHandler {

	/**
	 * @see DatabaseHandler#DatabaseHandler()
	 */
	public MahlzeitHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Methode, die eine Mahlzeit anlegt.
	 * @param name Name der Mahlzeit.
	 * @param art Art der Mahlzeit.
	 * @param vegetarisch Vegetarisch ja/nein
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 */
	public String createMahlzeit(String name, String art, boolean vegetarisch) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if (name == null || art == null || name.isEmpty() || art.isEmpty()) {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		List<Mahlzeit> mahlzeitliste = new ArrayList<Mahlzeit>();
		Query queryMahlzeit = em.createNamedQuery("Mahlzeit.findMahlzeit").setParameter("name", name)
				.setParameter("art", art).setParameter("vegetarisch", (byte) (vegetarisch ? 1 : 0));
		for (Object o : queryMahlzeit.getResultList()) {
			mahlzeitliste.add((Mahlzeit) o);
		}
		if (!mahlzeitliste.isEmpty()) {
			em.close();
			return ErrorHandler.MAHLZEITSCHONVORHANDEN;
		}
		Mahlzeit mahlzeit = new Mahlzeit(name, art, vegetarisch);
		em.persist(mahlzeit);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.MAHLZEITANLAGE;
	}
	/**
	 * gibt alle Mahlzeiten aus. <br>
	 * Beispiel: "1. Mahlzeit: Pizza Margarita (Teigwaren, vegetarisch: ja)" <br>
	 * Der Default Wert mit der mahlzeitid=1 wird nicht ausgegeben.<br>
	 * Der erste angezeigt Eintrag ist der zweite in der Datenbank. (Es wird auch mit 1. Mahlzeit begonnen)
	 * @return gibt eine List<String> aus. Beispiel siehe Beschreibung der Methode.
	 */
	public List<String> getAllMahlzeiten() {
		List<String> mahlzeitliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Mahlzeit.findAll");
		List<Mahlzeit> mahlzeitlisteDB = new ArrayList<Mahlzeit>();
		for (Object o : query.getResultList()) {
			mahlzeitlisteDB.add((Mahlzeit) o);
		}
		if (!mahlzeitlisteDB.isEmpty()) {
			for (Mahlzeit mahlzeit : mahlzeitlisteDB) {
				if (mahlzeit.getMahlzeitid() != 1) {
					String vegetarisch = "vegetarisch: ja";
					if (mahlzeit.getVegetarisch() == 0) {
						vegetarisch = "vegetarisch: nein";
					}
					int id = mahlzeit.getMahlzeitid() - 1;
					mahlzeitliste.add(id + ". Mahlzeit: " + mahlzeit.getName() + " (" + mahlzeit.getArt() + ", "
							+ vegetarisch + ")");
				}

			}
		}
		em.close();
		return mahlzeitliste;
	}
	/**
	 * Methode, die eine Mahlzeit einem Flug zuordnet.
	 * @param mahlzeitString String im Format der Rückgabe der Methode MahlzeitHandler.getAllMahlzeiten()
	 * @param flugString tring im Format der Rückgabe der Methode FlugHandler.getAllFluege()
	 * @return gibt eine Erfolgsmeldung (SuccessHandler) aus.
	 */
	public String assignMahlzeitToFlug(String mahlzeitString, String flugString) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		String[] mahlzeitArrayString = mahlzeitString.split("\\.");
		int mahlzeitid = Integer.parseInt(mahlzeitArrayString[0]);
		mahlzeitid+=1;
		
		String[] flugArrayString = flugString.split("\\:");
		String flugid = flugArrayString[0];
		
		Query queryFlug = em.createNamedQuery("Flug.findbyID").setParameter("id", flugid);
		List<Flug> flugliste = new ArrayList<Flug>();
		for (Object o : queryFlug.getResultList()) {
			flugliste.add((Flug) o);
		}
		Flug flug = new Flug();
		flug = flugliste.get(0);
		flug.setMahlzeitid(mahlzeitid);
		em.merge(flug);
		em.getTransaction().commit();
		em.close();
		return SuccessHandler.MAHLZEITZUORDNUNG;
	}

}
