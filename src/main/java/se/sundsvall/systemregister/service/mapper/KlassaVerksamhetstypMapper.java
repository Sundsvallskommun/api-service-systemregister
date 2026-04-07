package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.KlassaVerksamhetstyp;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetstypEntity;

import static java.util.Optional.ofNullable;

public final class KlassaVerksamhetstypMapper {

	private KlassaVerksamhetstypMapper() {}

	public static KlassaVerksamhetstyp toModel(final KlassaVerksamhetstypEntity entity) {
		return ofNullable(entity)
			.map(e -> KlassaVerksamhetstyp.create()
				.withId(e.getId())
				.withKod(e.getKod())
				.withNamn(e.getNamn())
				.withBeskrivning(e.getBeskrivning()))
			.orElse(null);
	}

	public static KlassaVerksamhetstypEntity toEntity(final KlassaVerksamhetstyp model) {
		return ofNullable(model)
			.map(m -> KlassaVerksamhetstypEntity.create()
				.withId(m.getId())
				.withKod(m.getKod())
				.withNamn(m.getNamn())
				.withBeskrivning(m.getBeskrivning()))
			.orElse(null);
	}
}
