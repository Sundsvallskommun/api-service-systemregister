package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.CriticalityLevelEntity;

@CircuitBreaker(name = "CriticalityLevelRepository")
public interface CriticalityLevelRepository extends JpaRepository<CriticalityLevelEntity, String> {

	List<CriticalityLevelEntity> findByCode(String code);

	List<CriticalityLevelEntity> findByIsActive(Boolean isActive);
}
