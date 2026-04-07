package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.EventLogEntity;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;

@CircuitBreaker(name = "EventLogRepository")
public interface EventLogRepository extends JpaRepository<EventLogEntity, String> {

	List<EventLogEntity> findByEntityTypeAndEntityId(EntityType entityType, String entityId);

	List<EventLogEntity> findByEntityTypeAndEntityIdOrderByPerformedAtDesc(EntityType entityType, String entityId);
}
