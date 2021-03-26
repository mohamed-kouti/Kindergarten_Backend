package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;
@Repository
public interface IDonnationRepository extends CrudRepository<Donnation, Integer> {

	@Query("SELECT d FROM Donnation d WHERE d.event=:event ")
	List<Donnation> DonationOfEvent(@Param("event")Event event);
}
