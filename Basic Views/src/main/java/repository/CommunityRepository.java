package repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import entity.Community;
 
public interface CommunityRepository extends JpaRepository<Community, Long> {
 
	  // Find communities by manager
      List<Community> findByManager(String manager);
      
      // Find community by title
      Community findByTitle(String title);
      
      // Find communities by category
      List<Community> findByCategory(String category);
 
}