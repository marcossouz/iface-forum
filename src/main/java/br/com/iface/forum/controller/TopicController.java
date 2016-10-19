package br.com.iface.forum.controller;
import br.com.iface.forum.model.Community;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.service.TopicService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TopicController {
	
	@Autowired
	TopicService topicService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/topic",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicCommon> SendTopic(@RequestBody int idTopic){
		TopicCommon topic = TopicService.sendTopic(idTopic);
		return new ResponseEntity<>(topic,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/topics", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TopicCommon>> allTopics(@RequestBody Community community){
		ArrayList<TopicCommon> topics = topicService.allTopics(community);
		return new ResponseEntity<>(topics,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topic",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTopic(@RequestBody TopicCommon topic){
		topicService.addTopic(topic);
	}

}
