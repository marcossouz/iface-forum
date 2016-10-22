package br.ufal.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.model.Community;
import br.ufal.ic.model.Topic;
import br.ufal.ic.service.CommunityService;
import br.ufal.ic.service.TopicService;

@RestController
public class TopicController {
	
	@Autowired
	TopicService topicService;
	CommunityService comunService;
	
	// end points
	
	@RequestMapping(method=RequestMethod.POST, value="/topics", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic t){
		
		Topic topicCreated = topicService.register(t);
		
		return new ResponseEntity<Topic>(topicCreated, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/AllTopics", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Topic>> findAllTopics(){
		
		List<Topic> topics = topicService.findTopics();
		
		return new ResponseEntity<>(topics, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public ResponseEntity<Topic> deletarTopic(@PathVariable Integer id){
		
		Topic topicFound = topicService.findById(id);
		
		if(topicFound == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		topicService.deleteTopic(topicFound);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> updateTopic(@RequestBody Topic t){
		
		Topic topic = topicService.update(t);
		
		return new ResponseEntity<>(topic, HttpStatus.OK);
	}
	
	
	
	

}
