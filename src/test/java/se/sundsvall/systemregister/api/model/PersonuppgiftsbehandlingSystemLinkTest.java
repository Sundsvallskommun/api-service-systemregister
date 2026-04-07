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

class PersonuppgiftsbehandlingSystemLinkTest {

	@Test
	void testBean() {
		assertThat(PersonuppgiftsbehandlingSystemLink.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var behandlingId = "behandlingId";
		final var systemId = "systemId";
		final var description = "description";

		final var result = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId(behandlingId)
			.withSystemId(systemId)
			.withDescription(description);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getBehandlingId()).isEqualTo(behandlingId);
		assertThat(result.getSystemId()).isEqualTo(systemId);
		assertThat(result.getDescription()).isEqualTo(description);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(PersonuppgiftsbehandlingSystemLink.create()).hasAllNullFieldsOrProperties();
		assertThat(new PersonuppgiftsbehandlingSystemLink()).hasAllNullFieldsOrProperties();
	}
}
