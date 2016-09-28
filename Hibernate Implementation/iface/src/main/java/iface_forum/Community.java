package iface_forum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import iface_forum.dao.BaseEntity;

@Entity
public class Community implements BaseEntity {
	@Transient
	Scanner input = new Scanner(System.in);

	@Id
	@GeneratedValue
	private int idCommunity;

	private int idCreator;

	private String title;

	private String description;

	@OneToMany
	@JoinTable(name = "com_topics", joinColumns = {
			@JoinColumn(name = "com_id", referencedColumnName = "idCommunity") }, inverseJoinColumns = {
					@JoinColumn(name = "topic_id", referencedColumnName = "id") })
	List<TopicsCommon> topics = new ArrayList<TopicsCommon>();

	@OneToMany
	@JoinTable(name = "com_questions", joinColumns = {
			@JoinColumn(name = "com_id", referencedColumnName = "idCommunity") }, inverseJoinColumns = {
					@JoinColumn(name = "quest_id", referencedColumnName = "id") })
	List<Questions> questions = new ArrayList<Questions>();

	@OneToMany
	@JoinTable(name = "com_article", joinColumns = {
			@JoinColumn(name = "com_id", referencedColumnName = "idCommunity") }, inverseJoinColumns = {
					@JoinColumn(name = "art_id", referencedColumnName = "id") })
	List<Article> articles = new ArrayList<Article>();

	@ElementCollection(fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@CollectionTable(name = "community_members", joinColumns = @JoinColumn(name = "community_id"))
	@Column(name = "member_id")
	private List<Integer> members = new ArrayList<Integer>();

	@ElementCollection(fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@CollectionTable(name = "community_permissions", joinColumns = @JoinColumn(name = "community_id"))
	@Column(name = "permission_id")
	private List<Integer> permission = new ArrayList<Integer>();
	
	public Community(){}
	
	public Community(int idCom, int idC, String tit, String desc) {
		this.idCommunity = idCom;
		this.idCreator = idC;
		this.title = tit;
		this.description = desc;
	}

	public void CreateTopic(int user) {

		int id = 0; // Determinar um modo de definir o id de cada t�pico
		String title;
		String description;

		System.out.printf("\nTitle: ");
		title = input.nextLine();

		System.out.printf("\nDescription: ");
		description = input.nextLine();

		TopicsCommon newTopic = new TopicsCommon(id, idCommunity, user, title, description);
		this.topics.add(newTopic);

	}

	public void CreateQuestion(int user) {

		int id = 0; // Determinar um modo de definir o id de cada t�pico
		String title;
		String description;

		System.out.printf("\nTitle: ");
		title = input.nextLine();

		System.out.printf("\nQuestion: ");
		description = input.nextLine();

		Questions newQuestion = new Questions(id, idCommunity, user, title, description);
		this.questions.add(newQuestion);

	}

	public void CreateArticle(int user) {

		int id = 0; // Determinar um modo de definir o id de cada t�pico
		String title;
		String description;
		String text;

		System.out.printf("\nTitle: ");
		title = input.nextLine();

		System.out.printf("\nDescription: ");
		description = input.nextLine();

		System.out.printf("\nText: \n");
		text = input.nextLine();

		Article newArticle = new Article(id, idCommunity, user, title, description, text);
		this.articles.add(newArticle);

	}

	public List<TopicsCommon> myTopics() {
		return this.topics;
	}

	public List<Questions> myQuestions() {
		return this.questions;
	}

	public List<Article> myArticles() {
		return this.articles;
	}

	public void setIdCreator(int id) {
		this.idCreator = id;
	}

	public void setId(int id) {
		this.idCommunity = id;
	}

	public void setTitle(String tit) {
		this.title = tit;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public void addMember(int idUser) {
		this.members.add(idUser);
	}

	public void addPermission(int idUser) {
		this.permission.add(idUser);
	}

	public void removeMember(int idUser) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i) == idUser) {
				members.remove(i);
			}
		}
	}

	public void removePermission(int idUser) {
		for (int i = 0; i < permission.size(); i++) {
			if (permission.get(i) == idUser) {
				permission.remove(i);
			}
		}
	}

	public int getIdCreator() {
		return this.idCreator;
	}

	public Integer getId() {
		return this.idCommunity;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public List<Integer> getMembers() {
		return this.members;
	}

	public List<Integer> getPermissions() {
		return this.permission;
	}
}
