package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("")
public class Complaint_kinder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_parent", referencedColumnName = "id")
	private Parent parent;
	@ManyToOne
	@JoinColumn(name = "id_kindergarten", referencedColumnName = "id")
	private KinderGarten garten;
	private String msg;
	@Temporal(TemporalType.DATE)
	private Date date_comp;
	private boolean stat;
	
	@Enumerated(EnumType.STRING)
	private ComplaintCategory complaintCategory;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ComplaintCategory getComplaintCategory() {
		return complaintCategory;
	}

	public void setComplaintCategory(ComplaintCategory complaintCategory) {
		this.complaintCategory = complaintCategory;
	}
	
	
}
