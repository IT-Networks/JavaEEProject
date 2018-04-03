package frontend.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.BuchungHandler;

@ManagedBean
@RequestScoped
public class AuslastungBean {

	@NotNull
	private String result = "";
	@NotNull
	private String selectedFlight;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(String selectedFlight) {

		BuchungHandler bh = new BuchungHandler();
		result = bh.calculateCapacity(selectedFlight);

		this.selectedFlight = selectedFlight;
	}

}