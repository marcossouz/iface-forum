package br.com.iface.forum.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private static int id;
	private String name;
	private static List<Integer> communities;
		
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
	public List<Integer> getCommunities() {
		return communities;
	}
	public void setCommunities(List<Integer> communities) {
		User.communities = communities;
	}
	public Integer getCommunity(int id){
		return communities.get(id);
	}
}
