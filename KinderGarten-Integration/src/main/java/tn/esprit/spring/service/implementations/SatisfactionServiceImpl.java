package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Satisfaction;
import tn.esprit.spring.repository.ISatisfactionRepository;
import tn.esprit.spring.service.interfaces.ISatisfactionService;

@Service
public class SatisfactionServiceImpl implements ISatisfactionService {

	@Autowired
	ISatisfactionRepository satrep;
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void add_Satisfaction(Satisfaction s) {
		satrep.save(s);

	}
	

	@Override
	public List<Satisfaction> getAllSatisfaction(){
		return (List<Satisfaction>) satrep.findAll();
	}

	//http://localhost:56588/Satisfaction/Create
	@Override
	@Scheduled(cron = "0 15 10 1 * ?", zone = "Europe/Paris")
	public void sendSat() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo("medkouti20@gmail.com");
		message.setSubject("Ask for Voting ");
		message.setText(
				"Hi \n http://localhost:56588/Satisfaction/Create  ");

		// Send Message!
		this.emailSender.send(message);
	}
}
