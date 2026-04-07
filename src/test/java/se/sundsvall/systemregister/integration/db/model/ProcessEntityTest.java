package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class ProcessEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(ProcessEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var processkod = "processkod";
		final var namn = "namn";
		final var beskrivning = "beskrivning";
		final var verksamhetstyp = "verksamhetstyp";
		final var processgrupId = "processgrupId";
		final var aktiv = true;

		final var result = ProcessEntity.create()
			.withProcesskod(processkod)
			.withNamn(namn)
			.withBeskrivning(beskrivning)
			.withVerksamhetstyp(verksamhetstyp)
			.withProcessgrupId(processgrupId)
			.withAktiv(aktiv);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getProcesskod()).isEqualTo(processkod);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getVerksamhetstyp()).isEqualTo(verksamhetstyp);
		assertThat(result.getProcessgrupId()).isEqualTo(processgrupId);
		assertThat(result.getAktiv()).isEqualTo(aktiv);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(ProcessEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new ProcessEntity()).hasAllNullFieldsOrProperties();
	}
}
