package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Passagier;

/**
 * Session Bean implementation class PassagierHandler
 */
@Stateless
@LocalBean
public class PassagierHandler extends DatabaseHandler {

	/**
	 * @see DatabaseHandler#DatabaseHandler()
	 */
	public PassagierHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String createPassagier(String vorname, String nachname, String anschrift, String geburtsdatum,
			String nationalitaet) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if (vorname == null || nachname == null || anschrift == null || geburtsdatum == null || nationalitaet == null
				|| vorname.isEmpty() || nachname.isEmpty() || anschrift.isEmpty() || geburtsdatum.isEmpty()
				|| nationalitaet.isEmpty()) {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		Passagier passagier = new Passagier(vorname, nachname, anschrift, geburtsdatum, nationalitaet);
		em.persist(passagier);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.PASSAGIERANLAGE;
	}

	public List<String> getAllPassagiere() {
		List<String> passagierliste = new ArrayList<String>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Passagier.findAll");
		List<Passagier> passagierlisteDB = new ArrayList<Passagier>();
		for (Object o : query.getResultList()) {
			passagierlisteDB.add((Passagier) o);
		}
		if (!passagierlisteDB.isEmpty()) {
			for (Passagier passagier : passagierlisteDB) {
				passagierliste.add(passagier.getPassagierid() + ". Passagier: " + passagier.getVorname() + " "
						+ passagier.getNachname() + " (Anschrift: " + passagier.getAnschrift() + ", Geburtsdatum: "
						+ passagier.getGeburtsdatum() + ", Nationalitaet: " + passagier.getNationalitaet() + ")");
			}

		}
		em.close();
		return passagierliste;
	}

}
