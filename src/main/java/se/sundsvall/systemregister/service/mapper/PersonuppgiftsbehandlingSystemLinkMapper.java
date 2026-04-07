package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingSystemLinkEntity;

import static java.util.Optional.ofNullable;

public final class PersonuppgiftsbehandlingSystemLinkMapper {

	private PersonuppgiftsbehandlingSystemLinkMapper() {
		// Private constructor to prevent instantiation
	}

	public static PersonuppgiftsbehandlingSystemLink toDto(final PersonuppgiftsbehandlingSystemLinkEntity entity) {
		return ofNullable(entity)
			.map(e -> PersonuppgiftsbehandlingSystemLink.create()
				.withBehandlingId(e.getBehandlingId())
				.withSystemId(e.getSystemId())
				.withDescription(e.getDescription()))
			.orElse(null);
	}

	public static PersonuppgiftsbehandlingSystemLinkEntity toEntity(final PersonuppgiftsbehandlingSystemLink dto) {
		return ofNullable(dto)
			.map(d -> PersonuppgiftsbehandlingSystemLinkEntity.create()
				.withBehandlingId(d.getBehandlingId())
				.withSystemId(d.getSystemId())
				.withDescription(d.getDescription()))
			.orElse(null);
	}
}
