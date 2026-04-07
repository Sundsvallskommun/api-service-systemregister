package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.systemregister.integration.db.model.ServiceEntity;

@CircuitBreaker(name = "ServiceRepository")
public interface ServiceRepository extends JpaRepository<ServiceEntity, String>, JpaSpecificationExecutor<ServiceEntity> {

	List<ServiceEntity> findByServiceId(String serviceId);

	List<ServiceEntity> findByServiceType(String serviceType);

	List<ServiceEntity> findByOwnerOrganizationId(String ownerOrganizationId);
}
