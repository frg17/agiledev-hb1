package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import agiledev.persistence.entities.PlanningPokerEstimate;
import agiledev.persistence.entities.PriorityEstimate;


/**
 * PriorityRepository
 */
public interface PlanningPokerRepository extends JpaRepository<PlanningPokerEstimate, Long> {


}