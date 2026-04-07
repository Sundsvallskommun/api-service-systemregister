package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceType;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class ServiceEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(ServiceEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var serviceId = "serviceId";
		final var name = "name";
		final var description = "description";
		final var version = "version";
		final var endpointUrl = "endpointUrl";
		final var documentationUrl = "documentationUrl";
		final var serviceType = ServiceType.values()[0];
		final var criticalityLevelId = "criticalityLevelId";
		final var konfidentialitet = 3;
		final var riktighet = 2;
		final var tillganglighet = 1;
		final var ownerOrganizationId = "ownerOrganizationId";
		final var serviceOwnerId = "serviceOwnerId";
		final var technicalContactId = "technicalContactId";
		final var hostingType = HostingType.values()[0];
		final var supplierId = "supplierId";

		final var result = ServiceEntity.create()
			.withServiceId(serviceId)
			.withName(name)
			.withDescription(description)
			.withVersion(version)
			.withEndpointUrl(endpointUrl)
			.withDocumentationUrl(documentationUrl)
			.withServiceType(serviceType)
			.withCriticalityLevelId(criticalityLevelId)
			.withKonfidentialitet(konfidentialitet)
			.withRiktighet(riktighet)
			.withTillganglighet(tillganglighet)
			.withOwnerOrganizationId(ownerOrganizationId)
			.withServiceOwnerId(serviceOwnerId)
			.withTechnicalContactId(technicalContactId)
			.withHostingType(hostingType)
			.withSupplierId(supplierId);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getServiceId()).isEqualTo(serviceId);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getVersion()).isEqualTo(version);
		assertThat(result.getEndpointUrl()).isEqualTo(endpointUrl);
		assertThat(result.getDocumentationUrl()).isEqualTo(documentationUrl);
		assertThat(result.getServiceType()).isEqualTo(serviceType);
		assertThat(result.getCriticalityLevelId()).isEqualTo(criticalityLevelId);
		assertThat(result.getKonfidentialitet()).isEqualTo(konfidentialitet);
		assertThat(result.getRiktighet()).isEqualTo(riktighet);
		assertThat(result.getTillganglighet()).isEqualTo(tillganglighet);
		assertThat(result.getOwnerOrganizationId()).isEqualTo(ownerOrganizationId);
		assertThat(result.getServiceOwnerId()).isEqualTo(serviceOwnerId);
		assertThat(result.getTechnicalContactId()).isEqualTo(technicalContactId);
		assertThat(result.getHostingType()).isEqualTo(hostingType);
		assertThat(result.getSupplierId()).isEqualTo(supplierId);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(ServiceEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new ServiceEntity()).hasAllNullFieldsOrProperties();
	}
}
