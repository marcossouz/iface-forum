package iface_forum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import iface_forum.dao.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "ANS_SEQ", sequenceName = "ANS_SEQ", allocationSize = 1, initialValue = 1)
public class Answers implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANS_SEQ")
	int id;
	int idType;
	int idForum;
	int user;
	String comment;
	
	public Answers(){}
	
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
	
	public Integer getId(){
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
