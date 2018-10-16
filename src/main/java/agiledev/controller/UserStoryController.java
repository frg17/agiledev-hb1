package agiledev.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;


@Controller
public class UserStoryController {
    private ProjectService projectService;
    private UserStoryService userStoryService;
    private AuthenticationService auth;

    @Autowired
    public UserStoryController(
        ProjectService projectService,
        UserStoryService userStoryService,
        AuthenticationService auth) 
    {
        this.projectService = projectService;
        this.userStoryService = userStoryService;
        this.auth = auth;
    }



    @RequestMapping(value = "/userstory/create", method = RequestMethod.POST)
    public String createUserStory(
        @CookieValue("projectToken") String projectToken,
        @ModelAttribute("userStory") UserStory userStory,
        HttpServletResponse res,
        Model model) 
    {
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/login";


        userStory.setProjectToken(projectToken);
        this.userStoryService.save(userStory);

        return "redirect:/";
    }


}