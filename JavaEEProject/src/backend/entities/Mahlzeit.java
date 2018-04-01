package backend.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mahlzeit database table.
 * 
 */
@Entity
@Table(name="mahlzeit")
@NamedQueries({
	@NamedQuery(name="Mahlzeit.findAll", query="SELECT m FROM Mahlzeit m"),
	@NamedQuery(name="Mahlzeit.findMahlzeit", query="SELECT m FROM Mahlzeit m WHERE m.name = :name AND m.art = :art AND m.vegetarisch = :vegetarisch"),
	@NamedQuery(name="Mahlzeit.findbyID", query="SELECT m FROM Mahlzeit m WHERE m.mahlzeitid = :id")
})

public class Mahlzeit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mahlzeitid;

	private String art;

	private String name;

	private byte vegetarisch;

	public Mahlzeit() {
	}
	public Mahlzeit(String name, String art, boolean vegetarisch) {
		this.name = name;
		this.art = art;
		this.vegetarisch = (byte) (vegetarisch ? 1 : 0 );
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