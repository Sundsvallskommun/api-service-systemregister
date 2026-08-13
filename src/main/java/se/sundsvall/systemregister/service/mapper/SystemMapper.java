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
				.withKonfidentialitetMotivering(e.getKonfidentialitetMotivering())
				.withRiktighet(e.getRiktighet())
				.withRiktighetMotivering(e.getRiktighetMotivering())
				.withTillganglighet(e.getTillganglighet())
				.withTillganglighetMotivering(e.getTillganglighetMotivering())
				.withSamhallsviktigt(e.getSamhallsviktigt())
				.withSamhallsviktigtMotivering(e.getSamhallsviktigtMotivering())
				.withKlassningsdatum(e.getKlassningsdatum())
				.withOwnerOrganizationId(e.getOwnerOrganizationId())
				.withSystemOwnerId(e.getSystemOwnerId())
				.withSystemManagerId(e.getSystemManagerId())
				.withTechnicalContactId(e.getTechnicalContactId())
				.withHostingType(Optional.ofNullable(e.getHostingType()).map(HostingType::toString).orElse(null))
				.withSupplierId(e.getSupplierId())
				.withRiskAnalysed(e.getRiskAnalysed())
				.withRiskAnalysedDate(e.getRiskAnalysedDate()))
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
				.withKonfidentialitetMotivering(m.getKonfidentialitetMotivering())
				.withRiktighet(m.getRiktighet())
				.withRiktighetMotivering(m.getRiktighetMotivering())
				.withTillganglighet(m.getTillganglighet())
				.withTillganglighetMotivering(m.getTillganglighetMotivering())
				.withSamhallsviktigt(Optional.ofNullable(m.getSamhallsviktigt()).orElse(false))
				.withSamhallsviktigtMotivering(m.getSamhallsviktigtMotivering())
				.withKlassningsdatum(m.getKlassningsdatum())
				.withOwnerOrganizationId(m.getOwnerOrganizationId())
				.withSystemOwnerId(m.getSystemOwnerId())
				.withSystemManagerId(m.getSystemManagerId())
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
				.withSupplierId(m.getSupplierId())
				.withRiskAnalysed(m.getRiskAnalysed())
				.withRiskAnalysedDate(m.getRiskAnalysedDate()))
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
			Optional.ofNullable(m.getKonfidentialitetMotivering()).ifPresent(entity::withKonfidentialitetMotivering);
			Optional.ofNullable(m.getRiktighet()).ifPresent(entity::withRiktighet);
			Optional.ofNullable(m.getRiktighetMotivering()).ifPresent(entity::withRiktighetMotivering);
			Optional.ofNullable(m.getTillganglighet()).ifPresent(entity::withTillganglighet);
			Optional.ofNullable(m.getTillganglighetMotivering()).ifPresent(entity::withTillganglighetMotivering);
			Optional.ofNullable(m.getSamhallsviktigt()).ifPresent(entity::setSamhallsviktigt);
			Optional.ofNullable(m.getSamhallsviktigtMotivering()).ifPresent(entity::withSamhallsviktigtMotivering);
			Optional.ofNullable(m.getKlassningsdatum()).ifPresent(entity::withKlassningsdatum);
			Optional.ofNullable(m.getOwnerOrganizationId()).ifPresent(entity::withOwnerOrganizationId);
			Optional.ofNullable(m.getSystemOwnerId()).ifPresent(entity::withSystemOwnerId);
			Optional.ofNullable(m.getSystemManagerId()).ifPresent(entity::withSystemManagerId);
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
			Optional.ofNullable(m.getRiskAnalysed()).ifPresent(entity::withRiskAnalysed);
			Optional.ofNullable(m.getRiskAnalysedDate()).ifPresent(entity::withRiskAnalysedDate);
		});
	}
}
