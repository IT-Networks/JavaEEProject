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
@NamedQuery(name="Buchung.findAll", query="SELECT b FROM Buchung b")
public class Buchung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int buchungid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date buchungsdatum;

	//bi-directional many-to-one association to Flug
	@ManyToOne
	@JoinColumn(name="flugid")
	private Flug flug;

	//bi-directional many-to-one association to Mahlzeit
	@ManyToOne
	@JoinColumn(name="gewaehlte_mahlzeitid")
	private Mahlzeit mahlzeit;

	//bi-directional many-to-one association to Nutzer
	@ManyToOne
	@JoinColumn(name="mitarbeiterid")
	private Nutzer nutzer;

	//bi-directional many-to-one association to Passagier
	@ManyToOne
	@JoinColumn(name="passagierid")
	private Passagier passagier;

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

	public Flug getFlug() {
		return this.flug;
	}

	public void setFlug(Flug flug) {
		this.flug = flug;
	}

	public Mahlzeit getMahlzeit() {
		return this.mahlzeit;
	}

	public void setMahlzeit(Mahlzeit mahlzeit) {
		this.mahlzeit = mahlzeit;
	}

	public Nutzer getNutzer() {
		return this.nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public Passagier getPassagier() {
		return this.passagier;
	}

	public void setPassagier(Passagier passagier) {
		this.passagier = passagier;
	}

}