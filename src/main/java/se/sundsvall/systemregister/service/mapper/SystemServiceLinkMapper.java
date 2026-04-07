package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.SystemServiceLink;
import se.sundsvall.systemregister.integration.db.model.SystemServiceLinkEntity;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceDirection;

public final class SystemServiceLinkMapper {

	private SystemServiceLinkMapper() {}

	public static SystemServiceLink toSystemServiceLink(final SystemServiceLinkEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> SystemServiceLink.create()
				.withId(e.getId())
				.withSystemId(e.getSystemId())
				.withServiceId(e.getServiceId())
				.withDirection(Optional.ofNullable(e.getDirection()).map(ServiceDirection::toString).orElse(null))
				.withDescription(e.getDescription()))
			.orElse(null);
	}

	public static SystemServiceLinkEntity toSystemServiceLinkEntity(final SystemServiceLink model) {
		return Optional.ofNullable(model)
			.map(m -> SystemServiceLinkEntity.create()
				.withSystemId(m.getSystemId())
				.withServiceId(m.getServiceId())
				.withDirection(Optional.ofNullable(m.getDirection())
					.flatMap(d -> {
						try {
							return Optional.of(ServiceDirection.valueOf(d));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withDescription(m.getDescription()))
			.orElse(null);
	}

	public static List<SystemServiceLink> toSystemServiceLinkList(final List<SystemServiceLinkEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(SystemServiceLinkMapper::toSystemServiceLink)
				.toList())
			.orElse(null);
	}

	public static void updateSystemServiceLinkEntity(final SystemServiceLinkEntity entity, final SystemServiceLink model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getSystemId()).ifPresent(entity::withSystemId);
			Optional.ofNullable(m.getServiceId()).ifPresent(entity::withServiceId);
			Optional.ofNullable(m.getDirection())
				.flatMap(d -> {
					try {
						return Optional.of(ServiceDirection.valueOf(d));
					} catch (final IllegalArgumentException _) {
						return Optional.empty();
					}
				})
				.ifPresent(entity::withDirection);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
		});
	}
}
