package frontend_beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.models.FlugModel;


@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	
	@NotNull
	private List<FlugModel> allFluege;

	@NotNull
	private List<String> allPassagiere;


	

public List<String> getAllPassagiere() {
	allPassagiere =  new ArrayList<String>();
	allPassagiere.add("Halil");
	allPassagiere.add("Markus");
	
		return allPassagiere;
	}

	public void setAllPassagiere(List<String> allPassagiere) {
		this.allPassagiere = allPassagiere;
	}

public List<FlugModel> getAllFluege() {
	allFluege = new ArrayList<FlugModel>();
	List<String> s = new ArrayList<String>();
	s.add("Markus");
	s.add("Halil");
	FlugModel f = new FlugModel("Start Platz", "Ziel", "Pommes", "Flug 63",s);
	FlugModel g = new FlugModel("Start Platz", "Ziel", "Pommes", "Flug 64",s);
	allFluege.add(f);
	allFluege.add(g);
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