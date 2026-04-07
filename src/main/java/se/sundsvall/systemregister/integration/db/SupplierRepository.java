package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.SupplierEntity;

@CircuitBreaker(name = "SupplierRepository")
public interface SupplierRepository extends JpaRepository<SupplierEntity, String> {

	List<SupplierEntity> findByIsActive(Boolean isActive);
}
