package agiledev.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "priorityEstimate")

/**
 * PriorityEstimate
 */
public class PriorityEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //Project id

    @NotNull
    private Long projectId;

    @NotNull
    private Long userStoryId;
    
    @NotNull
    private int estimate;

    public PriorityEstimate() {
    }

    public PriorityEstimate (Long projectId, Long userStoryId, int estimate) {
        this.projectId = projectId;
        this.userStoryId = userStoryId;
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
     * @return the user story id
     */
    public Long getUserStoryId() {
        return this.userStoryId;
    }

    /**
     * @param id the user story id to set
     */
    public void setUserStoryId(Long id) {
        this.userStoryId = id;
    }

    /**
     * @return the project id
     */
    public Long getProjectId() {
        return this.projectId;
    }

    /**
     * @param id the project id to set
     */
    public void setProjectId(Long id) {
        this.projectId = id;
    }
 }