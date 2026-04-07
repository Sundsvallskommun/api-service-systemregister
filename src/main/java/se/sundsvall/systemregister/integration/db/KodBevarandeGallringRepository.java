package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.KodBevarandeGallringEntity;

@CircuitBreaker(name = "KodBevarandeGallringRepository")
public interface KodBevarandeGallringRepository extends JpaRepository<KodBevarandeGallringEntity, Integer> {
}
