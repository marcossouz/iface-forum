package br.com.iface.forum.controller;
import br.com.iface.forum.information.IdArticle;
import br.com.iface.forum.information.IdCommunity;
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
	public ResponseEntity<Question> SendQuestion(@RequestBody IdArticle idQuestion){
		Question question = questionService.sendQuestion(idQuestion.getIdArticle());
		return new ResponseEntity<>(question,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/questions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Question>> allQuestions(@RequestBody IdCommunity community){
		ArrayList<Question> questions = questionService.allQuestion(community.getIdCommunity());
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/question",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addQuestion(@RequestBody Question question){
		questionService.addQuestion(question);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/questionR",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeQuestion(@RequestBody IdArticle question){
		questionService.removeQuestion(question.getIdArticle());
	}
	
}
