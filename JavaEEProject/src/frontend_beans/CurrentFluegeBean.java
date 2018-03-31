package frontend_beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.Flug;

@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	
@NotNull
private List<Flug> allFluege;



public List<Flug> getAllFluege() {
	allFluege = new ArrayList<Flug>();
	List<String> s = new ArrayList<String>();
	s.add("Markus");
	s.add("Halil");
	Flug f = new Flug("Start Platz", "Ziel", "Pommes", "Flug 63",s);
	Flug g = new Flug("Start Platz", "Ziel", "Pommes", "Flug 64",s);
	allFluege.add(f);
	allFluege.add(g);
	return allFluege;
}

public void setAllFluege(List<Flug> allFluege) {
	this.allFluege = allFluege;
}



}