package se.sundsvall.systemregister.integration.db.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class KodPersonuppgiftEntityTest {

	@Test
	void testBean() {
		assertThat(KodPersonuppgiftEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanHashCodeExcluding("lagrum", "gallringsfristAr"),
			hasValidBeanEqualsExcluding("lagrum", "gallringsfristAr"),
			hasValidBeanToStringExcluding("lagrum", "gallringsfristAr")));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1;
		final var kod = "kod";
		final var namn = "namn";
		final var ordning = 2;
		final var beskrivning = "beskrivning";

		final var result = KodPersonuppgiftEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withOrdning(ordning)
			.withBeskrivning(beskrivning);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getOrdning()).isEqualTo(ordning);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KodPersonuppgiftEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KodPersonuppgiftEntity()).hasAllNullFieldsOrProperties();
	}
}
