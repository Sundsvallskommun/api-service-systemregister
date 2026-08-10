package se.sundsvall.systemregister.service.mapper;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.integration.db.model.InformationsklassningEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class InformationsklassningMapperTest {
	@Test
	void toModel() {
		final var entity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp id")
			.withKonfidentialitet(1)
			.withKonfidentialitetMotivering("KonfidentialitetMotivering")
			.withRiktighet(2)
			.withRiktighetMotivering("RiktighetMotivering")
			.withTillganglighet(3)
			.withTillganglighetMotivering("TillganglighetMotivering")
			.withSparbarhet(4)
			.withKlassningDatum(LocalDate.of(2025, 1, 1))
			.withKlassadAv("test person")
			.withSamhallsviktigt(true)
			.withSamhallsviktigtMotivering("SamhallsviktigtMotivering");

		final var result = InformationsklassningMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp id");
		assertThat(result.getKonfidentialitet()).isEqualTo(1);
		assertThat(result.getKonfidentialitetMotivering()).isEqualTo("KonfidentialitetMotivering");
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getRiktighetMotivering()).isEqualTo("RiktighetMotivering");
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getTillganglighetMotivering()).isEqualTo("TillganglighetMotivering");
		assertThat(result.getSparbarhet()).isEqualTo(4);
		assertThat(result.getKlassningDatum()).isEqualTo(LocalDate.of(2025, 1, 1));
		assertThat(result.getKlassadAv()).isEqualTo("test person");
		assertThat(result.getSamhallsviktigt()).isEqualTo(true);
		assertThat(result.getSamhallsviktigtMotivering()).isEqualTo("SamhallsviktigtMotivering");

	}

	@Test
	void toModelNull() {
		final var result = InformationsklassningMapper.toModel(null);

		assertThat(result).isNull();
	}

	@Test
	void toEntity() {
		final var model = Informationsklassning.create()
			.withHandlingstypId("handlingstyp id")
			.withKonfidentialitet(1)
			.withKonfidentialitetMotivering("KonfidentialitetMotivering")
			.withRiktighet(2)
			.withRiktighetMotivering("RiktighetMotivering")
			.withTillganglighet(3)
			.withTillganglighetMotivering("TillganglighetMotivering")
			.withSparbarhet(4)
			.withKlassningDatum(LocalDate.of(2024, 2, 3))
			.withKlassadAv("test person")
			.withSamhallsviktigt(true)
			.withSamhallsviktigtMotivering("SamhallsviktigtMotivering");

		final var result = InformationsklassningMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp id");
		assertThat(result.getKonfidentialitet()).isEqualTo(1);
		assertThat(result.getKonfidentialitetMotivering()).isEqualTo("KonfidentialitetMotivering");
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getRiktighetMotivering()).isEqualTo("RiktighetMotivering");
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getTillganglighetMotivering()).isEqualTo("TillganglighetMotivering");
		assertThat(result.getSparbarhet()).isEqualTo(4);
		assertThat(result.getKlassningDatum()).isEqualTo(LocalDate.of(2024, 2, 3));
		assertThat(result.getKlassadAv()).isEqualTo("test person");
		assertThat(result.getSamhallsviktigt()).isEqualTo(true);
		assertThat(result.getSamhallsviktigtMotivering()).isEqualTo("SamhallsviktigtMotivering");

	}

	@Test
	void toEntityNull() {
		final var result = InformationsklassningMapper.toEntity(null);

		assertThat(result).isNull();
	}

	@Test
	void toEntityWithId() {
		final var model = Informationsklassning.create()
			.withId("id");

		final var result = InformationsklassningMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id");
	}
}
