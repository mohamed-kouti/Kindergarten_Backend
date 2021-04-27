package tn.esprit.spring.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tn.esprit.spring.entity.User;

import tn.esprit.spring.service.interfaces.IUserService;
//@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@Controller
@CrossOrigin
public class FrontController {

	@Autowired
	IUserService userser;

	@RequestMapping("/")
	public String viewHomePage() {
		return "chat";
	}

	@GetMapping("/registration/{userName}")
	public ResponseEntity<Void> register(@PathVariable String userName) {

		User user = userser.findByUserName(userName);

		return ResponseEntity.ok().build();

	}

	
	@GetMapping("/fetchAllUsers")
	@ResponseBody
	public Set<String> fetchAll() {
		return userser.findAllByName();
	}
	

}
