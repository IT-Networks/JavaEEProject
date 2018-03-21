package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the relation database table.
 * 
 */
@Entity
@Table(name="relation")
@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r")
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int relationid;

	//bi-directional many-to-one association to Flug
	@OneToMany(mappedBy="relation")
	private List<Flug> flugs;

	//bi-directional many-to-one association to Flughafen
	@ManyToOne
	@JoinColumn(name="startort")
	private Flughafen flughafen1;

	//bi-directional many-to-one association to Flughafen
	@ManyToOne
	@JoinColumn(name="zielort")
	private Flughafen flughafen2;

	public Relation() {
	}

	public int getRelationid() {
		return this.relationid;
	}

	public void setRelationid(int relationid) {
		this.relationid = relationid;
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

	public Flughafen getFlughafen1() {
		return this.flughafen1;
	}

	public void setFlughafen1(Flughafen flughafen1) {
		this.flughafen1 = flughafen1;
	}

	public Flughafen getFlughafen2() {
		return this.flughafen2;
	}

	public void setFlughafen2(Flughafen flughafen2) {
		this.flughafen2 = flughafen2;
	}

}