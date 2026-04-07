package se.sundsvall.systemregister.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonuppgiftsbehandlingTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Personuppgiftsbehandling.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var behandlingId = "behandlingId";
		final var name = "name";
		final var description = "description";
		final var status = "status";
		final var verksamhetsomrade = "verksamhetsomrade";
		final var controllerOrganizationId = "controllerOrganizationId";
		final var dpoId = "dpoId";
		final var processingResponsibleId = "processingResponsibleId";
		final var purposeDescription = "purposeDescription";
		final var detailedPurposes = List.of("purpose1");
		final var legalBasis = "legalBasis";
		final var legalReference = "legalReference";
		final var sensitiveDataBasis = "sensitiveDataBasis";
		final var dataSubjectCategories = List.of("category1");
		final var estimatedDataSubjectCount = 100L;
		final var standardDataCategories = List.of("standard1");
		final var processesSensitiveData = true;
		final var sensitiveDataCategories = List.of("sensitive1");
		final var processesCriminalData = false;
		final var processesSsn = true;
		final var internalRecipients = List.of("internal1");
		final var externalRecipients = List.of("external1");
		final var dataProcessors = List.of("processor1");
		final var transfersToThirdCountry = false;
		final var thirdCountryDestinations = List.of("country1");
		final var transferProtectionMechanism = "transferProtectionMechanism";
		final var retentionPeriod = "retentionPeriod";
		final var retentionBasis = "retentionBasis";
		final var hasArchivingObligation = true;
		final var technicalMeasures = List.of("measure1");
		final var organizationalMeasures = List.of("orgMeasure1");
		final var informationMethod = "informationMethod";
		final var privacyPolicyUrl = "privacyPolicyUrl";
		final var dpiaRequired = false;
		final var dpiaDate = LocalDate.of(2024, 1, 1);
		final var dpiaResults = "dpiaResults";
		final var dataSource = List.of("source1");
		final var hasAutomatedDecisions = true;
		final var automatedDecisionLogic = "automatedDecisionLogic";

		final var result = Personuppgiftsbehandling.create()
			.withBehandlingId(behandlingId)
			.withName(name)
			.withDescription(description)
			.withStatus(status)
			.withVerksamhetsomrade(verksamhetsomrade)
			.withControllerOrganizationId(controllerOrganizationId)
			.withDpoId(dpoId)
			.withProcessingResponsibleId(processingResponsibleId)
			.withPurposeDescription(purposeDescription)
			.withDetailedPurposes(detailedPurposes)
			.withLegalBasis(legalBasis)
			.withLegalReference(legalReference)
			.withSensitiveDataBasis(sensitiveDataBasis)
			.withDataSubjectCategories(dataSubjectCategories)
			.withEstimatedDataSubjectCount(estimatedDataSubjectCount)
			.withStandardDataCategories(standardDataCategories)
			.withProcessesSensitiveData(processesSensitiveData)
			.withSensitiveDataCategories(sensitiveDataCategories)
			.withProcessesCriminalData(processesCriminalData)
			.withProcessesSsn(processesSsn)
			.withInternalRecipients(internalRecipients)
			.withExternalRecipients(externalRecipients)
			.withDataProcessors(dataProcessors)
			.withTransfersToThirdCountry(transfersToThirdCountry)
			.withThirdCountryDestinations(thirdCountryDestinations)
			.withTransferProtectionMechanism(transferProtectionMechanism)
			.withRetentionPeriod(retentionPeriod)
			.withRetentionBasis(retentionBasis)
			.withHasArchivingObligation(hasArchivingObligation)
			.withTechnicalMeasures(technicalMeasures)
			.withOrganizationalMeasures(organizationalMeasures)
			.withInformationMethod(informationMethod)
			.withPrivacyPolicyUrl(privacyPolicyUrl)
			.withDpiaRequired(dpiaRequired)
			.withDpiaDate(dpiaDate)
			.withDpiaResults(dpiaResults)
			.withDataSource(dataSource)
			.withHasAutomatedDecisions(hasAutomatedDecisions)
			.withAutomatedDecisionLogic(automatedDecisionLogic);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getBehandlingId()).isEqualTo(behandlingId);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getStatus()).isEqualTo(status);
		assertThat(result.getVerksamhetsomrade()).isEqualTo(verksamhetsomrade);
		assertThat(result.getControllerOrganizationId()).isEqualTo(controllerOrganizationId);
		assertThat(result.getDpoId()).isEqualTo(dpoId);
		assertThat(result.getProcessingResponsibleId()).isEqualTo(processingResponsibleId);
		assertThat(result.getPurposeDescription()).isEqualTo(purposeDescription);
		assertThat(result.getDetailedPurposes()).isEqualTo(detailedPurposes);
		assertThat(result.getLegalBasis()).isEqualTo(legalBasis);
		assertThat(result.getLegalReference()).isEqualTo(legalReference);
		assertThat(result.getSensitiveDataBasis()).isEqualTo(sensitiveDataBasis);
		assertThat(result.getDataSubjectCategories()).isEqualTo(dataSubjectCategories);
		assertThat(result.getEstimatedDataSubjectCount()).isEqualTo(estimatedDataSubjectCount);
		assertThat(result.getStandardDataCategories()).isEqualTo(standardDataCategories);
		assertThat(result.getProcessesSensitiveData()).isEqualTo(processesSensitiveData);
		assertThat(result.getSensitiveDataCategories()).isEqualTo(sensitiveDataCategories);
		assertThat(result.getProcessesCriminalData()).isEqualTo(processesCriminalData);
		assertThat(result.getProcessesSsn()).isEqualTo(processesSsn);
		assertThat(result.getInternalRecipients()).isEqualTo(internalRecipients);
		assertThat(result.getExternalRecipients()).isEqualTo(externalRecipients);
		assertThat(result.getDataProcessors()).isEqualTo(dataProcessors);
		assertThat(result.getTransfersToThirdCountry()).isEqualTo(transfersToThirdCountry);
		assertThat(result.getThirdCountryDestinations()).isEqualTo(thirdCountryDestinations);
		assertThat(result.getTransferProtectionMechanism()).isEqualTo(transferProtectionMechanism);
		assertThat(result.getRetentionPeriod()).isEqualTo(retentionPeriod);
		assertThat(result.getRetentionBasis()).isEqualTo(retentionBasis);
		assertThat(result.getHasArchivingObligation()).isEqualTo(hasArchivingObligation);
		assertThat(result.getTechnicalMeasures()).isEqualTo(technicalMeasures);
		assertThat(result.getOrganizationalMeasures()).isEqualTo(organizationalMeasures);
		assertThat(result.getInformationMethod()).isEqualTo(informationMethod);
		assertThat(result.getPrivacyPolicyUrl()).isEqualTo(privacyPolicyUrl);
		assertThat(result.getDpiaRequired()).isEqualTo(dpiaRequired);
		assertThat(result.getDpiaDate()).isEqualTo(dpiaDate);
		assertThat(result.getDpiaResults()).isEqualTo(dpiaResults);
		assertThat(result.getDataSource()).isEqualTo(dataSource);
		assertThat(result.getHasAutomatedDecisions()).isEqualTo(hasAutomatedDecisions);
		assertThat(result.getAutomatedDecisionLogic()).isEqualTo(automatedDecisionLogic);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Personuppgiftsbehandling.create()).hasAllNullFieldsOrProperties();
		assertThat(new Personuppgiftsbehandling()).hasAllNullFieldsOrProperties();
	}
}
