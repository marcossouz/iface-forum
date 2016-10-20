package br.com.iface.forum.controller;
import br.com.iface.forum.information.RTopic;
import br.com.iface.forum.information.RegisterQuestion;
import br.com.iface.forum.model.Question;
import br.com.iface.forum.service.QuestionService;
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
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/question",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Question> SendQuestion(@RequestBody int idQuestion){
		Question question = questionService.sendQuestion(idQuestion);
		return new ResponseEntity<>(question,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/questions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Question>> allQuestions(@RequestBody int community){
		ArrayList<Question> questions = questionService.allQuestions(community);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/question",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addQuestion(@RequestBody RegisterQuestion question){
		questionService.addQuestion(question.getQuestion(),question.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/questionR",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeQuestion(@RequestBody RTopic question){
		questionService.removeQuestion(question.getIdTopic(),question.getIdCommunity());
	}
	
}
