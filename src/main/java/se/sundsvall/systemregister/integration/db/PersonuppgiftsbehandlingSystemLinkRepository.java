package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingSystemLinkEntity;

@CircuitBreaker(name = "PersonuppgiftsbehandlingSystemLinkRepository")
public interface PersonuppgiftsbehandlingSystemLinkRepository extends JpaRepository<PersonuppgiftsbehandlingSystemLinkEntity, String> {

	List<PersonuppgiftsbehandlingSystemLinkEntity> findByBehandlingId(String behandlingId);

	List<PersonuppgiftsbehandlingSystemLinkEntity> findBySystemId(String systemId);
}
