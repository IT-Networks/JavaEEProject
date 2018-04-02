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


	

public List<String> getAllPassagiere() {
	PassagierHandler ph = new PassagierHandler();
	return ph.getAllPassagiere();

	}

	public void setAllPassagiere(List<String> allPassagiere) {
		this.allPassagiere = allPassagiere;
	}

public List<FlugModel> getAllFluege() {
	FlugHandler fH = new FlugHandler();
	this.allFluege = fH.getAllFlugModels();
	return allFluege;
}

public void setAllFluege(List<FlugModel> allFluege) {
	this.allFluege = allFluege;
}

public void assignPassagier()
{
	FacesMessage msg;
	String result = "Erfolgreich angelegt!";
	
	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
			result);
	FacesContext.getCurrentInstance().addMessage("buchungForm:passagierDropDown", msg);
}


}