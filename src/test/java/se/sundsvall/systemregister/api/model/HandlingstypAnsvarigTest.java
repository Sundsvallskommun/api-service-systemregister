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

class HandlingstypAnsvarigTest {

	@Test
	void testBean() {
		assertThat(HandlingstypAnsvarig.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var handlingstypId = "handlingstypId";
		final var organisationId = "organisationId";
		final var roll = "roll";
		final var ansvarTypId = 1;

		final var result = HandlingstypAnsvarig.create()
			.withId(id)
			.withHandlingstypId(handlingstypId)
			.withOrganisationId(organisationId)
			.withRoll(roll)
			.withAnsvarTypId(ansvarTypId);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getHandlingstypId()).isEqualTo(handlingstypId);
		assertThat(result.getOrganisationId()).isEqualTo(organisationId);
		assertThat(result.getRoll()).isEqualTo(roll);
		assertThat(result.getAnsvarTypId()).isEqualTo(ansvarTypId);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(HandlingstypAnsvarig.create()).hasAllNullFieldsOrProperties();
		assertThat(new HandlingstypAnsvarig()).hasAllNullFieldsOrProperties();
	}
}
