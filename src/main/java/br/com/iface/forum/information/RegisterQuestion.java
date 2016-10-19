package br.com.iface.forum.information;
import br.com.iface.forum.model.Question;
import br.com.iface.forum.model.User;

public class RegisterQuestion {

	private int idCommunity;
	private Question question;
	private User user;	
	
	public User getUser() {
		return user;
	}
	public int getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
