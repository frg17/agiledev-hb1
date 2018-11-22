package agiledev.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agiledev.persistence.entities.PlanningPokerEstimate;
import agiledev.persistence.repositories.PlanningPokerRepository;

/*
    Service layer for access to persistent data
    concerning PlanningPoker entities.
*/
@Service
public class PlanningPokerService {

    PlanningPokerRepository repository;

    // Dependency injection
    @Autowired
    public PlanningPokerService(PlanningPokerRepository repository) {
        this.repository = repository;
    }

    public PlanningPokerEstimate save(PlanningPokerEstimate estimate) {
        return this.repository.save(estimate);
    }

    public void delete(PlanningPokerEstimate estimate) {
        this.repository.delete(estimate);
    }


    public List<PlanningPokerEstimate> findAll() {        //Eyða seinna, bara fyrir debug
        return this.repository.findAll();
    }



    /**
     * Finnur average af lista af estimates.
     * @param estimates
     * @return
     */
    public Integer findAverage(List<PlanningPokerEstimate> estimates) {
        if (estimates.size() == 0) return 0;
        int sum = 0;
        for (PlanningPokerEstimate es : estimates) {
            sum += es.getEstimate();
        }

        return sum / estimates.size();
    }
}