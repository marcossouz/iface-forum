package br.com.iface.forum.controller;
import br.com.iface.forum.model.Comment;

public class CommentController {

	public void newComment(int id, int idArticle, int idForum, int user, String comment){
		Comment newComment = new Comment();
		newComment.setComment(comment);
		newComment.setId(id);
		newComment.setIdForum(idForum);
		newComment.setUser(user);
		newComment.setIdQuestion(idArticle);
		newComment.setTypeOfComment(0);
	}
	
	public void newCommentResponse(int id, int idArticle, int idForum, int user, String comment){
		Comment newComment = new Comment();
		newComment.setComment(comment);
		newComment.setId(id);
		newComment.setIdForum(idForum);
		newComment.setUser(user);
		newComment.setIdQuestion(idArticle);
		newComment.setTypeOfComment(1);
	}

}
