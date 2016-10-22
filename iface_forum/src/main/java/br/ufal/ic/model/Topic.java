package br.ufal.ic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Topic {
	
	@Id
	@GeneratedValue
	private Integer idTopic;
	
	private String title;
	private String content;
	
	//Pode ser: discussão, tutorial, pergunta.
	private String type;
	
	private Integer id_community;
	
	
	public Integer getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(Integer idTopic) {
		this.idTopic = idTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId_community() {
		return id_community;
	}

	public void setId_community(Integer id_community) {
		this.id_community = id_community;
	}

	
	
	

}
