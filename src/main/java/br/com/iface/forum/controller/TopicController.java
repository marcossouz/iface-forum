package br.com.iface.forum.controller;
import br.com.iface.forum.information.InformationTopic;
import br.com.iface.forum.information.RegisterTopic;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.model.User;
import java.util.ArrayList;
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

	@RequestMapping(method = RequestMethod.GET,value = "/topic",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicCommon> SendTopic(@RequestBody InformationTopic topicInformations){
		TopicCommon topic = User.sendTopic(topicInformations.getIdCommunity(), topicInformations.getIdTopic());
		return new ResponseEntity<>(topic,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/topics", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TopicCommon>> allTopics(@RequestBody InformationTopic topicInformation){
		ArrayList<TopicCommon> topics = User.allTopics(topicInformation.getIdCommunity());
		return new ResponseEntity<>(topics,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topic",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTopic(@RequestBody RegisterTopic topic){
		User.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/myTopics", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<TopicCommon>> myTopics(@RequestBody InformationTopic information){
		ArrayList<TopicCommon> topics = User.myTopics(information.getIdCommunity());
		return new ResponseEntity<>(topics,HttpStatus.OK);
	}	
	

}
