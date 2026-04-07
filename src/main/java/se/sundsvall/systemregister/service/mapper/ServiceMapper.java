package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.integration.db.model.ServiceEntity;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceType;

public final class ServiceMapper {

	private ServiceMapper() {}

	public static ServiceModel toService(final ServiceEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> ServiceModel.create()
				.withId(e.getId())
				.withServiceId(e.getServiceId())
				.withName(e.getName())
				.withDescription(e.getDescription())
				.withVersion(e.getVersion())
				.withEndpointUrl(e.getEndpointUrl())
				.withDocumentationUrl(e.getDocumentationUrl())
				.withServiceType(Optional.ofNullable(e.getServiceType()).map(ServiceType::toString).orElse(null))
				.withCriticalityLevelId(e.getCriticalityLevelId())
				.withKonfidentialitet(e.getKonfidentialitet())
				.withRiktighet(e.getRiktighet())
				.withTillganglighet(e.getTillganglighet())
				.withOwnerOrganizationId(e.getOwnerOrganizationId())
				.withServiceOwnerId(e.getServiceOwnerId())
				.withTechnicalContactId(e.getTechnicalContactId())
				.withHostingType(Optional.ofNullable(e.getHostingType()).map(HostingType::toString).orElse(null))
				.withSupplierId(e.getSupplierId()))
			.orElse(null);
	}

	public static ServiceEntity toServiceEntity(final ServiceModel model) {
		return Optional.ofNullable(model)
			.map(m -> ServiceEntity.create()
				.withServiceId(m.getServiceId())
				.withName(m.getName())
				.withDescription(m.getDescription())
				.withVersion(m.getVersion())
				.withEndpointUrl(m.getEndpointUrl())
				.withDocumentationUrl(m.getDocumentationUrl())
				.withServiceType(Optional.ofNullable(m.getServiceType())
					.flatMap(s -> {
						try {
							return Optional.of(ServiceType.valueOf(s));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withCriticalityLevelId(m.getCriticalityLevelId())
				.withKonfidentialitet(m.getKonfidentialitet())
				.withRiktighet(m.getRiktighet())
				.withTillganglighet(m.getTillganglighet())
				.withOwnerOrganizationId(m.getOwnerOrganizationId())
				.withServiceOwnerId(m.getServiceOwnerId())
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

	public static List<ServiceModel> toServiceList(final List<ServiceEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(ServiceMapper::toService)
				.toList())
			.orElse(null);
	}

	public static void updateServiceEntity(final ServiceEntity entity, final ServiceModel model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getServiceId()).ifPresent(entity::withServiceId);
			Optional.ofNullable(m.getName()).ifPresent(entity::withName);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getVersion()).ifPresent(entity::withVersion);
			Optional.ofNullable(m.getEndpointUrl()).ifPresent(entity::withEndpointUrl);
			Optional.ofNullable(m.getDocumentationUrl()).ifPresent(entity::withDocumentationUrl);
			Optional.ofNullable(m.getServiceType())
				.flatMap(s -> {
					try {
						return Optional.of(ServiceType.valueOf(s));
					} catch (final IllegalArgumentException _) {
						return Optional.empty();
					}
				})
				.ifPresent(entity::withServiceType);
			Optional.ofNullable(m.getCriticalityLevelId()).ifPresent(entity::withCriticalityLevelId);
			Optional.ofNullable(m.getKonfidentialitet()).ifPresent(entity::withKonfidentialitet);
			Optional.ofNullable(m.getRiktighet()).ifPresent(entity::withRiktighet);
			Optional.ofNullable(m.getTillganglighet()).ifPresent(entity::withTillganglighet);
			Optional.ofNullable(m.getOwnerOrganizationId()).ifPresent(entity::withOwnerOrganizationId);
			Optional.ofNullable(m.getServiceOwnerId()).ifPresent(entity::withServiceOwnerId);
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
