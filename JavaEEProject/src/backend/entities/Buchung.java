package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the buchung database table.
 * 
 */
@Entity
@Table(name="buchung")
@NamedQueries({
	@NamedQuery(name="Buchung.findAll", query="SELECT b FROM Buchung b"),
	@NamedQuery(name="Buchung.findbyFlug", query="SELECT b FROM Buchung b WHERE b.flugid = :flugid"),
	@NamedQuery(name="Buchung.findbyPassagier", query="SELECT b FROM Buchung b WHERE b.passagierid = :passagierid"),
})
@NamedQuery(name="Buchung.findAll", query="SELECT b FROM Buchung b")
public class Buchung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int buchungid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date buchungsdatum;

	private String flugid;

	private int passagierid;

	public Buchung() { 
	}

	public int getBuchungid() {
		return this.buchungid;
	}

	public void setBuchungid(int buchungid) {
		this.buchungid = buchungid;
	}

	public Date getBuchungsdatum() {
		return this.buchungsdatum;
	}

	public void setBuchungsdatum(Date buchungsdatum) {
		this.buchungsdatum = buchungsdatum;
	}

	public String getFlugid() {
		return this.flugid;
	}

	public void setFlugid(String flugid) {
		this.flugid = flugid;
	}

	public int getPassagierid() {
		return this.passagierid;
	}

	public void setPassagierid(int passagierid) {
		this.passagierid = passagierid;
	}

}