package iface_forum;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceTeste {
	
	public static Scanner input = new Scanner(System.in);
	public static GerenciadorComunidade manager = new GerenciadorComunidade();
	public static int idUser = 1;
	
	public static void main(String[] args){
		
		int option;
		boolean exit = false;
		do{
			System.out.println("\n\n---Community---\n");
			System.out.println("Options:");
			System.out.println("1.Create Community");
			System.out.println("2.My Communities");
			System.out.println("3.Others Communities");
			System.out.println("4.Exit");
			option = input.nextInt();
			
			if(option == 1){
				GerenciadorComunidade.createCommunity(idUser);
			}else if (option == 2){
				GerenciadorComunidade.myCommunities(idUser);
			}else if(option == 3){
				othersCommunities(idUser);
			}
			else if(option == 4){
				exit = true;
			}
			
		}while(!exit);
	}
	
	public static void othersCommunities(int idUser){
		int option;
		option = GerenciadorComunidade.othersCommunities(idUser);
		if(option != -1)
			permissionsInput(option,idUser);
	}
	
	public static boolean communityInput(int idCommunity, int idUser){
		int option;
		Community community = GerenciadorComunidade.returnCommunity(idCommunity);
		System.out.printf("\n\n---%s---\n",community.getTitle());
		System.out.printf("Description: %s\n",community.getDescription());
		System.out.printf("Creator: %d\n\n",community.getIdCreator());
		System.out.println("Options: ");
		System.out.println("1. Articles");
		System.out.println("2. Topics");
		System.out.println("3. Questions");
		System.out.println("4. Solicitations");
		System.out.println("5. Return");
		option = input.nextInt();
		
		switch(option){
		case 1:
			articleIn(idCommunity,idUser);
			break;
		case 2:
			topicsIn(idCommunity,idUser);
			break;
		case 3:
			questionIn(idCommunity,idUser);
			break;
		case 4:
			allsPermissionsInput(idCommunity,idUser);
			break;
		case 5:
			return true;
		}
		return false;
	}
	
	public static void articleIn(int idCommunity,int idUser){
		
		int option;
		Article optionA;
		System.out.println("\nOptions:\n");
		System.out.println("1. New Article");
		System.out.println("2. View Articles");
		System.out.println("3. Return");
		option = input.nextInt();
		switch(option){
		case 1:
			GerenciadorComunidade.addArticle(idCommunity, idUser);
			break;
		case 2:
			   optionA = GerenciadorComunidade.allArticles(idCommunity);
			   optionA.printArticle(GerenciadorComunidade.getOptionA(),idUser,idCommunity);
			   break;
		case 3:
			communityInput(idCommunity,idUser);
			break;			
		}
		communityInput(idCommunity,idUser);
	}
	
	public static void questionIn(int idCommunity,int idUser){
		
		int option;
		Questions optionQ;
		System.out.println("Options:\n");
		System.out.println("1. New Questions");
		System.out.println("2. View Questions");
		System.out.println("3. Return");
		option = input.nextInt();		
		switch(option){
		case 1:
			GerenciadorComunidade.addQuestion(idCommunity, idUser);
			break;
		case 2:
			optionQ = GerenciadorComunidade.allQuestions(idCommunity);
			optionQ.printQuestion(GerenciadorComunidade.getOptionQ(),idCommunity,idUser);
			break;
		case 3:
			communityInput(idCommunity,idUser);
			break;		
		}	
		communityInput(idCommunity,idUser);	
	}
	
	public static void topicsIn(int idCommunity, int idUser){
		
		int option;
		TopicsCommon optionT;
		System.out.println("Options:\n");
		System.out.println("1. New Topics");
		System.out.println("2. View Topics");
		System.out.println("3. Return");
		option = input.nextInt();
		switch(option){
		case 1:
			GerenciadorComunidade.addTopic(idCommunity, idUser);
			break;
		case 2:
			optionT = GerenciadorComunidade.allTopics(idCommunity); 
			optionT.printTopic(GerenciadorComunidade.getOptionT(),idCommunity,idUser);
			break;
		case 3:
			communityInput(idCommunity,idUser);
			break;		
		}	
		communityInput(idCommunity,idUser);	
	}
	
	public static void allsPermissionsInput(int idCommunity,int idUser){
		ArrayList<Integer> permissions = GerenciadorComunidade.allPermissions(idCommunity);
		System.out.printf("\n---Permissions---\n");
		if(permissions.size()!=0){
			for(int i=0;i<permissions.size();i++){
				System.out.printf("User\t%d. %d\n",i+1,permissions.get(i));
			}
			System.out.printf("Accept user (Y/N):");
			String option = input.nextLine();
			if(option == "y" || option == "Y"){
				System.out.println("User: ");
				int user = input.nextInt();
				GerenciadorComunidade.removePermission(idCommunity, user);
				communityInput(idCommunity,idUser);
			}else{
				communityInput(idCommunity,idUser);
			}			
		}else{
			System.out.println("\nNo requests at the moment!");
		}
	}
	
	public static void permissionsInput(int idCommunity, int idUser){
		int option;
		
		System.out.println("\n\n---Solicitation---\n");
		
		if(manager.permissionById(idCommunity, idUser)){
			System.out.println("1. Remove Solicitation");
			System.out.println("2.Return");
			option = input.nextInt();
			if(option == 1){
				GerenciadorComunidade.removePermission(idCommunity, idUser);
			}					
		}else{
			System.out.println("1. Send Request");
			System.out.println("2.Return");
			option = input.nextInt();
			if(option == 1){
				GerenciadorComunidade.addPermission(idCommunity, idUser);
			}		
		}
		othersCommunities(idUser);	
	}
	
}
