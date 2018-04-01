package backend.enterpriseLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Buchung;
import backend.entities.Passagier;

/**
 * Session Bean implementation class BuchungHandler
 */
@Stateless
@LocalBean
public class BuchungHandler extends DatabaseHandler {

	/**
	 * @see DatabaseHandler#DatabaseHandler()
	 */
	public BuchungHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String createBuchung(String passagierString, String flugString) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		if (passagierString.isEmpty() || flugString.isEmpty()) {
			em.close();
			return ErrorHandler.DATENUNVOLLSTAENDIG;
		}
		String[] passagierArrayString = passagierString.split("\\.");
		int passagierid = Integer.parseInt(passagierArrayString[0]);

		String[] flugArrayString = flugString.split("\\:");
		String flugid = flugArrayString[0];

		Buchung buchung = new Buchung();
		buchung.setBuchungsdatum(new Date());
		buchung.setFlugid(flugid);
		buchung.setPassagierid(passagierid);

		em.persist(buchung);
		em.getTransaction().commit();

		em.close();
		return SuccessHandler.BUCHUNGANLAGE;
	}

	public List<String> getBuchungenbyFlug(String flugString) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<String> buchungsliste = new ArrayList<String>();

		String[] flugArrayString = flugString.split("\\:");
		String flugid = flugArrayString[0];

		Query query = em.createNamedQuery("Buchung.findbyFlug").setParameter("flugid", flugid);
		List<Buchung> buchungslisteDB = new ArrayList<Buchung>();
		for (Object o : query.getResultList()) {
			buchungslisteDB.add((Buchung) o);
		}
		if (!buchungslisteDB.isEmpty()) {
			for (Buchung buchung : buchungslisteDB) {
				int passagierid = buchung.getPassagierid();
				Query queryPassagier = em.createNamedQuery("Passagier.findbyID").setParameter("id", passagierid);
				Passagier passagier = (Passagier) queryPassagier.getResultList().get(0);
				buchungsliste.add(buchung.getBuchungid() + ". Buchung am " + buchung.getBuchungsdatum().toString()
						+ ", Flug: " + buchung.getFlugid() + ", Passagier: " + passagier.getVorname() + " " + passagier.getNachname());
			}

		}
		em.close();

		return buchungsliste;
	}
	public List<String> getBuchungenbyPassagier(String passagierString) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<String> buchungsliste = new ArrayList<String>();

		String[] passagierArrayString = passagierString.split("\\.");
		int passagierid = Integer.parseInt(passagierArrayString[0]);

		Query query = em.createNamedQuery("Buchung.findbyPassagier").setParameter("passagierid", passagierid);
		List<Buchung> buchungslisteDB = new ArrayList<Buchung>();
		for (Object o : query.getResultList()) {
			buchungslisteDB.add((Buchung) o);
		}
		if (!buchungslisteDB.isEmpty()) {
			for (Buchung buchung : buchungslisteDB) {
				Query queryPassagier = em.createNamedQuery("Passagier.findbyID").setParameter("id", passagierid);
				Passagier passagier = (Passagier) queryPassagier.getResultList().get(0);
				buchungsliste.add(buchung.getBuchungid() + ". Buchung am " + buchung.getBuchungsdatum().toString()
						+ ", Flug: " + buchung.getFlugid() + ", Passagier: " + passagier.getVorname() + " " + passagier.getNachname());
			}

		}
		em.close();

		return buchungsliste;
	}

}
