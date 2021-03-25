package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Message;

@Repository
public interface IMessageRepository extends CrudRepository<Message, Integer> {

	@Query(value = "select * from swear", nativeQuery = true)
	List<String> getAllWord();
}
