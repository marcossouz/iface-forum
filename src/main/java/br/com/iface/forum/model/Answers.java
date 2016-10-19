package br.com.iface.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANS_SEQ")
	int id;
	int idType;
	int idForum;
	int user;
	String comment;
	
	public void setId(int i){
		this.id = i;
	}
	
	public void setIdQuestion(int idQues){
		this.idType = idQues;
	}
	
	public void setIdForum(int idFor){
		this.idForum = idFor;
	}
	
	public void setUser(int u){
		this.user = u;
	}
	
	public void setComment(String com){
		this.comment = com;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getIdQuestion(){
		return this.idType;
	}
	
	public int getIdForum(){
		return this.idForum;
	}
	
	public int getUser(){		
		return this.user;
	}
	
	public String getComment(){
		return this.comment;
	}
}
