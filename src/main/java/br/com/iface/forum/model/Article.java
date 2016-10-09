package br.com.iface.forum.model;
import java.util.ArrayList;

public class Article extends Topic{

	private String text;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public void newComment(Comment newComment){
		comments.add(newComment);
	}
	
	public void newCommentResponse(Comment newComment){
		comments.add(newComment);
	}
		
	public ArrayList<Comment> getComments(){
		return this.comments;
	}
	
	public void setText(String txt){
		this.text = txt;
	}
	
	public String getText(){
		return this.text;
	}
}
