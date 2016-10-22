package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.iface.forum.model.Question;
import br.com.iface.forum.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	
	public void removeQuestion(int question){
		questionRepository.delete(question);
	}
	
	public Question sendQuestion(int idQuestion){
		return questionRepository.findOne(idQuestion);
	}
	
	public ArrayList<Question> allQuestions(){
		ArrayList<Question> question = (ArrayList<Question>) questionRepository.findAll();
		return question;
	}
	
	public ArrayList<Question> allQuestion(int idCommunity){
		ArrayList<Question> question = allQuestions();
		ArrayList<Question> resp = new ArrayList<Question>();
		for(int i=0;i<question.size();i++){
			if(question.get(i).getIdCommunity()==idCommunity){
				resp.add(question.get(i));
			}
		}
		return resp;
	}
	
	public ArrayList<Question> questionByCreator(int idCreator,int community){
		ArrayList<Question> question = new ArrayList<Question>();
		ArrayList<Question> questionCommunity = allQuestion(community);
		for(int i=0;i<questionCommunity.size();i++){
			if(questionCommunity.get(i).getIdCreator() == idCreator){
				question.add(questionCommunity.get(i));
			}
		}
		return question;
	}
}
