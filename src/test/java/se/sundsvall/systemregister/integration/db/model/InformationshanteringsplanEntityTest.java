package se.sundsvall.systemregister.integration.db.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;

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

class InformationshanteringsplanEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(InformationshanteringsplanEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var namn = "namn";
		final var version = "version";
		final var beskrivning = "beskrivning";
		final var organisationId = "organisationId";
		final var giltigFran = LocalDate.now();
		final var giltigTom = LocalDate.now().plusDays(1);
		final var beslutsdatum = LocalDate.now().plusDays(2);
		final var beslutsparagraf = "beslutsparagraf";
		final var status = IHPlanStatus.values()[0];

		final var result = InformationshanteringsplanEntity.create()
			.withNamn(namn)
			.withVersion(version)
			.withBeskrivning(beskrivning)
			.withOrganisationId(organisationId)
			.withGiltigFran(giltigFran)
			.withGiltigTom(giltigTom)
			.withBeslutsdatum(beslutsdatum)
			.withBeslutsparagraf(beslutsparagraf)
			.withStatus(status);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
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
		assertThat(InformationshanteringsplanEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new InformationshanteringsplanEntity()).hasAllNullFieldsOrProperties();
	}
}
