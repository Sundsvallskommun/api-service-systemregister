package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.KlassaVerksamhetsomrade;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetsomradeEntity;

import static java.util.Optional.ofNullable;

public final class KlassaVerksamhetsomradeMapper {

	private KlassaVerksamhetsomradeMapper() {}

	public static KlassaVerksamhetsomrade toModel(final KlassaVerksamhetsomradeEntity entity) {
		return ofNullable(entity)
			.map(e -> KlassaVerksamhetsomrade.create()
				.withId(e.getId())
				.withKod(e.getKod())
				.withNamn(e.getNamn())
				.withVerksamhetstypId(e.getVerksamhetstypId())
				.withAktiv(e.getAktiv()))
			.orElse(null);
	}

	public static KlassaVerksamhetsomradeEntity updateEntity(final KlassaVerksamhetsomradeEntity existing, final KlassaVerksamhetsomrade model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static KlassaVerksamhetsomradeEntity toEntity(final KlassaVerksamhetsomrade model) {
		return ofNullable(model)
			.map(m -> KlassaVerksamhetsomradeEntity.create()
				.withId(m.getId())
				.withKod(m.getKod())
				.withNamn(m.getNamn())
				.withVerksamhetstypId(m.getVerksamhetstypId())
				.withAktiv(m.getAktiv()))
			.orElse(null);
	}
}
