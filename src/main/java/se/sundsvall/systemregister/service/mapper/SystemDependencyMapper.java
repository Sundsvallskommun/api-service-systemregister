package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.SystemDependency;
import se.sundsvall.systemregister.integration.db.model.SystemDependencyEntity;
import se.sundsvall.systemregister.integration.db.model.enums.DependencyType;

public final class SystemDependencyMapper {

	private SystemDependencyMapper() {}

	public static SystemDependency toSystemDependency(final SystemDependencyEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> SystemDependency.create()
				.withId(e.getId())
				.withSourceSystemId(e.getSourceSystemId())
				.withTargetSystemId(e.getTargetSystemId())
				.withDependencyType(Optional.ofNullable(e.getDependencyType()).map(DependencyType::toString).orElse(null))
				.withDescription(e.getDescription())
				.withIsCritical(e.getIsCritical())
				.withDocumentationUrl(e.getDocumentationUrl())
				.withContractFilePath(e.getContractFilePath()))
			.orElse(null);
	}

	public static SystemDependencyEntity toSystemDependencyEntity(final SystemDependency model) {
		return Optional.ofNullable(model)
			.map(m -> SystemDependencyEntity.create()
				.withSourceSystemId(m.getSourceSystemId())
				.withTargetSystemId(m.getTargetSystemId())
				.withDependencyType(Optional.ofNullable(m.getDependencyType())
					.flatMap(d -> {
						try {
							return Optional.of(DependencyType.valueOf(d));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withDescription(m.getDescription())
				.withIsCritical(m.getIsCritical())
				.withDocumentationUrl(m.getDocumentationUrl())
				.withContractFilePath(m.getContractFilePath()))
			.orElse(null);
	}

	public static List<SystemDependency> toSystemDependencyList(final List<SystemDependencyEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(SystemDependencyMapper::toSystemDependency)
				.toList())
			.orElse(null);
	}

	public static void updateSystemDependencyEntity(final SystemDependencyEntity entity, final SystemDependency model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getSourceSystemId()).ifPresent(entity::withSourceSystemId);
			Optional.ofNullable(m.getTargetSystemId()).ifPresent(entity::withTargetSystemId);
			Optional.ofNullable(m.getDependencyType())
				.flatMap(d -> {
					try {
						return Optional.of(DependencyType.valueOf(d));
					} catch (final IllegalArgumentException _) {
						return Optional.empty();
					}
				})
				.ifPresent(entity::withDependencyType);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getIsCritical()).ifPresent(entity::withIsCritical);
			Optional.ofNullable(m.getDocumentationUrl()).ifPresent(entity::withDocumentationUrl);
			Optional.ofNullable(m.getContractFilePath()).ifPresent(entity::withContractFilePath);
		});
	}
}
