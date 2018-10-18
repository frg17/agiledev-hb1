package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.jpa.repository.Query;
import agiledev.persistence.entities.UserStory;

import java.util.List;

import javax.transaction.Transactional;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

    UserStory save(UserStory story);

    void delete(UserStory story);

    List<UserStory> findAllByProjectIdOrderByCreatedAsc(Long projectId);

    UserStory findOneByIdAndProjectId(Long id, Long projectId);

    @Modifying
    @Transactional
    @Query(value = 
    "UPDATE userstories u SET u.text_content = ?1, u.author = ?2 WHERE u.id = ?3 AND u.project_id = ?4", 
    nativeQuery = true)
    void update(String textContent, String author, Long id, Long projectId);

}