/*
 * Handles authentication of project.
 */

package agiledev.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import agiledev.service.ProjectService;
import agiledev.persistence.entities.Project;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private ProjectService projectService;

    @Autowired
    public AuthInterceptor(ProjectService service) {
        this.projectService = service;
    }


    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception      
    {

        //99.5% viss að þetta sé ekkert sérstaklega góð leið fyrir authentication.
        //100% Breyta þannig að við notum session en ekki cookie
        Cookie cookie = WebUtils.getCookie(request, "projectToken");
        if(cookie != null) {
            //Skoða hvort sé til verkefni með þessum token.
            Project project = this.projectService.findByToken(cookie.getValue());
            if(project != null) {
                //Ef verkefni er til, þá er user authenticated.
                response.addHeader("authenticated", cookie.getValue());
            }
        }

        return true;
    }

}
