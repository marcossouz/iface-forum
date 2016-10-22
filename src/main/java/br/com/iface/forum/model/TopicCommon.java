package br.com.iface.forum.model;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TopicCommon{

	@Id
	@GeneratedValue
	private int topic;
	private int idCommunity;
	private int idCreator;
	private String description;
	private String title;
	private ArrayList<Integer> answers = new ArrayList<Integer>();

	public int getIdTopic() {
		return topic;
	}

	public void setIdTopic(int idTopic) {
		this.topic = idTopic;
	}

	public int getIdCreator() {
		return idCreator;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
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

	public void setAnswer(ArrayList<Integer> answer) {
		this.answers = answer;
	}

	public void newAnswer(Integer answer){
		answers.add(answer);
	}
	
	public void removeAnswer(Integer answer){
		answers.remove(answer);
	}
		
	public ArrayList<Integer> getAnswer(){
		return this.answers;
	}
	
	public int getIdCommunity() {
		return idCommunity;
	}

	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}

}
