package br.com.iface.forum.controller;
import br.com.iface.forum.information.InformationTopic;
import br.com.iface.forum.information.RegisterQuestion;
import br.com.iface.forum.model.Question;
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
public class QuestionController {

	@RequestMapping(method = RequestMethod.GET,value = "/question",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Question> SendQuestion(@RequestBody InformationTopic Informations){
		Question question = User.sendQuestion(Informations.getIdCommunity(), Informations.getIdTopic());
		return new ResponseEntity<>(question,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/questions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Question>> allQuestions(@RequestBody InformationTopic Information){
		ArrayList<Question> questions = User.allQuestions(Information.getIdCommunity());
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/question",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addQuestion(@RequestBody RegisterQuestion question){
		User.addQuestion(question);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/myQuestions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Question>> myQuestions(@RequestBody InformationTopic information){
		ArrayList<Question> questions = User.myQuestions(information.getIdCommunity());
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}	
	
}
