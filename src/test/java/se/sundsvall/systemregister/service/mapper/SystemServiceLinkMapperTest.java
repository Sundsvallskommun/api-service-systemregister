package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.SystemServiceLink;
import se.sundsvall.systemregister.integration.db.model.SystemServiceLinkEntity;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceDirection;

import static org.assertj.core.api.Assertions.assertThat;

class SystemServiceLinkMapperTest {

	@Test
	void toSystemServiceLink() {
		final var entity = SystemServiceLinkEntity.create()
			.withSystemId("system-1")
			.withServiceId("service-1")
			.withDirection(ServiceDirection.PROVIDED)
			.withDescription("System provides email service");
		entity.withId("link-1");

		final var result = SystemServiceLinkMapper.toSystemServiceLink(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("link-1");
		assertThat(result.getSystemId()).isEqualTo("system-1");
		assertThat(result.getServiceId()).isEqualTo("service-1");
		assertThat(result.getDirection()).isEqualTo("PROVIDED");
		assertThat(result.getDescription()).isEqualTo("System provides email service");
	}

	@Test
	void toSystemServiceLinkNull() {
		assertThat(SystemServiceLinkMapper.toSystemServiceLink(null)).isNull();
	}

	@Test
	void toSystemServiceLinkEntity() {
		final var model = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1")
			.withDirection("PROVIDED")
			.withDescription("System provides email service");

		final var result = SystemServiceLinkMapper.toSystemServiceLinkEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("system-1");
		assertThat(result.getServiceId()).isEqualTo("service-1");
		assertThat(result.getDirection()).isEqualTo(ServiceDirection.PROVIDED);
		assertThat(result.getDescription()).isEqualTo("System provides email service");
	}

	@Test
	void toSystemServiceLinkEntityNull() {
		assertThat(SystemServiceLinkMapper.toSystemServiceLinkEntity(null)).isNull();
	}

	@Test
	void toSystemServiceLinkEntityWithInvalidDirection() {
		final var model = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1")
			.withDirection("INVALID_DIRECTION");

		final var result = SystemServiceLinkMapper.toSystemServiceLinkEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("system-1");
		assertThat(result.getDirection()).isNull();
	}

	@Test
	void toSystemServiceLinkList() {
		final var entity1 = SystemServiceLinkEntity.create()
			.withSystemId("system-1");
		entity1.withId("link-1");
		final var entity2 = SystemServiceLinkEntity.create()
			.withSystemId("system-2");
		entity2.withId("link-2");
		final var entities = List.of(entity1, entity2);

		final var result = SystemServiceLinkMapper.toSystemServiceLinkList(entities);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getSystemId()).isEqualTo("system-1");
		assertThat(result.get(1).getSystemId()).isEqualTo("system-2");
	}

	@Test
	void toSystemServiceLinkListNull() {
		assertThat(SystemServiceLinkMapper.toSystemServiceLinkList(null)).isNull();
	}

	@Test
	void updateSystemServiceLinkEntity() {
		final var entity = SystemServiceLinkEntity.create()
			.withSystemId("system-1")
			.withServiceId("service-1");

		final var model = SystemServiceLink.create()
			.withDirection("CONSUMED")
			.withDescription("System consumes email service");

		SystemServiceLinkMapper.updateSystemServiceLinkEntity(entity, model);

		assertThat(entity.getSystemId()).isEqualTo("system-1");
		assertThat(entity.getServiceId()).isEqualTo("service-1");
		assertThat(entity.getDirection()).isEqualTo(ServiceDirection.CONSUMED);
		assertThat(entity.getDescription()).isEqualTo("System consumes email service");
	}
}
