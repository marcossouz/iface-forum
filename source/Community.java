package iface_forum;

import java.util.ArrayList;
import java.util.Scanner;

public class Community {
	
	Scanner input = new Scanner(System.in);
	private String title;
	private String description;
	private int idCommunity;
	private int idCreator;
	ArrayList<TopicsCommon> topics = new ArrayList<TopicsCommon>();
	ArrayList<Questions> questions = new ArrayList<Questions>();
	ArrayList<Article> articles = new ArrayList<Article>();
    private ArrayList<Integer> members = new ArrayList<Integer>();
	private ArrayList<Integer> permission = new ArrayList<Integer>();
	
	public Community(int idCom, int idC, String tit, String desc){
		this.idCommunity = idCom;
		this.idCreator = idC;
		this.title = tit;
		this.description = desc;
	}
	
	public void CreateTopic(int user){
		
		int id = 0; //Determinar um modo de definir o id de cada tópico
		String title;
		String description;
		
		System.out.printf("\nTitle: ");
		title = input.nextLine();
		
		System.out.printf("\nDescription: ");
		description = input.nextLine();
		
		TopicsCommon newTopic = new TopicsCommon(id, idCommunity, user, title, description);
		this.topics.add(newTopic);
		
	}
	
	public void CreateQuestion(int user){
		
		int id = 0; //Determinar um modo de definir o id de cada tópico
		String title;
		String description;
		
		System.out.printf("\nTitle: ");
		title = input.nextLine();
		
		System.out.printf("\nQuestion: ");
		description = input.nextLine();
		
		Questions newQuestion = new Questions(id, idCommunity, user, title, description);
		this.questions.add(newQuestion);
		
	}
	
	public void CreateArticle(int user){
		
		int id = 0; //Determinar um modo de definir o id de cada tópico
		String title;
		String description;
		String text;
		
		System.out.printf("\nTitle: ");
		title = input.nextLine();
		
		System.out.printf("\nDescription: ");
		description = input.nextLine();
		
		System.out.printf("\nText: \n");
		text = input.nextLine();
		
		Article newArticle = new Article(id, idCommunity, user, title, description,text);
		this.articles.add(newArticle);
		
	}
	
	public ArrayList<TopicsCommon> myTopics(){
		return this.topics;
	}
	
	public ArrayList<Questions> myQuestions(){
		return this.questions;
	}
	
	public ArrayList<Article> myArticles(){
		return this.articles;
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
