package br.com.iface.forum.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.iface.forum.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{

}
