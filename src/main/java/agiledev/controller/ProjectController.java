package agiledev.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agiledev.persistence.entities.Project;
import agiledev.service.ProjectService;

@Controller
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(
        HttpServletResponse res,
        Model model)
    {
        String auth = res.getHeader("authenticated");
        if(auth == null) {
            model.addAttribute("project", new Project());  //Til að geyma login token
            return "Login";
        } else {
            model.addAttribute("loggedIn", true);
            return "Index";           
        }
    }

    @RequestMapping(value = "/project/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/project/logout", method = RequestMethod.POST)
    public String projectLogin(
        HttpServletRequest req,
        HttpServletResponse res)
    {
        Cookie cookie = new Cookie("projectToken", "");
        cookie.setPath("/");
        res.addCookie(cookie);

        return "redirect:/";
    }


    @RequestMapping(value = "/project/create", method = RequestMethod.GET)
    public String createProjectGET(Model model) 
    {
        model.addAttribute("project", new Project());

        return "CreateProject";
    }


    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    public String createProjectPOST(@ModelAttribute("project") Project project,
                                Model model) 
    {
        this.projectService.save(project);

        return "redirect:/";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String seeAllProjects(Model model) {
        model.addAttribute("projects", this.projectService.findAll());

        return "All";
    }

}
