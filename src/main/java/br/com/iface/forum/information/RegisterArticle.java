package br.com.iface.forum.information;
import br.com.iface.forum.model.Article;

public class RegisterArticle {
	
	private int idCommunity;
	private Article article;
	private int user;
	
	public void setUser(int user){
		this.user = user;
	}
	
	public int getUser(){
		return user; 
	}
	
	public int getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}	

}
