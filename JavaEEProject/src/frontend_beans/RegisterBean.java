package frontend_beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import backend.enterpriseLogic.NutzerHandler;
import backend.enterpriseLogic.SuccessHandler;

@ManagedBean
@RequestScoped
public class RegisterBean {

	@Size(min = 4, max = 10)
	@NotEmpty
	private String username;

	@Size(min = 4, max = 20)
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

	public void register() throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		NutzerHandler nh = new NutzerHandler();
		FacesMessage msg;
		String result = nh.createNutzer(name, lastname, username, password, profilTyp);
		if (result.equals(SuccessHandler.REGISTRIERUNG)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", result);
			FacesContext.getCurrentInstance().addMessage("registerForm:register", msg);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JavaEEProject/currentFluege.xhtml");
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
			FacesContext.getCurrentInstance().addMessage("registerForm:register", msg);
		} 
	}
}
