package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.iface.forum.model.TopicCommon;
import br.com.iface.forum.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	TopicRepository topicRepository;
				
	public void addTopic(TopicCommon topic){
		topicRepository.save(topic);
	}
	
	public void removeTopic(int topic){
		topicRepository.delete(topic);
	}
	
	public TopicCommon sendTopic(int idTopic){
		return topicRepository.findOne(idTopic);
	}
	
	public ArrayList<TopicCommon> allTopics(){
		ArrayList<TopicCommon> topic = (ArrayList<TopicCommon>) topicRepository.findAll();
		return topic;
	}
	
	public ArrayList<TopicCommon> allTopics(int idCommunity){
		ArrayList<TopicCommon> topic = allTopics();
		ArrayList<TopicCommon> resp = new ArrayList<TopicCommon>();
		for(int i=0;i<topic.size();i++){
			if(topic.get(i).getIdCommunity()==idCommunity){
				resp.add(topic.get(i));
			}
		}
		return resp;
	}
	
	public ArrayList<TopicCommon> topicByCreator(int creator,int idCommunity){
		ArrayList<TopicCommon> topic = new ArrayList<TopicCommon>();
		ArrayList<TopicCommon> topicCommunity = allTopics(idCommunity);
		for(int i=0;i<topicCommunity.size();i++){
			if(topicCommunity.get(i).getIdCreator() == creator){
				topic.add(topicCommunity.get(i));
			}
		}
		return topic;
	}
	
}
