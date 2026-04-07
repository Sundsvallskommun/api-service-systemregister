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

class HandlingstypTest {

	@Test
	void testBean() {
		assertThat(Handlingstyp.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
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
		final var innehallerPersonuppgifter = true;
		final var personuppgiftId = 10;
		final var anmarkning = "anmarkning";

		final var result = Handlingstyp.create()
			.withId(id)
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

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(Handlingstyp.create()).hasAllNullFieldsOrProperties();
		assertThat(new Handlingstyp()).hasAllNullFieldsOrProperties();
	}
}
