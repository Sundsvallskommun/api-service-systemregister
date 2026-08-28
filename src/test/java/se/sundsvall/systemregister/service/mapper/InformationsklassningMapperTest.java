package se.sundsvall.systemregister.service.mapper;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.integration.db.model.InformationsklassningEntity;

import static org.assertj.core.api.Assertions.assertThat;

class InformationsklassningMapperTest {
	@Test
	void toModel() {
		final var entity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp id")
			.withKonfidentialitet(1)
			.withRiktighet(2)
			.withTillganglighet(3)
			.withSparbarhet(4)
			.withKlassningDatum(LocalDate.of(2025, Month.JANUARY, 1))
			.withKlassadAv("test person");

		final var result = InformationsklassningMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp id");
		assertThat(result.getKonfidentialitet()).isEqualTo(1);
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getSparbarhet()).isEqualTo(4);
		assertThat(result.getKlassningDatum()).isEqualTo(LocalDate.of(2025, Month.JANUARY, 1));
		assertThat(result.getKlassadAv()).isEqualTo("test person");
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
			.withRiktighet(2)
			.withTillganglighet(3)
			.withSparbarhet(4)
			.withKlassningDatum(LocalDate.of(2024, Month.FEBRUARY, 3))
			.withKlassadAv("test person");

		final var result = InformationsklassningMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp id");
		assertThat(result.getKonfidentialitet()).isEqualTo(1);
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getSparbarhet()).isEqualTo(4);
		assertThat(result.getKlassningDatum()).isEqualTo(LocalDate.of(2024, Month.FEBRUARY, 3));
		assertThat(result.getKlassadAv()).isEqualTo("test person");

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
