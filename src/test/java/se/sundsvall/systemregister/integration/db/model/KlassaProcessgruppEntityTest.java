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

class KlassaProcessgruppEntityTest {

	@Test
	void testBean() {
		assertThat(KlassaProcessgruppEntity.class, allOf(
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
		final var verksamhetsomradeId = 2;
		final var aktiv = true;

		final var result = KlassaProcessgruppEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withVerksamhetsomradeId(verksamhetsomradeId)
			.withAktiv(aktiv);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getVerksamhetsomradeId()).isEqualTo(verksamhetsomradeId);
		assertThat(result.getAktiv()).isEqualTo(aktiv);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KlassaProcessgruppEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KlassaProcessgruppEntity()).hasAllNullFieldsOrProperties();
	}
}
