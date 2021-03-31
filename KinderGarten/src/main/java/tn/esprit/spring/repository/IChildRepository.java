package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Parent;

@Repository
public interface IChildRepository extends CrudRepository<Child, Integer> {

	@Query("select c from Child c where c.parent= :parent")
	public List<Child> findchildByparent(@Param("parent") Parent parent );
		
	

}
