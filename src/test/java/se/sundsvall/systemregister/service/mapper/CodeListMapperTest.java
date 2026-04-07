package se.sundsvall.systemregister.service.mapper;

import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.CodeList;
import se.sundsvall.systemregister.integration.db.model.KodBevarandeLagstodEntity;
import se.sundsvall.systemregister.integration.db.model.KodFormatEntity;

import static org.assertj.core.api.Assertions.assertThat;

class CodeListMapperTest {

	@Test
	void toModelWithFullEntity() {
		final var entity = KodBevarandeLagstodEntity.create()
			.withId(1)
			.withKod("BEV_001")
			.withNamn("Bevarande lagstod")
			.withOrdning(1)
			.withBeskrivning("Description")
			.withLagrum("5 kap 1 par")
			.withGallringsfristAr(10);

		final var result = CodeListMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		assertThat(result.getKod()).isEqualTo("BEV_001");
		assertThat(result.getNamn()).isEqualTo("Bevarande lagstod");
		assertThat(result.getOrdning()).isEqualTo(1);
		assertThat(result.getBeskrivning()).isEqualTo("Description");
		assertThat(result.getLagrum()).isEqualTo("5 kap 1 par");
		assertThat(result.getGallringsfristAr()).isEqualTo(10);
	}

	@Test
	void toModelWithMinimalEntity() {
		final var entity = KodFormatEntity.create()
			.withId(1)
			.withKod("FORMAT_001")
			.withNamn("PDF")
			.withOrdning(1);

		final var result = CodeListMapper.toModel(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		assertThat(result.getBeskrivning()).isNull();
		assertThat(result.getLagrum()).isNull();
		assertThat(result.getGallringsfristAr()).isNull();
	}

	@Test
	void toModelWithNull() {
		assertThat(CodeListMapper.toModel(null)).isNull();
	}

	@Test
	void updateEntity() {
		final var entity = KodBevarandeLagstodEntity.create()
			.withId(1)
			.withKod("OLD")
			.withNamn("Old name");

		final var model = CodeList.create()
			.withKod("NEW")
			.withNamn("New name")
			.withOrdning(5)
			.withBeskrivning("New desc")
			.withLagrum("3 kap")
			.withGallringsfristAr(7);

		CodeListMapper.updateEntity(entity, model);

		assertThat(entity.getKod()).isEqualTo("NEW");
		assertThat(entity.getNamn()).isEqualTo("New name");
		assertThat(entity.getOrdning()).isEqualTo(5);
		assertThat(entity.getBeskrivning()).isEqualTo("New desc");
		assertThat(entity.getLagrum()).isEqualTo("3 kap");
		assertThat(entity.getGallringsfristAr()).isEqualTo(7);
	}

	@Test
	void updateEntityWithPartialModel() {
		final var entity = KodFormatEntity.create()
			.withId(1)
			.withKod("ORIG")
			.withNamn("Original")
			.withOrdning(1);

		final var model = CodeList.create()
			.withNamn("Updated");

		CodeListMapper.updateEntity(entity, model);

		assertThat(entity.getKod()).isEqualTo("ORIG");
		assertThat(entity.getNamn()).isEqualTo("Updated");
		assertThat(entity.getOrdning()).isEqualTo(1);
	}
}
