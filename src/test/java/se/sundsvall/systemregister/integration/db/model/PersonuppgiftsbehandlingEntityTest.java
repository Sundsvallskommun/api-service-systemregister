package se.sundsvall.systemregister.integration.db.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.LegalBasis;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

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

class PersonuppgiftsbehandlingEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	private static PersonuppgiftsbehandlingEntity createPopulatedEntity() {
		return PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("behandlingId")
			.withName("name")
			.withDescription("description")
			.withStatus(ProcessingStatus.values()[0])
			.withVerksamhetsomrade("verksamhetsomrade")
			.withControllerOrganizationId("controllerOrganizationId")
			.withDpoId("dpoId")
			.withProcessingResponsibleId("processingResponsibleId")
			.withPurposeDescription("purposeDescription")
			.withDetailedPurposes("detailedPurposes")
			.withLegalBasis(LegalBasis.values()[0])
			.withLegalReference("legalReference")
			.withSensitiveDataBasis("sensitiveDataBasis")
			.withDataSubjectCategories("dataSubjectCategories")
			.withEstimatedDataSubjectCount(100L)
			.withStandardDataCategories("standardDataCategories")
			.withProcessesSensitiveData(true)
			.withSensitiveDataCategories("sensitiveDataCategories")
			.withProcessesCriminalData(false)
			.withProcessesSsn(true)
			.withInternalRecipients("internalRecipients")
			.withExternalRecipients("externalRecipients")
			.withDataProcessors("dataProcessors")
			.withTransfersToThirdCountry(false)
			.withThirdCountryDestinations("thirdCountryDestinations")
			.withTransferProtectionMechanism("transferProtectionMechanism")
			.withRetentionPeriod("retentionPeriod")
			.withRetentionBasis("retentionBasis")
			.withHasArchivingObligation(true)
			.withTechnicalMeasures("technicalMeasures")
			.withOrganizationalMeasures("organizationalMeasures")
			.withInformationMethod("informationMethod")
			.withPrivacyPolicyUrl("privacyPolicyUrl")
			.withDpiaRequired(false)
			.withDpiaDate(LocalDate.of(2024, 1, 1))
			.withDpiaResults("dpiaResults")
			.withDataSource("dataSource")
			.withHasAutomatedDecisions(true)
			.withAutomatedDecisionLogic("automatedDecisionLogic");
	}

	@Test
	void testBean() {
		assertThat(PersonuppgiftsbehandlingEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var result = createPopulatedEntity();

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
	}

	@Test
	void testBuilderMethodCoreValues() {
		final var result = createPopulatedEntity();

		assertThat(result.getBehandlingId()).isEqualTo("behandlingId");
		assertThat(result.getName()).isEqualTo("name");
		assertThat(result.getDescription()).isEqualTo("description");
		assertThat(result.getStatus()).isEqualTo(ProcessingStatus.values()[0]);
		assertThat(result.getVerksamhetsomrade()).isEqualTo("verksamhetsomrade");
		assertThat(result.getControllerOrganizationId()).isEqualTo("controllerOrganizationId");
		assertThat(result.getDpoId()).isEqualTo("dpoId");
		assertThat(result.getProcessingResponsibleId()).isEqualTo("processingResponsibleId");
		assertThat(result.getPurposeDescription()).isEqualTo("purposeDescription");
		assertThat(result.getDetailedPurposes()).isEqualTo("detailedPurposes");
		assertThat(result.getLegalBasis()).isEqualTo(LegalBasis.values()[0]);
		assertThat(result.getLegalReference()).isEqualTo("legalReference");
		assertThat(result.getSensitiveDataBasis()).isEqualTo("sensitiveDataBasis");
		assertThat(result.getDataSubjectCategories()).isEqualTo("dataSubjectCategories");
		assertThat(result.getEstimatedDataSubjectCount()).isEqualTo(100L);
		assertThat(result.getStandardDataCategories()).isEqualTo("standardDataCategories");
		assertThat(result.getProcessesSensitiveData()).isTrue();
		assertThat(result.getSensitiveDataCategories()).isEqualTo("sensitiveDataCategories");
		assertThat(result.getProcessesCriminalData()).isFalse();
	}

	@Test
	void testBuilderMethodAdditionalValues() {
		final var result = createPopulatedEntity();

		assertThat(result.getProcessesSsn()).isTrue();
		assertThat(result.getInternalRecipients()).isEqualTo("internalRecipients");
		assertThat(result.getExternalRecipients()).isEqualTo("externalRecipients");
		assertThat(result.getDataProcessors()).isEqualTo("dataProcessors");
		assertThat(result.getTransfersToThirdCountry()).isFalse();
		assertThat(result.getThirdCountryDestinations()).isEqualTo("thirdCountryDestinations");
		assertThat(result.getTransferProtectionMechanism()).isEqualTo("transferProtectionMechanism");
		assertThat(result.getRetentionPeriod()).isEqualTo("retentionPeriod");
		assertThat(result.getRetentionBasis()).isEqualTo("retentionBasis");
		assertThat(result.getHasArchivingObligation()).isTrue();
		assertThat(result.getTechnicalMeasures()).isEqualTo("technicalMeasures");
		assertThat(result.getOrganizationalMeasures()).isEqualTo("organizationalMeasures");
		assertThat(result.getInformationMethod()).isEqualTo("informationMethod");
		assertThat(result.getPrivacyPolicyUrl()).isEqualTo("privacyPolicyUrl");
		assertThat(result.getDpiaRequired()).isFalse();
		assertThat(result.getDpiaDate()).isEqualTo(LocalDate.of(2024, 1, 1));
		assertThat(result.getDpiaResults()).isEqualTo("dpiaResults");
		assertThat(result.getDataSource()).isEqualTo("dataSource");
		assertThat(result.getHasAutomatedDecisions()).isTrue();
		assertThat(result.getAutomatedDecisionLogic()).isEqualTo("automatedDecisionLogic");
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(PersonuppgiftsbehandlingEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new PersonuppgiftsbehandlingEntity()).hasAllNullFieldsOrProperties();
	}
}
