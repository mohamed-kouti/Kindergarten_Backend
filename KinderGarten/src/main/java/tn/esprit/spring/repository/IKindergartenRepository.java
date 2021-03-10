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
	
	 /*@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
	            + " OR p.brand LIKE %?1%"
	            + " OR p.madein LIKE %?1%"
	            + " OR CONCAT(p.price, '') LIKE %?1%")
	    public List<Product> search(String keyword);
	*/
	
	/*@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")*/

	
	/*@Query("SELECT k FROM KinderGarten k WHERE k.name LIKE %?1%"
			+ " OR k.place LIKE %?1%")
	public List<KinderGarten> search(String keyword);*/
	

	/*@Query("SELECT c FROM Claim c WHERE c.kindergarden.name =:name")
	public List<Claim> getClaimByKindergarden(@Param("name")String k);*/
	
	
	
	

}
