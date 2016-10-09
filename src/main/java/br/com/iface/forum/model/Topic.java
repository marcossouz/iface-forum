package br.com.iface.forum.model;

public class Topic{

	private int id;
	private int idForum;
	private int user;
	private String title;
	private String description;
	
	public void setId(int i){
		this.id = i;
	}
	
	public void setUser(int u){
		this.user = u;
	}
	
	public void setIdForum(int idF){
		this.idForum = idF;
	}
	
	public void setTitle(String tit){
		this.title = tit;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getUser(){
		return this.user;
	}
	
	public int getIdForum(){
		return this.idForum;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}	
}
