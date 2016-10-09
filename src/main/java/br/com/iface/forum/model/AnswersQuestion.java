package br.com.iface.forum.model;

public class AnswersQuestion extends Answers{

	private int like;
	private int noLike;
		
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
