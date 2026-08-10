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
				.withKonfidentialitetMotivering(e.getKonfidentialitetMotivering())
				.withRiktighet(e.getRiktighet())
				.withRiktighetMotivering(e.getRiktighetMotivering())
				.withTillganglighet(e.getTillganglighet())
				.withTillganglighetMotivering(e.getTillganglighetMotivering())
				.withSparbarhet(e.getSparbarhet())
				.withKlassningDatum(e.getKlassningDatum())
				.withKlassadAv(e.getKlassadAv())
				.withSamhallsviktigt(e.getSamhallsviktigt())
				.withSamhallsviktigtMotivering(e.getSamhallsviktigtMotivering()))
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
				entity.withKonfidentialitetMotivering(m.getKonfidentialitetMotivering());
				entity.withRiktighet(m.getRiktighet());
				entity.withRiktighetMotivering(m.getRiktighetMotivering());
				entity.withTillganglighet(m.getTillganglighet());
				entity.withTillganglighetMotivering(m.getTillganglighetMotivering());
				entity.withSparbarhet(m.getSparbarhet());
				entity.withKlassningDatum(m.getKlassningDatum());
				entity.withKlassadAv(m.getKlassadAv());
				entity.withSamhallsviktigt(m.getSamhallsviktigt());
				entity.withSamhallsviktigtMotivering(m.getSamhallsviktigtMotivering());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
