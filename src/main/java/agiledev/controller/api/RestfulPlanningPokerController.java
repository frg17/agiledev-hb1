package agiledev.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import agiledev.persistence.entities.PlanningPokerEstimate;
import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.PlanningPokerService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

@RestController
public class RestfulPlanningPokerController {

    private @Autowired PlanningPokerService planningPokerService;
    private @Autowired ProjectService projectService;
    private @Autowired AuthenticationService auth;
    private @Autowired UserStoryService userStoryService;

    @PostMapping(value = "api/planningpoker/estimate", produces = "application/json")
    public Object saveEstimates(
        HttpServletResponse res, 
        @RequestBody PlanningPokerEstimate estimate 
    ) {
        if (!this.auth.isAuthenticated(res)) {
            res.setStatus(401); // NotAuthorized status
            return new JSONResponse(false, "Unauthorized", null); // Skila engu
        }

        String projectToken = res.getHeader("authenticated");
        UserStory us = userStoryService.findOneByIdAndProjectId(estimate.getUserStory().getId(),
                getProjectId(projectToken));
        if (us != null) {
            PlanningPokerEstimate e = this.planningPokerService.save(estimate);
            return new JSONResponse(true, "Estimation Created", e);
        }
        return new JSONResponse(false, "User story ID not found", null);
    }

    @DeleteMapping(value = "api/planningpoker/delete", produces = "application/json")
    public Object deleteEstimate(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @RequestBody PlanningPokerEstimate estimate) {

        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401); //NotAuthorized status
            return new JSONResponse(false, "Unauthorized", null); //Skila engu.
        }

        UserStory us = userStoryService.findOneByIdAndProjectId( // Validate-a að userstory id
                estimate.getUserStory().getId(), getProjectId(projectToken)); // tilheyri þessu verkefni

        if (us != null) {
            this.planningPokerService.delete(estimate);
            return new JSONResponse(true, "Estimate deleted", null);
        }

        return new JSONResponse(false, "User story ID not found", null);
    }

    @PatchMapping("api/planningpoker/finalizeEstimates")
    public Object finalizeEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res
        ) {
            
        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401); //NotAuthorized status
            return new JSONResponse(false, "Unauthorized", null); //Skila engu.
        }  

        Project project = this.projectService.findByToken(projectToken);

        List<UserStory> userStories =  project.getUserStories(); //Lazy fetch

        for(UserStory us : userStories) {
            List<PlanningPokerEstimate> estimates = us.getPlanningPokerEstimates(); //Lazy fetch

            Integer ave = this.planningPokerService.findAverage(estimates).intValue();  //Finn average

            us.setPriority(ave);  //Bæti við average í userstory
            this.userStoryService.save(us);  //Uppfæri
        }

        
        return new JSONResponse(true, "Estimates finalized", null);
    }

    public Long getProjectId(String token) {
        return this.projectService.findOneByToken(token).getId();
    }
}
