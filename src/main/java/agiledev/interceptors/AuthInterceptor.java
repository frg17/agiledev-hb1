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
        Cookie cookie = WebUtils.getCookie(request, "projectToken");
        if(cookie != null) {
            Project project = this.projectService.findByToken(cookie.getValue());
            System.out.println(cookie.getValue());
            response.addCookie(new Cookie("projectToken", cookie.getValue()));
        }

        return true;
    }

}
