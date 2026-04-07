package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.integration.db.model.OrganizationEntity;

public final class OrganizationMapper {

	private OrganizationMapper() {}

	public static Organization toOrganization(final OrganizationEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> Organization.create()
				.withId(e.getId())
				.withName(e.getName())
				.withDescription(e.getDescription())
				.withOrgNumber(e.getOrgNumber())
				.withEmail(e.getEmail())
				.withPhone(e.getPhone())
				.withParentId(e.getParentId())
				.withLevel(e.getLevel()))
			.orElse(null);
	}

	public static OrganizationEntity toOrganizationEntity(final Organization model) {
		return Optional.ofNullable(model)
			.map(m -> OrganizationEntity.create()
				.withName(m.getName())
				.withDescription(m.getDescription())
				.withOrgNumber(m.getOrgNumber())
				.withEmail(m.getEmail())
				.withPhone(m.getPhone())
				.withParentId(m.getParentId())
				.withLevel(m.getLevel()))
			.orElse(null);
	}

	public static List<Organization> toOrganizationList(final List<OrganizationEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(OrganizationMapper::toOrganization)
				.toList())
			.orElse(null);
	}

	public static void updateOrganizationEntity(final OrganizationEntity entity, final Organization model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getName()).ifPresent(entity::withName);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getOrgNumber()).ifPresent(entity::withOrgNumber);
			Optional.ofNullable(m.getEmail()).ifPresent(entity::withEmail);
			Optional.ofNullable(m.getPhone()).ifPresent(entity::withPhone);
			Optional.ofNullable(m.getParentId()).ifPresent(entity::withParentId);
			Optional.ofNullable(m.getLevel()).ifPresent(entity::withLevel);
		});
	}
}
