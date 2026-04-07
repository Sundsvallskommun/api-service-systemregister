package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.integration.db.model.SecurityLevelDefinitionEntity;

public final class SecurityLevelDefinitionMapper {

	private SecurityLevelDefinitionMapper() {}

	public static SecurityLevelDefinition toSecurityLevelDefinition(final SecurityLevelDefinitionEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> SecurityLevelDefinition.create()
				.withId(e.getId())
				.withDimension(e.getDimension())
				.withLevel(e.getLevel())
				.withLabel(e.getLabel())
				.withDescription(e.getDescription())
				.withConsequence(e.getConsequence())
				.withColor(e.getColor())
				.withIsActive(e.getIsActive()))
			.orElse(null);
	}

	public static List<SecurityLevelDefinition> toSecurityLevelDefinitions(final List<SecurityLevelDefinitionEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(SecurityLevelDefinitionMapper::toSecurityLevelDefinition)
				.toList())
			.orElse(null);
	}

	public static SecurityLevelDefinitionEntity updateEntity(final SecurityLevelDefinitionEntity entity, final SecurityLevelDefinition dto) {
		return entity
			.withDimension(dto.getDimension())
			.withLevel(dto.getLevel())
			.withLabel(dto.getLabel())
			.withDescription(dto.getDescription())
			.withConsequence(dto.getConsequence())
			.withColor(dto.getColor())
			.withIsActive(dto.getIsActive());
	}

	public static SecurityLevelDefinitionEntity toSecurityLevelDefinitionEntity(final SecurityLevelDefinition model) {
		return Optional.ofNullable(model)
			.map(m -> {
				final var entity = SecurityLevelDefinitionEntity.create()
					.withDimension(m.getDimension())
					.withLevel(m.getLevel())
					.withLabel(m.getLabel())
					.withDescription(m.getDescription())
					.withConsequence(m.getConsequence())
					.withColor(m.getColor())
					.withIsActive(m.getIsActive());
				entity.withId(m.getId());
				return entity;
			})
			.orElse(null);
	}
}
