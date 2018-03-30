package backend.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flugzeug database table.
 * 
 */
@Entity
@Table(name="flugzeug")
@NamedQuery(name="Flugzeug.findAll", query="SELECT f FROM Flugzeug f")
public class Flugzeug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int flugzeugid;

	private String hersteller;

	private int sitzplaetze;

	private String typ;

	public Flugzeug() {
		
	}
	public Flugzeug(String hersteller, String typ, int sitzplaetze) {
		this.hersteller = hersteller;
		this.typ = typ;
		this.sitzplaetze = sitzplaetze;
	}

	public int getFlugzeugid() {
		return this.flugzeugid;
	}

	public void setFlugzeugid(int flugzeugid) {
		this.flugzeugid = flugzeugid;
	}

	public String getHersteller() {
		return this.hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public int getSitzplaetze() {
		return this.sitzplaetze;
	}

	public void setSitzplaetze(int sitzplaetze) {
		this.sitzplaetze = sitzplaetze;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

}