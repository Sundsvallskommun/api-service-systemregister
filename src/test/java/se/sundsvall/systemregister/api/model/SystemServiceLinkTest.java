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

class SystemServiceLinkTest {

	@Test
	void testBean() {
		assertThat(SystemServiceLink.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var systemId = "systemId";
		final var serviceId = "serviceId";
		final var direction = "direction";
		final var description = "description";

		final var result = SystemServiceLink.create()
			.withId(id)
			.withSystemId(systemId)
			.withServiceId(serviceId)
			.withDirection(direction)
			.withDescription(description);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getSystemId()).isEqualTo(systemId);
		assertThat(result.getServiceId()).isEqualTo(serviceId);
		assertThat(result.getDirection()).isEqualTo(direction);
		assertThat(result.getDescription()).isEqualTo(description);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(SystemServiceLink.create()).hasAllNullFieldsOrProperties();
		assertThat(new SystemServiceLink()).hasAllNullFieldsOrProperties();
	}
}
