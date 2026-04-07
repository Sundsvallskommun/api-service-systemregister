package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonuppgiftsbehandlingSystemLinkEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(PersonuppgiftsbehandlingSystemLinkEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var behandlingId = "behandlingId";
		final var systemId = "systemId";
		final var description = "description";

		final var result = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId(behandlingId)
			.withSystemId(systemId)
			.withDescription(description);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getBehandlingId()).isEqualTo(behandlingId);
		assertThat(result.getSystemId()).isEqualTo(systemId);
		assertThat(result.getDescription()).isEqualTo(description);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(PersonuppgiftsbehandlingSystemLinkEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new PersonuppgiftsbehandlingSystemLinkEntity()).hasAllNullFieldsOrProperties();
	}
}
