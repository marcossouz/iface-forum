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
@SequenceGenerator(name = "Q_SEQ", sequenceName = "Q_SEQ", allocationSize = 1, initialValue = 1)
public class Topics implements BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_SEQ")
	private int id;
	private int idForum;
	private int user;
	private String title;
	private String description;
	
	public Topics(){}
	
	public Topics( int idF, int u, String tit, String desc){
		this.user = u;
		this.idForum = idF;
		this.title = tit;
		this.description = desc;
	}
	
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
	
	public Integer getId(){
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
