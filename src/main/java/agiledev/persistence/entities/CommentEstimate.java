/*

    Class for the PlanningPokerEstimate entity

*/

package agiledev.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "estimatecomments")
public class CommentEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String textContent;
    private Long userStoryId;

    //Default constructor
    public CommentEstimate() {
        
    }

    //Constructor
    public CommentEstimate(String author, String textContent, Long userStoryId) {
        this.author = author;
        this.textContent = textContent;
        this.userStoryId = userStoryId;
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
     * @return the userStoryId
     */
    public Long getUserStoryId() {
        return userStoryId;
    }

    /**
     * @param userStoryId the userStoryId to set
     */
    public void setUserStoryId(Long userStoryId) {
        this.userStoryId = userStoryId;
    }
}