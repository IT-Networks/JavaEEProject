package frontend.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import backend.enterpriseLogic.FlugzeugHandler;
import backend.enterpriseLogic.SuccessHandler;

@ManagedBean
@RequestScoped
public class FlugzeugBean {

	@NotNull
	private String procducer;
	@NotNull
	private String typ;
	@NotNull
	@Min(10)
	private int seats;

	public String getProcducer() {
		return procducer;
	}

	public void setProcducer(String procducer) {
		this.procducer = procducer;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void createFlugzeug() {
		FacesMessage msg;
		FlugzeugHandler fh = new FlugzeugHandler();
		String result = fh.createFlugzeug(procducer, typ, seats);

		if (result.equals(SuccessHandler.FLUGZEUGANLAGE)) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, result, "");
			FacesContext.getCurrentInstance().addMessage("airplainForm:saveAirplain", msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, result, "");
			FacesContext.getCurrentInstance().addMessage("airplainForm:saveAirplain", msg);
		}
	}

}