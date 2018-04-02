package frontend_beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.BuchungHandler;
import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.FlugzeugHandler;
import backend.enterpriseLogic.MahlzeitHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.enterpriseLogic.SuccessHandler;


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