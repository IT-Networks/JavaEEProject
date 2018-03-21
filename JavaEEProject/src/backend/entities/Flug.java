package backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the flug database table.
 * 
 */
@Entity
@Table(name="flug")
@NamedQuery(name="Flug.findAll", query="SELECT f FROM Flug f")
public class Flug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String flugid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date abflug;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ankunft;

	private float distanz;

	private Time flugzeit;

	private BigDecimal preis;

	//bi-directional many-to-one association to Buchung
	@OneToMany(mappedBy="flug")
	private List<Buchung> buchungs;

	//bi-directional many-to-one association to Flugzeug
	@ManyToOne
	@JoinColumn(name="flugzeugid")
	private Flugzeug flugzeug;

	//bi-directional many-to-one association to Mahlzeit
	@ManyToOne
	@JoinColumn(name="mahlzeitid")
	private Mahlzeit mahlzeit;

	//bi-directional many-to-one association to Relation
	@ManyToOne
	@JoinColumn(name="relationid")
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

	public float getDistanz() {
		return this.distanz;
	}

	public void setDistanz(float distanz) {
		this.distanz = distanz;
	}

	public Time getFlugzeit() {
		return this.flugzeit;
	}

	public void setFlugzeit(Time flugzeit) {
		this.flugzeit = flugzeit;
	}

	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public List<Buchung> getBuchungs() {
		return this.buchungs;
	}

	public void setBuchungs(List<Buchung> buchungs) {
		this.buchungs = buchungs;
	}

	public Buchung addBuchung(Buchung buchung) {
		getBuchungs().add(buchung);
		buchung.setFlug(this);

		return buchung;
	}

	public Buchung removeBuchung(Buchung buchung) {
		getBuchungs().remove(buchung);
		buchung.setFlug(null);

		return buchung;
	}

	public Flugzeug getFlugzeug() {
		return this.flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	public Mahlzeit getMahlzeit() {
		return this.mahlzeit;
	}

	public void setMahlzeit(Mahlzeit mahlzeit) {
		this.mahlzeit = mahlzeit;
	}

	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}