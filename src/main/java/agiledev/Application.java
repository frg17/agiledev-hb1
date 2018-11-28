package agiledev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import agiledev.persistence.entities.Project;
import agiledev.persistence.entities.UserStory;
import agiledev.persistence.repositories.ProjectRepository;
import agiledev.persistence.repositories.UserStoryRepository;

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
    public CommandLineRunner demoData(ProjectRepository projectRepo, UserStoryRepository userStoryRepo) {
        Project pro = new Project("blarg", "blorg");

        UserStory us = new UserStory();
        us.setAuthor("Jón");
        us.setTextContent("As a passenger, I want to submit a review for a flight I have been on, "
                            + "in order to express my commendation or frustration to someone.");
        us.setProject(pro);

        UserStory us2 = new UserStory();
        us2.setAuthor("Gunna");
        us2.setTextContent("As a webmaster, I want to check reviews before they appear on the website, "
                            + "to ensure they do not contain spam.");
        us2.setProject(pro);

        return args -> { 
            projectRepo.save(pro);
            userStoryRepo.save(us);
            userStoryRepo.save(us2);
        };
    }
}
