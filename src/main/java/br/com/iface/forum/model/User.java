package br.com.iface.forum.model;
import java.util.List;

public class User {
	
	private static int id;
	private String name;
	private static List<Community> communities;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		User.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Community> getCommunities() {
		return communities;
	}
	public void setCommunities(List<Community> communities) {
		User.communities = communities;
	}
	public Community getCommunity(int id){
		return communities.get(id);
	}
}
