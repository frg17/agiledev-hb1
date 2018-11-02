package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agiledev.persistence.entities.UserStory;

/**
 * PriorityRepository
 */
public interface PriorityRepository extends JpaRepository<UserStory, Long> {

    
}