package se.sundsvall.systemregister.integration.db.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class KodBevarandeLagstodEntityTest {

	@Test
	void testBean() {
		assertThat(KodBevarandeLagstodEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1;
		final var kod = "kod";
		final var namn = "namn";
		final var ordning = 2;
		final var gallringsfristAr = 3;
		final var lagrum = "lagrum";
		final var beskrivning = "beskrivning";

		final var result = KodBevarandeLagstodEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withOrdning(ordning)
			.withGallringsfristAr(gallringsfristAr)
			.withLagrum(lagrum)
			.withBeskrivning(beskrivning);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getOrdning()).isEqualTo(ordning);
		assertThat(result.getGallringsfristAr()).isEqualTo(gallringsfristAr);
		assertThat(result.getLagrum()).isEqualTo(lagrum);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KodBevarandeLagstodEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KodBevarandeLagstodEntity()).hasAllNullFieldsOrProperties();
	}
}
