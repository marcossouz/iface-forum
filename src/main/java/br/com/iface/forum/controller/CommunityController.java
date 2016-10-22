package br.com.iface.forum.controller;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.iface.forum.information.IdCommunity;
import br.com.iface.forum.information.IdUser;
import br.com.iface.forum.information.InformationTopic;
import br.com.iface.forum.information.Member;
import br.com.iface.forum.model.Community;
import br.com.iface.forum.service.CommunityService;

@RestController
public class CommunityController {
		
	@Autowired
	CommunityService communityService;
	
	@RequestMapping(method = RequestMethod.POST,value = "/community",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addCommunity(@RequestBody Community community){
		communityService.addCommunity(community);
	}

	@RequestMapping(method = RequestMethod.GET,value = "/allCommunities",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Community>> allCommunities(){
		ArrayList<Community> communities = communityService.allCommunity();
		return new ResponseEntity<>(communities,HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.POST,value="/removeCommunity",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void removeCommunity(@RequestBody IdCommunity community){
		communityService.removeCommunity(community.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/myComunities",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Community>> communitiesByIdUser(@RequestBody int idUser){
		return new ResponseEntity<>(communityService.communitiesByIdUser(idUser),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/othersCommunities",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Community>> searchOthersCommunities(@RequestBody IdUser idUser){
		ArrayList<Community> others = new ArrayList<Community>();
		ArrayList<Community> myCommunities = communityService.CommunityByCreator(idUser.getIdUser());
		ArrayList<Community> communities = communityService.allCommunity();
		boolean aux;		
		for(int i=0;i<communities.size();i++){
			aux = false;
			for(int j=0;j<myCommunities.size();j++){
				if(myCommunities.get(j).getIdCommunity() == communities.get(i).getIdCommunity()){
					aux = true;
					break;
				}
			}
			if(!aux){
				others.add(communities.get(i));
			}
		}
		return new ResponseEntity<>(others,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/Allpermissions",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Integer>> allPermissions(@RequestBody IdCommunity idCommunity){
		ArrayList<Community> communities = communityService.allCommunity();
		idCommunity.setIdCommunity(idCommunity.getIdCommunity()-1);
		return new ResponseEntity<>(communities.get(idCommunity.getIdCommunity()).getPermissions(),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/Mypermissions",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> permissionById(@RequestBody Member Information){
		Community community = communityService.sendCommunity(Information.getIdCommunity());
		ArrayList<Integer> myPermissions = community.getPermissions();
		Integer answer;
		for(int i=0;i<myPermissions.size();i++){
			if(myPermissions.get(i)== Information.getIdMember()){
				answer = 1;
				break;
			}
		}		
		answer = 0;
		return new ResponseEntity<>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/creator",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Integer> creatorById(@RequestBody IdUser idUser){	
		ArrayList<Community> communities = communityService.allCommunity();
		Integer answer;
		for(int i=0;i<communities.size();i++){
			if(communities.get(i).getIdCreator() == idUser.getIdUser()){
				answer = 1;
				break;
			}
		}
		answer = 0;
		return new ResponseEntity<>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/communityId",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Integer> returnCommunity(@RequestBody InformationTopic information){
		return new ResponseEntity<>(information.getUser().getCommunity(information.getIdCommunity()),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/addPermission",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void addPermission(@RequestBody Member Information){
		communityService.addPermission(Information.getIdMember(), Information.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/removePermission",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void removePermission(@RequestBody Member Information){
		communityService.removePermission(Information.getIdMember(),Information.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/addMember",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void addMember(@RequestBody Member Information){
		communityService.addMember(Information.getIdMember(), Information.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/removeMember",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void removeMember(@RequestBody Member Information){
		communityService.removeMember(Information.getIdMember(),Information.getIdCommunity());
	}

}
