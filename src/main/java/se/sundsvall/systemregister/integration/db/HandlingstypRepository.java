package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.HandlingstypEntity;

@CircuitBreaker(name = "HandlingstypRepository")
public interface HandlingstypRepository extends JpaRepository<HandlingstypEntity, String> {

	List<HandlingstypEntity> findByIhPlanId(String ihPlanId);
}
