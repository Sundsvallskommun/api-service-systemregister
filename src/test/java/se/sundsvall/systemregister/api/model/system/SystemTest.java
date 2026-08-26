package se.sundsvall.systemregister.api.model.system;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class SystemTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(System.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var systemId = "systemId";
		final var name = "name";
		final var description = "description";
		final var status = "status";
		final var version = "version";
		final var documentationUrl = "documentationUrl";
		final var criticalityLevelId = "criticalityLevelId";
		final var konfidentialitet = 3;
		final var riktighet = 2;
		final var tillganglighet = 1;
		final var ownerOrganizationId = "ownerOrganizationId";
		final var systemOwnerId = "systemOwnerId";
		final var systemManagerId = "systemManagerId";
		final var technicalContactId = "technicalContactId";
		final var hostingType = "hostingType";
		final var supplierId = "supplierId";
		final var riskAnalysed = true;
		final var riskAnalysedDate = LocalDate.of(2026, Month.JUNE, 23);

		final var result = System.create()
			.withId(id)
			.withSystemId(systemId)
			.withName(name)
			.withDescription(description)
			.withStatus(status)
			.withVersion(version)
			.withDocumentationUrl(documentationUrl)
			.withCriticalityLevelId(criticalityLevelId)
			.withKonfidentialitet(konfidentialitet)
			.withRiktighet(riktighet)
			.withTillganglighet(tillganglighet)
			.withOwnerOrganizationId(ownerOrganizationId)
			.withSystemOwnerId(systemOwnerId)
			.withSystemManagerId(systemManagerId)
			.withTechnicalContactId(technicalContactId)
			.withHostingType(hostingType)
			.withSupplierId(supplierId)
			.withRiskAnalysed(riskAnalysed)
			.withRiskAnalysedDate(riskAnalysedDate);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getSystemId()).isEqualTo(systemId);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getStatus()).isEqualTo(status);
		assertThat(result.getVersion()).isEqualTo(version);
		assertThat(result.getDocumentationUrl()).isEqualTo(documentationUrl);
		assertThat(result.getCriticalityLevelId()).isEqualTo(criticalityLevelId);
		assertThat(result.getRiktighet()).isEqualTo(riktighet);
		assertThat(result.getTillganglighet()).isEqualTo(tillganglighet);
		assertThat(result.getOwnerOrganizationId()).isEqualTo(ownerOrganizationId);
		assertThat(result.getSystemOwnerId()).isEqualTo(systemOwnerId);
		assertThat(result.getSystemManagerId()).isEqualTo(systemManagerId);
		assertThat(result.getTechnicalContactId()).isEqualTo(technicalContactId);
		assertThat(result.getHostingType()).isEqualTo(hostingType);
		assertThat(result.getSupplierId()).isEqualTo(supplierId);
		assertThat(result.getRiskAnalysed()).isEqualTo(riskAnalysed);
		assertThat(result.getRiskAnalysedDate()).isEqualTo(riskAnalysedDate);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(System.create()).hasAllNullFieldsOrProperties();
		assertThat(new System()).hasAllNullFieldsOrProperties();
	}
}
