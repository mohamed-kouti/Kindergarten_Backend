package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Type;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {
	
	//CRUD specifique find event par nom
	@Query("SELECT ev FROM Event ev WHERE ev.title = :title")
	public Event findEventByName(@Param("title")String title);
	
	
	//retourner event par type
	@Query("SELECT ev FROM Event ev WHERE ev.type =:type")
	public List<Event> filterByType(@Param("type")Type type);
	
	//evenement daté de la jour systeme
	@Query("SELECT ev FROM Event ev WHERE ev.date_begin >= CURRENT_DATE() ")
	public List<Event> upcomingEvents();
	
	//les evenement qui sont deja terminés par rapport à la date systeme
	@Query("SELECT ev FROM Event ev WHERE ev.date_end <= current_timestamp()")
	public List<Event> passedEvents();
}
