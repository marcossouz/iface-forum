package br.com.iface.forum.information;

import br.com.iface.forum.model.User;

public class InformationTopic {
	
	private User user;
	private int idCommunity;
	private int idTopic;
	
	public User getUser() {
		return user;
	}
	public int getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(int idCommunity) {
		this.idCommunity = idCommunity;
	}
	public int getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}
	

}
