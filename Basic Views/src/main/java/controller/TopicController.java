package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entity.Topic;
import repository.TopicRepository;

import java.util.List;
 
@Controller
@RequestMapping("/forum/topic")

public class TopicController {
      
      private TopicRepository topicRepository;
 
      @Autowired
      public TopicController(TopicRepository topicRepository) {
            this.topicRepository = topicRepository;
      }
 
      @RequestMapping(value= "/topics", method = RequestMethod.GET)
      public String listTopics(Model model) {
    	  
            List<Topic> listTopics = topicRepository.findAll();
            
            if (listTopics != null) {
                  model.addAttribute("topics", listTopics);
            }
            
            return "listTopics";
      }
 
      @RequestMapping(value= "/topics", method = RequestMethod.POST)
      public String addTopic(Topic topic) {
    	  
            topicRepository.save(topic);
            
            return "redirect:/topics";
            
      }
}
