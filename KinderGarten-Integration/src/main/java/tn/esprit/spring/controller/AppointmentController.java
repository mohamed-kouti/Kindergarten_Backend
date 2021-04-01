package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.PK_APP;
import tn.esprit.spring.repository.IAppointmentRepository;
import tn.esprit.spring.service.implementations.AppointmentServiceImpl;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	IAppointmentRepository iAppointmentRepository;
	
	@Autowired
	AppointmentServiceImpl appointmentService;

	@GetMapping("")
	public ResponseEntity<List<Appointment>> index() {
		try {
			List<Appointment> appointments = new ArrayList<Appointment>();

			iAppointmentRepository.findAll().forEach(appointments::add);

			if (appointments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Appointment> getById(@PathVariable("id") int id) {
		Optional<Appointment> appointment = iAppointmentRepository.findById(id);

		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Object> add(@RequestBody Appointment appointment) {
		
			Appointment _appointment = iAppointmentRepository.save(appointment);
			
			
			List<Object> resp = appointmentService.setApointmentDate(_appointment);
			
			return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> update(PK_APP pk_app, @RequestBody Appointment appointment, @PathVariable("id") int id) {

		Optional<Appointment> appointmentData = iAppointmentRepository.findById(id);

		if (appointmentData.isPresent()) {
			Appointment _appointment = appointmentData.get();

			_appointment.setDate_app(new Date());
			_appointment.setDescription(appointment.getDescription());

			return new ResponseEntity<>(iAppointmentRepository.save(_appointment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			iAppointmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

