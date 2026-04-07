package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.systemregister.integration.db.model.AiApplicationEntity;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

@CircuitBreaker(name = "AiApplicationRepository")
public interface AiApplicationRepository extends JpaRepository<AiApplicationEntity, String>, JpaSpecificationExecutor<AiApplicationEntity> {

	List<AiApplicationEntity> findByAiApplicationId(String aiApplicationId);

	List<AiApplicationEntity> findByStatus(AIApplicationStatus status);

	List<AiApplicationEntity> findByRiskCategory(AIRiskCategory riskCategory);

	List<AiApplicationEntity> findBySystemId(String systemId);
}
