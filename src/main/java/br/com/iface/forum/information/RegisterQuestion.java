package br.com.iface.forum.information;
import br.com.iface.forum.model.Question;

public class RegisterQuestion {

	private int idCommunity;
	private Question question;
	
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
}
