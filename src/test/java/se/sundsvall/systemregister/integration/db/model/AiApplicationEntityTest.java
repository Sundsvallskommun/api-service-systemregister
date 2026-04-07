package se.sundsvall.systemregister.integration.db.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

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

class AiApplicationEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(AiApplicationEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var aiApplicationId = "aiAppId";
		final var name = "name";
		final var description = "description";
		final var status = AIApplicationStatus.ACTIVE;
		final var riskCategory = AIRiskCategory.HIGH_RISK;
		final var highRiskArea = "highRiskArea";
		final var classificationJustification = "justification";
		final var classificationDate = LocalDate.now();
		final var classificationResponsibleId = "classResponsibleId";
		final var systemId = "systemId";
		final var ownerOrganizationId = "ownerOrgId";
		final var contactPersonId = "contactPersonId";
		final var oversightResponsibleId = "oversightId";
		final var incidentContactId = "incidentContactId";
		final var usageDescription = "usageDescription";
		final var friaRequired = true;
		final var friaDate = LocalDate.now().plusDays(1);
		final var friaResults = "friaResults";
		final var transparencyType = "transparencyType";
		final var transparencyMeasures = "transparencyMeasures";
		final var monitoringMeasures = "monitoringMeasures";
		final var registrationStatus = "registrationStatus";
		final var registrationDate = LocalDate.now().plusDays(2);

		final var result = AiApplicationEntity.create()
			.withAiApplicationId(aiApplicationId)
			.withName(name)
			.withDescription(description)
			.withStatus(status)
			.withRiskCategory(riskCategory)
			.withHighRiskArea(highRiskArea)
			.withClassificationJustification(classificationJustification)
			.withClassificationDate(classificationDate)
			.withClassificationResponsibleId(classificationResponsibleId)
			.withSystemId(systemId)
			.withOwnerOrganizationId(ownerOrganizationId)
			.withContactPersonId(contactPersonId)
			.withOversightResponsibleId(oversightResponsibleId)
			.withIncidentContactId(incidentContactId)
			.withUsageDescription(usageDescription)
			.withFriaRequired(friaRequired)
			.withFriaDate(friaDate)
			.withFriaResults(friaResults)
			.withTransparencyType(transparencyType)
			.withTransparencyMeasures(transparencyMeasures)
			.withMonitoringMeasures(monitoringMeasures)
			.withRegistrationStatus(registrationStatus)
			.withRegistrationDate(registrationDate);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getAiApplicationId()).isEqualTo(aiApplicationId);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getStatus()).isEqualTo(status);
		assertThat(result.getRiskCategory()).isEqualTo(riskCategory);
		assertThat(result.getHighRiskArea()).isEqualTo(highRiskArea);
		assertThat(result.getClassificationJustification()).isEqualTo(classificationJustification);
		assertThat(result.getClassificationDate()).isEqualTo(classificationDate);
		assertThat(result.getClassificationResponsibleId()).isEqualTo(classificationResponsibleId);
		assertThat(result.getSystemId()).isEqualTo(systemId);
		assertThat(result.getOwnerOrganizationId()).isEqualTo(ownerOrganizationId);
		assertThat(result.getContactPersonId()).isEqualTo(contactPersonId);
		assertThat(result.getOversightResponsibleId()).isEqualTo(oversightResponsibleId);
		assertThat(result.getIncidentContactId()).isEqualTo(incidentContactId);
		assertThat(result.getUsageDescription()).isEqualTo(usageDescription);
		assertThat(result.getFriaRequired()).isEqualTo(friaRequired);
		assertThat(result.getFriaDate()).isEqualTo(friaDate);
		assertThat(result.getFriaResults()).isEqualTo(friaResults);
		assertThat(result.getTransparencyType()).isEqualTo(transparencyType);
		assertThat(result.getTransparencyMeasures()).isEqualTo(transparencyMeasures);
		assertThat(result.getMonitoringMeasures()).isEqualTo(monitoringMeasures);
		assertThat(result.getRegistrationStatus()).isEqualTo(registrationStatus);
		assertThat(result.getRegistrationDate()).isEqualTo(registrationDate);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(AiApplicationEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new AiApplicationEntity()).hasAllNullFieldsOrProperties();
	}
}
