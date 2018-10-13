package agiledev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agiledev.persistence.entities.Project;
import agiledev.service.ProjectService;

@Controller
public class Login {

    private ProjectService projectService;

    @Autowired
    public Login(ProjectService projectService) {
        this.projectService = projectService;
    }

    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(Model model) 
    {
        model.addAttribute("project", new Project());

        return "Login";
    }


    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("project") Project project,
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
