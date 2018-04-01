package backend.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flughafen database table.
 * 
 */
@Entity
@Table(name="flughafen")
@NamedQueries({
	@NamedQuery(name="Flughafen.findAll", query="SELECT f FROM Flughafen f"),
	@NamedQuery(name="Flughafen.findStartUndZiel", query="SELECT f FROM Flughafen f WHERE f.flughafenid = :start OR f.flughafenid = :ziel")
})
public class Flughafen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String flughafenid;

	private String land;

	private String name;

	private String ort;

	private String region;

	private String zeitzone;

	public Flughafen() {
	}

	public String getFlughafenid() {
		return this.flughafenid;
	}

	public void setFlughafenid(String flughafenid) {
		this.flughafenid = flughafenid;
	}

	public String getLand() {
		return this.land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZeitzone() {
		return this.zeitzone;
	}

	public void setZeitzone(String zeitzone) {
		this.zeitzone = zeitzone;
	}

}