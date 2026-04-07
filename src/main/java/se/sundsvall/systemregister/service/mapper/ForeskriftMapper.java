package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.Foreskrift;
import se.sundsvall.systemregister.integration.db.model.ForeskriftEntity;

import static java.util.Optional.ofNullable;

public final class ForeskriftMapper {

	private ForeskriftMapper() {}

	public static Foreskrift toModel(final ForeskriftEntity entity) {
		return ofNullable(entity)
			.map(e -> Foreskrift.create()
				.withId(e.getId())
				.withBeteckning(e.getBeteckning())
				.withNamn(e.getNamn())
				.withBeskrivning(e.getBeskrivning())
				.withUtfardare(ofNullable(e.getUtfardare()).map(Enum::name).orElse(null))
				.withGiltigFran(e.getGiltigFran())
				.withGiltigTom(e.getGiltigTom())
				.withUrl(e.getUrl()))
			.orElse(null);
	}

	public static ForeskriftEntity updateEntity(final ForeskriftEntity existing, final Foreskrift model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static ForeskriftEntity toEntity(final Foreskrift model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = ForeskriftEntity.create();
				entity.withBeteckning(m.getBeteckning());
				entity.withNamn(m.getNamn());
				entity.withBeskrivning(m.getBeskrivning());
				entity.withUtfardare(ofNullable(m.getUtfardare())
					.map(se.sundsvall.systemregister.integration.db.model.enums.ForeskriftUtfardare::valueOf)
					.orElse(null));
				entity.withGiltigFran(m.getGiltigFran());
				entity.withGiltigTom(m.getGiltigTom());
				entity.withUrl(m.getUrl());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
