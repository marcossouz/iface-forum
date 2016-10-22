package br.com.iface.forum.controller;
import br.com.iface.forum.information.IdArticle;
import br.com.iface.forum.information.IdCommunity;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.service.TopicService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	TopicService topicService;
	
	@RequestMapping(method = RequestMethod.POST,value = "/topicS",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicCommon> SendTopic(@RequestBody IdArticle idTopic){
		TopicCommon topic = topicService.sendTopic(idTopic.getIdArticle());
		return new ResponseEntity<>(topic,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/topics", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TopicCommon>> allTopics(@RequestBody IdCommunity community){
		ArrayList<TopicCommon> topics = topicService.allTopics(community.getIdCommunity());
		return new ResponseEntity<>(topics,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topicA",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTopic(@RequestBody TopicCommon register){
		topicService.addTopic(register);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topicR",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeTopic(@RequestBody IdArticle topic){
		topicService.removeTopic(topic.getIdArticle());
	}
	
}
