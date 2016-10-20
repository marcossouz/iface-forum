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
	
	@Autowired
	CommunityService communityService;
	
	public void addQuestion(Question question,int idCommunity){
		Community community = communityService.sendCommunity(idCommunity);
		community.addQuestion(question.getIdTopic());
		communityService.addCommunity(community);
		questionRepository.save(question);
	}
	
	public void removeQuestion(int question,int idCommunity){
		Community community = communityService.sendCommunity(idCommunity);
		community.removeQuestion(question);
		communityService.addCommunity(community);
		questionRepository.delete(question);
	}
	
	public Question sendQuestion(int idQuestion){
		return questionRepository.findOne(idQuestion);
	}
	
	public ArrayList<Question> allQuestions(int idCommunity){
		Community community = communityService.sendCommunity(idCommunity);
		ArrayList<Integer> ids = community.allQuestions();
		ArrayList<Question> allquestion = new ArrayList<Question>();
		for(int i=0;i<ids.size();i++){
			allquestion.add(sendQuestion(ids.get(i)));
		}
		return allquestion;
	}
	
	public ArrayList<Question> questionByCreator(int idCreator,int community){
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
