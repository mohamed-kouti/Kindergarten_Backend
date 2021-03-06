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
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PK_APP pk_app;
	@ManyToOne
	@JoinColumn(name = "id_parent", referencedColumnName = "id", insertable = false, updatable = false)
	private Parent parent;
	@ManyToOne
	@JoinColumn(name = "id_kindergarten_owner", referencedColumnName = "id", insertable = false, updatable = false)
	private KinderGarten_owner garten_owner;

	private String description;
	@Temporal(TemporalType.DATE)
	private Date date_app;

	public PK_APP getPk_app() {
		return pk_app;
	}

	public void setPk_app(PK_APP pk_app) {
		this.pk_app = pk_app;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public KinderGarten_owner getGarten_owner() {
		return garten_owner;
	}

	public void setGarten_owner(KinderGarten_owner garten_owner) {
		this.garten_owner = garten_owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_app() {
		return date_app;
	}

	public void setDate_app(Date date_app) {
		this.date_app = date_app;
	}

}
