package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Community {
 
      @Id 
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      @Column(name="title", nullable=false, unique=true)
      private String title; 
      
      @Column(name="manager", nullable=false)
      private String manager;
      
      @Column(name="category", nullable=false)
      private String category;
      
      @Column(name="description", nullable=true)
      private String description;
      
      public Long getId() {
            return id;
      }
      
      public void setId(Long id) {
            this.id = id;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
 
} 