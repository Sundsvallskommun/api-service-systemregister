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

class InformationsklassningTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt(365)), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Informationsklassning.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var handlingstypId = "handlingstypId";
		final var konfidentialitet = 3;
		final var riktighet = 2;
		final var tillganglighet = 1;
		final var sparbarhet = 3;
		final var klassningDatum = LocalDate.now();
		final var klassadAv = "klassadAv";
		final var motivering = "motivering";

		final var result = Informationsklassning.create()
			.withId(id)
			.withHandlingstypId(handlingstypId)
			.withKonfidentialitet(konfidentialitet)
			.withRiktighet(riktighet)
			.withTillganglighet(tillganglighet)
			.withSparbarhet(sparbarhet)
			.withKlassningDatum(klassningDatum)
			.withKlassadAv(klassadAv)
			.withMotivering(motivering);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(Informationsklassning.create()).hasAllNullFieldsOrProperties();
		assertThat(new Informationsklassning()).hasAllNullFieldsOrProperties();
	}
}
