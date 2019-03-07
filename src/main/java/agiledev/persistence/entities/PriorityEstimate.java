package agiledev.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;



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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserStory userStory;

    private String explanation;

    public PriorityEstimate() {
    }

    public PriorityEstimate (int estimate, String explanation) {

        this.estimate = estimate;
        this.explanation = explanation;
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

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return this.explanation;
    }
 }