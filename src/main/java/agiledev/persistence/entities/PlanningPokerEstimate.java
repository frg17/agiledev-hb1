/*

    Class for the PlanningPokerEstimate entity

*/

package agiledev.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "planningpokerestimates")
public class PlanningPokerEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String member;
    private Float estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserStory userStory;

    private String explanation;

    //Default constructor
    public PlanningPokerEstimate() {
        
    }

    //Constructor
    public PlanningPokerEstimate(String member, Float estimate, String explanation, Long userStoryId) {
        this.member = member;
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
     * @return the member
     */
    public String getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * @return the estimate
     */
    public Float getEstimate() {
        return estimate;
    }

    /**
     * @param estimate the estimate to set
     */
    public void setEstimate(Float estimate) {
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

    /**
     * @param explanation for the estimate
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return the estimate's explanation
     */
    public String getExplanation() {
        return this.explanation;
    }
}