package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.integration.db.model.SupplierEntity;

public final class SupplierMapper {

	private SupplierMapper() {}

	public static Supplier toSupplier(final SupplierEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> Supplier.create()
				.withId(e.getId())
				.withName(e.getName())
				.withOrgNumber(e.getOrgNumber())
				.withDescription(e.getDescription())
				.withWebsite(e.getWebsite())
				.withContactEmail(e.getContactEmail())
				.withIsActive(e.getIsActive()))
			.orElse(null);
	}

	public static SupplierEntity toSupplierEntity(final Supplier model) {
		return Optional.ofNullable(model)
			.map(m -> SupplierEntity.create()
				.withName(m.getName())
				.withOrgNumber(m.getOrgNumber())
				.withDescription(m.getDescription())
				.withWebsite(m.getWebsite())
				.withContactEmail(m.getContactEmail())
				.withIsActive(m.getIsActive()))
			.orElse(null);
	}

	public static List<Supplier> toSupplierList(final List<SupplierEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(SupplierMapper::toSupplier)
				.toList())
			.orElse(null);
	}

	public static void updateSupplierEntity(final SupplierEntity entity, final Supplier model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getName()).ifPresent(entity::withName);
			Optional.ofNullable(m.getOrgNumber()).ifPresent(entity::withOrgNumber);
			Optional.ofNullable(m.getDescription()).ifPresent(entity::withDescription);
			Optional.ofNullable(m.getWebsite()).ifPresent(entity::withWebsite);
			Optional.ofNullable(m.getContactEmail()).ifPresent(entity::withContactEmail);
			Optional.ofNullable(m.getIsActive()).ifPresent(entity::withIsActive);
		});
	}
}
