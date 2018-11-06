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

import agiledev.persistence.entities.PriorityEstimate;
import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.PriorityService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

/**
 * PriorityController
 */
@Controller
public class PriorityController {

    private PriorityService priorityService;
    private ProjectService projectService;
    private AuthenticationService auth;
    private UserStoryService userStoryService;

    @Autowired
    public PriorityController(
        PriorityService priorityService,
        ProjectService projectService,
        AuthenticationService auth,
        UserStoryService userStoryService) 
    {
        this.priorityService = priorityService;
        this.userStoryService = userStoryService;
        this.projectService = projectService;
        this.auth = auth;
    }

    
    /**
     * saves an estimate if the client is authenticated
     */
    @RequestMapping(value = "/priority/estimate", method = RequestMethod.POST)
    public String saveEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @ModelAttribute("priorityEstimate") PriorityEstimate estimate,
        Model model) {

        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";
    
        UserStory us = userStoryService.findOneByIdAndProjectId(            //Validate-a að userstory id
            estimate.getUserStory().getId(), getProjectId(projectToken));   //tilheyri þessu verkefni
        if(us != null) {
            this.priorityService.save(estimate);
        }

        return "redirect:/estimation";
    }

    /**
     * Finalizes the estimates for that project
     * Calculates the average and patches the corresponding user story
     */
    @RequestMapping(value = "/priority/finalizeEstimates", method = RequestMethod.PATCH)
    public String finalizeEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model) {
            
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";

        Project project = this.projectService.findByToken(projectToken);
        List<UserStory> userStories =  project.getUserStories(); //Lazy fetch

        for(UserStory us : userStories) {
            List<PriorityEstimate> estimates = us.getPriorityEstimates(); //Lazy fetch

            Integer ave = priorityService.findAverage(estimates);  //Finn average

            us.setPriority(ave);  //Bæti við average í userstory
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