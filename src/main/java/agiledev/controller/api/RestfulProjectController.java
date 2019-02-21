package agiledev.controller.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agiledev.persistence.entities.Project;
import agiledev.service.ProjectService;

@RestController
public class RestfulProjectController {

    private @Autowired ProjectService projectService;

    /*
     *  returns a boolean if you're 
     */
    @GetMapping("api/projects/")
    public Object index(
        @CookieValue(value = "projectToken", defaultValue = "") String projectToken)
    {
        if(projectService.findByToken(projectToken) == null) {
            return new JSONResponse(false, "Token invalid.", null);
        } else {
            return new JSONResponse(true, "Token valid", null);
        }   
    }

    /*
        Handles user login to project.
    */
    @PostMapping("api/projects/login")
    public String projectLogin(@RequestBody Project token, HttpServletResponse res)
    {
        
        if(token.getToken() != null) {
            if(this.projectService.findByToken(token.getToken()) != null) {
                Cookie cookie = new Cookie("projectToken", token.getToken());
                cookie.setPath("/");
                res.addCookie(cookie);

                return "Success";
            }
        }

        return "Failure";
    }


    /*
        Logs user out
    */
    @PostMapping("api/projects/logout")
    public String projectLogout(
        HttpServletRequest req,
        HttpServletResponse res)
    {
        Cookie cookie = new Cookie("projectToken", "");
        cookie.setPath("/");
        res.addCookie(cookie);

        return "Logged out";
    }



    /*
        Creates a new project on the server
    */
    @PostMapping("api/projects/create")
    public String createProjectPOST(@RequestBody Project project) 
    {

        if(this.projectService.findByToken(project.getToken()) != null) {
            return "Project already exists";
        }

        if (project.getToken() != null || !project.getToken().equals("")) {
                this.projectService.save(project);
                return "Project created";            
        }
    
        return "Input not valid";
    }
}
