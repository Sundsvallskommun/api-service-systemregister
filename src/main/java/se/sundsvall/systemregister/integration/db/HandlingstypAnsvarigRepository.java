package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.HandlingstypAnsvarigEntity;

@CircuitBreaker(name = "HandlingstypAnsvarigRepository")
public interface HandlingstypAnsvarigRepository extends JpaRepository<HandlingstypAnsvarigEntity, String> {

	List<HandlingstypAnsvarigEntity> findByHandlingstypId(String handlingstypId);

	List<HandlingstypAnsvarigEntity> findByOrganisationId(String organisationId);
}
