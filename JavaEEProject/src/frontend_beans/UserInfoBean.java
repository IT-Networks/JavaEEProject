package frontend_beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;

import frontend_controller.SessionUtils;


@ManagedBean
@RequestScoped
public class UserInfoBean {
	
	@NotEmpty
	private boolean isUserManager;

	@NotEmpty
	private boolean isLoggedIn;

	public boolean isLoggedIn() {
		HttpSession session = SessionUtils.getSession();
		if(session.getAttribute("user") == null)
			isLoggedIn = false;
		else 
			isLoggedIn = true;	    
		return isLoggedIn;
	}


	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


	public boolean isUserManager() {	  
		HttpSession session = SessionUtils.getSession();
		if(session.getAttribute("role") == null)
			isUserManager = false;
		else if(session.getAttribute("role").equals("Manager"))
			isUserManager = true;
		else 
			isUserManager = false;
		
		return isUserManager;
	    }
	

	public void setUserManager(boolean isUserManager) {
		this.isUserManager = isUserManager;
	}
	

	

}