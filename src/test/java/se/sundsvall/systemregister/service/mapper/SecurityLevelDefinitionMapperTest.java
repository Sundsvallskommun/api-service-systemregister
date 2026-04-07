package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.integration.db.model.SecurityLevelDefinitionEntity;

import static org.assertj.core.api.Assertions.assertThat;

class SecurityLevelDefinitionMapperTest {

	@Test
	void toSecurityLevelDefinition() {
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium")
			.withDescription("Medium security level")
			.withConsequence("Moderate risk")
			.withColor("#FFAA00")
			.withIsActive(true);
		entity.withId("id-1");

		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinition(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		assertThat(result.getLevel()).isEqualTo(2);
		assertThat(result.getLabel()).isEqualTo("Medium");
		assertThat(result.getDescription()).isEqualTo("Medium security level");
		assertThat(result.getConsequence()).isEqualTo("Moderate risk");
		assertThat(result.getColor()).isEqualTo("#FFAA00");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toSecurityLevelDefinitionNull() {
		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinition(null);

		assertThat(result).isNull();
	}

	@Test
	void toSecurityLevelDefinitionPartialEntity() {
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K");
		entity.withId("id-1");

		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinition(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		assertThat(result.getLevel()).isNull();
		assertThat(result.getLabel()).isNull();
	}

	@Test
	void toSecurityLevelDefinitions() {
		final var entity1 = SecurityLevelDefinitionEntity.create()
			.withDimension("K");
		entity1.withId("id-1");
		final var entity2 = SecurityLevelDefinitionEntity.create()
			.withDimension("R");
		entity2.withId("id-2");

		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(List.of(entity1, entity2));

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.getFirst().getDimension()).isEqualTo("K");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		assertThat(result.get(1).getDimension()).isEqualTo("R");
	}

	@Test
	void toSecurityLevelDefinitionsEmptyList() {
		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(List.of());

		assertThat(result).isNotNull().isEmpty();
	}

	@Test
	void toSecurityLevelDefinitionsNull() {
		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(null);

		assertThat(result).isNull();
	}

	@Test
	void toSecurityLevelDefinitionEntity() {
		final var model = SecurityLevelDefinition.create()
			.withId("id-1")
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium")
			.withDescription("Medium security level")
			.withConsequence("Moderate risk")
			.withColor("#FFAA00")
			.withIsActive(true);

		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitionEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		assertThat(result.getLevel()).isEqualTo(2);
		assertThat(result.getLabel()).isEqualTo("Medium");
		assertThat(result.getDescription()).isEqualTo("Medium security level");
		assertThat(result.getConsequence()).isEqualTo("Moderate risk");
		assertThat(result.getColor()).isEqualTo("#FFAA00");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toSecurityLevelDefinitionEntityNull() {
		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitionEntity(null);

		assertThat(result).isNull();
	}

	@Test
	void toSecurityLevelDefinitionEntityPartialModel() {
		final var model = SecurityLevelDefinition.create()
			.withId("id-1")
			.withDimension("K");

		final var result = SecurityLevelDefinitionMapper.toSecurityLevelDefinitionEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		assertThat(result.getLevel()).isNull();
		assertThat(result.getLabel()).isNull();
	}
}
