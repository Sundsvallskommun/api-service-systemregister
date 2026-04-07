package se.sundsvall.systemregister.api.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PagedSystemsResponseTest {

	@Test
	void testBean() {
		assertThat(PagedSystemsResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var metadata = new se.sundsvall.dept44.models.api.paging.PagingMetaData();
		final var systems = java.util.List.of(System.create().withId("id-1"));

		final var result = PagedSystemsResponse.create()
			.withMetadata(metadata)
			.withSystems(systems);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getMetadata()).isEqualTo(metadata);
		assertThat(result.getSystems()).isEqualTo(systems);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(PagedSystemsResponse.create()).hasAllNullFieldsOrProperties();
		assertThat(new PagedSystemsResponse()).hasAllNullFieldsOrProperties();
	}
}
