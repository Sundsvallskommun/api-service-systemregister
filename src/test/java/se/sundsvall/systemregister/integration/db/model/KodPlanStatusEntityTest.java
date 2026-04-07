package se.sundsvall.systemregister.integration.db.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class KodPlanStatusEntityTest {

	@Test
	void testBean() {
		assertThat(KodPlanStatusEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanHashCodeExcluding("beskrivning", "lagrum", "gallringsfristAr"),
			hasValidBeanEqualsExcluding("beskrivning", "lagrum", "gallringsfristAr"),
			hasValidBeanToStringExcluding("beskrivning", "lagrum", "gallringsfristAr")));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1;
		final var kod = "kod";
		final var namn = "namn";
		final var ordning = 2;

		final var result = KodPlanStatusEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withOrdning(ordning);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getOrdning()).isEqualTo(ordning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KodPlanStatusEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KodPlanStatusEntity()).hasAllNullFieldsOrProperties();
	}
}
