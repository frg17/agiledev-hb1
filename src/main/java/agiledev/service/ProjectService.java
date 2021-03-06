package agiledev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import agiledev.persistence.entities.Project;
import agiledev.persistence.repositories.ProjectRepository;

import java.util.List;
/*
    Service layer for access to persistent data
    concerning Project entities.
*/

@Service
public class ProjectService {
    
    ProjectRepository repository;

    //Dependency injection.
    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project save(Project project) {
        return this.repository.save(project);
    }

    public void delete(Project project) {
        this.repository.delete(project);
    }

    public Project findByToken(String token) {
        return this.repository.findByToken(token);
    }

    public List<Project> findAll() {        //Eyða seinna, bara fyrir debug
        return this.repository.findAll();
    }

    public Project findOneByToken(String token) {
        return this.repository.findOneByToken(token);
    }
}