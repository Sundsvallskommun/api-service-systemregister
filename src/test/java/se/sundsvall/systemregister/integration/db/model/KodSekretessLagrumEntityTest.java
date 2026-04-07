package se.sundsvall.systemregister.integration.db.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class KodSekretessLagrumEntityTest {

	@Test
	void testBean() {
		assertThat(KodSekretessLagrumEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanHashCodeExcluding("gallringsfristAr"),
			hasValidBeanEqualsExcluding("gallringsfristAr"),
			hasValidBeanToStringExcluding("gallringsfristAr")));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1;
		final var kod = "kod";
		final var namn = "namn";
		final var ordning = 2;
		final var lagrum = "lagrum";
		final var beskrivning = "beskrivning";

		final var result = KodSekretessLagrumEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withOrdning(ordning)
			.withLagrum(lagrum)
			.withBeskrivning(beskrivning);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getOrdning()).isEqualTo(ordning);
		assertThat(result.getLagrum()).isEqualTo(lagrum);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KodSekretessLagrumEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KodSekretessLagrumEntity()).hasAllNullFieldsOrProperties();
	}
}
