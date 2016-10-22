package br.ufal.ic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufal.ic.model.Community;
import br.ufal.ic.repository.CommunityRepository;

@Service
public class CommunityService {
	
	@Autowired
	CommunityRepository comunRep;
	
	// CRUD
	public Community register(Community c){
		
		return comunRep.save(c);
	}
	
	public List<Community> findCommunities(){
		return comunRep.findAll();
	}
	
	public void deletarCommunity(Community c){
		comunRep.delete(c);
	}
	
	public Community findById(Integer id){
		return comunRep.findOne(id);
	}
	
	public Community update(Community c){
		return comunRep.save(c);
	}
	

}
