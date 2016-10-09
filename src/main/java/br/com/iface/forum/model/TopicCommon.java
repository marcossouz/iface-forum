package br.com.iface.forum.model;
import java.util.ArrayList;

public class TopicCommon extends Topic{

	private ArrayList<Answers> answers = new ArrayList<Answers>();
		
	public void newAnswer(int id, int idTopic, int idForum, int user, String comment){
		AnswersQuestion newAnswer = new AnswersQuestion();
		newAnswer.setComment(comment);
		newAnswer.setId(id);
		newAnswer.setIdForum(idForum);
		newAnswer.setUser(user);
		newAnswer.setIdQuestion(idTopic);
		answers.add(newAnswer);
		answers.add(newAnswer);
	}
	
	public ArrayList<Answers> getAnswers(){
		return this.answers;
	}

}
