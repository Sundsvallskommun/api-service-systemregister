package se.sundsvall.systemregister.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import se.sundsvall.systemregister.integration.db.model.PersonEntity;

@CircuitBreaker(name = "PersonRepository")
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

	Optional<PersonEntity> findByEmail(String email);

	Optional<PersonEntity> findByUsername(String username);

	List<PersonEntity> findByOrganizationId(String organizationId);
}
