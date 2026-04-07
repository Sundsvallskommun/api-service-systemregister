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

class KlassaVerksamhetsomradeEntityTest {

	@Test
	void testBean() {
		assertThat(KlassaVerksamhetsomradeEntity.class, allOf(
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
		final var verksamhetstypId = 2;
		final var aktiv = true;

		final var result = KlassaVerksamhetsomradeEntity.create()
			.withId(id)
			.withKod(kod)
			.withNamn(namn)
			.withVerksamhetstypId(verksamhetstypId)
			.withAktiv(aktiv);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getKod()).isEqualTo(kod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getVerksamhetstypId()).isEqualTo(verksamhetstypId);
		assertThat(result.getAktiv()).isEqualTo(aktiv);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(KlassaVerksamhetsomradeEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new KlassaVerksamhetsomradeEntity()).hasAllNullFieldsOrProperties();
	}
}
