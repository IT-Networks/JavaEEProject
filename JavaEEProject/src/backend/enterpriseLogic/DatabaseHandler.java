package backend.enterpriseLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * 
 * Superklasse für alle Methoden, die eine Datenbankverbindung benötigen.
 *
 */
public class DatabaseHandler {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	
    public DatabaseHandler() {
		emf = Persistence.createEntityManagerFactory("JavaEEProject");
    }

}
