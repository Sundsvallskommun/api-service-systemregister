package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.SystemServiceLinkEntity;

@CircuitBreaker(name = "SystemServiceLinkRepository")
public interface SystemServiceLinkRepository extends JpaRepository<SystemServiceLinkEntity, String> {

	List<SystemServiceLinkEntity> findBySystemId(String systemId);

	List<SystemServiceLinkEntity> findByServiceId(String serviceId);
}
