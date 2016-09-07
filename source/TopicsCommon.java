package iface_forum;
import java.util.ArrayList;
import java.util.Scanner;

public class TopicsCommon extends Topics{
	
	static Scanner input =  new Scanner(System.in);
	private ArrayList<Answers> answers = new ArrayList<Answers>();
	
	public TopicsCommon(int id, int idF, int user, String title, String description){
		super(id,idF,user,title,description);
	}
	
	public void printTopic(int idTopic, int idCommunity, int idUser){
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
			 newAnswer(answers.size(),idTopic,idCommunity,idUser,comment);
		 }
		 InterfaceTeste.topicsIn(idCommunity, idUser);		
	}
	
	public void newAnswer(int id, int idTopic, int idForum, int user, String comment){
		AnswersQuestion newAnswer = new AnswersQuestion(id,idTopic,idForum,user, comment);
		answers.add(newAnswer);
	}
	
	public ArrayList<Answers> getAnswers(){
		return this.answers;
	}
}
