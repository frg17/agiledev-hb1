package agiledev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import agiledev.persistence.entities.Project;
import agiledev.persistence.repositories.ProjectRepository;

/**
 * The main class of the project.
 * By running the main class of {@link Application} then you start the Spring Boot system
 */
@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    //Setja inn data í database þegar forrit fer af stað.
    @Bean
    public CommandLineRunner demoData(ProjectRepository repo) {
        return args -> { 
            repo.save(new Project("blarg", "blorg"));
        };
    }
}
