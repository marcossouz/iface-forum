package br.com.iface.forum.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.iface.forum.model.Article;
import br.com.iface.forum.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
			
	public void addArticle(Article article){
		articleRepository.save(article);
	}
	
	public void removeArticle(int article){
		articleRepository.delete(article);
	}
	
	public Article sendArticle(int idArticle){
		return articleRepository.findOne(idArticle);
	}
	
	public ArrayList<Article> allArticles(){
		ArrayList<Article> article = (ArrayList<Article>) articleRepository.findAll();
		return article;
	}
	
	public ArrayList<Article> allArticle(int idCommunity){
		ArrayList<Article> article = allArticles();
		ArrayList<Article> resp = new ArrayList<Article>();
		for(int i=0;i<article.size();i++){
			if(article.get(i).getIdCommunity()==idCommunity){
				resp.add(article.get(i));
			}
		}
		return resp;
	}

	public ArrayList<Article> articleByCreator(int idCreator,int community){
		ArrayList<Article> article = new ArrayList<Article>();
		ArrayList<Article> articleCommunity = allArticle(community);
		for(int i=0;i<articleCommunity.size();i++){
			if(articleCommunity.get(i).getIdCreator() == idCreator){
				article.add(articleCommunity.get(i));
			}
		}
		return article;
	} 	
}
