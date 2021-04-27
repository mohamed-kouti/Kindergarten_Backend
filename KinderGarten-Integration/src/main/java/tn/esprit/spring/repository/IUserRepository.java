package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	@Query(value = "select dtype from user u where u.email= :mail and actif='1' ", nativeQuery = true)
	String getRole(@Param("mail") String mail);

	User findByFname(String name);
	
	@Query(value = "select count(*) from user " ,nativeQuery = true)
	int getnbrUser();
	
	@Query(value = "select * from user u where u.dtype='Parent' and u.actif='1' ", nativeQuery = true)
	List<User> getAllParent();

	@Query(value = "select * from user u where u.actif='0' ", nativeQuery = true)
	List<User> getAllInactive();
	
	@Query(value = "select * from user u where u.dtype='Admin' ", nativeQuery = true)
	List<User> getAllAdmin();
	@Query(value = "select * from user u where u.dtype='KinderGarten_owner' and u.actif='1' ", nativeQuery = true)
	List<User> getAllkdowner();

	@Transactional
	@Modifying
	@Query(value = "update User u set u.actif= 0 where u.id= :id ")
	void blockUser(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "update User u set u.actif= 1 where u.id= :id ")
	void actifUser(@Param("id") int id);

	
	
}
