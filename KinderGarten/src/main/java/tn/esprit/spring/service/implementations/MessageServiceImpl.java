package tn.esprit.spring.service.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Message;
import tn.esprit.spring.repository.IMessageRepository;
import tn.esprit.spring.service.interfaces.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {

	@Autowired
	IMessageRepository messagerep;

	@Override
	public void addMessage(Message m) {
		String ch = m.getMessage();
		List<String> words = messagerep.getAllWord();
		for (int i = 0; i < words.size(); i++) {
			// System.out.println(words.get(i));
			if (ch.toUpperCase().contains(words.get(i).toUpperCase())) {

				ch = ch.toUpperCase().replaceAll(words.get(i).toUpperCase(), "****");

			}

		}
		m.setMessage(ch);
		m.setDate_msg(java.sql.Date.valueOf(LocalDate.now()));
		messagerep.save(m);

	}

	@Override
	public void updateMessage(Message m) {
		messagerep.save(m);

	}

	@Override
	public void deleteMessage(int id) {
		messagerep.deleteById(id);

	}

	@Override
	public List<Message> getAllMessage() {

		return (List<Message>) messagerep.findAll();
	}

	@Override
	public List<String> getAllword() {
		return messagerep.getAllWord();
	}

	@Override
	public List<Message> getAllMessageById(int id) {
		List<Message>msgs=(List<Message>)messagerep.findAll();
		List<Message>rslt=new ArrayList<Message>();
		
		for(int i=0;i<msgs.size();i++) {
			if(msgs.get(i).getUser().getId()==id)
				rslt.add(msgs.get(i));
		}
		return rslt;
	}

}
