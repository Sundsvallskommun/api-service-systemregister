package se.sundsvall.systemregister.api.model.system;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;

class SystemSearchParametersTest {

	@Test
	void testBean() {
		MatcherAssert.assertThat(SystemSearchParameters.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals()));
	}

	@Test
	void toStringContainsAllFields() {
		final var parameters = new SystemSearchParameters();
		parameters.setStatus("PRODUCTION");
		parameters.setSearch("HR");
		parameters.setSystemManagerId("manager-1");
		parameters.setOwnerOrganizationId("org-1");

		assertThat(parameters.toString())
			.contains("PRODUCTION")
			.contains("HR")
			.contains("manager-1")
			.contains("org-1")
			.contains("page=1")
			.contains("limit=20")
			.contains("sortBy=[name]");
	}

	@Test
	void equalsReturnsFalseForDifferentSubclass() {
		final var parameters = new SystemSearchParameters();
		final var otherSubclass = new AbstractParameterPagingAndSortingBase() {};
		otherSubclass.setLimit(parameters.getLimit());
		otherSubclass.setSortBy(parameters.getSortBy());

		assertThat(parameters).isNotEqualTo(otherSubclass);
	}
}
