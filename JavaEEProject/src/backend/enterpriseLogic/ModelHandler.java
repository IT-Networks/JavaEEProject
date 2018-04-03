package backend.enterpriseLogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import backend.entities.Flug;
import backend.entities.Flughafen;
import backend.entities.Mahlzeit;
import backend.entities.Relation;
import backend.models.DepartureSchedulesModel;
import backend.models.FlugModel;

/**
 * Session Bean implementation class ModelHandler. <br>
 * In dieser Klasse befinden sich Methoden, rund um die DataModels im Package backend.models, <br>
 * die für die Anzeige im Frontend verwendet werden.
 */
@Stateless
@LocalBean
public class ModelHandler extends DatabaseHandler {

	/**
	 * @see DatabaseHandler#DatabaseHandler()
	 */
	public ModelHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gibt alle Flüge im Fomat FlugModel aus.
	 * @return Gibt eine Liste von Typ FlugModel zurück
	 */
	public List<FlugModel> getAllFlugModels() {
		List<FlugModel> flugModelListe = new ArrayList<FlugModel>();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query queryFlug = em.createNamedQuery("Flug.findAll");
		List<Flug> fluglisteDB = new ArrayList<Flug>();
		for (Object o : queryFlug.getResultList()) {
			fluglisteDB.add((Flug) o);
		}
		for (Flug flug : fluglisteDB) {
			Query queryRelation = em.createNamedQuery("Relation.findbyID").setParameter("id",
					flug.getRelation().getRelationid());
			Relation relation = (Relation) queryRelation.getResultList().get(0);
			Query queryFlughafen = em.createNamedQuery("Flughafen.findStartUndZiel")
					.setParameter("start", relation.getStartort()).setParameter("ziel", relation.getZielort());
			Flughafen startFlughafen = (Flughafen) queryFlughafen.getResultList().get(0);
			Flughafen zielFlughafen = (Flughafen) queryFlughafen.getResultList().get(1);
			Query queryMahlzeit = em.createNamedQuery("Mahlzeit.findbyID").setParameter("id", flug.getMahlzeitid());
			Mahlzeit mahlzeit = (Mahlzeit) queryMahlzeit.getResultList().get(0);
			BuchungHandler bH = new BuchungHandler();
			flugModelListe.add(new FlugModel(startFlughafen.getName(), zielFlughafen.getName(), mahlzeit.getName(),
					flug.getFlugid(), bH.getBuchungenbyFlug(flug.getFlugid() + ":")));
		}
		return flugModelListe;
	}
	/**
	 * 
	 * Gibt alle Flüge im Fomat DepartureSchedulesModel aus.
	 * 
	 * @param time muss im Format EEE MMM dd HH:mm:ss zzz yyyy  (z.B. Tue Apr 17 17:46:00 CEST 2018) sein
	 * @return Gibt eine Liste von Typ DepartureSchedulesModel zurück
	 * @throws ParseException kann bei einem falsch formatieren time-String auftreten.
	 */
	public List<DepartureSchedulesModel> getDepartureSchedulesModels(String time) throws ParseException {
		List<DepartureSchedulesModel> flugModelListe = new ArrayList<DepartureSchedulesModel>();
		DateFormat rightFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		em = emf.createEntityManager();
		em.getTransaction().begin();
		FlugHandler fH = new FlugHandler();
		Query queryFlug = em.createNamedQuery("Flug.findAll");
		List<Flug> fluglisteDB = new ArrayList<Flug>();
		for (Object o : queryFlug.getResultList()) {
			fluglisteDB.add((Flug) o);
		}
		for (Flug flug : fluglisteDB) {
			Query queryRelation = em.createNamedQuery("Relation.findbyID").setParameter("id",
					flug.getRelation().getRelationid());
			Relation relation = (Relation) queryRelation.getResultList().get(0);
			Query queryFlughafen = em.createNamedQuery("Flughafen.findStartUndZiel")
					.setParameter("start", relation.getStartort()).setParameter("ziel", relation.getZielort());
			Flughafen startFlughafen = (Flughafen) queryFlughafen.getResultList().get(0);
			Flughafen zielFlughafen = (Flughafen) queryFlughafen.getResultList().get(1);

			flugModelListe.add(
					new DepartureSchedulesModel(flug.getFlugid(), startFlughafen.getName(), zielFlughafen.getName(),
							fH.getFlugStatus(time, flug.getFlugid() + ":"), rightFormat.format(flug.getAbflug()),
							rightFormat.format(flug.getAnkunft()), flug.getPreis().toString()));
		}
		return flugModelListe;
	}

}
