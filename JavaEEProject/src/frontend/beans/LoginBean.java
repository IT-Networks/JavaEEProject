package frontend.beans;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.NutzerHandler;
import backend.enterpriseLogic.SuccessHandler;
import frontend.controller.SessionUtils;

@ManagedBean
@RequestScoped
public class LoginBean {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	public void setUsername(String name) {
		this.username = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		NutzerHandler nh = new NutzerHandler();
		String result = nh.checkPasswort(username, password);
		FacesMessage msg;
		if (result.equals(SuccessHandler.LOGIN)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", result);
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", username);
			session.setAttribute("profil", "Mitarbeiter");
			session.setAttribute("role", nh.getNutzertyp(username));
			FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JavaEEProject/currentFluege.xhtml");
		} else if (result.equals(ErrorHandler.NUTZERNICHTGEFUNDEN)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
			FacesContext.getCurrentInstance().addMessage("loginForm:username", msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", result);
			FacesContext.getCurrentInstance().addMessage("loginForm:password", msg);
		}

	}

	public void logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

}
