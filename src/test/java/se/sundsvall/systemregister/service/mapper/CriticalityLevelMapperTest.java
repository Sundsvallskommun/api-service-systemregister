package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.CriticalityLevel;
import se.sundsvall.systemregister.integration.db.model.CriticalityLevelEntity;

import static org.assertj.core.api.Assertions.assertThat;

class CriticalityLevelMapperTest {

	@Test
	void toCriticalityLevel() {
		final var entity = CriticalityLevelEntity.create()
			.withName("Critical")
			.withCode("CRITICAL")
			.withDescription("Systems critical to operations")
			.withLevel(1)
			.withColor("FF0000")
			.withIsActive(true);
		entity.withId("critical-1");

		final var result = CriticalityLevelMapper.toCriticalityLevel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("critical-1");
		assertThat(result.getName()).isEqualTo("Critical");
		assertThat(result.getCode()).isEqualTo("CRITICAL");
		assertThat(result.getDescription()).isEqualTo("Systems critical to operations");
		assertThat(result.getLevel()).isEqualTo(1);
		assertThat(result.getColor()).isEqualTo("FF0000");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toCriticalityLevelNull() {
		assertThat(CriticalityLevelMapper.toCriticalityLevel(null)).isNull();
	}

	@Test
	void toCriticalityLevelEntity() {
		final var model = CriticalityLevel.create()
			.withName("Critical")
			.withCode("CRITICAL")
			.withDescription("Systems critical to operations")
			.withLevel(1)
			.withColor("FF0000")
			.withIsActive(true);

		final var result = CriticalityLevelMapper.toCriticalityLevelEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Critical");
		assertThat(result.getCode()).isEqualTo("CRITICAL");
		assertThat(result.getDescription()).isEqualTo("Systems critical to operations");
		assertThat(result.getLevel()).isEqualTo(1);
		assertThat(result.getColor()).isEqualTo("FF0000");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toCriticalityLevelEntityNull() {
		assertThat(CriticalityLevelMapper.toCriticalityLevelEntity(null)).isNull();
	}

	@Test
	void toCriticalityLevelList() {
		final var entity1 = CriticalityLevelEntity.create()
			.withName("Critical");
		entity1.withId("critical-1");
		final var entity2 = CriticalityLevelEntity.create()
			.withName("High");
		entity2.withId("critical-2");
		final var entities = List.of(entity1, entity2);

		final var result = CriticalityLevelMapper.toCriticalityLevelList(entities);

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getName()).isEqualTo("Critical");
		assertThat(result.get(1).getName()).isEqualTo("High");
	}

	@Test
	void toCriticalityLevelListNull() {
		assertThat(CriticalityLevelMapper.toCriticalityLevelList(null)).isNull();
	}

	@Test
	void updateCriticalityLevelEntity() {
		final var entity = CriticalityLevelEntity.create()
			.withName("Old Name")
			.withCode("OLD");

		final var model = CriticalityLevel.create()
			.withName("New Name")
			.withDescription("New Description")
			.withLevel(2)
			.withColor("00FF00");

		CriticalityLevelMapper.updateCriticalityLevelEntity(entity, model);

		assertThat(entity.getName()).isEqualTo("New Name");
		assertThat(entity.getCode()).isEqualTo("OLD");
		assertThat(entity.getDescription()).isEqualTo("New Description");
		assertThat(entity.getLevel()).isEqualTo(2);
		assertThat(entity.getColor()).isEqualTo("00FF00");
	}
}
