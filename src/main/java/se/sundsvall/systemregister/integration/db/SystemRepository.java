package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;

@CircuitBreaker(name = "SystemRepository")
public interface SystemRepository extends JpaRepository<SystemEntity, String>, JpaSpecificationExecutor<SystemEntity> {
}
