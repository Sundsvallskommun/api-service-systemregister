package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.System;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static org.assertj.core.api.Assertions.assertThat;

class SystemMapperTest {

	@Test
	void toSystem() {
		final var entity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Test System")
			.withDescription("Test description")
			.withStatus(SystemStatus.PRODUCTION)
			.withVersion("1.0.0")
			.withDocumentationUrl("https://docs.example.com")
			.withCriticalityLevelId("critical-1")
			.withKonfidentialitet(3)
			.withRiktighet(2)
			.withTillganglighet(3)
			.withOwnerOrganizationId("org-1")
			.withSystemOwnerId("person-1")
			.withTechnicalContactId("person-2")
			.withHostingType(HostingType.CLOUD)
			.withSupplierId("supplier-1");
		entity.withId("id-1");

		final var result = SystemMapper.toSystem(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getSystemId()).isEqualTo("SYS-001");
		assertThat(result.getName()).isEqualTo("Test System");
		assertThat(result.getDescription()).isEqualTo("Test description");
		assertThat(result.getStatus()).isEqualTo("PRODUCTION");
		assertThat(result.getVersion()).isEqualTo("1.0.0");
		assertThat(result.getDocumentationUrl()).isEqualTo("https://docs.example.com");
		assertThat(result.getCriticalityLevelId()).isEqualTo("critical-1");
		assertThat(result.getKonfidentialitet()).isEqualTo(3);
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getOwnerOrganizationId()).isEqualTo("org-1");
		assertThat(result.getSystemOwnerId()).isEqualTo("person-1");
		assertThat(result.getTechnicalContactId()).isEqualTo("person-2");
		assertThat(result.getHostingType()).isEqualTo("CLOUD");
		assertThat(result.getSupplierId()).isEqualTo("supplier-1");
	}

	@Test
	void toSystemNull() {
		assertThat(SystemMapper.toSystem(null)).isNull();
	}

	@Test
	void toSystemWithNullStatusAndHostingType() {
		final var entity = SystemEntity.create()
			.withSystemId("SYS-002")
			.withName("Test System 2");

		final var result = SystemMapper.toSystem(entity);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("SYS-002");
		assertThat(result.getName()).isEqualTo("Test System 2");
		assertThat(result.getStatus()).isNull();
		assertThat(result.getHostingType()).isNull();
	}

	@Test
	void toSystemEntity() {
		final var model = System.create()
			.withSystemId("SYS-001")
			.withName("Test System")
			.withDescription("Test description")
			.withStatus("PRODUCTION")
			.withVersion("1.0.0")
			.withDocumentationUrl("https://docs.example.com")
			.withCriticalityLevelId("critical-1")
			.withKonfidentialitet(3)
			.withRiktighet(2)
			.withTillganglighet(3)
			.withOwnerOrganizationId("org-1")
			.withSystemOwnerId("person-1")
			.withTechnicalContactId("person-2")
			.withHostingType("CLOUD")
			.withSupplierId("supplier-1");

		final var result = SystemMapper.toSystemEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("SYS-001");
		assertThat(result.getName()).isEqualTo("Test System");
		assertThat(result.getDescription()).isEqualTo("Test description");
		assertThat(result.getStatus()).isEqualTo(SystemStatus.PRODUCTION);
		assertThat(result.getVersion()).isEqualTo("1.0.0");
		assertThat(result.getDocumentationUrl()).isEqualTo("https://docs.example.com");
		assertThat(result.getCriticalityLevelId()).isEqualTo("critical-1");
		assertThat(result.getKonfidentialitet()).isEqualTo(3);
		assertThat(result.getRiktighet()).isEqualTo(2);
		assertThat(result.getTillganglighet()).isEqualTo(3);
		assertThat(result.getOwnerOrganizationId()).isEqualTo("org-1");
		assertThat(result.getSystemOwnerId()).isEqualTo("person-1");
		assertThat(result.getTechnicalContactId()).isEqualTo("person-2");
		assertThat(result.getHostingType()).isEqualTo(HostingType.CLOUD);
		assertThat(result.getSupplierId()).isEqualTo("supplier-1");
	}

	@Test
	void toSystemEntityNull() {
		assertThat(SystemMapper.toSystemEntity(null)).isNull();
	}

	@Test
	void toSystemEntityWithInvalidStatus() {
		final var model = System.create()
			.withSystemId("SYS-003")
			.withName("Test System 3")
			.withStatus("INVALID_STATUS");

		final var result = SystemMapper.toSystemEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("SYS-003");
		assertThat(result.getStatus()).isNull();
	}

	@Test
	void toSystemEntityWithInvalidHostingType() {
		final var model = System.create()
			.withSystemId("SYS-004")
			.withName("Test System 4")
			.withHostingType("INVALID_TYPE");

		final var result = SystemMapper.toSystemEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSystemId()).isEqualTo("SYS-004");
		assertThat(result.getHostingType()).isNull();
	}

	@Test
	void toSystemList() {
		final var entity1 = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("System 1");
		final var entity2 = SystemEntity.create()
			.withSystemId("SYS-002")
			.withName("System 2");
		final var entities = List.of(entity1, entity2);

		final var result = SystemMapper.toSystemList(entities);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getSystemId()).isEqualTo("SYS-001");
		assertThat(result.get(1).getSystemId()).isEqualTo("SYS-002");
	}

	@Test
	void toSystemListNull() {
		assertThat(SystemMapper.toSystemList(null)).isNull();
	}

	@Test
	void toSystemListEmpty() {
		final var result = SystemMapper.toSystemList(List.of());

		assertThat(result).isNotNull();
		assertThat(result).isEmpty();
	}

	@Test
	void updateSystemEntity() {
		final var entity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Old Name");

		final var model = System.create()
			.withName("New Name")
			.withDescription("New Description")
			.withStatus("DEPRECATED");

		SystemMapper.updateSystemEntity(entity, model);

		assertThat(entity.getSystemId()).isEqualTo("SYS-001");
		assertThat(entity.getName()).isEqualTo("New Name");
		assertThat(entity.getDescription()).isEqualTo("New Description");
		assertThat(entity.getStatus()).isEqualTo(SystemStatus.DEPRECATED);
	}

	@Test
	void updateSystemEntityNull() {
		final var entity = SystemEntity.create()
			.withSystemId("SYS-001");

		SystemMapper.updateSystemEntity(entity, null);

		assertThat(entity.getSystemId()).isEqualTo("SYS-001");
	}
}
