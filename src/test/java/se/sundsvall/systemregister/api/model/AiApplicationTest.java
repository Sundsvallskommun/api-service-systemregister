package se.sundsvall.systemregister.api.model;

import java.time.LocalDate;
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

class AiApplicationTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt(365)), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(AiApplication.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var aiApplicationId = "aiApplicationId";
		final var name = "name";
		final var description = "description";
		final var status = "status";
		final var riskCategory = "riskCategory";
		final var highRiskArea = "highRiskArea";
		final var classificationJustification = "classificationJustification";
		final var classificationDate = LocalDate.now();
		final var classificationResponsibleId = "classificationResponsibleId";
		final var systemId = "systemId";
		final var ownerOrganizationId = "ownerOrganizationId";
		final var contactPersonId = "contactPersonId";
		final var oversightResponsibleId = "oversightResponsibleId";
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

		final var result = AiApplication.create()
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

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
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
		assertThat(AiApplication.create()).hasAllNullFieldsOrProperties();
		assertThat(new AiApplication()).hasAllNullFieldsOrProperties();
	}
}
