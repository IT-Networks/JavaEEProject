package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * The persistent class for the relation database table.
 * 
 */
@Entity
@Table(name = "relation")
@NamedQueries({ @NamedQuery(name = "Relation.findAll", query = "SELECT r FROM Relation r"),
		@NamedQuery(name = "Relation.findRelations", query = "SELECT r FROM Relation r where r.startort = :start and r.zielort = :ziel or r.startort = :ziel and r.zielort = :start"),
		@NamedQuery(name = "Relation.findbyID", query = "SELECT r FROM Relation r where r.relationid = :id") })

public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int relationid;

	private int distanz;

	private Time flugzeit;

	private String startort;

	private String zielort;

	// bi-directional many-to-one association to Flug
	@OneToMany(mappedBy = "relation")
	private List<Flug> flugs;

	public Relation() {
	}

	public int getRelationid() {
		return this.relationid;
	}

	public void setRelationid(int relationid) {
		this.relationid = relationid;
	}

	public int getDistanz() {
		return this.distanz;
	}

	public void setDistanz(int distanz) {
		this.distanz = distanz;
	}

	public Time getFlugzeit() {
		return this.flugzeit;
	}

	public void setFlugzeit(Time flugzeit) {
		this.flugzeit = flugzeit;
	}

	public String getStartort() {
		return this.startort;
	}

	public void setStartort(String startort) {
		this.startort = startort;
	}

	public String getZielort() {
		return this.zielort;
	}

	public void setZielort(String zielort) {
		this.zielort = zielort;
	}

	public List<Flug> getFlugs() {
		return this.flugs;
	}

	public void setFlugs(List<Flug> flugs) {
		this.flugs = flugs;
	}

	public Flug addFlug(Flug flug) {
		getFlugs().add(flug);
		flug.setRelation(this);

		return flug;
	}

	public Flug removeFlug(Flug flug) {
		getFlugs().remove(flug);
		flug.setRelation(null);

		return flug;
	}

}