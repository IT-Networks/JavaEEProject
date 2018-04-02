package frontend_beans;

import java.text.ParseException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.FlugzeugHandler;
import backend.enterpriseLogic.RelationHandler;
import backend.enterpriseLogic.SuccessHandler;

@ManagedBean
@RequestScoped
public class FluegeRelationBean {

	@NotNull
	@Max(23)
	private String time;
	
	@NotNull
	@Max(59)
	private String minutes;

	@NotNull
	private String distance;

	@NotNull
	private String startLocation;

	@NotNull
	private String goalLocation;

	@NotNull
	private String startTime;

	@NotNull
	private String fluglinieRelation;
	
	@NotNull
	@Max(999)
	private String price;
	
	@NotNull
	private String airplain;
	

	@NotNull
	private String flight;
	
	@NotNull
	private List<String> flights;
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTime() {

		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private List<String> airports;

	private List<String> relations;

	private List<String> airplains;

	public List<String> getRelations() {
		FlugHandler fh = new FlugHandler();
		relations = fh.getAllRelationen();
		return relations;
	}

	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	public List<String> getAirports() {
		RelationHandler rh = new RelationHandler();
		airports = rh.getAllFlughafennamen();
		return airports;
	}

	public void setAirports(List<String> airports) {
		this.airports = airports;
	}

	public void setRelation() {
		FacesMessage msg;
		RelationHandler rh = new RelationHandler();

		if (time.equals("") || distance.equals("")) {
			if (time.equals("")) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Flugzeit muss angegeben werden");
				FacesContext.getCurrentInstance().addMessage("relationForm:flugzeit", msg);
			}
			if (distance.equals("")) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Distanz muss angegeben werden");
				FacesContext.getCurrentInstance().addMessage("relationForm:distanz", msg);
			}
		} else {
			distance = distance.replaceAll("_", "");
			String result = rh.createRelation(startLocation, goalLocation, time + ":" + minutes + ":00", Integer.parseInt(distance));

			if (result.equals(SuccessHandler.RELATIONANLAGE)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", result);
				FacesContext.getCurrentInstance().addMessage("relationForm:saveRelation", msg);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
				FacesContext.getCurrentInstance().addMessage("relationForm:saveRelation", msg);
			}
		}
	}

	public void createFluglinie() {

		FlugHandler fh = new FlugHandler();
		FacesMessage msg;
		String result = "Error while parsing date";
		try {
			double d = Double.parseDouble(price);
			result = fh.createFlug(startTime, fluglinieRelation, d);
			
			if(result.equals(SuccessHandler.FLUGANLAGE)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", result);
				FacesContext.getCurrentInstance().addMessage("flightForm:saveFluglinie", msg);
			}
			else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
				FacesContext.getCurrentInstance().addMessage("flightForm:saveFluglinie", msg);
			}

		} catch (ParseException e) {
		
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
			FacesContext.getCurrentInstance().addMessage("flightForm:saveFluglinie", msg);
		}

	}
	
	public void assignAirplainToRelation() {
		FlugzeugHandler fh = new FlugzeugHandler();
		String result = fh.assignFlugzeugToFlug(airplain, flight);
		FacesMessage msg;
		
		if(result.equals(SuccessHandler.FLUGZEUGZUORDNUNG)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", result);
			FacesContext.getCurrentInstance().addMessage("plainForm:saveFlugToAirplain", msg);
		}
		else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
			FacesContext.getCurrentInstance().addMessage("plainForm:saveFlugToAirplain", msg);
		}
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getGoalLocation() {
		return goalLocation;
	}

	public void setGoalLocation(String goalLocation) {
		this.goalLocation = goalLocation;
	}

	public List<String> getAirplains() {
		FlugzeugHandler fh = new FlugzeugHandler();
		airplains = fh.getAllFlugzeuge();
		return airplains;
	}

	public void setAirplains(List<String> airplains) {
		this.airplains = airplains;
	}
	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	
	public String getFluglinieRelation() {
		return fluglinieRelation;
	}

	public void setFluglinieRelation(String fluglinieRelation) {
		this.fluglinieRelation = fluglinieRelation;
	}

	public String getAirplain() {
		return airplain;
	}

	public void setAirplain(String airplain) {
		this.airplain = airplain;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public List<String> getFlights() {
		FlugHandler fh = new FlugHandler();
		flights = fh.getAllFluege();
		return flights;
	}

	public void setFlights(List<String> flights) {
		this.flights = flights;
	}

}