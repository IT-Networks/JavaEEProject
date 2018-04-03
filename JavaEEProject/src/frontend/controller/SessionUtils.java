package frontend.controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	private static HttpSession mysession;
	private static ExternalContext context;
	
	public static HttpSession getSession() {
		context = FacesContext.getCurrentInstance().getExternalContext();
		return mysession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static String getUserRole() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("role");
		else
			return null;
	}

	public static String logout() {

		if (mysession != null && context != null) {
			mysession.invalidate();
			context.invalidateSession();			
			return "Augeloggt";
		} else {
			return "Bereits ausgeloggt";
		}
	}

}
