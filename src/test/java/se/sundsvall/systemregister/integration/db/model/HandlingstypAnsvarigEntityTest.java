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

class HandlingstypAnsvarigEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(HandlingstypAnsvarigEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var handlingstypId = "handlingstypId";
		final var organisationId = "organisationId";
		final var roll = "roll";
		final var ansvarTypId = 1;

		final var result = HandlingstypAnsvarigEntity.create()
			.withHandlingstypId(handlingstypId)
			.withOrganisationId(organisationId)
			.withRoll(roll)
			.withAnsvarTypId(ansvarTypId);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getHandlingstypId()).isEqualTo(handlingstypId);
		assertThat(result.getOrganisationId()).isEqualTo(organisationId);
		assertThat(result.getRoll()).isEqualTo(roll);
		assertThat(result.getAnsvarTypId()).isEqualTo(ansvarTypId);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(HandlingstypAnsvarigEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new HandlingstypAnsvarigEntity()).hasAllNullFieldsOrProperties();
	}
}
