package tn.esprit.spring.repository;

import java.util.Optional;

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

}
