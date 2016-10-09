package br.com.iface.forum.model;
import java.util.ArrayList;
import java.util.List;

import br.com.iface.forum.information.RegisterArticle;
import br.com.iface.forum.information.RegisterQuestion;
import br.com.iface.forum.information.RegisterTopic;

public class User {
	
	private static int id;
	private String name;
	private static List<Community> communities;
	
	public static ArrayList<Article> myArticles(int idCommunity){
		return communities.get(idCommunity).articleByCreator(id);
	}
	
	public static ArrayList<Question> myQuestions(int idCommunity){
		return communities.get(idCommunity).questionByCreator(id);
	}
	
	public static ArrayList<TopicCommon> myTopics(int idCommunity){
		return communities.get(idCommunity).topicByCreator(id);
	}
	
	public static void addArticle(RegisterArticle article){
		communities.get(article.getIdCommunity()).addArticle(article.getArticle());
	}
	
	public static void addQuestion(RegisterQuestion question){
		communities.get(question.getIdCommunity()).addQuestion(question.getQuestion());
	}
	
	public static void addTopic(RegisterTopic topic){
		communities.get(topic.getIdCommunity()).addTopic(topic.getTopic());
	}
	
	public static Article sendArticle(int idCommunity, int idArticle){
		return communities.get(idCommunity).sendArticle(idArticle);		
	}
	
	public static Question sendQuestion(int idCommunity, int idQuestion){
		return communities.get(idCommunity).sendQuestion(idQuestion);
	}
	
	public static TopicCommon sendTopic(int idCommunity, int idTopic){
		return communities.get(idCommunity).sendTopic(idTopic);
	}
	
	public static ArrayList<Article> allArticle(int idCommunity){
		return communities.get(idCommunity).allArticles();
	}
	
	public static ArrayList<Question> allQuestions(int idCommunity){
		return communities.get(idCommunity).allQuestions();
	}
	
	public static ArrayList<TopicCommon> allTopics(int idCommunity){
		return communities.get(idCommunity).allTopics();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		User.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Community> getCommunities() {
		return communities;
	}
	public void setCommunities(List<Community> communities) {
		User.communities = communities;
	}
}
