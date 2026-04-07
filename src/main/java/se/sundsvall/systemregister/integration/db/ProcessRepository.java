package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.ProcessEntity;

@CircuitBreaker(name = "ProcessRepository")
public interface ProcessRepository extends JpaRepository<ProcessEntity, String> {

	Optional<ProcessEntity> findByProcesskod(String processkod);

	List<ProcessEntity> findByAktiv(Boolean aktiv);
}
