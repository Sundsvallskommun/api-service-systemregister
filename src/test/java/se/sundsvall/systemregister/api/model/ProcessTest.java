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

class ProcessTest {

	@Test
	void testBean() {
		assertThat(Process.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var processkod = "processkod";
		final var namn = "namn";
		final var beskrivning = "beskrivning";
		final var verksamhetstyp = "verksamhetstyp";
		final var processgrupId = "processgrupId";
		final var aktiv = true;

		final var result = Process.create()
			.withId(id)
			.withProcesskod(processkod)
			.withNamn(namn)
			.withBeskrivning(beskrivning)
			.withVerksamhetstyp(verksamhetstyp)
			.withProcessgrupId(processgrupId)
			.withAktiv(aktiv);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getProcesskod()).isEqualTo(processkod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getVerksamhetstyp()).isEqualTo(verksamhetstyp);
		assertThat(result.getProcessgrupId()).isEqualTo(processgrupId);
		assertThat(result.getAktiv()).isEqualTo(aktiv);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Process.create()).hasAllNullFieldsOrProperties();
		assertThat(new Process()).hasAllNullFieldsOrProperties();
	}
}
