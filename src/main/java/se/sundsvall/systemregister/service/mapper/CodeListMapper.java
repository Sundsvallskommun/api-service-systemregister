package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.CodeList;
import se.sundsvall.systemregister.integration.db.model.KodEntity;

import static java.util.Optional.ofNullable;

public final class CodeListMapper {

	private CodeListMapper() {}

	public static CodeList toModel(final KodEntity entity) {
		return ofNullable(entity)
			.map(e -> CodeList.create()
				.withId(e.getId())
				.withKod(e.getKod())
				.withNamn(e.getNamn())
				.withOrdning(e.getOrdning())
				.withBeskrivning(e.getBeskrivning())
				.withLagrum(e.getLagrum())
				.withGallringsfristAr(e.getGallringsfristAr()))
			.orElse(null);
	}

	public static void updateEntity(final KodEntity entity, final CodeList model) {
		ofNullable(model.getKod()).ifPresent(entity::setKod);
		ofNullable(model.getNamn()).ifPresent(entity::setNamn);
		ofNullable(model.getOrdning()).ifPresent(entity::setOrdning);
		ofNullable(model.getBeskrivning()).ifPresent(entity::setBeskrivning);
		ofNullable(model.getLagrum()).ifPresent(entity::setLagrum);
		ofNullable(model.getGallringsfristAr()).ifPresent(entity::setGallringsfristAr);
	}
}
