package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.SystemDependencyEntity;

@CircuitBreaker(name = "SystemDependencyRepository")
public interface SystemDependencyRepository extends JpaRepository<SystemDependencyEntity, String> {

	List<SystemDependencyEntity> findBySourceSystemId(String sourceSystemId);

	List<SystemDependencyEntity> findByTargetSystemId(String targetSystemId);
}
