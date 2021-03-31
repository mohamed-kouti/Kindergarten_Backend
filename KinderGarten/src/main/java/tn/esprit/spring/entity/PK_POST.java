package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PK_POST implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_user;
	private int id_post;
	
	public PK_POST() {}
	

	public PK_POST(int id_user, int id_post) {
		super();
		this.id_user = id_user;
		this.id_post = id_post;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}
}
