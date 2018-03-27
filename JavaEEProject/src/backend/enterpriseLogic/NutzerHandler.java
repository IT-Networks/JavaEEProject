package backend.enterpriseLogic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import backend.entities.Nutzer;

/**
 * Session Bean implementation class NutzerHandler
 */
@Stateless
@LocalBean
public class NutzerHandler {

	/**
	 * Default constructor.
	 */
	public NutzerHandler() {
	}

	public String createNutzer(String vorname, String nachname, String anmeldename, String passwort, String nutzertyp)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEEProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findAll");
		List<Nutzer> nutzerliste = new ArrayList<Nutzer>();
		for (Object o : query.getResultList()) {
			nutzerliste.add((Nutzer) o);
		}
		for (Nutzer nutzerschleife : nutzerliste) {
			String foranmeldename = nutzerschleife.getAnmeldename();
			if (foranmeldename.equals(anmeldename)) {
				return ErrorHandler.NUTZERSCHONVORHANDEN;
			}
		}
		Nutzer nutzer = new Nutzer();
		if (vorname != null && nachname != null && anmeldename != null && passwort != null && nutzertyp != null
				&& !vorname.isEmpty() && !nachname.isEmpty() && !anmeldename.isEmpty() && !passwort.isEmpty()
				&& !nutzertyp.isEmpty() && !vorname.equals("") && !nachname.equals("") && !anmeldename.equals("")
				&& !passwort.equals("") && !nutzertyp.equals("")) {
			nutzer.setVorname(vorname);
			nutzer.setNachname(nachname);
			nutzer.setAnmeldename(anmeldename);
			nutzer.setPasswort(encryptPasswort(passwort));
			if (nutzertyp.equals("Mitarbeiter") || nutzertyp.equals("Manager")) {
				nutzer.setNutzertyp(nutzertyp);
			}
			em.persist(nutzer);
			em.getTransaction().commit();

			em.close();
		} else {
			return ErrorHandler.NUTZERDATENUNVOLLSTAENDIG;
		}
		return "Erfolgreiche Registrierung!";
	}
	
	public String checkPasswort(String anmeldename, String passwort) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEEProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findByName").setParameter("name", anmeldename);
		List<Nutzer> nutzer = new ArrayList<Nutzer>();
		for (Object o : query.getResultList()) {
			nutzer.add((Nutzer) o);
		}
		if(nutzer.isEmpty()) {
			return ErrorHandler.NUTZERNICHTGEFUNDEN;
		}
		
		if(!passwort.equals(decryptPasswort(nutzer.get(0).getPasswort()))) {
			return ErrorHandler.NUTZERPASSWORTFALSCH;
		}
		
		return "Login erfolgreich.";
	}

	// Code von: https://blog.axxg.de/java-aes-verschluesselung-mit-beispiel/
	private SecretKeySpec generateKey() throws UnsupportedEncodingException, NoSuchAlgorithmException {

		// Das Passwort bzw der Schluesseltext
		String keyStr = "!~|1234$%&HES@";
		// byte-Array erzeugen
		byte[] key = (keyStr).getBytes("UTF-8");
		// aus dem Array einen Hash-Wert erzeugen mit MD5 oder SHA
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		// nur die ersten 128 bit nutzen
		key = Arrays.copyOf(key, 16);
		// der fertige Schluessel
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		return secretKeySpec;
	}

	private String encryptPasswort(String passwort) throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = generateKey();
		// der zu verschl. Text

		// Verschluesseln
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] encrypted = cipher.doFinal(passwort.getBytes());

		String encryptedPasswort = Base64.getEncoder().encodeToString(encrypted);

		return encryptedPasswort;
	}

	private String decryptPasswort(String encryptedPasswort)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = generateKey();
		// BASE64 String zu Byte-Array konvertieren
		byte[] crypted = Base64.getDecoder().decode(encryptedPasswort);

		// Entschluesseln
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] cipherData = cipher.doFinal(crypted);
		String decryptedPasswort = new String(cipherData);

		return decryptedPasswort;

	}

}
