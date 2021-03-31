package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;

@Repository
public interface IParticipantRepository extends CrudRepository<Participation, Integer>{

	@Query("SELECT p FROM Participation p WHERE p.event= :event")
	List<Participation> Particiapations(@Param("event") Event event);
}
