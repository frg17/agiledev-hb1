package agiledev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agiledev.persistence.entities.UserStory;
import agiledev.persistence.repositories.UserStoryRepository;

import java.util.List;

/*

    Service layer handling persistent data access
    concerning User Story entities.

*/

@Service
public class UserStoryService {
    
    UserStoryRepository repository;

    //Dependency injection.
    @Autowired
    public UserStoryService(UserStoryRepository repository) {
        this.repository = repository;
    }

    public UserStory save(UserStory story) {
        return this.repository.save(story);   
    }

    public void delete(UserStory story) {
        this.repository.delete(story);
    }

    public List<UserStory> findAllByProjectId(Long projectId) {
        return this.repository.findAllByProjectIdOrderByCreatedAsc(projectId);
    }

}