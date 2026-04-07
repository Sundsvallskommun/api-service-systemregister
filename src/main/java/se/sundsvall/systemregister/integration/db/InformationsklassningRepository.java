package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.InformationsklassningEntity;

@CircuitBreaker(name = "InformationsklassningRepository")
public interface InformationsklassningRepository extends JpaRepository<InformationsklassningEntity, String> {

	List<InformationsklassningEntity> findByHandlingstypId(String handlingstypId);
}
