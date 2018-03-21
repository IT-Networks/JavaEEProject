package frontend_beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import backend.enterpriseLogic.NutzerHandler;
import backend.enterpriseLogic.RelationHandlerLocal;
import frontend_controller.SessionUtils;

@ManagedBean
@RequestScoped
public class RegisterBean {

	@Size(min=4, max=10)
  @NotEmpty
  private String username;
 
  @Size(min=4, max=20)
  @NotEmpty
  private String password;
  
  @NotEmpty
  private String profilTyp;
  
  @NotEmpty
  private String name;
  
  public void setName(String name) {
	this.name = name;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

@NotEmpty
  private String lastname;
 
 
  public String getProfilTyp() {
	return profilTyp;
}

public void setProfilTyp(String profilTyp) {
	this.profilTyp = profilTyp;
}

public void setUsername(String name) {
    this.username = name;
  }
 
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}
	

	public void register() {
		NutzerHandler nh = new NutzerHandler();
		nh.createNutzer(name, lastname, username, password, profilTyp);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
				"Congratulations! You've successfully logged in.");
		FacesContext.getCurrentInstance().addMessage("registerForm:name", msg);
	}
}