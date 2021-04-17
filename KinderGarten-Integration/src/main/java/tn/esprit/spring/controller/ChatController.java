package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Message;
import tn.esprit.spring.entity.PK_SAT;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.Satisfaction;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.ICommentService;
import tn.esprit.spring.service.interfaces.IMessageService;
import tn.esprit.spring.service.interfaces.IPostService;
import tn.esprit.spring.service.interfaces.ISatisfactionService;
import tn.esprit.spring.service.interfaces.IUserService;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping(path = "/chat")
@CrossOrigin("*")
public class ChatController {

	@Autowired
	IPostService postser;
	@Autowired
	ICommentService commentser;
	@Autowired
	IMessageService messageser;
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	ISatisfactionService satser;
	@Autowired
	IUserService userser;

	@PostMapping("/add-post/{id}")
	@ResponseBody
	public void addPost(@RequestBody Post p, @PathVariable("id") int id) {
		p.setUser(userser.getUserById(id));
		postser.addPost(p);
	}

	@PutMapping("/update-post/{id}")
	@ResponseBody
	public void updatePost(@RequestBody Post p,@PathVariable("id")int id) {
		p.setUser(userser.getUserById(id));
		postser.updatePost(p);
	}

	@DeleteMapping("/delete-post/{postid}")
	@ResponseBody
	public void deletePost(@PathVariable("postid") int id) {
		postser.deletePost(id);
	}

	@PostMapping("/add-comment/{id}")
	@ResponseBody
	public void addComment(@RequestBody Comment c, @PathVariable("id") int id) {
		c.setPost(postser.getPostbyId(id));
		commentser.addComment(c);
	}

	@PutMapping("/update-comment/{id}")
	@ResponseBody
	public void updateComment(@RequestBody Comment c,@PathVariable("id")int id) {
		c.setPost(postser.getPostbyId(id));
		commentser.updateComment(c);
	}

	@DeleteMapping("/delete-comment/{commentid}")
	@ResponseBody
	public void deleteComment(@PathVariable("commentid") int id) {
		commentser.deleteComment(id);
	}



	@GetMapping("/all-message")
	@ResponseBody
	public List<Message> getAllMessage() {
		return messageser.getAllMessage();
	}

	@GetMapping("/all-post")
	@ResponseBody
	public List<Post> getAllPost() {
		return postser.getAllPost();
	}

	@GetMapping("/all-comment")
	@ResponseBody
	public List<Comment> getAllComment() {
		return commentser.getAllComment();
	}

	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, Message message) {
		User destination = userser.findByUserName(to);
		User sender = userser.findByUserName(message.getFromLogin());

		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
		message.setUser(sender);
		message.setTo_user(destination.getId());
		messageser.addMessage(message);
	}

	@GetMapping("/send/{msg}/{from}/{to}")
	public String send(@PathVariable("msg") String msg, @PathVariable("from") String from,
			@PathVariable("to") String to) {
		Message ms = new Message();
		User destination = userser.findByUserName(to);
		User sender = userser.findByUserName(from);
		ms.setFromLogin(from);
		ms.setTo_user(destination.getId());
		ms.setMessage(msg);
		ms.setUser(sender);
		messageser.addMessage(ms);
		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, ms);
		return "Sended";
	}

	@GetMapping("/getmsgbyid/{id}")
	public List<Message> getAllMsgById(@PathVariable("id") int id) {
		return messageser.getAllMessageById(id);
	}

	@PostMapping("/addsat/{id_pa}/{id_ki}")
	@ResponseBody
	public void add_Sat(@PathVariable("id_pa") int idp, @PathVariable("id_ki") int idk, @RequestBody Satisfaction s) {
		PK_SAT pk = new PK_SAT();
		pk.setId_kindergarten(idk);
		pk.setId_parent(idp);
		s.setPk_sat(pk);
		satser.add_Satisfaction(s);

	}

	@GetMapping("/allsat")
	@ResponseBody
	public List<Satisfaction> getAllSat() {
		return satser.getAllSatisfaction();
	}
	
	@PutMapping("/like/{id}")
	@ResponseBody
	public void likePost(@PathVariable("id")int id) {
		postser.updateLike(id);
	}
	@PutMapping("/dislike/{id}")
	@ResponseBody
	public void dislikePost(@PathVariable("id")int id) {
		postser.updateDislike(id);
	}
}
