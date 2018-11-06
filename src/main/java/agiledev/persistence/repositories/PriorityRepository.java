package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import agiledev.persistence.entities.PriorityEstimate;


/**
 * PriorityRepository
 */
public interface PriorityRepository extends JpaRepository<PriorityEstimate, Long> {

    @Query(value = "SELECT avg(e.estimate) FROM priority_estimate e WHERE e.user_story_id = ?1 AND e.project_id = ?2", 
    nativeQuery = true)
    Long findAverageByUserStoryIdAndProjectId(Long userStoryId, Long projectId);

}