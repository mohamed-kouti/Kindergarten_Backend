package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.KinderGarten_owner;

public interface IAppointmentRepository extends CrudRepository<Appointment, Integer> {
	
	@Query("select a from Appointment a where a.garten_owner=:id_kindergarten_owner and a.date_app >= :date_app")
	List<Appointment> findByGarten_ownerDateTime(@Param("id_kindergarten_owner")KinderGarten_owner garten_owner, 
			@Param("date_app")Date date_app);

}
