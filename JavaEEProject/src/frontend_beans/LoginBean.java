package frontend_beans;

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
import frontend_controller.SessionUtils;

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

	public void login() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		NutzerHandler nh = new NutzerHandler();
		String result = nh.checkPasswort(username, password);
		FacesMessage msg;
		if (result.equals("Login erfolgreich.")) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					result);
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);

		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					result);
		}
		FacesContext.getCurrentInstance().addMessage("loginForm:name", msg);
	}

}