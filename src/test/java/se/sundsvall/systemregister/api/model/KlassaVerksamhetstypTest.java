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

class KlassaVerksamhetstypTest {

	@Test
	void testBean() {
		assertThat(KlassaVerksamhetstyp.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = 1;
		final var kod = 2;
		final var namn = "namn";
		final var beskrivning = "beskrivning";

		final var result = KlassaVerksamhetstyp.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withBeskrivning(beskrivning);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KlassaVerksamhetstyp.create()).hasAllNullFieldsOrProperties();
		assertThat(new KlassaVerksamhetstyp()).hasAllNullFieldsOrProperties();
	}
}
