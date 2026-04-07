package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetsomradeEntity;

@CircuitBreaker(name = "KlassaVerksamhetsomradeRepository")
public interface KlassaVerksamhetsomradeRepository extends JpaRepository<KlassaVerksamhetsomradeEntity, Integer> {

	List<KlassaVerksamhetsomradeEntity> findByVerksamhetstypId(Integer verksamhetstypId);

	List<KlassaVerksamhetsomradeEntity> findByAktiv(Boolean aktiv);
}
