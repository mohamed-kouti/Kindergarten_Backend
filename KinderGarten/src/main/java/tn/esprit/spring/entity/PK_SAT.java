package tn.esprit.spring.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PK_SAT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_parent;
	private int id_kindergarten;

	public PK_SAT() {
	}

	public PK_SAT(int id_parent, int id_kindergarten) {
		this.id_parent = id_parent;
		this.id_kindergarten = id_kindergarten;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public int getId_kindergarten() {
		return id_kindergarten;
	}

	public void setId_kindergarten(int id_kindergarten) {
		this.id_kindergarten = id_kindergarten;
	}

	
}
