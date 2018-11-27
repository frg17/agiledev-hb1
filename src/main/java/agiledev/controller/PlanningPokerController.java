package agiledev.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agiledev.persistence.entities.PlanningPokerEstimate;
import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.PlanningPokerService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

/**
 * PlanningPokerController
 */
@Controller
public class PlanningPokerController {

    private PlanningPokerService planningPokerService;
    private ProjectService projectService;
    private AuthenticationService auth;
    private UserStoryService userStoryService;

    @Autowired
    public PlanningPokerController(
        PlanningPokerService planningPokerService,
        ProjectService projectService,
        AuthenticationService auth,
        UserStoryService userStoryService) 
    {
        this.planningPokerService = planningPokerService;
        this.userStoryService = userStoryService;
        this.projectService = projectService;
        this.auth = auth;
    }

    
    /**
     * saves an estimate if the client is authenticated
     */
    @RequestMapping(value = "/planningpoker/estimate", method = RequestMethod.POST)
    public String saveEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @ModelAttribute("planningPokerEstimate") PlanningPokerEstimate estimate,
        Model model) {

        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";
    
        UserStory us = userStoryService.findOneByIdAndProjectId(            //Validate-a að userstory id
            estimate.getUserStory().getId(), getProjectId(projectToken));   //tilheyri þessu verkefni
        if(us != null) {
            this.planningPokerService.save(estimate);
        }

        return "redirect:/estimation";
    }

    /**
     * deletes an estimate if the client is authenticated
     */
    @RequestMapping(value = "/planningpoker/estimate", method = RequestMethod.DELETE)
    public String deleteEstimate(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @ModelAttribute("planningPokerEstimate") PlanningPokerEstimate estimate,
        Model model) {

        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";

        System.out.println("!I!I!I!I!I!I" + estimate.getEstimate());

        UserStory us = userStoryService.findOneByIdAndProjectId(            //Validate-a að userstory id
            estimate.getUserStory().getId(), getProjectId(projectToken));   //tilheyri þessu verkefni

        if(us != null) {
            this.planningPokerService.delete(estimate);
        }

        return "redirect:/estimation";
    }

    /**
     * Finalizes the estimates for that project
     * Calculates the average and patches the corresponding user story
     */
    @RequestMapping(value = "/planningpoker/finalizeEstimates", method = RequestMethod.PATCH)
    public String finalizeEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model) {
            
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";

        Project project = this.projectService.findByToken(projectToken);
        List<UserStory> userStories =  project.getUserStories(); //Lazy fetch

        for(UserStory us : userStories) {
            us.getPlanningPokerEstimates(); //Lazy fetch

            Double ave = this.planningPokerService.findAverage(estimates); //finn average
    
            us.setPlanningPokerPriority(ave);

            this.userStoryService.save(us);  //Uppfæri
        }

        
        return "redirect:/";
    }


    /**
     * Finds project id from a project token.
     * @param token
     * @return
     */
    public Long getProjectId(String token) {
        return this.projectService.findOneByToken(token).getId();
    }
}