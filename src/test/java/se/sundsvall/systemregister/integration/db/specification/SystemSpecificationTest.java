package se.sundsvall.systemregister.integration.db.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SystemSpecificationTest {

	@Test
	void createSpecificationCombinesAllFilters() {
		final var parameters = new SystemSearchParameters();
		parameters.setStatus("PRODUCTION");
		parameters.setSearch("HR");
		parameters.setSystemManagerId("manager-1");
		parameters.setOwnerOrganizationId("org-1");

		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();

		when(root.get(anyString())).thenReturn(mock(Path.class));
		when(cb.lower(any())).thenReturn(mock(Expression.class));
		when(cb.equal(any(), ArgumentMatchers.<Object>any())).thenReturn(mock(Predicate.class));
		when(cb.like(any(), anyString(), anyChar())).thenReturn(mock(Predicate.class));
		when(cb.or(any(), any())).thenReturn(mock(Predicate.class));
		lenient().when(cb.and(any(Predicate.class), any(Predicate.class))).thenReturn(mock(Predicate.class));
		lenient().when(cb.and(any(Predicate[].class))).thenReturn(mock(Predicate.class));

		final var spec = SystemSpecification.createSpecification(parameters);
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isNotNull();
		verify(cb, times(3)).equal(any(), ArgumentMatchers.<Object>any());
		verify(cb, times(2)).like(any(), anyString(), anyChar());
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
	void withStatusWithNullValue() {
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
	void withStatusWithInvalidValue() {
		assertThatThrownBy(() -> SystemSpecification.withStatus("invalid"))
			.isInstanceOf(IllegalArgumentException.class);
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
		when(cb.like(lowerName, "%hr%", SystemSpecification.ESCAPE_CHAR)).thenReturn(namePred);
		when(cb.like(lowerId, "%hr%", SystemSpecification.ESCAPE_CHAR)).thenReturn(idPred);
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
