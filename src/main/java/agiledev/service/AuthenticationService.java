package agiledev.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class AuthenticationService {
    private ProjectService service;

    @Autowired
    public AuthenticationService(ProjectService service) {
        this.service = service;
    }

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
            model.addAttribute("loggedIn", true);
            return true;
        }
        model.addAttribute("loggedIn", false);
        return false;
    }
}