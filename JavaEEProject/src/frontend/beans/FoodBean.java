package frontend.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.MahlzeitHandler;
import backend.enterpriseLogic.SuccessHandler;

@ManagedBean
@RequestScoped
public class FoodBean {

	@NotNull
	private String name;
	@NotNull
	private String selectedFood;
	@NotNull
	private String kind;
	@NotNull
	private boolean vegetarian;
	@NotNull
	private List<String> allFood;
	@NotNull
	private List<String> flights;
	@NotNull
	private String flight;

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

	public String getSelectedFood() {
		return selectedFood;
	}

	public void setSelectedFood(String selectedFood) {
		this.selectedFood = selectedFood;
	}

	public List<String> getAllFood() {
		MahlzeitHandler mh = new MahlzeitHandler();
		allFood = mh.getAllMahlzeiten();
		return allFood;
	}

	public void setAllFood(List<String> allFood) {
		this.allFood = allFood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public void createMahlzeit() {
		FacesMessage msg;
		MahlzeitHandler mh = new MahlzeitHandler();
		String result = mh.createMahlzeit(name, kind, vegetarian);

		if (result.equals(SuccessHandler.MAHLZEITANLAGE)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result, "");
			FacesContext.getCurrentInstance().addMessage("foodForm:saveFood", msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result, "");
			FacesContext.getCurrentInstance().addMessage("foodForm:saveFood", msg);
		}
	}

	public void assignMahlzeit() {
		FacesMessage msg;
		MahlzeitHandler mh = new MahlzeitHandler();
		String result = mh.assignMahlzeitToFlug(selectedFood, flight);

		if (result.equals(SuccessHandler.MAHLZEITZUORDNUNG)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result, "");
			FacesContext.getCurrentInstance().addMessage("assignfoodForm:assignFood", msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result, "");
			FacesContext.getCurrentInstance().addMessage("assignfoodForm:assignFood", msg);
		}
	}

}