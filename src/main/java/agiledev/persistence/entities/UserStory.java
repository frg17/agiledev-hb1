/*

    Class for the UserStory entity

*/

package agiledev.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "userstories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String textContent;
    private String projectToken;
    private Integer priority;

    //Default constructor
    public UserStory() {

    }

    //Constructor
    public UserStory(String author, String textContent, String projectToken, int priority) {

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
     * @return the projectToken
     */
    public String getProjectToken() {
        return projectToken;
    }

    /**
     * @param projectToken the projectToken to set
     */
    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
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


}