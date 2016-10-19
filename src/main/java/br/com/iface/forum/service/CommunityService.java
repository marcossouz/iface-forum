package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iface.forum.model.Article;
import br.com.iface.forum.model.Community;
import br.com.iface.forum.model.Question;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.repository.CommunityRepository;

@Service
public class CommunityService {	

	@Autowired
	CommunityRepository communityRepository; 
	
	public ArrayList<Community> allCommunity(){
		return (ArrayList<Community>) communityRepository.findAll();
	}
	
	public void addCommunity(Community community){
		communityRepository.save(community);
	}
	
	public Community sendCommunity(int idCommunity){
		return communityRepository.findOne(idCommunity);
	}
	
	public void removeCommunity(Community community){
		communityRepository.delete(community);
	}
	
	public ArrayList<Community> communitiesByIdUser(int idUser){
		ArrayList<Community> all =  (ArrayList<Community>) communityRepository.findAll();
		ArrayList<Community> allCommunityUser = new ArrayList<Community>();
		for(int i=0;i<all.size();i++){
			for(int j=0;j<all.get(i).getMembers().size();j++){
				if(all.get(i).getMembers().get(j) == idUser){
					allCommunityUser.add(all.get(i));
				}
			}
		}
		return allCommunityUser;
	}

	public ArrayList<Community> CommunityByCreator(int idCreator){
		ArrayList<Community> community = new ArrayList<Community>();
		ArrayList<Community> communityCreator = communitiesByIdUser(idCreator);
		for(int i=0;i<communityCreator.size();i++){
			if(communityCreator.get(i).getIdCreator() == idCreator){
				community.add(communityCreator.get(i));
			}
		}
		return community;
	}
	
	public void removeMember(int idUser,Community community){
		ArrayList<Integer> members = community.getMembers();
		for(int i=0;i<members.size();i++){
			if(members.get(i) == idUser){
				members.remove(i);
				break;
			}
		}
	}
	
	public void removePermission(int idUser,Community community){
		ArrayList<Integer> permission = community.getPermissions();
		for(int i=0;i<permission.size();i++){
			if(permission.get(i) == idUser){
				permission.remove(i);
				break;
			}
		}
	}
	
	public void addArticle(Community community, Article article){
		
	}
	
	public void addQuestion(Community community, Question question){
		
	}
	
	public void addTopic(Community community, TopicCommon topic){
		
	}
}
