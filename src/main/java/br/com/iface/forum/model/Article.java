package br.com.iface.forum.model;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANS_SEQ")
	private int idTopic;
	private int idCreator;
	private String description;;
	private String title;
	private String text;
	private ArrayList<Integer> comments = new ArrayList<Integer>();
	
	public int getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}
	
	public void setText(String txt){
		this.text = txt;
	}
	
	public String getText(){
		return this.text;
	}
	
	public int getIdCreator() {
		return idCreator;
	}

	public void setComments(ArrayList<Integer> comments) {
		this.comments = comments;
	}

	public void newComment(Integer newComment){
		comments.add(newComment);
	}
	
	public void removeComment(Integer comment){
		comments.remove(comment);
	}
		
	public ArrayList<Integer> getComments(){
		return this.comments;
	}
}
