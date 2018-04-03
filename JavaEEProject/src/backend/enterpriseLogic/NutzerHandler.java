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
import javax.persistence.Query;

import backend.entities.Nutzer;

/**
 * Session Bean implementation class NutzerHandler <br> In dieser Klasse befinden
 * sich Methoden rund um die Entität Nutzer.
 */
@Stateless
@LocalBean
public class NutzerHandler extends DatabaseHandler {

	public NutzerHandler() {
		super();
	}
	/**
	 *  Methode, die einen Nutzer in der Datenbank anlegt und somit registriert. 
	 * @param vorname Vorname des Uers
	 * @param nachname Nachname des Users
	 * @param anmeldename eindeutiger Anmeldename
	 * @param passwort Das Passwort wird an die Methode als Klartext übertragen.
	 * @param nutzertyp entweder Manager oder Mitarbeiter
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 * @throws InvalidKeyException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws UnsupportedEncodingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws NoSuchAlgorithmException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws NoSuchPaddingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws IllegalBlockSizeException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws BadPaddingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 */
	public String createNutzer(String vorname, String nachname, String anmeldename, String passwort, String nutzertyp)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findByName").setParameter("name", anmeldename);
		List<Nutzer> nutzerliste = new ArrayList<Nutzer>();
		for (Object o : query.getResultList()) {
			nutzerliste.add((Nutzer) o);
		}
		if (!nutzerliste.isEmpty()) {
			em.close();
			return ErrorHandler.NUTZERSCHONVORHANDEN;
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
			} else {
				em.close();
				return ErrorHandler.NUTZERTYPNICHTVORHANDEN;
			}
			em.persist(nutzer);
			em.getTransaction().commit();
		} else {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		em.close();
		return SuccessHandler.REGISTRIERUNG;
	}
	/**
	 * Methode, die die Richtigkeit der Nutzerdaten gewährleistet. Datenbankabfrage auf Nutzername und Passwort.
	 * @param anmeldename Anmeldename des Nutzers
	 * @param passwort Das Passwort wird an die Methode als Klartext übertragen.
	 * @return gibt entweder eine Fehlermeldung (ErrorHandler) oder eine Erfolgsmeldung (SuccessHandler) aus.
	 * @throws InvalidKeyException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws UnsupportedEncodingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws NoSuchAlgorithmException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws NoSuchPaddingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws IllegalBlockSizeException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 * @throws BadPaddingException Exception bei Verschlüsselung/Entschlüsselung des Passworts.
	 */
	public String checkPasswort(String anmeldename, String passwort)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findByName").setParameter("name", anmeldename);
		List<Nutzer> nutzer = new ArrayList<Nutzer>();
		for (Object o : query.getResultList()) {
			nutzer.add((Nutzer) o);
		}
		if (nutzer.isEmpty()) {
			em.close();
			
			return ErrorHandler.NUTZERNICHTGEFUNDEN;
		}

		if (!passwort.equals(decryptPasswort(nutzer.get(0).getPasswort()))) {
			em.close();
			
			return ErrorHandler.NUTZERPASSWORTFALSCH;
		}
		em.close();
		
		return SuccessHandler.LOGIN;
	}
	/**
	 * Methode, die zu einem Anmeldenamen den Nutzertypen ausgibt. Es sollte vorher die Methode checkPasswort ausgeführt werden.
	 * @param anmeldename Nutzer, zu dem Nutzertyp ausgegebnw werden soll
	 * @return Nutzertyp des Nutzers
	 */
	public String getNutzertyp(String anmeldename) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findByName").setParameter("name", anmeldename);
		List<Nutzer> nutzerliste = new ArrayList<Nutzer>();
		for (Object o : query.getResultList()) {
			nutzerliste.add((Nutzer) o);
		}
		em.close();
		
		return nutzerliste.get(0).getNutzertyp();
	}
	/**
	 * Generierung eines Keys für die AES Verschlüsselung/Entschlüsselung
	 * @return generierter Key
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
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
	/**
	 *  Verschlüsselung des Passworts, damit dieses in der Datenbank nicht als Klartext gespeichert wird.
	 * @param passwort Passwort in Klartext
	 * @return verschlüsseltes Passwort
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
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
	/**
	 * Entschlüsselung eines Passwort
	 * @param encryptedPasswort verschlüsseltes Passwort
	 * @return entschlüsseltes Passwort
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
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
