package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;




@Entity
public class Child implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fname;
	private String lname;
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	private String gender;
	private String address;
	

	@ManyToOne
	private Parent parent;
	@ManyToOne
	private Classroom classroom;
	
	
	
	public Child() {
		super();
	}

	public Child(int id, String fname, String lname, Date birthDate, String gender, String address, Parent parent,
			Classroom classroom) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.address = address;
		this.parent = parent;
		this.classroom = classroom;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}


	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return "Child [id=" + id + ", fname=" + fname + ", lname=" + lname + ", birthDate=" + birthDate + ", gender="
				+ gender + ", address=" + address + ", parent=" + parent + ", classroom=" + classroom + "]";
	}

}
