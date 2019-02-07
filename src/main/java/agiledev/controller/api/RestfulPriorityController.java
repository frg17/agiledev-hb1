package agiledev.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agiledev.persistence.entities.PriorityEstimate;
import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.PriorityService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;
/**
 * RestfulPriorityController
 */
@RestController
public class RestfulPriorityController {

    private @Autowired PriorityService priorityService;
    private @Autowired ProjectService projectService;
    private @Autowired AuthenticationService auth;
    private @Autowired UserStoryService userStoryService;

    /**
     * saves an estimate if the client is authenticated
     */
    @PostMapping("api/priority/estimate")
    public String saveEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @RequestBody PriorityEstimate estimate //Segir spring að parse-a JSON sem að client sendir beint í UserStory hlut.
        ) {

        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401); //NotAuthorized status
            return null; //Skila engu.
        }
    
        UserStory us = userStoryService.findOneByIdAndProjectId(             
            estimate.getUserStory().getId(), projectService.findOneByToken(projectToken).getId());
        
        if(us != null) {
            this.priorityService.save(estimate);
            return "estimate saved";
        }

        res.setStatus(400);
        return "unsuccessful saving the estimate";
    }

     /**
     * deletes an estimate if the client is authenticated
     */
    @DeleteMapping("api/priority/delete")
    public String deleteEstimate(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @RequestBody PriorityEstimate estimate) {

        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401); //NotAuthorized status
            return null; //Skila engu.
        }

        UserStory us = userStoryService.findOneByIdAndProjectId(            //Validate-a að userstory id
            estimate.getUserStory().getId(), projectService.findOneByToken(projectToken).getId());   //tilheyri þessu verkefni

        if(us != null) {
            this.priorityService.delete(estimate);
            return "estimate deleted";
        }

        return "unsuccessful";
    }

    /**
     * Finalizes the estimates for that project
     * Calculates the average and patches the corresponding user story
     */
    @PatchMapping("api/priority/finalizeEstimates")
    public String finalizeEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res) {
            
        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401); //NotAuthorized status
            return null; //Skila engu.
        }  

        Project project = this.projectService.findByToken(projectToken);

        // skiptum yfir í phase 0 við að finalize-a
        project.setProjectPhase(Project.PROJECT_PHASE_DEFAULT);


        List<UserStory> userStories =  project.getUserStories(); //Lazy fetch

        for(UserStory us : userStories) {
            List<PriorityEstimate> estimates = us.getPriorityEstimates(); //Lazy fetch

            Integer ave = this.priorityService.findAverage(estimates);  //Finn average

            us.setPriority(ave);  //Bæti við average í userstory
            this.userStoryService.save(us);  //Uppfæri
        }

        
        return "Estimates have been finalized";
    }
    
}