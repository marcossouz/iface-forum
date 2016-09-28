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
public class Questions extends Topics{
	
	@Transient
	static Scanner input = new Scanner(System.in);
	
	@OneToMany
	@JoinTable(name = "question_answersq", joinColumns = {
			@JoinColumn(name = "question_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "answer_id", referencedColumnName = "id") })	
	private List<AnswersQuestion> answers = new ArrayList<AnswersQuestion>();
	
	public Questions(){
		super();
	}
	
	public Questions(int id, int idF, int user, String title, String description){
		super(id,idF,user,title,description);
	}
	
	public void printQuestion(int idQuestion,int idCommunity,int idUser){
		System.out.printf("\n\nTitle: %s\n",this.getTitle());
		System.out.printf("Creator: %d\n", this.getUser());
		System.out.printf("Description: %s\n\n",this.getDescription());
		 if(answers.size()!=0){
			 System.out.println("Comments\n");
			 for(int i=0;i<answers.size();i++){
				 System.out.printf("User %d\n%s\n",answers.get(i).getUser(),answers.get(i).getComment());				 			 
			 }
		}
		 System.out.print("\n Add Comment (Y/N): ");
		 String option = input.nextLine();
		 if(option.equals("Y") || option.equals("y")){
			 System.out.println("Comment:");
			 String comment = input.nextLine();
			 newAnswer(answers.size(),idQuestion,idCommunity,idUser,comment);
		 }
		 InterfaceTeste.questionIn(idCommunity, idUser);		
	}
	
	public void newAnswer(int id, int idArticle, int idForum, int user, String comment){
		AnswersQuestion newAnswer = new AnswersQuestion(id,idArticle,idForum,user, comment);
		answers.add(newAnswer);
	}
	
	public List<AnswersQuestion> getAnswers(){
		return this.answers;
	}
}
