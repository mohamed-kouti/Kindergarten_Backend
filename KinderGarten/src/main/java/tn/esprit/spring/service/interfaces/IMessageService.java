package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Message;

public interface IMessageService {

	public void addMessage(Message m);

	public void updateMessage(Message m);

	public void deleteMessage(int id);

	public List<Message> getAllMessage();
	public List<String>getAllword();
}
