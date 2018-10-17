

package agiledev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import agiledev.interceptors.AuthInterceptor;

/*
    Config for adding interceptors
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Add authentication interceptor. Ignore static assets.
        registry.addInterceptor(authInterceptor).excludePathPatterns("/css/**");
    }
}