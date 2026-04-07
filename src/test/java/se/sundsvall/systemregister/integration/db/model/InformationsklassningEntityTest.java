package se.sundsvall.systemregister.integration.db.model;

import java.time.LocalDate;
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

class InformationsklassningEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(InformationsklassningEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var handlingstypId = "handlingstypId";
		final var konfidentialitet = 3;
		final var riktighet = 2;
		final var tillganglighet = 1;
		final var sparbarhet = 3;
		final var klassningDatum = LocalDate.now();
		final var klassadAv = "klassadAv";
		final var motivering = "motivering";

		final var result = InformationsklassningEntity.create()
			.withHandlingstypId(handlingstypId)
			.withKonfidentialitet(konfidentialitet)
			.withRiktighet(riktighet)
			.withTillganglighet(tillganglighet)
			.withSparbarhet(sparbarhet)
			.withKlassningDatum(klassningDatum)
			.withKlassadAv(klassadAv)
			.withMotivering(motivering);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getHandlingstypId()).isEqualTo(handlingstypId);
		assertThat(result.getKonfidentialitet()).isEqualTo(konfidentialitet);
		assertThat(result.getRiktighet()).isEqualTo(riktighet);
		assertThat(result.getTillganglighet()).isEqualTo(tillganglighet);
		assertThat(result.getSparbarhet()).isEqualTo(sparbarhet);
		assertThat(result.getKlassningDatum()).isEqualTo(klassningDatum);
		assertThat(result.getKlassadAv()).isEqualTo(klassadAv);
		assertThat(result.getMotivering()).isEqualTo(motivering);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(InformationsklassningEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new InformationsklassningEntity()).hasAllNullFieldsOrProperties();
	}
}
