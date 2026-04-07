package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.Handlingstyp;
import se.sundsvall.systemregister.integration.db.model.HandlingstypEntity;

import static java.util.Optional.ofNullable;

public final class HandlingstypMapper {

	private HandlingstypMapper() {}

	public static Handlingstyp toModel(final HandlingstypEntity entity) {
		return ofNullable(entity)
			.map(e -> Handlingstyp.create()
				.withId(e.getId())
				.withIhPlanId(e.getIhPlanId())
				.withKlassakProcessId(e.getKlassakProcessId())
				.withNamn(e.getNamn())
				.withBeskrivning(e.getBeskrivning())
				.withFormatId(e.getFormatId())
				.withBevarandeGallringId(e.getBevarandeGallringId())
				.withGallringsfristAr(e.getGallringsfristAr())
				.withGallringsfristTypId(e.getGallringsfristTypId())
				.withGallringsforeskriftId(e.getGallringsforeskriftId())
				.withBevarandeLagstodId(e.getBevarandeLagstodId())
				.withRegistreringId(e.getRegistreringId())
				.withItSystemId(e.getItSystemId())
				.withForvaring(e.getForvaring())
				.withLeveransTillArkiv(e.getLeveransTillArkiv())
				.withLeveransfristAr(e.getLeveransfristAr())
				.withSekretess(e.getSekretess())
				.withSekretessLagrumId(e.getSekretessLagrumId())
				.withInnehallerPersonuppgifter(e.getInnehallerPersonuppgifter())
				.withPersonuppgiftId(e.getPersonuppgiftId())
				.withAnmarkning(e.getAnmarkning()))
			.orElse(null);
	}

	public static HandlingstypEntity updateEntity(final HandlingstypEntity existing, final Handlingstyp model) {
		final var entity = toEntity(model);
		entity.withId(existing.getId());
		return entity;
	}

	public static HandlingstypEntity toEntity(final Handlingstyp model) {
		return ofNullable(model)
			.map(m -> {
				final var entity = HandlingstypEntity.create();
				entity.withIhPlanId(m.getIhPlanId());
				entity.withKlassakProcessId(m.getKlassakProcessId());
				entity.withNamn(m.getNamn());
				entity.withBeskrivning(m.getBeskrivning());
				entity.withFormatId(m.getFormatId());
				entity.withBevarandeGallringId(m.getBevarandeGallringId());
				entity.withGallringsfristAr(m.getGallringsfristAr());
				entity.withGallringsfristTypId(m.getGallringsfristTypId());
				entity.withGallringsforeskriftId(m.getGallringsforeskriftId());
				entity.withBevarandeLagstodId(m.getBevarandeLagstodId());
				entity.withRegistreringId(m.getRegistreringId());
				entity.withItSystemId(m.getItSystemId());
				entity.withForvaring(m.getForvaring());
				entity.withLeveransTillArkiv(m.getLeveransTillArkiv());
				entity.withLeveransfristAr(m.getLeveransfristAr());
				entity.withSekretess(m.getSekretess());
				entity.withSekretessLagrumId(m.getSekretessLagrumId());
				entity.withInnehallerPersonuppgifter(m.getInnehallerPersonuppgifter());
				entity.withPersonuppgiftId(m.getPersonuppgiftId());
				entity.withAnmarkning(m.getAnmarkning());
				if (m.getId() != null) {
					entity.withId(m.getId());
				}
				return entity;
			})
			.orElse(null);
	}
}
