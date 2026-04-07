package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.integration.db.model.ServiceEntity;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceType;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceMapperTest {

	@Test
	void toServiceModel() {
		final var entity = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("Email Service")
			.withDescription("Email delivery service")
			.withVersion("1.5.0")
			.withEndpointUrl("https://api.example.com/email")
			.withDocumentationUrl("https://docs.example.com/email")
			.withServiceType(ServiceType.API)
			.withCriticalityLevelId("critical-1")
			.withKonfidentialitet(3)
			.withRiktighet(2)
			.withTillganglighet(3)
			.withOwnerOrganizationId("org-1")
			.withServiceOwnerId("person-1")
			.withTechnicalContactId("person-2")
			.withHostingType(HostingType.CLOUD)
			.withSupplierId("supplier-1");
		entity.withId("id-1");

		final var result = ServiceMapper.toService(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getServiceId()).isEqualTo("SVC-001");
		assertThat(result.getName()).isEqualTo("Email Service");
		assertThat(result.getDescription()).isEqualTo("Email delivery service");
		assertThat(result.getVersion()).isEqualTo("1.5.0");
		assertThat(result.getEndpointUrl()).isEqualTo("https://api.example.com/email");
		assertThat(result.getDocumentationUrl()).isEqualTo("https://docs.example.com/email");
		assertThat(result.getServiceType()).isEqualTo("API");
		assertThat(result.getCriticalityLevelId()).isEqualTo("critical-1");
	}

	@Test
	void toServiceModelNull() {
		assertThat(ServiceMapper.toService(null)).isNull();
	}

	@Test
	void toServiceEntity() {
		final var model = ServiceModel.create()
			.withServiceId("SVC-001")
			.withName("Email Service")
			.withDescription("Email delivery service")
			.withVersion("1.5.0")
			.withEndpointUrl("https://api.example.com/email")
			.withServiceType("API")
			.withHostingType("CLOUD");

		final var result = ServiceMapper.toServiceEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getServiceId()).isEqualTo("SVC-001");
		assertThat(result.getName()).isEqualTo("Email Service");
		assertThat(result.getVersion()).isEqualTo("1.5.0");
		assertThat(result.getServiceType()).isEqualTo(ServiceType.API);
		assertThat(result.getHostingType()).isEqualTo(HostingType.CLOUD);
	}

	@Test
	void toServiceEntityNull() {
		assertThat(ServiceMapper.toServiceEntity(null)).isNull();
	}

	@Test
	void toServiceModelList() {
		final var entity1 = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("Service 1");
		final var entity2 = ServiceEntity.create()
			.withServiceId("SVC-002")
			.withName("Service 2");
		final var entities = List.of(entity1, entity2);

		final var result = ServiceMapper.toServiceList(entities);

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getServiceId()).isEqualTo("SVC-001");
		assertThat(result.get(1).getServiceId()).isEqualTo("SVC-002");
	}

	@Test
	void toServiceModelListNull() {
		assertThat(ServiceMapper.toServiceList(null)).isNull();
	}

	@Test
	void updateServiceEntity() {
		final var entity = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("Old Name");

		final var model = ServiceModel.create()
			.withName("New Name")
			.withDescription("New Description")
			.withServiceType("WEB");

		ServiceMapper.updateServiceEntity(entity, model);

		assertThat(entity.getServiceId()).isEqualTo("SVC-001");
		assertThat(entity.getName()).isEqualTo("New Name");
		assertThat(entity.getDescription()).isEqualTo("New Description");
		assertThat(entity.getServiceType()).isEqualTo(ServiceType.WEB);
	}
}
