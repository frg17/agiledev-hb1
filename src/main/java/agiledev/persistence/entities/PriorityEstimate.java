package agiledev.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



/**
 * PriorityEstimate
 */
@Entity
@Table(name = "priorityEstimate")
public class PriorityEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //Project id
    
    @NotNull
    private int estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserStory userStory;

    public PriorityEstimate() {
    }

    public PriorityEstimate (int estimate) {

        this.estimate = estimate;
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
     * @return the estimate
     */
    public int getEstimate() {
        return this.estimate;
    }

    /**
     * @param estimate the estimate to set
     */
    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    /**
     * @return the userStory
     */
    public UserStory getUserStory() {
        return userStory;
    }

    /**
     * @param userStory the userStory to set
     */
    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }
 }