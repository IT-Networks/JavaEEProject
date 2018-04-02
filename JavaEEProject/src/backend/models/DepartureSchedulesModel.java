package backend.models;

public class DepartureSchedulesModel {
	private String flugid;
	private String startort;
	private String zielort;
	private String status;
	private String abflug;
	private String ankunft;
	private String preis;

	public DepartureSchedulesModel(String flugid, String startort, String zielort, String status, String abflug,
			String ankunft, String preis) {
		super();
		this.flugid = flugid;
		this.startort = startort;
		this.zielort = zielort;
		this.status = status;
		this.abflug = abflug;
		this.ankunft = ankunft;
		this.preis = preis;
	}

	public String getFlugid() {
		return flugid;
	}

	public void setFlugid(String flugid) {
		this.flugid = flugid;
	}

	public String getStartort() {
		return startort;
	}

	public void setStartort(String startort) {
		this.startort = startort;
	}

	public String getZielort() {
		return zielort;
	}

	public void setZielort(String zielort) {
		this.zielort = zielort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAbflug() {
		return abflug;
	}

	public void setAbflug(String abflug) {
		this.abflug = abflug;
	}

	public String getAnkunft() {
		return ankunft;
	}

	public void setAnkunft(String ankunft) {
		this.ankunft = ankunft;
	}

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

}
