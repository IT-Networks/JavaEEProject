package frontend_beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.BuchungHandler;
import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.enterpriseLogic.SuccessHandler;

@ManagedBean
@RequestScoped
public class BuchungBean {

	@NotNull
	private String selectedPassenger;
	@NotNull
	private String selectedForPassengerForBuchung;
	@NotNull
	private String selectedFlight;
	@NotNull
	private List<String> passangers;
	@NotNull
	private List<String> flights;
	
	private String detailsBuchung;
	
	private String test;
	
	
	public String getSelectedForPassengerForBuchung() {
		return selectedForPassengerForBuchung;
	}

	public void setSelectedForPassengerForBuchung(String selectedForPassengerForBuchung) {
		
		BuchungHandler bh = new BuchungHandler();
		List<String> buchungen = bh.getBuchungenbyPassagier(selectedForPassengerForBuchung);
		this.detailsBuchung = "";
		for(int i = 0; i< buchungen.size(); i++) {
			this.detailsBuchung += buchungen.get(i) + "\n";
			this.test = this.detailsBuchung;
		}
		this.selectedForPassengerForBuchung = selectedForPassengerForBuchung;
	}

	public String getSelectedPassenger() {
		return selectedPassenger;
	}

	public void setSelectedPassenger(String selectedPassenger) {
		this.selectedPassenger = selectedPassenger;
	}

	public String getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(String selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public List<String> getPassangers() {
		PassagierHandler ph = new PassagierHandler();
		passangers = ph.getAllPassagiere();
		return passangers;
	}

	public void setPassangers(List<String> passangers) {
		this.passangers = passangers;
	}

	public List<String> getFlights() {
		FlugHandler fh = new FlugHandler();
		flights = fh.getAllFluege();
		return flights;
	}

	public void setFlights(List<String> flights) {
		this.flights = flights;
	}

	public void assignPassengerToFlight() {
		FacesMessage msg;
		BuchungHandler bh = new BuchungHandler();
		String result = bh.createBuchung(selectedPassenger, selectedFlight);

		if (result.equals(SuccessHandler.BUCHUNGANLAGE)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result, "");
			FacesContext.getCurrentInstance().addMessage("buchungForm:assignPassenger", msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result, "");
			FacesContext.getCurrentInstance().addMessage("buchungForm:assignPassenger", msg);
		}

	}

	public String getDetailsBuchung() {
		return this.test;
	}

	public void setDetailsBuchung(String detailsBuchung) {
		this.detailsBuchung = detailsBuchung;
	}

}