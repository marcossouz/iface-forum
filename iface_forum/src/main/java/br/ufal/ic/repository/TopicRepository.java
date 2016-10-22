package br.ufal.ic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufal.ic.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
