package br.com.iface.forum.controller;
import br.com.iface.forum.information.IdArticle;
import br.com.iface.forum.information.IdCommunity;
import br.com.iface.forum.model.Article;
import br.com.iface.forum.service.ArticleService;
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
	
	@RequestMapping(method = RequestMethod.POST,value = "/articleS",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> SendArticle(@RequestBody IdArticle idArticle){
		Article article = articleService.sendArticle(idArticle.getIdArticle());
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/articles", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Article>> allArticles(@RequestBody IdCommunity community){
		ArrayList<Article> articles = articleService.allArticle(community.getIdCommunity());
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/articleA",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addArticle(@RequestBody Article register){
		articleService.addArticle(register);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/articleR",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeArticle(@RequestBody IdArticle article){
		articleService.removeArticle(article.getIdArticle());
	}
	
}
