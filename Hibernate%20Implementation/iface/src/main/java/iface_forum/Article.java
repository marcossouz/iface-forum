package iface_forum;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Article extends Topics{
	
	@Transient
	static Scanner input = new Scanner(System.in);
	
	private String text;
	
	@OneToMany
	@JoinTable(name = "article_comments", joinColumns = {
			@JoinColumn(name = "art_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "comment_id", referencedColumnName = "id") })		
	private List<Comments> comments = new ArrayList<Comments>();
	
	public Article(){
		super();
	}
	
	public Article( int idF, int user, String title, String description, String txt){
		super(idF,user,title,description);
		this.text = txt;
	}	
	
	public void newComment(int id, int idArticle, int idForum, int user, String comment){
		Comments newComment = new Comments(id,idArticle,idForum,user, 0, comment);
		comments.add(newComment);
	}
	
	public void newCommentResponse(int id, int idArticle, int idForum, int user, String comment){
		Comments newComment = new Comments(id,idArticle,idForum,user, 1, comment);
		comments.add(newComment);
	}
	
	public void printArticle(int idArticle,int idUser,int idCommunity){
		 System.out.printf("\n\nTitle: %s\n",this.getTitle());
		 System.out.printf("Creator: %d\n",this.getUser());
		 System.out.printf("Description: %s\n\n",this.getDescription());
		 System.out.printf("%s\n\n",this.getText());
		 if(comments.size()!=0){
			 System.out.println("Comments\n");
			 for(int i=0;i<comments.size();i++){
				 if(comments.get(i).getTypeOfComment()== 0)
					 System.out.printf("User %d\n%s\n\n",comments.get(i).getUser(),comments.get(i).getComment());
				 else
					 System.out.printf("\tUser %d\n\t%s\n\n",comments.get(i).getUser(),comments.get(i).getComment());					 
			 }
		 }
		 System.out.print("\n Add Comment (Y/N): ");
		 String option = input.nextLine();
		 if(option.equals("Y") || option.equals("y")){
			 System.out.println("Comment:");
			 String comment = input.nextLine();
			 newComment(comments.size(),idArticle,idCommunity,idUser,comment);
		 }
		 InterfaceTeste.articleIn(idCommunity, idUser);
	}
	
	public List<Comments> getComments(){
		return this.comments;
	}
	
	public void setText(String txt){
		this.text = txt;
	}
	
	public String getText(){
		return this.text;
	}
}
