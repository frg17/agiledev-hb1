package agiledev.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agiledev.persistence.entities.PlanningPokerEstimate;


/**
 * PriorityRepository
 */
public interface PlanningPokerRepository extends JpaRepository<PlanningPokerEstimate, Long> {


}
