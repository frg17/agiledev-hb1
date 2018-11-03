package agiledev.controller;

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

    
    @RequestMapping(value = "/priority/estimate", method = RequestMethod.POST)
    public String saveEstimates(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @ModelAttribute("priorityEstimate") PriorityEstimate estimate,
        Model model) {

        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";
        
        this.priorityService.save(estimate);

        return "redirect:/estimation";
    }
}