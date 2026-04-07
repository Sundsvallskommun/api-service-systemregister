package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessgruppEntity;

@CircuitBreaker(name = "KlassaProcessgruppRepository")
public interface KlassaProcessgruppRepository extends JpaRepository<KlassaProcessgruppEntity, Integer> {

	List<KlassaProcessgruppEntity> findByVerksamhetsomradeId(Integer verksamhetsomradeId);

	List<KlassaProcessgruppEntity> findByAktiv(Boolean aktiv);
}
