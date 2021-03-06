package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.PK_APP;

public interface IAppointmentRepository extends CrudRepository<Appointment, PK_APP> {

}
