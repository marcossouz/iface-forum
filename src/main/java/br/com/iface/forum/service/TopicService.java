package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iface.forum.model.Community;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	static TopicRepository topicRepository;
			
	public void addTopic(TopicCommon topic){
		topicRepository.save(topic);
	}
	
	public void removeTopic(TopicCommon topic){
		topicRepository.delete(topic);
	}
	
	public static TopicCommon sendTopic(int idTopic){
		return topicRepository.findOne(idTopic);
	}
	
	public ArrayList<TopicCommon> allTopics(Community community){
		ArrayList<Integer> ids = community.allTopics();
		ArrayList<TopicCommon> alltopics = new ArrayList<TopicCommon>();
		for(int i=0;i<ids.size();i++){
			alltopics.add(sendTopic(ids.get(i)));
		}
		return alltopics;
	}
	
	public ArrayList<TopicCommon> topicByCreator(int creator,Community community){
		ArrayList<TopicCommon> topic = new ArrayList<TopicCommon>();
		ArrayList<TopicCommon> topicCommunity = allTopics(community);
		for(int i=0;i<topicCommunity.size();i++){
			if(topicCommunity.get(i).getIdCreator() == creator){
				topic.add(topicCommunity.get(i));
			}
		}
		return topic;
	}
	
}
