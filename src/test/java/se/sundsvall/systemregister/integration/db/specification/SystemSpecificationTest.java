package se.sundsvall.systemregister.integration.db.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemSpecificationTest {

	@Test
	void createSpecificationReturnsNonNullSpecification() {
		final var parameters = new SystemSearchParameters();
		parameters.setStatus("PRODUCTION");
		parameters.setSearch("HR");
		parameters.setSystemManagerId("manager-1");
		parameters.setOwnerOrganizationId("org-1");

		final var spec = SystemSpecification.createSpecification(parameters);

		assertThat(spec).isNotNull();
	}

	@Test
	void withStatusWithValue() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<Object> statusPath = mock();
		final Predicate statusPred = mock();

		doReturn(statusPath).when(root).get("status");
		when(cb.equal(statusPath, SystemStatus.PRODUCTION)).thenReturn(statusPred);

		final var spec = SystemSpecification.withStatus("PRODUCTION");
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(statusPred);
	}

	@Test
	void withStatusWithNull() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Predicate alwaysTrue = mock();

		when(cb.and()).thenReturn(alwaysTrue);

		final var spec = SystemSpecification.withStatus(null);
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(alwaysTrue);
	}

	@Test
	void withSystemManagerIdWithValue() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<String> path = mock();
		final Predicate pred = mock();

		doReturn(path).when(root).get("systemManagerId");
		when(cb.equal(path, "manager-1")).thenReturn(pred);

		final var spec = SystemSpecification.withSystemManagerId("manager-1");
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(pred);
	}

	@Test
	void withOwnerOrganizationIdWithValue() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<String> path = mock();
		final Predicate pred = mock();

		doReturn(path).when(root).get("ownerOrganizationId");
		when(cb.equal(path, "org-1")).thenReturn(pred);

		final var spec = SystemSpecification.withOwnerOrganizationId("org-1");
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(pred);
	}

	@Test
	void withSearchWithValue() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<String> namePath = mock();
		final Path<String> systemIdPath = mock();
		final Expression<String> lowerName = mock();
		final Expression<String> lowerId = mock();
		final Predicate namePred = mock();
		final Predicate idPred = mock();
		final Predicate orPred = mock();

		doReturn(namePath).when(root).get("name");
		doReturn(systemIdPath).when(root).get("systemId");
		when(cb.lower(namePath)).thenReturn(lowerName);
		when(cb.lower(systemIdPath)).thenReturn(lowerId);
		when(cb.like(lowerName, "%hr%")).thenReturn(namePred);
		when(cb.like(lowerId, "%hr%")).thenReturn(idPred);
		when(cb.or(namePred, idPred)).thenReturn(orPred);

		final var spec = SystemSpecification.withSearch("HR");
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(orPred);
	}

	@Test
	void withSearchWithNull() {
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Predicate alwaysTrue = mock();

		when(cb.and()).thenReturn(alwaysTrue);

		final var spec = SystemSpecification.withSearch(null);
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(alwaysTrue);
	}

}
