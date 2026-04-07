package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.Process;
import se.sundsvall.systemregister.integration.db.model.ProcessEntity;

import static java.util.Optional.ofNullable;

public final class ProcessMapper {

	private ProcessMapper() {}

	public static Process toModel(final ProcessEntity entity) {
		return ofNullable(entity)
			.map(e -> Process.create()
				.withId(e.getId())
				.withProcesskod(e.getProcesskod())
				.withNamn(e.getNamn())
				.withBeskrivning(e.getBeskrivning())
				.withVerksamhetstyp(e.getVerksamhetstyp())
				.withProcessgrupId(e.getProcessgrupId())
				.withAktiv(e.getAktiv()))
			.orElse(null);
	}

	public static ProcessEntity updateEntity(final ProcessEntity existing, final Process model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static ProcessEntity toEntity(final Process model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = ProcessEntity.create();
				entity.withProcesskod(m.getProcesskod());
				entity.withNamn(m.getNamn());
				entity.withBeskrivning(m.getBeskrivning());
				entity.withVerksamhetstyp(m.getVerksamhetstyp());
				entity.withProcessgrupId(m.getProcessgrupId());
				entity.withAktiv(m.getAktiv());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
