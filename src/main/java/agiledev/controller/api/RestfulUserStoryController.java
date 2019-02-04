package agiledev.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.service.AuthenticationService;
import agiledev.service.ProjectService;
import agiledev.service.UserStoryService;

@RestController 
public class RestfulUserStoryController {

    private @Autowired ProjectService projectService;
    private @Autowired UserStoryService userStoryService;
    private @Autowired AuthenticationService auth;


    /*
        Klasi verður að vera merktur sem @RestController
        Láta RequestMapping byrja á /api
        Láta RequestMethod vera:
            * POST ef búið er eitthvað til í gagnagrunn server.
            * PATCH ef verið er að update-a eitthvað sem til er í gagnagrunn.
            * GET ef það er bara verið að ná í eitthvað (ENGU ER BREYTT Á SERVER)
            * DELETE ef verið er að eyða einhverju
        produces = application/json segir Spring að við erum að skila JSON efni.
    */
    @RequestMapping(value = "/api/userstory/create", method = RequestMethod.POST, produces = "application/json") 
    public Object createUserStoryGet(
        HttpServletResponse res, //Response sem að er notað fyrir authentication. Geymir token.
        @RequestBody UserStory story //Segir spring að parse-a JSON sem að client sendir beint í UserStory hlut.
    ) {
        if(!this.auth.isAuthenticated(res)) { //Authentication
            res.setStatus(401); //NotAuthorized status
            return null; //Skila engu.
        }

        story.setProject(this.getProject(res)); //Þarf að nota setja project sem eiganda fyrir user story.
        return this.userStoryService.save(story); //Save-a user story í gagnagrunn. Return object (Verður automatically að JSON)
    }

    @RequestMapping(value = "/api/userstories", method = RequestMethod.GET, produces = "application/json")
    public Object getAllUserStories(HttpServletResponse res) {
        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401);
            return null;
        }
        
        //Authenticated header geymir token.
        Project project = this.getProject(res);
        List<UserStory> stories = project.getUserStories();
        return stories.toArray();
    }

    @RequestMapping(value = "/api/userstory/edit", method = RequestMethod.PATCH, produces = "application/json")
    public Object editUserStory(HttpServletResponse res, @RequestBody UserStory story) {
        if (!this.auth.isAuthenticated(res)) {
            res.setStatus(401);
            return null;
        }

        if(story.getId() == null) {
            res.setStatus(400);
            return "Need ID of user story";
        }

        story.setProject(this.getProject(res));
        return this.userStoryService.save(story);
    }

    @RequestMapping(value = "api/userstory/delete", method = RequestMethod.DELETE)
    public Object deleteUserStory(HttpServletResponse res, @RequestBody UserStory story) {
        if(!this.auth.isAuthenticated(res)) {
            res.setStatus(401);
            return null;
        }

        if(story.getId() == null) {
            res.setStatus(400);
            return "Need ID of user story";
        }

        story.setProject(this.getProject(res));

        this.userStoryService.delete(story);
        return "Sucess";
    }


    /**
     * Helper function til að finna prjoct í gegnum header.
     * @param res
     * @return Project sem er authenticated í header.
     */
    private Project getProject(HttpServletResponse res) {
        return this.projectService.findByToken(res.getHeader("authenticated"));
    }
}