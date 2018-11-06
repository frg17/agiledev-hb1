package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agiledev.persistence.entities.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    void delete(Project project);

    Project findByToken(String token);

    List<Project> findAll();   //Eyða, bara fyrir debug


    Project findOneByToken(String token);


}