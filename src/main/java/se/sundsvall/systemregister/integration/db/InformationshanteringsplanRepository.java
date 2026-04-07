package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.InformationshanteringsplanEntity;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;

@CircuitBreaker(name = "InformationshanteringsplanRepository")
public interface InformationshanteringsplanRepository extends JpaRepository<InformationshanteringsplanEntity, String> {

	List<InformationshanteringsplanEntity> findByOrganisationId(String organisationId);

	List<InformationshanteringsplanEntity> findByStatus(IHPlanStatus status);
}
