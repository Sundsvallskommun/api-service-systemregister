package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.KlassaProcess;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessEntity;

import static java.util.Optional.ofNullable;

public final class KlassaProcessMapper {

	private KlassaProcessMapper() {}

	public static KlassaProcess toModel(final KlassaProcessEntity entity) {
		return ofNullable(entity)
			.map(e -> KlassaProcess.create()
				.withId(e.getId())
				.withKod(e.getKod())
				.withNamn(e.getNamn())
				.withBeskrivning(e.getBeskrivning())
				.withProcessgrupId(e.getProcessgrupId())
				.withAktiv(e.getAktiv()))
			.orElse(null);
	}

	public static KlassaProcessEntity updateEntity(final KlassaProcessEntity existing, final KlassaProcess model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static KlassaProcessEntity toEntity(final KlassaProcess model) {
		return ofNullable(model)
			.map(m -> KlassaProcessEntity.create()
				.withId(m.getId())
				.withKod(m.getKod())
				.withNamn(m.getNamn())
				.withBeskrivning(m.getBeskrivning())
				.withProcessgrupId(m.getProcessgrupId())
				.withAktiv(m.getAktiv()))
			.orElse(null);
	}
}
