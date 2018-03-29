package backend.entities;

import java.io.Serializable;
import javax.persistence.*;


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

}