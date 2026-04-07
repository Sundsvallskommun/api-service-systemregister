package se.sundsvall.systemregister.integration.db.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KodEntityTest {

	@Test
	void defaultMethodsReturnNull() {
		final KodEntity entity = KodFormatEntity.create()
			.withId(1)
			.withKod("K")
			.withNamn("N")
			.withOrdning(1);

		assertThat(entity.getBeskrivning()).isNull();
		assertThat(entity.getLagrum()).isNull();
		assertThat(entity.getGallringsfristAr()).isNull();
	}

	@Test
	void defaultSettersAreNoOp() {
		final KodEntity entity = KodFormatEntity.create();

		entity.setBeskrivning("test");
		entity.setLagrum("test");
		entity.setGallringsfristAr(5);

		assertThat(entity.getBeskrivning()).isNull();
		assertThat(entity.getLagrum()).isNull();
		assertThat(entity.getGallringsfristAr()).isNull();
	}
}
