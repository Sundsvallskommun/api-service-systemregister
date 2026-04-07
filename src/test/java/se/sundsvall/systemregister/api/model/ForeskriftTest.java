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

class ForeskriftTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt(365)), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Foreskrift.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var beteckning = "beteckning";
		final var namn = "namn";
		final var beskrivning = "beskrivning";
		final var utfardare = "utfardare";
		final var giltigFran = LocalDate.now();
		final var giltigTom = LocalDate.now().plusDays(1);
		final var url = "url";

		final var result = Foreskrift.create()
			.withId(id)
			.withBeteckning(beteckning)
			.withNamn(namn)
			.withBeskrivning(beskrivning)
			.withUtfardare(utfardare)
			.withGiltigFran(giltigFran)
			.withGiltigTom(giltigTom)
			.withUrl(url);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(Foreskrift.create()).hasAllNullFieldsOrProperties();
		assertThat(new Foreskrift()).hasAllNullFieldsOrProperties();
	}
}
