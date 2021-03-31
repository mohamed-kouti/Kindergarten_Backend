package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
    public JavaMailSender emailSender;
	
	@GetMapping("/sendmail")
	public String sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("ikram.boussif@esprit.tn");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email from akram");

        // Send Message!
        this.emailSender.send(message);
		return "Email Sent";
	}
	

}
