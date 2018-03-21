package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the flughafen database table.
 * 
 */
@Entity
@Table(name="flughafen")
@NamedQuery(name="Flughafen.findAll", query="SELECT f FROM Flughafen f")
public class Flughafen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String flughafenid;

	private String land;

	private String name;

	private String ort;

	private String region;

	private String zeitzone;

	//bi-directional many-to-one association to Relation
	@OneToMany(mappedBy="flughafen1")
	private List<Relation> relations1;

	//bi-directional many-to-one association to Relation
	@OneToMany(mappedBy="flughafen2")
	private List<Relation> relations2;

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

	public List<Relation> getRelations1() {
		return this.relations1;
	}

	public void setRelations1(List<Relation> relations1) {
		this.relations1 = relations1;
	}

	public Relation addRelations1(Relation relations1) {
		getRelations1().add(relations1);
		relations1.setFlughafen1(this);

		return relations1;
	}

	public Relation removeRelations1(Relation relations1) {
		getRelations1().remove(relations1);
		relations1.setFlughafen1(null);

		return relations1;
	}

	public List<Relation> getRelations2() {
		return this.relations2;
	}

	public void setRelations2(List<Relation> relations2) {
		this.relations2 = relations2;
	}

	public Relation addRelations2(Relation relations2) {
		getRelations2().add(relations2);
		relations2.setFlughafen2(this);

		return relations2;
	}

	public Relation removeRelations2(Relation relations2) {
		getRelations2().remove(relations2);
		relations2.setFlughafen2(null);

		return relations2;
	}

}