package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Event;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {
	
}
