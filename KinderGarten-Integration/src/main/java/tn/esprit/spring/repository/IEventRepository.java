package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
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
	@Query("SELECT ev FROM Event ev WHERE ev.date_end <= CURRENT_DATE()")
	public List<Event> passedEvents();
	
	@Modifying
	@Transactional
	@Query("UPDATE Event e SET e.title = :title,e.date_begin = :date_begin,e.date_end = :date_end,e.description = :description,"
			+ "e.place = :place,e.photo = :photo,e.Price = :Price,e.collAmount = :collAmount,e.nbr_participants = :nbr_participants,"
			+ "e.Nbr_places = :Nbr_places,e.earlyParticipants = :earlyParticipants,e.discountPercentage = :discountPercentage,e.nbrTicketEarlyParticipants = :nbrTicketEarlyParticipants,e.type = :type"
			+ "  WHERE e.id = :id")
	public int updateEvent(@Param("title")String title,@Param("date_begin") Date date_begin,@Param("date_end") Date date_end,
			@Param("description")String description,@Param("place") String place,@Param("photo") String photo,@Param ("Price") float Price,@Param ("collAmount") float collAmount,
			@Param ("nbr_participants") int nbr_participants,@Param ("Nbr_places") int Nbr_places,@Param ("earlyParticipants") boolean earlyParticipants,@Param ("discountPercentage") float discountPercentage,
			@Param ("nbrTicketEarlyParticipants") int nbrTicketEarlyParticipants,@Param ("type") Type type,@Param("id")int id	 );
	
	
	@Query("SELECT e.jackpot FROM Event e where e.jackpot =:jackpot")
	public Jackpot findJackpot (@Param("jackpot")Jackpot jackpot);
	
	@Modifying
	@Transactional
	@Query("UPDATE Event p SET p.views = :views+1 WHERE p.id =:id")
	public int updateViewsCountEvent(@Param("views")int view ,@Param("id")int id);
	}
