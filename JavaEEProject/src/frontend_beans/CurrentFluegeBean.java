package frontend_beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.models.FlugModel;

@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	@NotNull
	private List<FlugModel> allFluege;

	@NotNull
	private List<String> allPassagiere;

	@NotNull
	private FlugModel currentSelectedFlugModel;
	
	@NotNull
	private String buchungen;
	
	public String getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(String buchungen) {
		this.buchungen = buchungen;
	}

	public FlugModel getCurrentSelectedFlugModel() {
		return currentSelectedFlugModel;
	}

	public void setCurrentSelectedFlugModel(FlugModel currentSelectedFlugModel) {
		this.currentSelectedFlugModel = currentSelectedFlugModel;
	}

	public List<String> getAllPassagiere() {
		PassagierHandler ph = new PassagierHandler();
		return ph.getAllPassagiere();

	}

	public void setAllPassagiere(List<String> allPassagiere) {
		this.allPassagiere = allPassagiere;
	}

	public List<FlugModel> getAllFluege() {
		if(allFluege == null) {
			FlugHandler fH = new FlugHandler();
			this.allFluege = fH.getAllFlugModels();
			return allFluege;
		}
		else
			return allFluege;
	}

	public void setAllFluege(List<FlugModel> allFluege) {
		this.allFluege = allFluege;
	}

	public void onSelect(FlugModel flug) {
		if(flug != null) {
		  System.out.println("OnSelect:" + flug.getName());
		  buchungen = flug.getBuchungen().toString();
		}
		  
	}

}