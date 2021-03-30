package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Classroom;
@Repository
public interface IClassroomRepository extends CrudRepository<Classroom, Integer> {
	
	@Query("SELECT c FROM Classroom c WHERE c.nbr_max <= c.nbInscrit")
	public List<Classroom> dispalyClassroomSaturated();
	
	@Query("SELECT c FROM Classroom c WHERE c.nbr_max>c.nbInscrit")
	public List<Classroom> displayClassroomNonSaturated();
	
	@Query(value="SELECT * FROM Classroom WHERE date_end  Like %?1%", nativeQuery = true)
	List<Classroom> classroomRevenueYear(@Param("year") String year);
	
	@Query("SELECT c FROM Classroom c ORDER BY c.datebegin ASC")
	List<Classroom> displayClassroomByDate();
	
/*
	@Query("SELECT c FROM Classroom c WHERE c.kindergarten= :kinder")
	public List<Classroom> findClassroomByKinder(@Param("kinder")int kinder);*/
	
}
