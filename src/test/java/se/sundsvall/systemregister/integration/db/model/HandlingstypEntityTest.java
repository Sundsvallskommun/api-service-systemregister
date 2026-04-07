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

class HandlingstypEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(HandlingstypEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var ihPlanId = "ihPlanId";
		final var klassakProcessId = 1;
		final var namn = "namn";
		final var beskrivning = "beskrivning";
		final var formatId = 2;
		final var bevarandeGallringId = 3;
		final var gallringsfristAr = 4;
		final var gallringsfristTypId = 5;
		final var gallringsforeskriftId = "gallringsforeskriftId";
		final var bevarandeLagstodId = 6;
		final var registreringId = 7;
		final var itSystemId = "itSystemId";
		final var forvaring = "forvaring";
		final var leveransTillArkiv = "leveransTillArkiv";
		final var leveransfristAr = 8;
		final var sekretess = true;
		final var sekretessLagrumId = 9;
		final var innehallerPersonuppgifter = false;
		final var personuppgiftId = 10;
		final var anmarkning = "anmarkning";

		final var result = HandlingstypEntity.create()
			.withIhPlanId(ihPlanId)
			.withKlassakProcessId(klassakProcessId)
			.withNamn(namn)
			.withBeskrivning(beskrivning)
			.withFormatId(formatId)
			.withBevarandeGallringId(bevarandeGallringId)
			.withGallringsfristAr(gallringsfristAr)
			.withGallringsfristTypId(gallringsfristTypId)
			.withGallringsforeskriftId(gallringsforeskriftId)
			.withBevarandeLagstodId(bevarandeLagstodId)
			.withRegistreringId(registreringId)
			.withItSystemId(itSystemId)
			.withForvaring(forvaring)
			.withLeveransTillArkiv(leveransTillArkiv)
			.withLeveransfristAr(leveransfristAr)
			.withSekretess(sekretess)
			.withSekretessLagrumId(sekretessLagrumId)
			.withInnehallerPersonuppgifter(innehallerPersonuppgifter)
			.withPersonuppgiftId(personuppgiftId)
			.withAnmarkning(anmarkning);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getIhPlanId()).isEqualTo(ihPlanId);
		assertThat(result.getKlassakProcessId()).isEqualTo(klassakProcessId);
		assertThat(result.getNamn()).isEqualTo(namn);
		assertThat(result.getBeskrivning()).isEqualTo(beskrivning);
		assertThat(result.getFormatId()).isEqualTo(formatId);
		assertThat(result.getBevarandeGallringId()).isEqualTo(bevarandeGallringId);
		assertThat(result.getGallringsfristAr()).isEqualTo(gallringsfristAr);
		assertThat(result.getGallringsfristTypId()).isEqualTo(gallringsfristTypId);
		assertThat(result.getGallringsforeskriftId()).isEqualTo(gallringsforeskriftId);
		assertThat(result.getBevarandeLagstodId()).isEqualTo(bevarandeLagstodId);
		assertThat(result.getRegistreringId()).isEqualTo(registreringId);
		assertThat(result.getItSystemId()).isEqualTo(itSystemId);
		assertThat(result.getForvaring()).isEqualTo(forvaring);
		assertThat(result.getLeveransTillArkiv()).isEqualTo(leveransTillArkiv);
		assertThat(result.getLeveransfristAr()).isEqualTo(leveransfristAr);
		assertThat(result.getSekretess()).isEqualTo(sekretess);
		assertThat(result.getSekretessLagrumId()).isEqualTo(sekretessLagrumId);
		assertThat(result.getInnehallerPersonuppgifter()).isEqualTo(innehallerPersonuppgifter);
		assertThat(result.getPersonuppgiftId()).isEqualTo(personuppgiftId);
		assertThat(result.getAnmarkning()).isEqualTo(anmarkning);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(HandlingstypEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new HandlingstypEntity()).hasAllNullFieldsOrProperties();
	}
}
