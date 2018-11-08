package agiledev.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agiledev.persistence.entities.PriorityEstimate;
import agiledev.persistence.repositories.PriorityRepository;

/*
    Service layer for access to persistent data
    concerning Priority entities.
*/
@Service
public class PriorityService {

    PriorityRepository repository;

    // Dependency injection
    @Autowired
    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public PriorityEstimate save(PriorityEstimate estimate) {
        return this.repository.save(estimate);
    }


    public List<PriorityEstimate> findAll() {        //Eyða seinna, bara fyrir debug
        return this.repository.findAll();
    }

    
    public Long findAverageByUserStoryIdAndProjectId(Long userStoryId, Long projectId){
        return this.repository.findAverageByUserStoryIdAndProjectId(userStoryId, projectId);
    }


    /**
     * Finnur average af lista af estimates.
     * @param estimates
     * @return
     */
    public Integer findAverage(List<PriorityEstimate> estimates) {
        if (estimates.size() == 0) return 0;
        int sum = 0;
        for (PriorityEstimate es : estimates) {
            sum += es.getEstimate();
        }

        return sum / estimates.size();
    }
}