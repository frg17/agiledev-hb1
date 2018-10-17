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

    /**
     * Bætir við hlutum í gagnagrunn þegar er runnað
     * forritið. Gott að nota í development til að 
     * hafa eitthvað í gagnagrunninum án þess að manually þurfa
     * að setja það inn í hvert skipti þegar forritið er keyrt.
     * 
     * Bættið bara við köllum á viðeigandi repo eins og 
     * fyrir neðan. (Ef repo er ekki sem parameter í falli, 
     * þá bætiru henni bara við)
     */
    @Bean
    public CommandLineRunner demoData(ProjectRepository repo) {
        return args -> { 
            repo.save(new Project("blarg", "blorg"));
        };
    }
}
