package se.sundsvall.systemregister.api.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class ServiceModelTest {

	@Test
	void testBean() {
		assertThat(ServiceModel.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var serviceId = "serviceId";
		final var name = "name";
		final var description = "description";
		final var version = "version";
		final var endpointUrl = "endpointUrl";
		final var documentationUrl = "documentationUrl";
		final var serviceType = "serviceType";
		final var criticalityLevelId = "criticalityLevelId";
		final var konfidentialitet = 3;
		final var riktighet = 2;
		final var tillganglighet = 1;
		final var ownerOrganizationId = "ownerOrganizationId";
		final var serviceOwnerId = "serviceOwnerId";
		final var technicalContactId = "technicalContactId";
		final var hostingType = "hostingType";
		final var supplierId = "supplierId";

		final var result = ServiceModel.create()
			.withId(id)
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

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(ServiceModel.create()).hasAllNullFieldsOrProperties();
		assertThat(new ServiceModel()).hasAllNullFieldsOrProperties();
	}
}
