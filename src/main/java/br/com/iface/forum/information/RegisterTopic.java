package br.com.iface.forum.information;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.model.User;

public class RegisterTopic {

	private int idCommunity;
	private TopicCommon topic;
	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}	
	public User getUser() {
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
