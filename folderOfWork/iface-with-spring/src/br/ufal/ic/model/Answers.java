package br.ufal.ic.model;

public class Answers {

	int id;
	int idType;
	int idForum;
	int user;
	String comment;
	
	public Answers(int ID, int IDTYPE, int IDFORUM, int USER, String COMMENT){
		this.id = ID;
		this.idType = IDTYPE;
		this.idForum = IDFORUM;
		this.user = USER;
		this.comment = COMMENT;
	}
	
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
