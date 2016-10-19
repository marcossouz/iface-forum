package br.com.iface.forum.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.iface.forum.model.Article;
import br.com.iface.forum.model.Community;
import br.com.iface.forum.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
		
	public void addArticle(Article article){
		articleRepository.save(article);
	}
	
	public void removeArticle(Article article){
		articleRepository.delete(article);
	}
	
	public Article sendArticle(int idArticle){
		return articleRepository.findOne(idArticle);
	}
	
	public ArrayList<Article> allArticle(Community community){
		ArrayList<Integer> ids = community.allArticles();
		ArrayList<Article> allArticleByCommunity = new ArrayList<Article>();
		for(int i=0;i<ids.size();i++){
			allArticleByCommunity.add(sendArticle(ids.get(i)));
		}
		return allArticleByCommunity;
	}

	public ArrayList<Article> articleByCreator(int idCreator,Community community){
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
