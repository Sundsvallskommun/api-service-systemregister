package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.ForeskriftEntity;

@CircuitBreaker(name = "ForeskriftRepository")
public interface ForeskriftRepository extends JpaRepository<ForeskriftEntity, String> {

	Optional<ForeskriftEntity> findByBeteckning(String beteckning);
}
