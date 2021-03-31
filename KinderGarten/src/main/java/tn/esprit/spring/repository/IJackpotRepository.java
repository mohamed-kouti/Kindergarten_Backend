package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Jackpot;
@Repository
public interface IJackpotRepository extends CrudRepository<Jackpot, Integer> {

	@Query("SELECT e.jackpot FROM Event e WHERE e.id=:id ")
	Jackpot findJackpotEvent(@Param("id") int event);
}
