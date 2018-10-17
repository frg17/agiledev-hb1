

package agiledev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import agiledev.interceptors.AuthInterceptor;

/*
    Config for adding interceptors
    More info: https://www.baeldung.com/spring-mvc-handlerinterceptor
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //Hlaða interceptors hér. Autowired sér um þetta á bakvið tjöldin.
    private @Autowired AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Bæta við interceptors hér.                        //Hunsa css (bæta við js kannski.)
        registry.addInterceptor(authInterceptor).excludePathPatterns("/css/**");
    }
}