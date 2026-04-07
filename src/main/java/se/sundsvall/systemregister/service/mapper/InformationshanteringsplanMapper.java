package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.integration.db.model.InformationshanteringsplanEntity;

import static java.util.Optional.ofNullable;

public final class InformationshanteringsplanMapper {

	private InformationshanteringsplanMapper() {}

	public static Informationshanteringsplan toModel(final InformationshanteringsplanEntity entity) {
		return ofNullable(entity)
			.map(e -> Informationshanteringsplan.create()
				.withId(e.getId())
				.withNamn(e.getNamn())
				.withVersion(e.getVersion())
				.withBeskrivning(e.getBeskrivning())
				.withOrganisationId(e.getOrganisationId())
				.withGiltigFran(e.getGiltigFran())
				.withGiltigTom(e.getGiltigTom())
				.withBeslutsdatum(e.getBeslutsdatum())
				.withBeslutsparagraf(e.getBeslutsparagraf())
				.withStatus(ofNullable(e.getStatus()).map(Enum::name).orElse(null)))
			.orElse(null);
	}

	public static InformationshanteringsplanEntity updateEntity(final InformationshanteringsplanEntity existing, final Informationshanteringsplan model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static InformationshanteringsplanEntity toEntity(final Informationshanteringsplan model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = InformationshanteringsplanEntity.create();
				entity.withNamn(m.getNamn());
				entity.withVersion(m.getVersion());
				entity.withBeskrivning(m.getBeskrivning());
				entity.withOrganisationId(m.getOrganisationId());
				entity.withGiltigFran(m.getGiltigFran());
				entity.withGiltigTom(m.getGiltigTom());
				entity.withBeslutsdatum(m.getBeslutsdatum());
				entity.withBeslutsparagraf(m.getBeslutsparagraf());
				entity.withStatus(ofNullable(m.getStatus())
					.map(se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus::valueOf)
					.orElse(null));
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
