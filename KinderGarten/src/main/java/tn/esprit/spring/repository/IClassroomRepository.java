package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Classroom;

@Repository
public interface IClassroomRepository extends CrudRepository<Classroom, Integer> {
	
	@Query("SELECT c FROM Classroom c WHERE c.nbr_max <= c.nbInscrit")
	public List<Classroom> dispalyClassroomSaturated();
	
	@Query("SELECT c FROM Classroom c WHERE c.nbr_max>c.nbInscrit")
	public List<Classroom> displayClassroomNonSaturated();
	
	
	

}
