package frontend_beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;


import backend.enterpriseLogic.RelationHandler;

@ManagedBean
@RequestScoped
public class FluegeRelationBean {

	@NotNull
	private String time = null;

	@NotNull
	private String distance;
	
	@NotNull
	private String startLocation;
	
	@NotNull
	private String goalLocation;
	
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


	public List<String> getAirports() {
		return airports;
	}

	public void setAirports(List<String> airports) {
		this.airports = airports;
	}
	public void setRelation()
	{
		RelationHandler rh = new RelationHandler();
		String result = rh.createRelation(startLocation, goalLocation, time, Integer.parseInt(distance));
		FacesMessage msg;
		if(result.equals("")) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					result);
			FacesContext.getCurrentInstance().addMessage("relationForm:container", msg);
		}
		else
		{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					result);
			FacesContext.getCurrentInstance().addMessage("relationForm:container", msg);
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



}