package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iface.forum.model.Community;
import br.com.iface.forum.model.Question;
import br.com.iface.forum.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	
	public void removeQuestion(Question question){
		questionRepository.delete(question);
	}
	
	public Question sendQuestion(int idQuestion){
		return questionRepository.findOne(idQuestion);
	}
	
	public ArrayList<Question> allQuestions(Community community){
		ArrayList<Integer> ids = community.allQuestions();
		ArrayList<Question> allquestion = new ArrayList<Question>();
		for(int i=0;i<ids.size();i++){
			allquestion.add(sendQuestion(ids.get(i)));
		}
		return allquestion;
	}
	
	public ArrayList<Question> questionByCreator(int idCreator,Community community){
		ArrayList<Question> question = new ArrayList<Question>();
		ArrayList<Question> questionCommunity = allQuestions(community);
		for(int i=0;i<questionCommunity.size();i++){
			if(questionCommunity.get(i).getIdCreator() == idCreator){
				question.add(questionCommunity.get(i));
			}
		}
		return question;
	}
}
