package br.com.iface.forum.controller;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.iface.forum.information.InformationTopic;
import br.com.iface.forum.model.Community;

@Controller
@RestController
public class CommunityController {
	private static int idCommunity = 1;
	private static ArrayList<Community> communities = new ArrayList<Community>();
	
	public static ArrayList<Community> cbi(int idUser){
		ArrayList<Community> myCommunities = new ArrayList<Community>();
		for(int i=0;i<idCommunity-1;i++){
			if(communities.get(i).getIdCreator() == idUser){
				myCommunities.add(communities.get(i));
			}
		}
		return myCommunities;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/community",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addCommunity(@RequestBody Community community){
		communities.add(community);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/myComunities",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Community>> communitiesByIdUser(@RequestBody int idUser){
		ArrayList<Community> myCommunities = new ArrayList<Community>();
		for(int i=0;i<communities.size();i++){
			if(communities.get(i).getIdCreator() == idUser){
				myCommunities.add(communities.get(i));
			}
		}
		return new ResponseEntity<>(myCommunities,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/othersCommunities",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Community>> searchOthersCommunities(@RequestBody int idUser){
		ArrayList<Community> others = new ArrayList<Community>();
		ArrayList<Community> myCommunities = cbi(idUser);
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
	public ResponseEntity<ArrayList<Integer>> allPermissions(@RequestBody int idCommunity){
		idCommunity--;
		return new ResponseEntity<>(communities.get(idCommunity).getPermissions(),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/Mypermissions",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> permissionById(@RequestBody InformationTopic Information){
		ArrayList<Integer> myPermissions = communities.get(idCommunity).getPermissions();	
		Integer answer;
		for(int i=0;i<myPermissions.size();i++){
			if(myPermissions.get(i)== Information.getUser()){
				answer = 1;
				break;
			}
		}		
		answer = 0;
		return new ResponseEntity<>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/creator",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Integer> creatorById(@RequestBody int idUser){	
		Integer answer;
		for(int i=0;i<idCommunity-1;i++){
			if(communities.get(i).getIdCreator() == idUser){
				answer = 1;
				break;
			}
		}
		answer = 0;
		return new ResponseEntity<>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/communityId",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Community> returnCommunity(@RequestBody int idCommunity){
		return new ResponseEntity<>(communities.get(idCommunity),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/addPermission",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void addPermission(@RequestBody InformationTopic Information){
		communities.get(Information.getIdCommunity()).addPermission(Information.getUser());
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/removePermission",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void removePermission(@RequestBody InformationTopic Information){
		communities.get(Information.getIdCommunity()).removePermission(Information.getUser());
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/addMember",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void addMember(@RequestBody int idUser){
		communities.get(idCommunity).addMember(idUser);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/removeMember",consumes = MediaType.APPLICATION_JSON_VALUE)	
	public void removeMember(@RequestBody int idUser){
		communities.get(idCommunity).removeMember(idUser);
	}

}
