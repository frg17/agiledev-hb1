package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import agiledev.persistence.entities.UserStory;

import java.util.List;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

    UserStory save(UserStory story);

    void delete(UserStory story);

    List<UserStory> findAllByProjectToken(String projectToken);   //Eyða, bara fyrir debug

}