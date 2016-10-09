package br.com.iface.forum.model;

public class Comment extends Answers{

	int typeOfComment; //type = 0 -> Comment and type = 1 -> Answer of Comment
			
	public void setTypeOfComment(int type){
		this.typeOfComment = type;
	}
	
	public int getTypeOfComment(){
		return this.typeOfComment;
	}

}
