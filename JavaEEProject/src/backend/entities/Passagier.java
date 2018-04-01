package backend.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the passagier database table.
 * 
 */
@Entity
@Table(name = "passagier")
@NamedQueries({
	@NamedQuery(name = "Passagier.findAll", query = "SELECT p FROM Passagier p"),
	@NamedQuery(name = "Passagier.findbyID", query = "SELECT p FROM Passagier p where p.passagierid= :id")
})
@NamedQuery(name = "Passagier.findAll", query = "SELECT p FROM Passagier p")
public class Passagier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int passagierid;

	private String anschrift;

	private String geburtsdatum;

	private String nachname;

	private String nationalitaet;

	private String vorname;

	public Passagier() {
	}

	public Passagier(String vorname, String nachname, String anschrift, String geburtsdatum, String nationalitaet) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.anschrift = anschrift;
		this.geburtsdatum = geburtsdatum;
		this.nationalitaet = nationalitaet;
	}

	public int getPassagierid() {
		return this.passagierid;
	}

	public void setPassagierid(int passagierid) {
		this.passagierid = passagierid;
	}

	public String getAnschrift() {
		return this.anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	public String getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getNationalitaet() {
		return this.nationalitaet;
	}

	public void setNationalitaet(String nationalitaet) {
		this.nationalitaet = nationalitaet;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}