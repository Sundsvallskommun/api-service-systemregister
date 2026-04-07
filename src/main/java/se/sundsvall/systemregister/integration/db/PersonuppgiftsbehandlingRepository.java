package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingEntity;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

@CircuitBreaker(name = "PersonuppgiftsbehandlingRepository")
public interface PersonuppgiftsbehandlingRepository extends JpaRepository<PersonuppgiftsbehandlingEntity, String>, JpaSpecificationExecutor<PersonuppgiftsbehandlingEntity> {

	List<PersonuppgiftsbehandlingEntity> findByBehandlingId(String behandlingId);

	List<PersonuppgiftsbehandlingEntity> findByStatus(ProcessingStatus status);
}
