package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Complaint_kinder implements Serializable {

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
	private String msg;
	@Temporal(TemporalType.DATE)
	private Date date_comp;
	private boolean stat;

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate_comp() {
		return date_comp;
	}

	public void setDate_comp(Date date_comp) {
		this.date_comp = date_comp;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

}
