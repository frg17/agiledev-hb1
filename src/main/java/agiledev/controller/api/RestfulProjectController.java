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
    public Object projectLogin(@RequestBody Project token, HttpServletResponse res)
    {
        
        if(token.getToken() != null) {
            Project project = this.projectService.findByToken(token.getToken());
            if(project != null) {
                try {
                    Cookie cookie = new Cookie("projectToken", token.getToken());
                    cookie.setPath("/");
                    cookie.setMaxAge(3600 * 24 * 365);
                    res.addCookie(cookie);
                } catch (Exception e) {

                }

                return new JSONResponse(true, "Log in success.", project);
            }
        }

        return new JSONResponse(false, "Request body must include token.", null);
    }


    /*
        Logs user out
    */
    @PostMapping("api/projects/logout")
    public Object projectLogout(
        HttpServletRequest req,
        HttpServletResponse res)
    {
        Cookie cookie = new Cookie("projectToken", "");
        cookie.setPath("/");
        res.addCookie(cookie);

        return new JSONResponse(true, "Logout success", null);
    }



    /*
        Creates a new project on the server
    */
    @PostMapping("api/projects/create")
    public Object createProjectPOST(@RequestBody Project project) 
    {

        if(this.projectService.findByToken(project.getToken()) != null) {
            return new JSONResponse(false, "Project token already exists", null);
        }

        if (project.getToken() != null || !project.getToken().equals("")) {
                Project created =this.projectService.save(project);
                return new JSONResponse(true, "Project Created", created);            
        }
    
        return new JSONResponse(false, "Request body must include 'name' and 'token'.", null);
    }
}
