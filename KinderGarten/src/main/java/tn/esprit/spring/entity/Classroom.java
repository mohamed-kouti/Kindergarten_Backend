package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classroom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int nbr_max;
	private int nbInscrit;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classroom")
	private List<Child> childs;
	@ManyToOne
	private KinderGarten kindergarten;
	

	public Classroom() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getNbInscrit() {
		return nbInscrit;
	}

	public void setNbInscrit(int nbInscrit) {
		this.nbInscrit = nbInscrit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbr_max() {
		return nbr_max;
	}

	public void setNbr_max(int nbr_max) {
		this.nbr_max = nbr_max;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public KinderGarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(KinderGarten kindergarten) {
		this.kindergarten = kindergarten;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", nbr_max=" + nbr_max + ", nbInscrit=" + nbInscrit
				+ ", childs=" + childs + ", kindergarten=" + kindergarten + "]";
	}

}
