package agiledev.controller;

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
    public String loginPage(
        HttpServletResponse res,
        Model model)
    {
        Project project = null;
        if(project == null) {
            System.out.println("Login");
            model.addAttribute("project", new Project());  //Til að geyma login token
            return "Login";
        } else {
            model.addAttribute("project", new Project());
            return "Index";           
        }
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

        model.addAttribute("project", new Project());

        return "Login";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String seeAllProjects(Model model) {
        model.addAttribute("projects", this.projectService.findAll());

        return "All";
    }

}
