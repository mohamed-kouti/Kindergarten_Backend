package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	@Query(value ="select dtype from user u where u.email= :mail ",nativeQuery = true )
	String getRole(@Param("mail")String mail);
	
	@Query(value ="select * from user u where u.dtype='Parent' ",nativeQuery = true )
	List<User> getAllParent();
	
	@Query(value ="select * from user u where u.dtype='Admin' ",nativeQuery = true )
	List<User> getAllAdmin();

}
