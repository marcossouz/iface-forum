package br.ufal.ic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.ufal.ic.model.Topic;
import br.ufal.ic.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	TopicRepository topicRep;
	
	//CRUD
	
	public Topic register(Topic t){
		return topicRep.save(t);
	}
	
	public List<Topic> findTopics(){
		return topicRep.findAll();
	}
	
	
	public void deleteTopic(Topic t){
		topicRep.delete(t);
	}
	
	public Topic findById(Integer id){
		return topicRep.findOne(id);
	}
	
	public Topic update(Topic t){
		return topicRep.save(t);
	}

}
