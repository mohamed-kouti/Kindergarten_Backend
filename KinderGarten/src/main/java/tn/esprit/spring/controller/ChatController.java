package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.ICommentService;
import tn.esprit.spring.service.interfaces.IMessageService;
import tn.esprit.spring.service.interfaces.IPostService;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

	@Autowired
	IPostService postser;
	@Autowired
	ICommentService commentser;
	@Autowired
	IMessageService messageser;
	@Autowired
	IUserRepository userServiceInterface;
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@PostMapping("/add-post")
	@ResponseBody
	public void addPost(@RequestBody Post p) {
		postser.addPost(p);
	}

	@PutMapping("/update-post")
	@ResponseBody
	public void updatePost(@RequestBody Post p) {
		postser.updatePost(p);
	}

	@DeleteMapping("/delete-post/{postid}")
	@ResponseBody
	public void deletePost(@PathVariable("postid") int id) {
		postser.deletePost(id);
	}

	@PostMapping("/add-comment")
	@ResponseBody
	public void addComment(@RequestBody Comment c) {
		commentser.addComment(c);
	}

	@PutMapping("/update-comment")
	@ResponseBody
	public void updateComment(@RequestBody Comment c) {
		commentser.updateComment(c);
	}

	@DeleteMapping("/delete-comment/{commentid}")
	@ResponseBody
	public void deleteComment(@PathVariable("commentid") int id) {
		commentser.deleteComment(id);
	}

	@PostMapping("/add-message")
	@ResponseBody
	public void addMessage(@RequestBody Message m) {
		messageser.addMessage(m);
	}

	@PutMapping("/update-message")
	@ResponseBody
	public void updateMessage(@RequestBody Message m) {
		messageser.updateMessage(m);
	}

	@DeleteMapping("/delete-message/{messageid}")
	@ResponseBody
	public void deleteMessage(@PathVariable("messageid") int id) {
		messageser.deleteMessage(id);
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
		User destination = userServiceInterface.findByFname(to);
		User sender = userServiceInterface.findByFname(message.getFromLogin());

		messageser.addMessage(message);

		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

	}

	@GetMapping("/send/{msg}/{from}/{to}")
	public void send(@PathVariable("msg") String msg, @PathVariable("from") String from,
			@PathVariable("to") String to) {
		Message ms = new Message();
		ms.setFromLogin(from);

		ms.setMessage(msg);
		messageser.addMessage(ms);

		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, ms);
	}

}
