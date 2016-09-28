package iface_forum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import iface_forum.dao.CommunityDAO;

public class GerenciadorComunidade {

	public static Scanner input = new Scanner(System.in);
	private static int idCommunity = 1;
	static int optionT = -1;
	static int optionQ = -1;
	static int optionA = -1;
	private static ArrayList<Community> communities = new ArrayList<Community>();
	
	public static void createCommunity(int idCreator){
		String title,description;
		
		System.out.println("\n\n---Creating a community---\n");
		System.out.print("Title: ");
		title = input.nextLine();
		System.out.print("Description: ");
		description = input.nextLine();
				
		Community auxCommunity = new Community(idCommunity, idCreator,title,description);
		CommunityDAO temp = new CommunityDAO();
		temp.salvar(auxCommunity);
		communities.add(auxCommunity);
		idCommunity++;
		
		System.out.println("Successfully create community!");
	}
	
	public static ArrayList<Community> communitiesByIdUser(int idUser){
		ArrayList<Community> myCommunities = new ArrayList<Community>();
		for(int i=0;i<idCommunity-1;i++){
			if(communities.get(i).getIdCreator() == idUser){
				myCommunities.add(communities.get(i));
			}
		}
		return myCommunities;
	}
	
	public static ArrayList<Community> searchOthersCommunities(int idUser){
		ArrayList<Community> others = new ArrayList<Community>();
		ArrayList<Community> myCommunities = communitiesByIdUser(idUser);
		boolean aux;
		
		for(int i=0;i<communities.size();i++){
			aux = false;
			for(int j=0;j<myCommunities.size();j++){
				if(myCommunities.get(j).getId() == communities.get(i).getId()){
					aux = true;
					break;
				}
			}
			if(!aux){
				others.add(communities.get(i));
			}
		}
		return others;
	}
	
	public static int othersCommunities(int idUser){
		ArrayList<Community> others = searchOthersCommunities(idUser);
		int option;
		
		if(others.isEmpty() == false){			
			System.out.println("\n\n---My Communities---\n");
			for(int i=0;i<others.size();i++){
				System.out.printf("%d. %s",i+1,others.get(i).getTitle());
			}
			System.out.print("\n\nOption: ");
			option = input.nextInt();
			return option;		
		}else{
			System.out.println("\nYou do not have registered communities!");
		}
		return -1;		
	}
	
	public static List<Integer> allPermissions(int idCommunity){
		idCommunity--;
		return communities.get(idCommunity).getPermissions();
	}
	
	public boolean permissionById(int idCommunity, int idUser){
		List<Integer> myPermissions = communities.get(idCommunity).getPermissions();
		
		for(int i=0;i<myPermissions.size();i++){
			if(myPermissions.get(i)== idUser){
				return true;
			}
		}		
		return false;
	}
	
	public static boolean creatorById(int idUser){
		for(int i=0;i<idCommunity-1;i++){
			if(communities.get(i).getIdCreator() == idUser){
				return true;
			}
		}
		return false;
	}
	
	public static void myCommunities(int idUser){
		ArrayList<Community> my = communitiesByIdUser(idUser);
		int option;
		
		if(my.size()>=1){			
			System.out.println("\n\n---My Communities---\n");
			for(int i=0;i<my.size();i++){
				System.out.printf("%d. %s\n",i+1,my.get(i).getTitle());
			}
			System.out.print("\n\nOption: ");
			option = input.nextInt();
			InterfaceTeste.communityInput(option,idUser);
			
		}else{
			System.out.println("\nYou do not have registered communities!");
		}
		
	}
	
	public static Article allArticles(int index){
		index=index-1;
		for(int i=0;i<communities.get(index).myArticles().size();i++){
			System.out.printf("%d. %s\n",i+1,communities.get(index).articles.get(i).getTitle());
		}
		System.out.print("Option: ");
		optionA = input.nextInt();
		return communities.get(index).articles.get(optionA-1);
	}
	
	public static TopicsCommon allTopics(int index){
		index=index-1;
		for(int i=0;i<communities.get(index).myTopics().size();i++){
			System.out.printf("%d. %s\n",i+1,communities.get(index).topics.get(i).getTitle());					
		}	
		System.out.print("Option: ");
		optionT = input.nextInt();	
		return communities.get(index).topics.get(optionT-1);
	}
	
	public static Questions allQuestions(int index){
		index=index-1;
		for(int i=0;i<communities.get(index).myQuestions().size();i++){
			System.out.printf("%d. %s\n",i+1,communities.get(index).questions.get(i).getTitle());						
		}	
		System.out.print("Option: ");
		optionQ = input.nextInt();	
		return communities.get(index).questions.get(optionQ-1);
	}
	
	public static Community returnCommunity(int idCommunity){
		return communities.get(idCommunity-1);
	}
	
	public static void addPermission(int index, int idUser){
		index--;
		communities.get(index).addPermission(idUser);
	}
	
	public static void removePermission(int index, int idUser){
		index--;
		communities.get(index).removePermission(idUser);
	}
	
	public static void addArticle(int index,int idUser){
		index--;
		communities.get(index).CreateArticle(idUser);
	}
	
	public static void addTopic(int index, int idUser){
		index--;
		communities.get(index).CreateTopic(idUser);
	}
	
	public static void addQuestion(int index, int idUser){
		index--;
		communities.get(index).CreateQuestion(idUser);
	}
	
	public static int getOptionA(){
		return optionA;
	}
	
	public static int getOptionQ(){
		return optionQ;
	}
	
	public static int getOptionT(){
		return optionT;
	}
}
