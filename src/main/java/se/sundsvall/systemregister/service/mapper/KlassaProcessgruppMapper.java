package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.KlassaProcessgrupp;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessgruppEntity;

import static java.util.Optional.ofNullable;

public final class KlassaProcessgruppMapper {

	private KlassaProcessgruppMapper() {}

	public static KlassaProcessgrupp toModel(final KlassaProcessgruppEntity entity) {
		return ofNullable(entity)
			.map(e -> KlassaProcessgrupp.create()
				.withId(e.getId())
				.withKod(e.getKod())
				.withNamn(e.getNamn())
				.withVerksamhetsomradeId(e.getVerksamhetsomradeId())
				.withAktiv(e.getAktiv()))
			.orElse(null);
	}

	public static KlassaProcessgruppEntity updateEntity(final KlassaProcessgruppEntity existing, final KlassaProcessgrupp model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static KlassaProcessgruppEntity toEntity(final KlassaProcessgrupp model) {
		return ofNullable(model)
			.map(m -> KlassaProcessgruppEntity.create()
				.withId(m.getId())
				.withKod(m.getKod())
				.withNamn(m.getNamn())
				.withVerksamhetsomradeId(m.getVerksamhetsomradeId())
				.withAktiv(m.getAktiv()))
			.orElse(null);
	}
}
