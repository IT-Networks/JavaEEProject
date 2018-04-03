package backend.enterpriseLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * 
 * Superklasse f�r alle Methoden, die eine Datenbankverbindung ben�tigen.
 *
 */
public class DatabaseHandler {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	
    public DatabaseHandler() {
		emf = Persistence.createEntityManagerFactory("JavaEEProject");
    }

}
