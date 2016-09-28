package iface_forum;

import javax.persistence.Entity;

@Entity
public class AnswersQuestion extends Answers{

	private int like;
	private int noLike;
	
	public AnswersQuestion(){}
	
	public AnswersQuestion(int ID, int IDQUESTION, int IDFORUM, int USER, String COMMENT) {
		super(ID, IDQUESTION, IDFORUM, USER, COMMENT);
	}
		
	public void addLike(){
		this.like = this.like + 1;
	}
	
	public void removeLike(){
		this.like = this.like - 1;
	}
	
	public void addNoLike(){
		this.noLike = this.noLike + 1;
	}
	
	public void removeNoLike(){
		this.noLike = this.noLike - 1;
	}
	
	public void setLike(int l){
		this.like = l;
	}
	
	public void setNoLike(int n){
		this.noLike = n;
	}
	
	public int getLike(){
		return this.like;
	}
	
	public int getNoLike(){
		return this.noLike;
	}

}
