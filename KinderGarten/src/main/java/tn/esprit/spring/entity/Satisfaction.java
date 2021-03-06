package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Satisfaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PK_SAT pk_sat;
	@ManyToOne
	@JoinColumn(name = "id_parent", referencedColumnName = "id", insertable = false, updatable = false)
	private Parent parent;
	@ManyToOne
	@JoinColumn(name = "id_kindergarten", referencedColumnName = "id", insertable = false, updatable = false)
	private KinderGarten garten;
	private int nps;
	private int csat;
	private int ces;

	public PK_SAT getPk_sat() {
		return pk_sat;
	}

	public void setPk_sat(PK_SAT pk_sat) {
		this.pk_sat = pk_sat;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public KinderGarten getGarten() {
		return garten;
	}

	public void setGarten(KinderGarten garten) {
		this.garten = garten;
	}

	public int getNps() {
		return nps;
	}

	public void setNps(int nps) {
		this.nps = nps;
	}

	public int getCsat() {
		return csat;
	}

	public void setCsat(int csat) {
		this.csat = csat;
	}

	public int getCes() {
		return ces;
	}

	public void setCes(int ces) {
		this.ces = ces;
	}

}
