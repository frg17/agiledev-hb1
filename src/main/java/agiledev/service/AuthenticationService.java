package agiledev.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


/*
    Service handles checking if a user is authenticated.
*/

@Component
public class AuthenticationService {


    private @Autowired ProjectService projectService;

    /**
     * Is called in a controller to check if user is authenticated.
     * Adds loggedIn attribute to a model
     * @param res response (holds authentication)
     * @param model model, gets attribute "loggedIn" for template engine to use.
     * @return true if authenticated, else false
     */
    public boolean isAuthenticated(HttpServletResponse res, Model model) {
        String token = res.getHeader("authenticated");
        if (token != null) {
            model.addAttribute("project", this.projectService.findByToken(token));
            model.addAttribute("loggedIn", true);
            return true;
        }
        model.addAttribute("loggedIn", false);
        return false;
    }

    /**
     * Overload af isAuthenticated án model
     * @param res
     * @return
     */
    public boolean isAuthenticated(HttpServletResponse res) {
        String token = res.getHeader("authenticated");
        if (token != null) return true;
        return false;
    }
}