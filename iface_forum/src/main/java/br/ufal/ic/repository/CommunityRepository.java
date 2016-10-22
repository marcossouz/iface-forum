package br.ufal.ic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufal.ic.model.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

}
