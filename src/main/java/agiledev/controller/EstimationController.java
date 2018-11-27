package agiledev.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agiledev.persistence.entities.PlanningPokerEstimate;
import agiledev.persistence.entities.PriorityEstimate;
import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.ProjectService;

/**
 * PriorityController
 */
@Controller
@RequestMapping(value = "/estimation")
public class EstimationController {

    private ProjectService projectService;
    private AuthenticationService auth;

    @Autowired
    public EstimationController(
        ProjectService projectService,
        AuthenticationService auth) 
    {
        this.projectService = projectService;
        this.auth = auth;
    }

    /**
     * Displays the view for either planning poker 
     * or priority according to which
     * phase the project is in
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String estimationView(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model)
    {
        if (!this.auth.isAuthenticated(res, model)) return "redirect:/";

        Project project = projectService.findOneByToken(projectToken);
        List<UserStory> userStories = project.getUserStories();
        
        model.addAttribute("userStories", userStories);
        if(project.getProjectPhase() == Project.PROJECT_PHASE_PRIORITY) {
            //Priority estimate fyrir form.
            PriorityEstimate estimate = new PriorityEstimate();
            estimate.setUserStory(new UserStory());
            model.addAttribute("priorityEstimate", estimate);
            model.addAttribute("phase", 1);
            for(int i = 0; i < userStories.size(); i++) {
                userStories.get(i).getPriorityEstimates(); //Þarf að sækja vegna LAZY fetch
            }
            return "estimation/estimation";
        } else if(project.getProjectPhase() == Project.PROJECT_PHASE_PLANNINGPOKER) {
            PlanningPokerEstimate estimate = new PlanningPokerEstimate();
            estimate.setUserStory(new UserStory());
            model.addAttribute("planningPokerEstimate", estimate);
            model.addAttribute("phase", 2);
            for(int i = 0; i < userStories.size(); i++) {
                userStories.get(i).getPlanningPokerEstimates(); //Þarf að sækja vegna LAZY fetch
            }
            return "estimation/estimation";
        }

        return "redirect:/";


    }


    @RequestMapping(value = "/priority/start", method = RequestMethod.GET)
    public String startPriorityPhase(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model)
    {
        if (!this.auth.isAuthenticated(res, model)) return "redirect:/";
        Project project = projectService.findOneByToken(projectToken);
        project.setProjectPhase(Project.PROJECT_PHASE_PRIORITY);
        projectService.save(project);

        return "redirect:/estimation";
    }

    @RequestMapping(value = "/planningpoker/start", method = RequestMethod.GET)
    public String startPlanningPokerPhase(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model)
    {
        if (!this.auth.isAuthenticated(res, model)) return "redirect:/";
        Project project = projectService.findOneByToken(projectToken);
        project.setProjectPhase(Project.PROJECT_PHASE_PLANNINGPOKER);
        projectService.save(project);

        return "redirect:/estimation";
    }

}
