package br.com.iface.forum.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.iface.forum.model.Community;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Integer>{

}
