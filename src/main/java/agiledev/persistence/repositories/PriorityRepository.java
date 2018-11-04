package agiledev.persistence.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import agiledev.persistence.entities.PriorityEstimate;


/**
 * PriorityRepository
 */
public interface PriorityRepository extends JpaRepository<PriorityEstimate, Long> {

    //void saveList(List<PriorityEstimate> estimates);

    
    List<PriorityEstimate> findAllByUserStoryId(Long id);

    PriorityEstimate save(PriorityEstimate estimate);
    
    //void updateOne(PriorityEstimate estimate);

    List<PriorityEstimate> findAllByProjectId(Long id);

    List<PriorityEstimate> findAllByProjectIdAndUserStoryId(Long projectId, Long userStoryId);


    @Transactional
    @Query(value = "SELECT avg(e.estimate) FROM priorityEstimate e WHERE e.userStoryId = ?1 AND e.projectId = ?2", 
    nativeQuery = true)
    Long findAverageByUserStoryIdAndProjectId(Long userStoryId, Long projectId);

    List<PriorityEstimate> findAll();   //Eyða, bara fyrir debug
}