package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the nutzer database table.
 * 
 */
@Entity
@Table(name = "nutzer")
@NamedQueries({ @NamedQuery(name = "Nutzer.findAll", query = "SELECT n FROM Nutzer n"),
		@NamedQuery(name = "Nutzer.findByName", query = "SELECT n FROM Nutzer n WHERE n.anmeldename = :name"), })
public class Nutzer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nutzerrid;

	private String anmeldename;

	private String nachname;

	private String nutzertyp;

	private String passwort;

	private String vorname;

	// bi-directional many-to-one association to Buchung
	@OneToMany(mappedBy = "nutzer")
	private List<Buchung> buchungs;

	public Nutzer() {
	}

	public int getNutzerrid() {
		return this.nutzerrid;
	}

	public void setNutzerrid(int nutzerrid) {
		this.nutzerrid = nutzerrid;
	}

	public String getAnmeldename() {
		return this.anmeldename;
	}

	public void setAnmeldename(String anmeldename) {
		this.anmeldename = anmeldename;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getNutzertyp() {
		return this.nutzertyp;
	}

	public void setNutzertyp(String nutzertyp) {
		this.nutzertyp = nutzertyp;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public List<Buchung> getBuchungs() {
		return this.buchungs;
	}

	public void setBuchungs(List<Buchung> buchungs) {
		this.buchungs = buchungs;
	}

	public Buchung addBuchung(Buchung buchung) {
		getBuchungs().add(buchung);
		buchung.setNutzer(this);

		return buchung;
	}

	public Buchung removeBuchung(Buchung buchung) {
		getBuchungs().remove(buchung);
		buchung.setNutzer(null);

		return buchung;
	}

}