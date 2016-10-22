package br.com.iface.forum.model;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Community {

	@Id
	@GeneratedValue
	private int idCommunity;
	private String title;
	private String description;
	private int idCreator;
    private ArrayList<Integer> members = new ArrayList<Integer>();
	private ArrayList<Integer> permission = new ArrayList<Integer>();
	
		
	public void setIdCreator(int id){
		this.idCreator = id;
	}
	
	public void setIdCommunity(int id){
		this.idCommunity = id;
	}
	
	public void setTitle(String tit){
		this.title = tit;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public void addMember(int idUser){
		this.members.add(idUser);
	}
	
	public void addPermission(int idUser){
		this.permission.add(idUser);
	}
	
	public void setMembers(ArrayList<Integer> newMembers){
		this.members = newMembers;
	}
	
	public void setPermission(ArrayList<Integer> newPermissions){
		this.permission = newPermissions;
	}
	
	public int getIdCreator(){
		return this.idCreator;
	}
	
	public int getIdCommunity(){
		return this.idCommunity;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public ArrayList<Integer> getMembers(){
		return this.members;
	}
	
	public ArrayList<Integer> getPermissions(){
		return this.permission;
	}
}
