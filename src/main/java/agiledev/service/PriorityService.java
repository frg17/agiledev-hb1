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

    /*
    public void saveList(List<PriorityEstimate> estimates) {
        this.repository.saveList(estimates);
    }
    public void update(PriorityEstimate estimate) {
        this.repository.updateOne(estimate);
    }
    */

    public List<PriorityEstimate> findAllByUserStoryId(Long id) {
        return this.repository.findAllByUserStoryId(id);
    }

    public List<PriorityEstimate> findAll() {        //Eyða seinna, bara fyrir debug
        return this.repository.findAll();
    }

    public List<PriorityEstimate> findAllByProjectId(Long id) {
        return this.repository.findAllByProjectId(id);
    }

    public List<PriorityEstimate> findAllByProjectIdAndUserStoryId(Long projectId, Long userStoryId) {
        return this.repository.findAllByProjectIdAndUserStoryId(projectId, userStoryId);
    }

    public Long findAverageByUserStoryIdAndProjectId(Long userStoryId, Long projectId){
        return this.repository.findAverageByUserStoryIdAndProjectId(userStoryId, projectId);
    }
}