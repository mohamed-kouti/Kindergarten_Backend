package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.KinderGarten;


@Repository
public interface IKindergartenRepository extends CrudRepository<KinderGarten, Integer> {
	
	@Query("SELECT c FROM KinderGarten c WHERE c.name=:name")
	public List<KinderGarten> getKindergartenByName(@Param("name")String n);
	
	@Query("SELECT c FROM KinderGarten c WHERE c.place=:place")
	public List<KinderGarten> getKindergartenByPlace(@Param("place")String p);


	//List<KinderGarten> findByName(String Name , Pageable pageable);
	
	//List<KinderGarten> findByNameLike(String name);
	
	 /*@Query("SELECT k FROM KinderGarten k WHERE k.name LIKE %?1%"
			+ " OR k.place LIKE %?1%")
	public List<KinderGarten> search(String keyword);*/
	

	
	

}
