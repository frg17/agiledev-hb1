package agiledev.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

@Controller
public class ProjectController {

    private ProjectService projectService;
    private UserStoryService userStoryService;
    private AuthenticationService auth;

    //Dependency injection
    @Autowired
    public ProjectController(
        ProjectService projectService,
        UserStoryService userStoryService,
        AuthenticationService auth) 
    {
        this.projectService = projectService;
        this.userStoryService = userStoryService;
        this.auth = auth;
    }

    /*
     *  Shows client all user stories of projects IF AUTHENTICATED.
     *  ELSE goes to login.  
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model)
    {
        if(!auth.isAuthenticated(res, model)) {
            model.addAttribute("project", new Project());  //Til að geyma token fyrir login
            return "projects/login";
        } else {
            Long projectId = projectService.findOneByToken(projectToken).getId();
            List<UserStory> userStories = 
                this.userStoryService.findAllByProjectId(projectId); 

            model.addAttribute("userStories", userStories);
            model.addAttribute("userStory", new UserStory());
            return "Index";           
        }   
    }

    /*
        Handles user login to project.
    */
    @RequestMapping(value = "/projects/login", method = RequestMethod.POST)
    public String projectLogin(@ModelAttribute("project") Project project,
        HttpServletResponse res)
    {
        String token = project.getToken();
        if(token != null) {
            if(this.projectService.findByToken(token) != null) {
                Cookie cookie = new Cookie("projectToken", project.getToken());
                cookie.setPath("/");
                res.addCookie(cookie);
            }
        }

        return "redirect:/";
    }

    /*
        Logs user out
    */
    @RequestMapping(value = "/projects/logout", method = RequestMethod.POST)
    public String projectLogout(
        HttpServletRequest req,
        HttpServletResponse res)
    {
        Cookie cookie = new Cookie("projectToken", "");
        cookie.setPath("/");
        res.addCookie(cookie);

        return "redirect:/";
    }

    /*
        Should return a view to create a new project.
    */
    @RequestMapping(value = "/projects/create", method = RequestMethod.GET)
    public String createProjectGET(Model model) 
    {
        model.addAttribute("project", new Project());

        return "projects/create";
    }

    /*
        Creates a new project on the server
    */
    @RequestMapping(value = "/projects/create", method = RequestMethod.POST)
    public String createProjectPOST(@ModelAttribute("project") Project project,
                                Model model) 
    {
        this.projectService.save(project);

        return "redirect:/";
    }


    /**
     * Displays the view for either planning poker 
     * or priority according to which
     * phase the project is in
     */
    @RequestMapping(value = "/estimation", method = RequestMethod.GET)
    public String estimationView(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model)
    {

        if (!this.auth.isAuthenticated(res, model)) return "redirect:/";


        Long projectId = projectService.findOneByToken(projectToken).getId();
        List<UserStory> userStories = 
            this.userStoryService.findAllByProjectId(projectId); 

        model.addAttribute("userStories", userStories);
        model.addAttribute("userStory", new UserStory());
        model.addAttribute("priorityEstimate", new PriorityEstimate());


        return "estimation/estimation";
    }

    /*
        DEBUG METHOD, REMOVE IN PROD
    */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String seeAllProjects(Model model) {
        model.addAttribute("projects", this.projectService.findAll());

        return "All";
    }

}
