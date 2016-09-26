package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    
    // Find topic by title
    Topic findByTitle(String title);
    
}
