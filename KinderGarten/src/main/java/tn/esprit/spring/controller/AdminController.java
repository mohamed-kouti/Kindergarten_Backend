package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.KinderGarten_owner;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.interfaces.IUserService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	@Autowired
	IUserService userser;
	@Autowired
    public JavaMailSender emailSender;

	@PostMapping("/addparent")
	@ResponseBody
	public void addParent(@RequestBody Parent p) {
		userser.addPrent(p);
	}

	@PostMapping("/addadmin")
	@ResponseBody
	public void addAdmin(@RequestBody Admin a) {
		userser.addAdmin(a);
	}

	@PostMapping("/addkdowner")
	@ResponseBody
	public void addKdowner(@RequestBody KinderGarten_owner k) {
		userser.addKindergarten(k);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") int id) {
		userser.deleteUser(id);
	}
	
	
	@PutMapping("/updateparent")
	@ResponseBody
	public void updateParent(@RequestBody Parent p) {
		userser.updateParent(p);
	}
	
	@PutMapping("/updatekgonwer")
	@ResponseBody
	public void updateKgowner(@RequestBody KinderGarten_owner k) {
		userser.updateKgowner(k);
	}
	
	@GetMapping("/allparent")
	@ResponseBody
	public List<User> getAll() {
		return userser.getAllParents();
	}
	
	@GetMapping("/alladmin")
	@ResponseBody
	public List<User> getAllAdmin() {
		return userser.getAllAdmins();
	}
	
	@PutMapping("/updateadmin")
	@ResponseBody
	public void updateAdmin(@RequestBody Admin a) {
		userser.updateAdmin(a);
	}
	
	@PutMapping("/blockuser/{id}")
	@ResponseBody
	public void blockUser(@PathVariable("id") int id) {
		userser.BlockUser(id);
	}
	
	@PutMapping("/actifuser/{id}")
	@ResponseBody
	public void actifUser(@PathVariable("id") int id) {
		userser.ActifUser(id);
	}
	
	@GetMapping("/sendmail")
	public String sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("mohamed.kouti@esprit.tn");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email from akram");
 
        // Send Message!
        this.emailSender.send(message);
		return "Email Sent";
	}
	
}
