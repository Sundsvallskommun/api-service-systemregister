package se.sundsvall.systemregister.service.mapper;

import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.KlassaVerksamhetstyp;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetstypEntity;

import static org.assertj.core.api.Assertions.assertThat;

class KlassaVerksamhetstypMapperTest {

	@Test
	void toModel() {
		final var entity = KlassaVerksamhetstypEntity.create()
			.withKod(100)
			.withNamn("Allmän verksamhet")
			.withBeskrivning("En beskrivning");
		entity.withId(1);

		final var result = KlassaVerksamhetstypMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		assertThat(result.getKod()).isEqualTo(100);
		assertThat(result.getNamn()).isEqualTo("Allmän verksamhet");
		assertThat(result.getBeskrivning()).isEqualTo("En beskrivning");
	}

	@Test
	void toModelWithNullEntity() {
		final var result = KlassaVerksamhetstypMapper.toModel(null);

		assertThat(result).isNull();
	}

	@Test
	void toEntity() {
		final var model = KlassaVerksamhetstyp.create()
			.withId(1)
			.withKod(100)
			.withNamn("Allmän verksamhet")
			.withBeskrivning("En beskrivning");

		final var result = KlassaVerksamhetstypMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		assertThat(result.getKod()).isEqualTo(100);
		assertThat(result.getNamn()).isEqualTo("Allmän verksamhet");
		assertThat(result.getBeskrivning()).isEqualTo("En beskrivning");
	}

	@Test
	void toEntityWithNullModel() {
		final var result = KlassaVerksamhetstypMapper.toEntity(null);

		assertThat(result).isNull();
	}

	@Test
	void toModelWithMinimalEntity() {
		final var entity = KlassaVerksamhetstypEntity.create()
			.withNamn("Test");

		final var result = KlassaVerksamhetstypMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNull();
		assertThat(result.getKod()).isNull();
		assertThat(result.getNamn()).isEqualTo("Test");
		assertThat(result.getBeskrivning()).isNull();
	}

	@Test
	void toEntityWithMinimalModel() {
		final var model = KlassaVerksamhetstyp.create()
			.withNamn("Test");

		final var result = KlassaVerksamhetstypMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNull();
		assertThat(result.getKod()).isNull();
		assertThat(result.getNamn()).isEqualTo("Test");
		assertThat(result.getBeskrivning()).isNull();
	}
}
