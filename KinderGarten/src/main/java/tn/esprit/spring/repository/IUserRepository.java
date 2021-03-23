package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	@Query(value ="select dtype from user u where u.email= :mail ",nativeQuery = true )
	String getRole(@Param("mail")String mail);

}
