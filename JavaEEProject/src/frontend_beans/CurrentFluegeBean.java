package frontend_beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.Flug;
import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.FlugzeugHandler;
import backend.enterpriseLogic.RelationHandler;

@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	
@NotNull
private List<Flug> allFluege;



public List<Flug> getAllFluege() {
	
	return allFluege;
}

public void setAllFluege(List<Flug> allFluege) {
	this.allFluege = allFluege;
}



}