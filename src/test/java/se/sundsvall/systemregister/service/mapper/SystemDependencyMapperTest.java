package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.SystemDependency;
import se.sundsvall.systemregister.integration.db.model.SystemDependencyEntity;
import se.sundsvall.systemregister.integration.db.model.enums.DependencyType;

import static org.assertj.core.api.Assertions.assertThat;

class SystemDependencyMapperTest {

	@Test
	void toSystemDependency() {
		final var entity = SystemDependencyEntity.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2")
			.withDependencyType(DependencyType.INTEGRATION)
			.withDescription("System depends on database cluster")
			.withIsCritical(true)
			.withDocumentationUrl("https://docs.example.com/dependency-info")
			.withContractFilePath("/contracts/system-dependency.pdf");
		entity.withId("dependency-1");

		final var result = SystemDependencyMapper.toSystemDependency(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("dependency-1");
		assertThat(result.getSourceSystemId()).isEqualTo("system-1");
		assertThat(result.getTargetSystemId()).isEqualTo("system-2");
		assertThat(result.getDependencyType()).isEqualTo("INTEGRATION");
		assertThat(result.getDescription()).isEqualTo("System depends on database cluster");
		assertThat(result.getIsCritical()).isTrue();
		assertThat(result.getDocumentationUrl()).isEqualTo("https://docs.example.com/dependency-info");
		assertThat(result.getContractFilePath()).isEqualTo("/contracts/system-dependency.pdf");
	}

	@Test
	void toSystemDependencyNull() {
		assertThat(SystemDependencyMapper.toSystemDependency(null)).isNull();
	}

	@Test
	void toSystemDependencyEntity() {
		final var model = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2")
			.withDependencyType("INTEGRATION")
			.withDescription("System depends on database cluster")
			.withIsCritical(true)
			.withDocumentationUrl("https://docs.example.com/dependency-info")
			.withContractFilePath("/contracts/system-dependency.pdf");

		final var result = SystemDependencyMapper.toSystemDependencyEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSourceSystemId()).isEqualTo("system-1");
		assertThat(result.getTargetSystemId()).isEqualTo("system-2");
		assertThat(result.getDependencyType()).isEqualTo(DependencyType.INTEGRATION);
		assertThat(result.getDescription()).isEqualTo("System depends on database cluster");
		assertThat(result.getIsCritical()).isTrue();
	}

	@Test
	void toSystemDependencyEntityNull() {
		assertThat(SystemDependencyMapper.toSystemDependencyEntity(null)).isNull();
	}

	@Test
	void toSystemDependencyEntityWithInvalidType() {
		final var model = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2")
			.withDependencyType("INVALID_TYPE");

		final var result = SystemDependencyMapper.toSystemDependencyEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getSourceSystemId()).isEqualTo("system-1");
		assertThat(result.getDependencyType()).isNull();
	}

	@Test
	void toSystemDependencyList() {
		final var entity1 = SystemDependencyEntity.create()
			.withSourceSystemId("system-1");
		entity1.withId("dependency-1");
		final var entity2 = SystemDependencyEntity.create()
			.withSourceSystemId("system-2");
		entity2.withId("dependency-2");
		final var entities = List.of(entity1, entity2);

		final var result = SystemDependencyMapper.toSystemDependencyList(entities);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getSourceSystemId()).isEqualTo("system-1");
		assertThat(result.get(1).getSourceSystemId()).isEqualTo("system-2");
	}

	@Test
	void toSystemDependencyListNull() {
		assertThat(SystemDependencyMapper.toSystemDependencyList(null)).isNull();
	}

	@Test
	void updateSystemDependencyEntity() {
		final var entity = SystemDependencyEntity.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");

		final var model = SystemDependency.create()
			.withDescription("Updated dependency description")
			.withDependencyType("API")
			.withIsCritical(false);

		SystemDependencyMapper.updateSystemDependencyEntity(entity, model);

		assertThat(entity.getSourceSystemId()).isEqualTo("system-1");
		assertThat(entity.getTargetSystemId()).isEqualTo("system-2");
		assertThat(entity.getDescription()).isEqualTo("Updated dependency description");
		assertThat(entity.getDependencyType()).isEqualTo(DependencyType.API);
		assertThat(entity.getIsCritical()).isFalse();
	}
}
