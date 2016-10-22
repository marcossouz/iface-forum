package br.ufal.ic.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.model.Community;
import br.ufal.ic.service.CommunityService;


@RestController
public class CommunityController {
	
	@Autowired
	CommunityService communityService;
	
	// End points
	@RequestMapping(method=RequestMethod.POST, value="/communities", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Community> cadastrarComunidade(@RequestBody Community c){
		
		Community comunCreated = communityService.register(c);
		
		return new ResponseEntity<Community>(comunCreated, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/communities", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Community>> buscarComunidades(){
		
		List<Community> communities = communityService.findCommunities();
		
		return new ResponseEntity<>(communities, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/communities/{id}")
	public ResponseEntity<Community> excluirComunidade(@PathVariable Integer id){
		
		Community comunFound = communityService.findById(id);
		
		if(comunFound == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		communityService.deletarCommunity(comunFound);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/communities", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Community> alterarComunidade(@RequestBody Community c){
		
		Community comun = communityService.update(c);
		
		return new ResponseEntity<>(comun, HttpStatus.OK);
	}
	
	
	
}
