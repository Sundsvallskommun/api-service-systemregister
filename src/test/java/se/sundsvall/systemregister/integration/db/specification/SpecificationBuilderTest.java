package se.sundsvall.systemregister.integration.db.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecificationBuilderTest {

	@Test
	void buildEqualFilterWithValue() {
		final Root<Object> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<Object> path = mock();
		final Predicate equalPred = mock();

		doReturn(path).when(root).get("status");
		when(cb.equal(path, "PRODUCTION")).thenReturn(equalPred);

		final var spec = SpecificationBuilder.buildEqualFilter("status", "PRODUCTION");
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(equalPred);
	}

	@Test
	void buildEqualFilterWithNullValue() {
		final Root<Object> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Predicate alwaysTruePred = mock();

		when(cb.and()).thenReturn(alwaysTruePred);

		final var spec = SpecificationBuilder.buildEqualFilter("status", null);
		final var result = spec.toPredicate(root, query, cb);

		assertThat(result).isEqualTo(alwaysTruePred);
	}
}
