package backend.enterpriseLogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Passagier;

/**
 * Session Bean implementation class PassagierHandler. <br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Passagier.
 */
@Stateless
@LocalBean
public class PassagierHandler extends DatabaseHandler {

	/**
	 * @see DatabaseHandler#DatabaseHandler()
	 */
	public PassagierHandler() {
		super();
		
	}
	/**
	 * Methode, die einen Passagier anlegt.
	 * @param vorname Vorname des Passagiers
	 * @param nachname Nachname des Passagiers
	 * @param anschrift Anschrift des Passagiers
	 * @param geburtsdatum Geburtsdatum des Passagiers
	 * @param nationalitaet Nationalitaet des Passagiers
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 * @throws ParseException
	 */
	public String createPassagier(String vorname, String nachname, String anschrift, String geburtsdatum,
			String nationalitaet) throws ParseException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if (vorname == null || nachname == null || anschrift == null || geburtsdatum == null || nationalitaet == null
				|| vorname.isEmpty() || nachname.isEmpty() || anschrift.isEmpty() || geburtsdatum.isEmpty()
				|| nationalitaet.isEmpty()) {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date geburtsdate = dateFormat.parse(geburtsdatum);
		DateFormat rightFormat = new SimpleDateFormat("dd.MM.YYYY");
		String bday = rightFormat.format(geburtsdate);
		Passagier passagier = new Passagier(vorname, nachname, anschrift, bday, nationalitaet);
		em.persist(passagier);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.PASSAGIERANLAGE;
	}
	/**
	 * gibt alle Passagier aus:<br>
	 * Beispiel: "1. Passagier: Halil Özdogan (Anschrift: Am Stockhof 2, 31785 Hameln, Geburtsdatum: 08.09.1995, Nationalitaet: deutsch)"
	 * @return gibt eine List<String> aus. Beispiel siehe Beschreibung der Methode.
	 */
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
