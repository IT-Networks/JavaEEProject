package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the flug database table.
 * 
 */
@Entity
@Table(name = "flug")
@NamedQueries({ @NamedQuery(name = "Flug.findAll", query = "SELECT f FROM Flug f"),
		@NamedQuery(name = "Flug.findbyRelationID", query = "SELECT f FROM Relation f where f.relationid = :id") })
public class Flug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String flugid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date abflug;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ankunft;
	@JoinColumn(nullable = true)
	private int flugzeugid;
	@JoinColumn(nullable = true)
	private int mahlzeitid;

	private BigDecimal preis;

	// bi-directional many-to-one association to Relation
	@ManyToOne
	@JoinColumn(name = "relationid")
	private Relation relation;

	public Flug() {
	}

	public String getFlugid() {
		return this.flugid;
	}

	public void setFlugid(String flugid) {
		this.flugid = flugid;
	}

	public Date getAbflug() {
		return this.abflug;
	}

	public void setAbflug(Date abflug) {
		this.abflug = abflug;
	}

	public Date getAnkunft() {
		return this.ankunft;
	}

	public void setAnkunft(Date ankunft) {
		this.ankunft = ankunft;
	}

	public int getFlugzeugid() {
		return this.flugzeugid;
	}

	public void setFlugzeugid(int flugzeugid) {
		this.flugzeugid = flugzeugid;
	}

	public int getMahlzeitid() {
		return this.mahlzeitid;
	}

	public void setMahlzeitid(int mahlzeitid) {
		this.mahlzeitid = mahlzeitid;
	}

	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}