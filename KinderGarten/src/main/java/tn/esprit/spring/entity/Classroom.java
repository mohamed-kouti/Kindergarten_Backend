package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
	@Temporal(TemporalType.DATE)
	private Date datebegin;
	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	@Transient
	private long periode;
	
	private Double price_T;
	private Double price_M;
	
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "classroom" ,fetch= FetchType.EAGER)
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
	

	public Date getDatebegin() {
		return datebegin;
	}

	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public long getPeriode() {
		return periode;
	}

	public void setPeriode(long periode) {
		this.periode = periode;
	}

	public Double getPrice_T() {
		return price_T;
	}

	public void setPrice_T(Double price_T) {
		this.price_T = price_T;
	}

	public Double getPrice_M() {
		return price_M;
	}

	public void setPrice_M(Double price_M) {
		this.price_M = price_M;
	}

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", nbr_max=" + nbr_max + ", nbInscrit=" + nbInscrit
				+ ", datebegin=" + datebegin + ", dateEnd=" + dateEnd + ", periode=" + periode + ", price_T=" + price_T
				+ ", price_M=" + price_M + ", childs=" + childs + ", kindergarten=" + kindergarten + "]";
	}
}
