/*

    Class for the UserStory entity

*/

package agiledev.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "userstories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String textContent;
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userStory")
    private List<PriorityEstimate> priorityEstimates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userStory")
    private List<PlanningPokerEstimate> planningPokerEstimates;


    private Date created;   //Timestamp

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.priority = 0;
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
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the planningPokerEstimates
     */
    public List<PlanningPokerEstimate> getPlanningPokerEstimates() {
        return planningPokerEstimates;
    }

    /**
     * @return the priorityEstimates
     */
    public List<PriorityEstimate> getPriorityEstimates() {
        return priorityEstimates;
    }

    /**
     * @param planningPokerEstimates the planningPokerEstimates to set
     */
    public void setPlanningPokerEstimates(List<PlanningPokerEstimate> planningPokerEstimates) {
        this.planningPokerEstimates = planningPokerEstimates;
    }

    /**
     * @param priorityEstimates the priorityEstimates to set
     */
    public void setPriorityEstimates(List<PriorityEstimate> priorityEstimates) {
        this.priorityEstimates = priorityEstimates;
    }

    @Override
    public String toString() {
        return "Project Id: " + this.project.getId() +
            "\nId: " + this.id +
            "\nAuthor: " + this.author + 
            "\nContent: " + this.textContent;
    }


}