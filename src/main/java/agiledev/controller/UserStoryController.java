package agiledev.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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


    @RequestMapping(value = "/userstory/create", method = RequestMethod.GET)
    public String createUserStoryGET(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        Model model) 
    {
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";

        model.addAttribute("userStory", new UserStory());
        return "userstories/create";
    }


    /*
        Should create a new user story on server.
    */
    @RequestMapping(value = "/userstory/create", method = RequestMethod.POST)
    public String createUserStory(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        @ModelAttribute("userStory") UserStory userStory,
        HttpServletResponse res,
        Model model) 
    {
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";

        Long projectId = getProjectId(projectToken);
        userStory.setProjectId(projectId);
        this.userStoryService.save(userStory);

        return "redirect:/";
    }


    @RequestMapping(value = "/userstory/edit/{id}", method = RequestMethod.GET)
    public String editUserStoryGET(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        @PathVariable Long id,
        HttpServletResponse res,
        Model model)
    {
        if(!this.auth.isAuthenticated(res, model)) return "redirect:/";
        Long projectId = getProjectId(projectToken);
        UserStory story = this.userStoryService.findOneByIdAndProjectId(id, projectId);

        model.addAttribute("userStory", story);

        return "userstories/edit";
    }

    @RequestMapping(value = "/userstory/edit", method = RequestMethod.PATCH)
    public String editUserStory(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res,
        @ModelAttribute("userStory") UserStory userStory,
        Model model
    ) {
        if (!this.auth.isAuthenticated(res, model)) return "redirect:/";

        this.userStoryService.update(
            userStory.getTextContent(), 
            userStory.getAuthor(), 
            userStory.getId(),
            getProjectId(projectToken)
        );

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