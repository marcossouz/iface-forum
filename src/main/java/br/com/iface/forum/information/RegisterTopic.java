package br.com.iface.forum.information;
import br.com.iface.forum.model.TopicCommon;

public class RegisterTopic {

	private int idCommunity;
	private TopicCommon topic;
	private int user;
	
	public void setUser(int user) {
		this.user = user;
	}	
	public int getUser() {
		return user;
	}	
	public int getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}
	public TopicCommon getTopic() {
		return topic;
	}
	public void setTopic(TopicCommon topic) {
		this.topic = topic;
	}
	
	
}
