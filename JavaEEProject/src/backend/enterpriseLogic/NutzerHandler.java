package backend.enterpriseLogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
    
	public String createNutzer(String vorname, String nachname, String anmeldename, String passwort, String nutzertyp) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaEEProject");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Nutzer.findAll");
		List<Nutzer> nutzerliste = new ArrayList<Nutzer>();
		for(Object o : query.getResultList()) {
			nutzerliste.add((Nutzer) o);
		}
		for (Nutzer nutzerschleife : nutzerliste) {
			String foranmeldename = nutzerschleife.getAnmeldename();
			if (foranmeldename.equals(anmeldename)) {
				return ErrorHandler.NUTZERSCHONVORHANDEN;
			}
		}
		Nutzer nutzer = new Nutzer();
		if(!vorname.isEmpty()&&!nachname.isEmpty()&&!anmeldename.isEmpty()&&!passwort.isEmpty()&&!nutzertyp.isEmpty()) {
			nutzer.setVorname(vorname);
			nutzer.setNachname(nachname);
			nutzer.setAnmeldename(anmeldename);
			nutzer.setPasswort(hashPasswort(passwort));
			if(nutzertyp.equals("Mitarbeiter") || nutzertyp.equals("Manager")) {
				nutzer.setNutzertyp(nutzertyp);
			}
			em.persist(nutzer);
			em.getTransaction().commit();

			em.close();
		}
		else {
			return ErrorHandler.NUTZERDATENUNVOLLSTAENDIG;
		}
    	return "";
    }
    
    private String hashPasswort(String passwort) {
    	String hashedPasswort = "";
    	try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwort.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashedPasswort = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    	return hashedPasswort;
    }

}
