package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
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
		p.setActif(false);
		userser.addPrent(p);
	}
	
	@PostMapping("/addadmin")
	@ResponseBody
	public void addAdmin(@RequestBody Admin a) {
		a.setActif(true);
		userser.addAdmin(a);
	}

	@PostMapping("/addkdowner")
	@ResponseBody
	public void addKdowner(@RequestBody KinderGarten_owner k) {
		k.setActif(false);
		userser.addKindergarten(k);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@DeleteMapping("/deleteuser/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") int id) {
		userser.deleteUser(id);
	}
	
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/updateparent")
	@ResponseBody
	public void updateParent(@RequestBody Parent p) {
		userser.updateParent(p);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/updatekgonwer")
	@ResponseBody
	public void updateKgowner(@RequestBody KinderGarten_owner k) {
		userser.updateKgowner(k);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/allparent")
	@ResponseBody
	public List<User> getAll() {
		return userser.getAllParents();
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/alladmin")
	@ResponseBody
	public List<User> getAllAdmin() {
		return userser.getAllAdmins();
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/updateadmin")
	@ResponseBody
	public void updateAdmin(@RequestBody Admin a) {
		userser.updateAdmin(a);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/blockuser/{id}")
	@ResponseBody
	public void blockUser(@PathVariable("id") int id) {
		userser.BlockUser(id);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/actifuser/{id}")
	@ResponseBody
	public void actifUser(@PathVariable("id") int id) {
		userser.ActifUser(id);
	}
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PostMapping("/login/{mail}/{pass}")
	@ResponseBody
	public User Login(@PathVariable("mail")String mail,@PathVariable("pass")String pass) {
		return userser.getUserByLogin(mail, pass);
	}
	
}
