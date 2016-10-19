package br.com.iface.forum.model;
import  java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANS_SEQ")
	private int idTopic;
	private int idCreator;
	private String description;;
	private String title;
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	public int getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
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

	public void setAnswers(ArrayList<Integer> answers) {
		this.answers = answers;
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
}
