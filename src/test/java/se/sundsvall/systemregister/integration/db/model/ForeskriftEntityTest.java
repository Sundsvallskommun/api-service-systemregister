package se.sundsvall.systemregister.integration.db.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.ForeskriftUtfardare;

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

class ForeskriftEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(ForeskriftEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var beteckning = "beteckning";
		final var namn = "namn";
		final var beskrivning = "beskrivning";
		final var utfardare = ForeskriftUtfardare.values()[0];
		final var giltigFran = LocalDate.now();
		final var giltigTom = LocalDate.now().plusDays(1);
		final var url = "url";

		final var result = ForeskriftEntity.create()
			.withBeteckning(beteckning)
			.withNamn(namn)
			.withBeskrivning(beskrivning)
			.withUtfardare(utfardare)
			.withGiltigFran(giltigFran)
			.withGiltigTom(giltigTom)
			.withUrl(url);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getBeteckning()).isEqualTo(beteckning);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getUtfardare()).isEqualTo(utfardare);
		assertThat(result.getGiltigFran()).isEqualTo(giltigFran);
		assertThat(result.getGiltigTom()).isEqualTo(giltigTom);
		assertThat(result.getUrl()).isEqualTo(url);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(ForeskriftEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new ForeskriftEntity()).hasAllNullFieldsOrProperties();
	}
}
