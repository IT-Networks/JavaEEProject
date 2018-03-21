package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mahlzeit database table.
 * 
 */
@Entity
@Table(name="mahlzeit")
@NamedQuery(name="Mahlzeit.findAll", query="SELECT m FROM Mahlzeit m")
public class Mahlzeit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mahlzeitid;

	private String art;

	private String name;

	private byte vegetarisch;

	//bi-directional many-to-one association to Buchung
	@OneToMany(mappedBy="mahlzeit")
	private List<Buchung> buchungs;

	//bi-directional many-to-one association to Flug
	@OneToMany(mappedBy="mahlzeit")
	private List<Flug> flugs;

	public Mahlzeit() {
	}

	public int getMahlzeitid() {
		return this.mahlzeitid;
	}

	public void setMahlzeitid(int mahlzeitid) {
		this.mahlzeitid = mahlzeitid;
	}

	public String getArt() {
		return this.art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getVegetarisch() {
		return this.vegetarisch;
	}

	public void setVegetarisch(byte vegetarisch) {
		this.vegetarisch = vegetarisch;
	}

	public List<Buchung> getBuchungs() {
		return this.buchungs;
	}

	public void setBuchungs(List<Buchung> buchungs) {
		this.buchungs = buchungs;
	}

	public Buchung addBuchung(Buchung buchung) {
		getBuchungs().add(buchung);
		buchung.setMahlzeit(this);

		return buchung;
	}

	public Buchung removeBuchung(Buchung buchung) {
		getBuchungs().remove(buchung);
		buchung.setMahlzeit(null);

		return buchung;
	}

	public List<Flug> getFlugs() {
		return this.flugs;
	}

	public void setFlugs(List<Flug> flugs) {
		this.flugs = flugs;
	}

	public Flug addFlug(Flug flug) {
		getFlugs().add(flug);
		flug.setMahlzeit(this);

		return flug;
	}

	public Flug removeFlug(Flug flug) {
		getFlugs().remove(flug);
		flug.setMahlzeit(null);

		return flug;
	}

}