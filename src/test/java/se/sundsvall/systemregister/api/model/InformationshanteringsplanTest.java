package se.sundsvall.systemregister.api.model;

import java.time.LocalDate;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class InformationshanteringsplanTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt(365)), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Informationshanteringsplan.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var namn = "namn";
		final var version = "version";
		final var beskrivning = "beskrivning";
		final var organisationId = "organisationId";
		final var giltigFran = LocalDate.now();
		final var giltigTom = LocalDate.now().plusDays(1);
		final var beslutsdatum = LocalDate.now().plusDays(2);
		final var beslutsparagraf = "beslutsparagraf";
		final var status = "status";

		final var result = Informationshanteringsplan.create()
			.withId(id)
			.withNamn(namn)
			.withVersion(version)
			.withBeskrivning(beskrivning)
			.withOrganisationId(organisationId)
			.withGiltigFran(giltigFran)
			.withGiltigTom(giltigTom)
			.withBeslutsdatum(beslutsdatum)
			.withBeslutsparagraf(beslutsparagraf)
			.withStatus(status);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getVersion()).isEqualTo(version);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getOrganisationId()).isEqualTo(organisationId);
		assertThat(result.getGiltigFran()).isEqualTo(giltigFran);
		assertThat(result.getGiltigTom()).isEqualTo(giltigTom);
		assertThat(result.getBeslutsdatum()).isEqualTo(beslutsdatum);
		assertThat(result.getBeslutsparagraf()).isEqualTo(beslutsparagraf);
		assertThat(result.getStatus()).isEqualTo(status);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Informationshanteringsplan.create()).hasAllNullFieldsOrProperties();
		assertThat(new Informationshanteringsplan()).hasAllNullFieldsOrProperties();
	}
}
