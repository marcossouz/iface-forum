package br.ufal.ic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Community {
	
	@Id
	@GeneratedValue
	private Integer idCommunity;
	
	private String title;
	private String description;
	private String category;
	
	public Integer getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(Integer idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
