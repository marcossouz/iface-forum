package br.com.iface.forum.model;
import java.util.ArrayList;

public class Community {

	private String title;
	private String description;
	private int idCommunity;
	private int idCreator;
	private int nArticles=0,nQuestions=0,nTopics=0;
	public ArrayList<TopicCommon> topics = new ArrayList<TopicCommon>();
	public ArrayList<Question> questions = new ArrayList<Question>();
	public ArrayList<Article> articles = new ArrayList<Article>();
    private ArrayList<Integer> members = new ArrayList<Integer>();
	private ArrayList<Integer> permission = new ArrayList<Integer>();
	
	public ArrayList<Article> articleByCreator(int creator){
		ArrayList<Article> article = new ArrayList<Article>();
		for(int i=0;i<articles.size();i++){
			if(articles.get(i).getUser() == creator){
				article.add(articles.get(i));
			}
		}
		return article;
	}
	
	public ArrayList<Question> questionByCreator(int creator){
		ArrayList<Question> question = new ArrayList<Question>();
		for(int i=0;i<questions.size();i++){
			if(questions.get(i).getUser() == creator){
				question.add(questions.get(i));
			}
		}
		return question;
	}
	
	public ArrayList<TopicCommon> topicByCreator(int creator){
		ArrayList<TopicCommon> topic = new ArrayList<TopicCommon>();
		for(int i=0;i<topics.size();i++){
			if(topics.get(i).getUser() == creator){
				topic.add(topics.get(i));
			}
		}
		return topic;
	}
	
	public void addArticle(Article article){
		nArticles++;
		article.setId(nArticles);
		articles.add(article);
	}
	
	public void addQuestion(Question question){
		nQuestions++;
		question.setId(nQuestions);
		questions.add(question);
	}
	
	public void addTopic(TopicCommon topic){
		nTopics++;
		topic.setId(nTopics);
		topics.add(topic);
	}
	
	public ArrayList<TopicCommon> myTopics(){
		return this.topics;
	}
	
	public ArrayList<Question> myQuestions(){
		return this.questions;
	} 
	
	public ArrayList<Article> myArticles(){
		return this.articles;
	}
	
	public ArrayList<Article> allArticles(){
		return articles;
	}
	
	public ArrayList<Question> allQuestions(){
		return questions;
	}
	
	public ArrayList<TopicCommon> allTopics(){
		return topics;
	}
	
	public Article sendArticle(int idArticle){
		return articles.get(idArticle);
	}
	
	public Question sendQuestion(int idQuestion){
		return questions.get(idQuestion);
	}
	
	public TopicCommon sendTopic(int idTopic){
		return topics.get(idTopic);
	}
	
	public void setIdCreator(int id){
		this.idCreator = id;
	}
	
	public void setIdCommunity(int id){
		this.idCommunity = id;
	}
	
	public void setTitle(String tit){
		this.title = tit;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}

	public void setnArticles(int nArticles) {
		this.nArticles = nArticles;
	}

	public void setnQuestions(int nQuestions) {
		this.nQuestions = nQuestions;
	}

	public void setnTopics(int nTopics) {
		this.nTopics = nTopics;
	}
	
	public void addMember(int idUser){
		this.members.add(idUser);
	}
	
	public void addPermission(int idUser){
		this.permission.add(idUser);
	}
	
	public void removeMember(int idUser){
		for(int i=0;i<members.size();i++){
			if(members.get(i) == idUser){
				members.remove(i);
			}
		}
	}
	
	public void removePermission(int idUser){
		for(int i=0;i<permission.size();i++){
			if(permission.get(i) == idUser){
				permission.remove(i);
			}
		}
	} 
	public int getnArticles() {
		return nArticles;
	}
	
	public int getnQuestions() {
		return nQuestions;
	}

	public int getnTopics() {
		return nTopics;
	}
	
	public int getIdCreator(){
		return this.idCreator;
	}
	
	public int getIdCommunity(){
		return this.idCommunity;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public ArrayList<Integer> getMembers(){
		return this.members;
	}
	
	public ArrayList<Integer> getPermissions(){
		return this.permission;
	}
}
