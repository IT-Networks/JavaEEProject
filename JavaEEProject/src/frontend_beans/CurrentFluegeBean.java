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

import backend.models.Flug2;

@ManagedBean
@RequestScoped
public class CurrentFluegeBean {

	
@NotNull
private List<Flug2> allFluege;



public List<Flug2> getAllFluege() {
	allFluege = new ArrayList<Flug2>();
	List<String> s = new ArrayList<String>();
	s.add("Markus");
	s.add("Halil");
	Flug2 f = new Flug2("Start Platz", "Ziel", "Pommes", "Flug 63",s);
	Flug2 g = new Flug2("Start Platz", "Ziel", "Pommes", "Flug 64",s);
	allFluege.add(f);
	allFluege.add(g);
	return allFluege;
}

public void setAllFluege(List<Flug2> allFluege) {
	this.allFluege = allFluege;
}



}