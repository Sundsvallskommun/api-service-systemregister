package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessEntity;

@CircuitBreaker(name = "KlassaProcessRepository")
public interface KlassaProcessRepository extends JpaRepository<KlassaProcessEntity, Integer> {

	List<KlassaProcessEntity> findByProcessgrupId(Integer processgrupId);

	List<KlassaProcessEntity> findByAktiv(Boolean aktiv);
}
