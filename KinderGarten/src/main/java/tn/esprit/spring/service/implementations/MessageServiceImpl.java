package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Message;
import tn.esprit.spring.repository.IMessageRepository;
import tn.esprit.spring.service.interfaces.IMessageService;

public class MessageServiceImpl implements IMessageService {

	@Autowired
	IMessageRepository messagerep;

	@Override
	public void addMessage(Message m) {
		messagerep.save(m);

	}

	@Override
	public void updateMessage(Message m) {
		messagerep.save(m);

	}

	@Override
	public void deleteMessage(Message m) {
		messagerep.delete(m);

	}

	@Override
	public List<Message> getAllMessage() {

		return (List<Message>) messagerep.findAll();
	}

}
