package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import agiledev.persistence.entities.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project save(Project project);

    void delete(Project project);

    Project findByToken(String token);

    List<Project> findAll();   //Eyða, bara fyrir debug

}