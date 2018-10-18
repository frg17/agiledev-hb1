/*

    Class for the UserStory entity

*/

package agiledev.persistence.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "userstories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String textContent;
    private Long projectId;
    private Integer priority;

    private Date created;   //Timestamp

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
    }

    //Default constructor
    public UserStory() {

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
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the textContent
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * @param textContent the textContent to set
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    
    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }
    
    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }


}