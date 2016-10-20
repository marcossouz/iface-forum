package br.com.iface.forum.controller;
import br.com.iface.forum.information.RTopic;
import br.com.iface.forum.information.RegisterArticle;
import br.com.iface.forum.model.Article;
import br.com.iface.forum.model.Community;
import br.com.iface.forum.service.ArticleService;
import br.com.iface.forum.service.QuestionService;
import br.com.iface.forum.service.TopicService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired 
	QuestionService questionService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/article",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> SendArticle(@RequestBody int idArticle){
		Article article = articleService.sendArticle(idArticle);
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/articles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Article>> allArticles(@RequestBody Community community){
		ArrayList<Article> articles = articleService.allArticle(community);
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/article",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addArticle(@RequestBody RegisterArticle register){
		articleService.addArticle(register.getArticle(),register.getIdCommunity());
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/articleR",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeArticle(@RequestBody RTopic article){
		articleService.removeArticle(article.getIdTopic(),article.getIdCommunity());
	}
	
}
