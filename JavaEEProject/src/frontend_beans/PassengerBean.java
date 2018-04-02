package frontend_beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.FlugzeugHandler;
import backend.enterpriseLogic.MahlzeitHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.enterpriseLogic.SuccessHandler;


@ManagedBean
@RequestScoped
public class PassengerBean { 

	@NotNull
	private String name;
	@NotNull
	private String lastname;
	@NotNull
	private String location;
	@NotNull
	private String birthdy;
	@NotNull
	private String nationality;

	

	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getBirthdy() {
		return birthdy;
	}




	public void setBirthdy(String birthdy) {
		this.birthdy = birthdy;
	}




	public String getNationality() {
		return nationality;
	}




	public void setNationality(String nationality) {
		this.nationality = nationality;
	}




	public void creatPessenger(){
		FacesMessage msg;
		PassagierHandler ph = new PassagierHandler();
		String result = ph.createPassagier(name, lastname, location, birthdy, nationality);
		
		if(result.equals(SuccessHandler.PASSAGIERANLAGE)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,result,"");
			FacesContext.getCurrentInstance().addMessage("passengerForm:creatPessenger", msg);
		}
		else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,result,"");
			FacesContext.getCurrentInstance().addMessage("passengerForm:creatPessenger", msg);
		}
	}
	

	

	
}