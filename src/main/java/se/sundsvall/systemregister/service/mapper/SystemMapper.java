package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.System;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

public final class SystemMapper {

	private SystemMapper() {}

	public static System toSystem(final SystemEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> System.create()
				.withId(e.getId())
				.withSystemId(e.getSystemId())
				.withName(e.getName())
				.withDescription(e.getDescription())
				.withStatus(Optional.ofNullable(e.getStatus()).map(SystemStatus::toString).orElse(null))
				.withVersion(e.getVersion())
				.withDocumentationUrl(e.getDocumentationUrl())
				.withCriticalityLevelId(e.getCriticalityLevelId())
				.withKonfidentialitet(e.getKonfidentialitet())
				.withRiktighet(e.getRiktighet())
				.withTillganglighet(e.getTillganglighet())
				.withOwnerOrganizationId(e.getOwnerOrganizationId())
				.withSystemOwnerId(e.getSystemOwnerId())
				.withTechnicalContactId(e.getTechnicalContactId())
				.withHostingType(Optional.ofNullable(e.getHostingType()).map(HostingType::toString).orElse(null))
				.withSupplierId(e.getSupplierId()))
			.orElse(null);
	}

	public static SystemEntity toSystemEntity(final System model) {
		return Optional.ofNullable(model)
			.map(m -> SystemEntity.create()
				.withSystemId(m.getSystemId())
				.withName(m.getName())
				.withDescription(m.getDescription())
				.withStatus(Optional.ofNullable(m.getStatus())
					.flatMap(s -> {
						try {
							return Optional.of(SystemStatus.valueOf(s));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withVersion(m.getVersion())
				.withDocumentationUrl(m.getDocumentationUrl())
				.withCriticalityLevelId(m.getCriticalityLevelId())
				.withKonfidentialitet(m.getKonfidentialitet())
				.withRiktighet(m.getRiktighet())
				.withTillganglighet(m.getTillganglighet())
				.withOwnerOrganizationId(m.getOwnerOrganizationId())
				.withSystemOwnerId(m.getSystemOwnerId())
				.withTechnicalContactId(m.getTechnicalContactId())
				.withHostingType(Optional.ofNullable(m.getHostingType())
					.flatMap(h -> {
						try {
							return Optional.of(HostingType.valueOf(h));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withSupplierId(m.getSupplierId()))
			.orElse(null);
	}

	public static List<System> toSystemList(final List<SystemEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(SystemMapper::toSystem)
				.toList())
			.orElse(null);
	}

	public static void updateSystemEntity(final SystemEntity entity, final System model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getSystemId()).ifPresent(entity::withSystemId);
			Optional.ofNullable(m.getName()).ifPresent(entity::withName);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getStatus())
				.flatMap(s -> {
					try {
						return Optional.of(SystemStatus.valueOf(s));
					} catch (final IllegalArgumentException _) {
						return Optional.empty();
					}
				})
				.ifPresent(entity::withStatus);
			Optional.ofNullable(m.getVersion()).ifPresent(entity::withVersion);
			Optional.ofNullable(m.getDocumentationUrl()).ifPresent(entity::withDocumentationUrl);
			Optional.ofNullable(m.getCriticalityLevelId()).ifPresent(entity::withCriticalityLevelId);
			Optional.ofNullable(m.getKonfidentialitet()).ifPresent(entity::withKonfidentialitet);
			Optional.ofNullable(m.getRiktighet()).ifPresent(entity::withRiktighet);
			Optional.ofNullable(m.getTillganglighet()).ifPresent(entity::withTillganglighet);
			Optional.ofNullable(m.getOwnerOrganizationId()).ifPresent(entity::withOwnerOrganizationId);
			Optional.ofNullable(m.getSystemOwnerId()).ifPresent(entity::withSystemOwnerId);
			Optional.ofNullable(m.getTechnicalContactId()).ifPresent(entity::withTechnicalContactId);
			Optional.ofNullable(m.getHostingType())
				.flatMap(h -> {
					try {
						return Optional.of(HostingType.valueOf(h));
					} catch (final IllegalArgumentException _) {
						return Optional.empty();
					}
				})
				.ifPresent(entity::withHostingType);
			Optional.ofNullable(m.getSupplierId()).ifPresent(entity::withSupplierId);
		});
	}
}
