package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.Community;
import repository.CommunityRepository;

import java.util.List;
 
@Controller
@RequestMapping("/forum/community")
public class CommunityController {
      
      private CommunityRepository communityRepository;
 
      @Autowired
      public CommunityController(CommunityRepository communityRepository) {
            this.communityRepository = communityRepository;
      }
 
      @RequestMapping(value = "/{manager}", method = RequestMethod.GET)
      public String listCommunities(@PathVariable("manager") String manager, Model model) {
    	  
            List<Community> listCommunities = communityRepository.findByManager(manager);
            
            if (listCommunities != null) {
                  model.addAttribute("communities", listCommunities);
            }
            
            return "listCommunities";
      }
 
      @RequestMapping(value = "/{manager}", method = RequestMethod.POST)
      public String addCommunityManager(@PathVariable("manager") String manager, Community community) {
    	  
            community.setManager(manager);;
            communityRepository.save(community);
            return "redirect:/{manager}";
            
      }
}
