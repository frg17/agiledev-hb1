package agiledev.controller.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import agiledev.service.AuthenticationService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

@RestController 
public class RestfulUserStoryController {

    private @Autowired ProjectService projectService;
    private @Autowired UserStoryService userStoryService;
    private @Autowired AuthenticationService auth;


    @RequestMapping(value = "/api/userstory/create", method = RequestMethod.POST) 
    public Object createUserStoryGet(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken,
        HttpServletResponse res
    ) {
        if(!this.auth.isAuthenticated(res, null));
        return null;
    }


}