package se.sundsvall.systemregister.service.mapper;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.integration.db.model.InformationshanteringsplanEntity;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;

import static org.assertj.core.api.Assertions.assertThat;

class InformationshanteringsplanMapperTest {

	@Test
	void toModel() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withVersion("1.0.0")
			.withBeskrivning("Description")
			.withOrganisationId("org-123")
			.withGiltigFran(LocalDate.of(2025, 1, 1))
			.withGiltigTom(LocalDate.of(2025, 12, 31))
			.withBeslutsdatum(LocalDate.of(2024, 12, 1))
			.withBeslutsparagraf("5 kap 1 par")
			.withStatus(IHPlanStatus.BESLUTAD);

		final var result = InformationshanteringsplanMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getVersion()).isEqualTo("1.0.0");
		assertThat(result.getBeskrivning()).isEqualTo("Description");
		assertThat(result.getOrganisationId()).isEqualTo("org-123");
		assertThat(result.getGiltigFran()).isEqualTo(LocalDate.of(2025, 1, 1));
		assertThat(result.getGiltigTom()).isEqualTo(LocalDate.of(2025, 12, 31));
		assertThat(result.getBeslutsdatum()).isEqualTo(LocalDate.of(2024, 12, 1));
		assertThat(result.getBeslutsparagraf()).isEqualTo("5 kap 1 par");
		assertThat(result.getStatus()).isEqualTo("BESLUTAD");
	}

	@Test
	void toModelNull() {
		final var result = InformationshanteringsplanMapper.toModel(null);

		assertThat(result).isNull();
	}

	@Test
	void toModelWithNullStatus() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withStatus(null);

		final var result = InformationshanteringsplanMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getStatus()).isNull();
	}

	@Test
	void toEntity() {
		final var model = Informationshanteringsplan.create()
			.withNamn("Test Plan")
			.withVersion("1.0.0")
			.withBeskrivning("Description")
			.withOrganisationId("org-123")
			.withGiltigFran(LocalDate.of(2025, 1, 1))
			.withGiltigTom(LocalDate.of(2025, 12, 31))
			.withBeslutsdatum(LocalDate.of(2024, 12, 1))
			.withBeslutsparagraf("5 kap 1 par")
			.withStatus("BESLUTAD");

		final var result = InformationshanteringsplanMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getVersion()).isEqualTo("1.0.0");
		assertThat(result.getBeskrivning()).isEqualTo("Description");
		assertThat(result.getOrganisationId()).isEqualTo("org-123");
		assertThat(result.getGiltigFran()).isEqualTo(LocalDate.of(2025, 1, 1));
		assertThat(result.getGiltigTom()).isEqualTo(LocalDate.of(2025, 12, 31));
		assertThat(result.getBeslutsdatum()).isEqualTo(LocalDate.of(2024, 12, 1));
		assertThat(result.getBeslutsparagraf()).isEqualTo("5 kap 1 par");
		assertThat(result.getStatus()).isEqualTo(IHPlanStatus.BESLUTAD);
	}

	@Test
	void toEntityNull() {
		final var result = InformationshanteringsplanMapper.toEntity(null);

		assertThat(result).isNull();
	}

	@Test
	void toEntityWithId() {
		final var model = Informationshanteringsplan.create()
			.withId("plan-id")
			.withNamn("Test Plan");

		final var result = InformationshanteringsplanMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("plan-id");
		assertThat(result.getNamn()).isEqualTo("Test Plan");
	}

	@Test
	void toEntityWithNullStatus() {
		final var model = Informationshanteringsplan.create()
			.withNamn("Test Plan")
			.withStatus(null);

		final var result = InformationshanteringsplanMapper.toEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getStatus()).isNull();
	}
}
