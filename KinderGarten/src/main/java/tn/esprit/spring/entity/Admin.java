package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("0")
public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563020950778936883L;

}
