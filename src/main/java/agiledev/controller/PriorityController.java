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
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.PriorityService;
import agiledev.service.ProjectService;
import static java.lang.Math.toIntExact;


/**
 * PriorityController
 */
@Controller
public class PriorityController {

    private PriorityService priorityService;
    private ProjectService projectService;
    private AuthenticationService auth;

    @Autowired
    public PriorityController(
        PriorityService priorityService,
        ProjectService projectService,
        AuthenticationService auth) 
    {
        this.priorityService = priorityService;
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

        // set the projectId for the estimate
        estimate.setProjectId(getProjectId(projectToken));
        
        this.priorityService.save(estimate);

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

        Long projectId = getProjectId(projectToken);

        List<PriorityEstimate> priorityEstimates = priorityService.findAllByProjectId(projectId);

        /**
         * sort the estimates by the userstory id
         * toIntExact is a function for converting long (userStoryId)
         * to an int. Then we can compare and sor 
         */
        priorityEstimates.sort((PriorityEstimate e1, PriorityEstimate e2) ->
            toIntExact(e1.getUserStoryId())-
            toIntExact(e2.getUserStoryId()));
        
        for (PriorityEstimate estimate : priorityEstimates) {
            System.out.println("is sorted!!!!!!!!!!!" + estimate.getUserStoryId());
            System.out.println("average is:" + priorityService.findAverageByUserStoryIdAndProjectId(estimate.getUserStoryId(), projectId));
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