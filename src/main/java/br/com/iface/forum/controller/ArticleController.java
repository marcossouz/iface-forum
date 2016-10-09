package br.com.iface.forum.controller;
import br.com.iface.forum.information.InformationTopic;
import br.com.iface.forum.information.RegisterArticle;
import br.com.iface.forum.model.Article;
import br.com.iface.forum.model.User;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class ArticleController {

	@RequestMapping(method = RequestMethod.GET,value = "/article",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> SendArticle(@RequestBody InformationTopic articleInformations){
		Article article = User.sendArticle(articleInformations.getIdCommunity(), articleInformations.getIdTopic());
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/articles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Article>> allArticles(@RequestBody InformationTopic articleInformation){
		ArrayList<Article> articles = User.allArticle(articleInformation.getIdCommunity());
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/article",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addArticle(@RequestBody RegisterArticle article){
		User.addArticle(article);
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/myArticles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Article>> myArticles(@RequestBody InformationTopic information){
		ArrayList<Article> articles = User.myArticles(information.getIdCommunity());
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}	
	
}
