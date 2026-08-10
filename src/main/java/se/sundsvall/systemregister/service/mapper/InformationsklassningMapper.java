package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.integration.db.model.InformationsklassningEntity;

import static java.util.Optional.ofNullable;

public final class InformationsklassningMapper {

	private InformationsklassningMapper() {}

	public static Informationsklassning toModel(final InformationsklassningEntity entity) {
		return ofNullable(entity)
			.map(e -> Informationsklassning.create()
				.withId(e.getId())
				.withHandlingstypId(e.getHandlingstypId())
				.withKonfidentialitet(e.getKonfidentialitet())
				.withRiktighet(e.getRiktighet())
				.withTillganglighet(e.getTillganglighet())
				.withSparbarhet(e.getSparbarhet())
				.withKlassningDatum(e.getKlassningDatum())
				.withKlassadAv(e.getKlassadAv())
				.withMotivering(e.getMotivering()))
			.orElse(null);
	}

	public static InformationsklassningEntity toEntityForUpsert(final Informationsklassning model, final String handlingstypId, final String existingId) {
		final var entity = toEntity(model);
		entity.withHandlingstypId(handlingstypId);
		ofNullable(existingId).ifPresent(entity::withId);
		return entity;
	}

	public static InformationsklassningEntity toEntity(final Informationsklassning model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = InformationsklassningEntity.create();
				entity.withHandlingstypId(m.getHandlingstypId());
				entity.withKonfidentialitet(m.getKonfidentialitet());
				entity.withRiktighet(m.getRiktighet());
				entity.withTillganglighet(m.getTillganglighet());
				entity.withSparbarhet(m.getSparbarhet());
				entity.withKlassningDatum(m.getKlassningDatum());
				entity.withKlassadAv(m.getKlassadAv());
				entity.withMotivering(m.getMotivering());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
