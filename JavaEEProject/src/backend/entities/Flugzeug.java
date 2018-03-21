package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to Flug
	@OneToMany(mappedBy="flugzeug")
	private List<Flug> flugs;

	public Flugzeug() {
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

	public List<Flug> getFlugs() {
		return this.flugs;
	}

	public void setFlugs(List<Flug> flugs) {
		this.flugs = flugs;
	}

	public Flug addFlug(Flug flug) {
		getFlugs().add(flug);
		flug.setFlugzeug(this);

		return flug;
	}

	public Flug removeFlug(Flug flug) {
		getFlugs().remove(flug);
		flug.setFlugzeug(null);

		return flug;
	}

}