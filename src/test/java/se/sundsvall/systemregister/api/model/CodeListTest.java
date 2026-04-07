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

class CodeListTest {

	@Test
	void testBean() {
		assertThat(CodeList.class, allOf(
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
		final var beskrivning = "beskrivning";
		final var lagrum = "lagrum";
		final var gallringsfristAr = 3;

		final var result = CodeList.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withOrdning(ordning)
			.withBeskrivning(beskrivning)
			.withLagrum(lagrum)
			.withGallringsfristAr(gallringsfristAr);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getOrdning()).isEqualTo(ordning);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getLagrum()).isEqualTo(lagrum);
		assertThat(result.getGallringsfristAr()).isEqualTo(gallringsfristAr);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CodeList.create()).hasAllNullFieldsOrProperties();
		assertThat(new CodeList()).hasAllNullFieldsOrProperties();
	}
}
