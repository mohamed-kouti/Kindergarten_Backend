package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class PK_APP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id_parent;
	private int id_kindergarten_owner;
	private String uuid = UUID.randomUUID().toString();

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public int getId_kindergarten_owner() {
		return id_kindergarten_owner;
	}

	public void setId_kindergarten_owner(int id_kindergarten_owner) {
		this.id_kindergarten_owner = id_kindergarten_owner;
	}

}
