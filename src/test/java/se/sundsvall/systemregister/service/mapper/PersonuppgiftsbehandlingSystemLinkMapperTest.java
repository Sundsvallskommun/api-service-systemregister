package se.sundsvall.systemregister.service.mapper;

import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingSystemLinkEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PersonuppgiftsbehandlingSystemLinkMapperTest {

	@Test
	void toDtoWithNull() {
		assertThat(PersonuppgiftsbehandlingSystemLinkMapper.toDto(null)).isNull();
	}

	@Test
	void toDto() {
		final var entity = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001")
			.withDescription("Customer database integration");

		final var dto = PersonuppgiftsbehandlingSystemLinkMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(dto.getSystemId()).isEqualTo("system-001");
		assertThat(dto.getDescription()).isEqualTo("Customer database integration");
	}

	@Test
	void toDtoWithNullFields() {
		final var entity = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withDescription(null);

		final var dto = PersonuppgiftsbehandlingSystemLinkMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(dto.getSystemId()).isNull();
		assertThat(dto.getDescription()).isNull();
	}

	@Test
	void toEntityWithNull() {
		assertThat(PersonuppgiftsbehandlingSystemLinkMapper.toEntity(null)).isNull();
	}

	@Test
	void toEntity() {
		final var dto = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001")
			.withDescription("Customer database integration");

		final var entity = PersonuppgiftsbehandlingSystemLinkMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(entity.getSystemId()).isEqualTo("system-001");
		assertThat(entity.getDescription()).isEqualTo("Customer database integration");
	}

	@Test
	void toEntityWithNullFields() {
		final var dto = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId("PPB-001")
			.withDescription(null);

		final var entity = PersonuppgiftsbehandlingSystemLinkMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(entity.getSystemId()).isNull();
		assertThat(entity.getDescription()).isNull();
	}

	@Test
	void roundTrip() {
		final var dto = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001")
			.withDescription("Test description");

		final var entity = PersonuppgiftsbehandlingSystemLinkMapper.toEntity(dto);
		final var roundTrip = PersonuppgiftsbehandlingSystemLinkMapper.toDto(entity);

		assertThat(roundTrip.getBehandlingId()).isEqualTo(dto.getBehandlingId());
		assertThat(roundTrip.getSystemId()).isEqualTo(dto.getSystemId());
		assertThat(roundTrip.getDescription()).isEqualTo(dto.getDescription());
	}
}
