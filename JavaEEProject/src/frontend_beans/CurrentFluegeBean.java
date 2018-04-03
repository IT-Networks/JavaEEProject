package frontend_beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.BuchungHandler;
import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.ModelHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.enterpriseLogic.SuccessHandler;
import backend.models.DepartureSchedulesModel;
import backend.models.FlugModel;

@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	@NotNull
	private List<FlugModel> allFluege;

	@NotNull
	private List<DepartureSchedulesModel> allFluegeWithStatus = new ArrayList<DepartureSchedulesModel>();

	@NotNull
	private List<String> allPassengers;

	@NotNull
	private DepartureSchedulesModel currentSelectedDepartureModel;

	@NotNull
	private String buchungen;

	@NotNull
	private String time;
	
	private String lastTime = "1";

	@NotNull
	private String detailsFlug;

	@NotNull
	private String selectedPassenger;

	@NotNull
	private boolean canBook;
	
	
	public boolean isCanBook() {
		if(currentSelectedDepartureModel != null) {
			String state = currentSelectedDepartureModel.getStatus();
			if(state.equals(FlugHandler.Status.SCHEDULED))
				canBook = true;
			else
				canBook = false;
		}
		else
			canBook = false;
		return canBook;
	}

	public void setCanBook(boolean canBook) {
		this.canBook = canBook;
	}

	public String getSelectedPassenger() {
		return selectedPassenger;
	}

	public void setSelectedPassenger(String selectedPassenger) {
		this.selectedPassenger = selectedPassenger;
	}

	public DepartureSchedulesModel getCurrentSelectedDepartureModel() {
		return currentSelectedDepartureModel;
	}

	public void setCurrentSelectedDepartureModel(DepartureSchedulesModel currentSelectedDepartureModel) {
		this.currentSelectedDepartureModel = currentSelectedDepartureModel;
	}

	public String getSelectedPassagier() {
		return selectedPassenger;
	}

	public void setSelectedPassagier(String selectedPassenger) {
		this.selectedPassenger = selectedPassenger;
	}

	public String getDetailsFlug() {
		return detailsFlug;
	}

	public void setDetailsFlug(String detailsFlug) {
		this.detailsFlug = detailsFlug;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<DepartureSchedulesModel> getAllFluegeWithStatus() {
			ModelHandler mH = new ModelHandler();
			try {
				if(time != null && time != "" && time != lastTime) {
					this.allFluegeWithStatus = mH.getDepartureSchedulesModels(time);
					lastTime = time;
				}
			} catch (ParseException e) {
				// Message for parse exception
			}
			return allFluegeWithStatus;

	}

	public void setAllFluegeWithStatus(List<DepartureSchedulesModel> allFluegeWithStatus) {
		this.allFluegeWithStatus = allFluegeWithStatus;
	}

	public String getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(String buchungen) {
		this.buchungen = buchungen;
	}

	public List<String> getAllPassengers() {
		PassagierHandler ph = new PassagierHandler();
		return ph.getAllPassagiere();

	}

	public void setAllPassengers(List<String> allPassengers) {
		this.allPassengers = allPassengers;
	}

	public List<FlugModel> getAllFluege() {
		if (allFluege == null) {
			ModelHandler mH = new ModelHandler();
			this.allFluege = mH.getAllFlugModels();
			return allFluege;
		} else
			return allFluege;
	}

	public void setAllFluege(List<FlugModel> allFluege) {
		this.allFluege = allFluege;
	}

	public void onSelect(FlugModel flug) {
		if (flug != null) {
			System.out.println("OnSelect:" + flug.getName());
			buchungen = "";
			for (int i = 0; i < flug.getBuchungen().size(); i++) {
				buchungen += flug.getBuchungen().get(i) + "&lt;br/&gt;";
			}

		}

	}

	public void onSelectDeparture(DepartureSchedulesModel depatures) {
		if (depatures != null) {
			System.out.println("OnSelect:" + depatures.getFlugid());
			detailsFlug += "Flug Nummer: " + depatures.getFlugid() + " Preis: " + depatures.getPreis() + " Ankunft: "
					+ depatures.getAnkunft();
			currentSelectedDepartureModel = depatures;

		}

	}

	public void assignPassengerToFlight() {
		FacesMessage msg;
		BuchungHandler bh = new BuchungHandler();

		if (currentSelectedDepartureModel != null) {
			String result = bh.createBuchung(selectedPassenger, currentSelectedDepartureModel.getFlugid() + ":");

			if (result.equals(SuccessHandler.BUCHUNGANLAGE)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result, "");
				FacesContext.getCurrentInstance().addMessage("currentFlightsForm:assignPassenger", msg);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result, "");
				FacesContext.getCurrentInstance().addMessage("currentFlightsForm:assignPassenger", msg);
			}
		}
	}

}
