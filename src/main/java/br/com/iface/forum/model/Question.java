package br.com.iface.forum.model;
import  java.util.ArrayList;

public class Question extends Topic{

	private ArrayList<AnswersQuestion> answers = new ArrayList<AnswersQuestion>();
	
	public void newAnswer(int id, int idArticle, int idForum, int user, String comment){
		AnswersQuestion newAnswer = new AnswersQuestion();
		newAnswer.setComment(comment);
		newAnswer.setId(id);
		newAnswer.setIdForum(idForum);
		newAnswer.setUser(user);
		newAnswer.setIdQuestion(idArticle);
		answers.add(newAnswer);
	}
	
	public ArrayList<AnswersQuestion> getAnswers(){
		return this.answers;
	}
}
