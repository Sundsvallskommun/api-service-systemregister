package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.CriticalityLevel;
import se.sundsvall.systemregister.integration.db.model.CriticalityLevelEntity;

public final class CriticalityLevelMapper {

	private CriticalityLevelMapper() {}

	public static CriticalityLevel toCriticalityLevel(final CriticalityLevelEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> CriticalityLevel.create()
				.withId(e.getId())
				.withName(e.getName())
				.withCode(e.getCode())
				.withDescription(e.getDescription())
				.withLevel(e.getLevel())
				.withColor(e.getColor())
				.withIsActive(e.getIsActive()))
			.orElse(null);
	}

	public static CriticalityLevelEntity toCriticalityLevelEntity(final CriticalityLevel model) {
		return Optional.ofNullable(model)
			.map(m -> CriticalityLevelEntity.create()
				.withName(m.getName())
				.withCode(m.getCode())
				.withDescription(m.getDescription())
				.withLevel(m.getLevel())
				.withColor(m.getColor())
				.withIsActive(m.getIsActive()))
			.orElse(null);
	}

	public static List<CriticalityLevel> toCriticalityLevelList(final List<CriticalityLevelEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(CriticalityLevelMapper::toCriticalityLevel)
				.toList())
			.orElse(null);
	}

	public static void updateCriticalityLevelEntity(final CriticalityLevelEntity entity, final CriticalityLevel model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getName()).ifPresent(entity::withName);
			Optional.ofNullable(m.getCode()).ifPresent(entity::withCode);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getLevel()).ifPresent(entity::withLevel);
			Optional.ofNullable(m.getColor()).ifPresent(entity::withColor);
			Optional.ofNullable(m.getIsActive()).ifPresent(entity::withIsActive);
		});
	}
}
