/*
 * Class for the Project entity.
 */


package agiledev.persistence.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //Project id

    private Date created;   //Timestamp

    private String name;    //Name of project
    private String token;   //Token for project

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<UserStory> userStories;


    @PrePersist
    protected void onCreate() {
        this.created = new Date();
    }

    public Project() {

    }

    public Project(String name, String token) {
        this.name = name;
        this.token = token;
    }


    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }


    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the userStories
     */
    public List<UserStory> getUserStories() {
        return userStories;
    }

    /**
     * @param userStories the userStories to set
     */
    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }



    @Override
    public String toString() {
        return String.format("Project name: %s", this.name);
    }

}