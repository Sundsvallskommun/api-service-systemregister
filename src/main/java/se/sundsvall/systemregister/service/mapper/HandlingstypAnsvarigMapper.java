package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.HandlingstypAnsvarig;
import se.sundsvall.systemregister.integration.db.model.HandlingstypAnsvarigEntity;

import static java.util.Optional.ofNullable;

public final class HandlingstypAnsvarigMapper {

	private HandlingstypAnsvarigMapper() {}

	public static HandlingstypAnsvarig toModel(final HandlingstypAnsvarigEntity entity) {
		return ofNullable(entity)
			.map(e -> HandlingstypAnsvarig.create()
				.withId(e.getId())
				.withHandlingstypId(e.getHandlingstypId())
				.withOrganisationId(e.getOrganisationId())
				.withRoll(e.getRoll())
				.withAnsvarTypId(e.getAnsvarTypId()))
			.orElse(null);
	}

	public static HandlingstypAnsvarigEntity toEntity(final HandlingstypAnsvarig model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = HandlingstypAnsvarigEntity.create();
				entity.withHandlingstypId(m.getHandlingstypId());
				entity.withOrganisationId(m.getOrganisationId());
				entity.withRoll(m.getRoll());
				entity.withAnsvarTypId(m.getAnsvarTypId());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
