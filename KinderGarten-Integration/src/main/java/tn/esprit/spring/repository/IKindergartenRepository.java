package tn.esprit.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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
	
	@Modifying   //used for update insert.. not only select
	@Transactional
	@Query("UPDATE KinderGarten k   SET k.views = :view+1 WHERE k.id =:id ")
	public int updateViewCountKinderG(@Param("view")int view,@Param("id")int id);
	
	/*@Query("SELECT c FROM kinderGarten c WHERE c.revenue=:c.revenue")
	public List<KinderGarten> getRevenue(@Param("year") String year);*/



	//List<KinderGarten> findByName(String Name , Pageable pageable);
	
	//List<KinderGarten> findByNameLike(String name);
	
	 /*@Query("SELECT k FROM KinderGarten k WHERE k.name LIKE %?1%"
			+ " OR k.place LIKE %?1%")
	public List<KinderGarten> search(String keyword);*/
	
}
