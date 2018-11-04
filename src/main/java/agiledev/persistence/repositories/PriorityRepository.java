package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agiledev.persistence.entities.PriorityEstimate;
import java.util.List;


/**
 * PriorityRepository
 */
public interface PriorityRepository extends JpaRepository<PriorityEstimate, Long> {

    //void saveList(List<PriorityEstimate> estimates);

    
    List<PriorityEstimate> findAllByUserStoryId(Long id);

    PriorityEstimate save(PriorityEstimate estimate);
    
    //void updateOne(PriorityEstimate estimate);

    List<PriorityEstimate> findAllByProjectId(Long id);

    List<PriorityEstimate> findAll();   //Eyða, bara fyrir debug

}