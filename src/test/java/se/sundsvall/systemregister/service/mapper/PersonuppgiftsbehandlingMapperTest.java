package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingEntity;
import se.sundsvall.systemregister.integration.db.model.enums.LegalBasis;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

import static org.assertj.core.api.Assertions.assertThat;

class PersonuppgiftsbehandlingMapperTest {

	@Test
	void toDtoWithNull() {
		assertThat(PersonuppgiftsbehandlingMapper.toDto(null)).isNull();
	}

	@Test
	void toDto() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withName("Customer Processing")
			.withDescription("Process customer data")
			.withStatus(ProcessingStatus.ACTIVE)
			.withVerksamhetsomrade("Finance")
			.withControllerOrganizationId("org-001")
			.withDpoId("dpo-001")
			.withProcessingResponsibleId("person-001")
			.withPurposeDescription("Billing")
			.withDetailedPurposes("[\"billing\",\"invoicing\"]")
			.withLegalBasis(LegalBasis.AVTAL)
			.withLegalReference("Agreement")
			.withSensitiveDataBasis("Business need")
			.withDataSubjectCategories("[\"customers\"]")
			.withEstimatedDataSubjectCount(5000L)
			.withStandardDataCategories("[\"name\"]")
			.withProcessesSensitiveData(false)
			.withSensitiveDataCategories("[\"health\"]")
			.withProcessesCriminalData(false)
			.withProcessesSsn(true)
			.withInternalRecipients("[\"accounting\"]")
			.withExternalRecipients("[\"tax\"]")
			.withDataProcessors("[\"proc\"]")
			.withTransfersToThirdCountry(false)
			.withThirdCountryDestinations("[\"USA\"]")
			.withTransferProtectionMechanism("SCCs")
			.withRetentionPeriod("5 years")
			.withRetentionBasis("Legal")
			.withHasArchivingObligation(true)
			.withTechnicalMeasures("[\"encryption\"]")
			.withOrganizationalMeasures("[\"training\"]")
			.withInformationMethod("Email")
			.withPrivacyPolicyUrl("https://example.com/privacy")
			.withDpiaRequired(true)
			.withDpiaResults("Low risk")
			.withDataSource("[\"registration\"]")
			.withHasAutomatedDecisions(false)
			.withAutomatedDecisionLogic("None");

		final var dto = PersonuppgiftsbehandlingMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(dto.getName()).isEqualTo("Customer Processing");
		assertThat(dto.getDescription()).isEqualTo("Process customer data");
		assertThat(dto.getStatus()).isEqualTo("ACTIVE");
		assertThat(dto.getVerksamhetsomrade()).isEqualTo("Finance");
		assertThat(dto.getControllerOrganizationId()).isEqualTo("org-001");
		assertThat(dto.getDpoId()).isEqualTo("dpo-001");
		assertThat(dto.getProcessingResponsibleId()).isEqualTo("person-001");
		assertThat(dto.getPurposeDescription()).isEqualTo("Billing");
		assertThat(dto.getDetailedPurposes()).isEqualTo(List.of("billing", "invoicing"));
		assertThat(dto.getLegalBasis()).isEqualTo("AVTAL");
		assertThat(dto.getLegalReference()).isEqualTo("Agreement");
		assertThat(dto.getSensitiveDataBasis()).isEqualTo("Business need");
		assertThat(dto.getDataSubjectCategories()).isEqualTo(List.of("customers"));
		assertThat(dto.getEstimatedDataSubjectCount()).isEqualTo(5000L);
		assertThat(dto.getStandardDataCategories()).isEqualTo(List.of("name"));
		assertThat(dto.getProcessesSensitiveData()).isFalse();
		assertThat(dto.getSensitiveDataCategories()).isEqualTo(List.of("health"));
		assertThat(dto.getProcessesCriminalData()).isFalse();
		assertThat(dto.getProcessesSsn()).isTrue();
		assertThat(dto.getInternalRecipients()).isEqualTo(List.of("accounting"));
		assertThat(dto.getExternalRecipients()).isEqualTo(List.of("tax"));
		assertThat(dto.getDataProcessors()).isEqualTo(List.of("proc"));
		assertThat(dto.getTransfersToThirdCountry()).isFalse();
		assertThat(dto.getThirdCountryDestinations()).isEqualTo(List.of("USA"));
		assertThat(dto.getTransferProtectionMechanism()).isEqualTo("SCCs");
		assertThat(dto.getRetentionPeriod()).isEqualTo("5 years");
		assertThat(dto.getRetentionBasis()).isEqualTo("Legal");
		assertThat(dto.getHasArchivingObligation()).isTrue();
		assertThat(dto.getTechnicalMeasures()).isEqualTo(List.of("encryption"));
		assertThat(dto.getOrganizationalMeasures()).isEqualTo(List.of("training"));
		assertThat(dto.getInformationMethod()).isEqualTo("Email");
		assertThat(dto.getPrivacyPolicyUrl()).isEqualTo("https://example.com/privacy");
		assertThat(dto.getDpiaRequired()).isTrue();
		assertThat(dto.getDpiaResults()).isEqualTo("Low risk");
		assertThat(dto.getDataSource()).isEqualTo(List.of("registration"));
		assertThat(dto.getHasAutomatedDecisions()).isFalse();
		assertThat(dto.getAutomatedDecisionLogic()).isEqualTo("None");
	}

	@Test
	void toDtoWithNullEnums() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withStatus(null)
			.withLegalBasis(null);

		final var dto = PersonuppgiftsbehandlingMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getStatus()).isNull();
		assertThat(dto.getLegalBasis()).isNull();
	}

	@Test
	void toEntityWithNull() {
		assertThat(PersonuppgiftsbehandlingMapper.toEntity(null)).isNull();
	}

	@Test
	void toEntity() {
		final var dto = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Customer Processing")
			.withDescription("Process customer data")
			.withStatus("ACTIVE")
			.withVerksamhetsomrade("Finance")
			.withControllerOrganizationId("org-001")
			.withDpoId("dpo-001")
			.withProcessingResponsibleId("person-001")
			.withPurposeDescription("Billing")
			.withDetailedPurposes(List.of("billing", "invoicing"))
			.withLegalBasis("AVTAL")
			.withLegalReference("Agreement")
			.withSensitiveDataBasis("Business need")
			.withDataSubjectCategories(List.of("customers"))
			.withEstimatedDataSubjectCount(5000L)
			.withStandardDataCategories(List.of("name"))
			.withProcessesSensitiveData(false)
			.withSensitiveDataCategories(List.of("health"))
			.withProcessesCriminalData(false)
			.withProcessesSsn(true)
			.withInternalRecipients(List.of("accounting"))
			.withExternalRecipients(List.of("tax"))
			.withDataProcessors(List.of("proc"))
			.withTransfersToThirdCountry(false)
			.withThirdCountryDestinations(List.of("USA"))
			.withTransferProtectionMechanism("SCCs")
			.withRetentionPeriod("5 years")
			.withRetentionBasis("Legal")
			.withHasArchivingObligation(true)
			.withTechnicalMeasures(List.of("encryption"))
			.withOrganizationalMeasures(List.of("training"))
			.withInformationMethod("Email")
			.withPrivacyPolicyUrl("https://example.com/privacy")
			.withDpiaRequired(true)
			.withDpiaResults("Low risk")
			.withDataSource(List.of("registration"))
			.withHasAutomatedDecisions(false)
			.withAutomatedDecisionLogic("None");

		final var entity = PersonuppgiftsbehandlingMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(entity.getName()).isEqualTo("Customer Processing");
		assertThat(entity.getDescription()).isEqualTo("Process customer data");
		assertThat(entity.getStatus()).isEqualTo(ProcessingStatus.ACTIVE);
		assertThat(entity.getVerksamhetsomrade()).isEqualTo("Finance");
		assertThat(entity.getControllerOrganizationId()).isEqualTo("org-001");
		assertThat(entity.getDpoId()).isEqualTo("dpo-001");
		assertThat(entity.getProcessingResponsibleId()).isEqualTo("person-001");
		assertThat(entity.getPurposeDescription()).isEqualTo("Billing");
		assertThat(entity.getDetailedPurposes()).isEqualTo("[\"billing\",\"invoicing\"]");
		assertThat(entity.getLegalBasis()).isEqualTo(LegalBasis.AVTAL);
		assertThat(entity.getLegalReference()).isEqualTo("Agreement");
		assertThat(entity.getSensitiveDataBasis()).isEqualTo("Business need");
		assertThat(entity.getDataSubjectCategories()).isEqualTo("[\"customers\"]");
		assertThat(entity.getEstimatedDataSubjectCount()).isEqualTo(5000L);
		assertThat(entity.getStandardDataCategories()).isEqualTo("[\"name\"]");
		assertThat(entity.getProcessesSensitiveData()).isFalse();
		assertThat(entity.getSensitiveDataCategories()).isEqualTo("[\"health\"]");
		assertThat(entity.getProcessesCriminalData()).isFalse();
		assertThat(entity.getProcessesSsn()).isTrue();
		assertThat(entity.getInternalRecipients()).isEqualTo("[\"accounting\"]");
		assertThat(entity.getExternalRecipients()).isEqualTo("[\"tax\"]");
		assertThat(entity.getDataProcessors()).isEqualTo("[\"proc\"]");
		assertThat(entity.getTransfersToThirdCountry()).isFalse();
		assertThat(entity.getThirdCountryDestinations()).isEqualTo("[\"USA\"]");
		assertThat(entity.getTransferProtectionMechanism()).isEqualTo("SCCs");
		assertThat(entity.getRetentionPeriod()).isEqualTo("5 years");
		assertThat(entity.getRetentionBasis()).isEqualTo("Legal");
		assertThat(entity.getHasArchivingObligation()).isTrue();
		assertThat(entity.getTechnicalMeasures()).isEqualTo("[\"encryption\"]");
		assertThat(entity.getOrganizationalMeasures()).isEqualTo("[\"training\"]");
		assertThat(entity.getInformationMethod()).isEqualTo("Email");
		assertThat(entity.getPrivacyPolicyUrl()).isEqualTo("https://example.com/privacy");
		assertThat(entity.getDpiaRequired()).isTrue();
		assertThat(entity.getDpiaResults()).isEqualTo("Low risk");
		assertThat(entity.getDataSource()).isEqualTo("[\"registration\"]");
		assertThat(entity.getHasAutomatedDecisions()).isFalse();
		assertThat(entity.getAutomatedDecisionLogic()).isEqualTo("None");
	}

	@Test
	void toEntityWithNullEnums() {
		final var dto = Personuppgiftsbehandling.create()
			.withStatus(null)
			.withLegalBasis(null);

		final var entity = PersonuppgiftsbehandlingMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getStatus()).isNull();
		assertThat(entity.getLegalBasis()).isNull();
	}

	@Test
	void roundTrip() {
		final var dto = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Test")
			.withStatus("ACTIVE")
			.withLegalBasis("AVTAL");

		final var entity = PersonuppgiftsbehandlingMapper.toEntity(dto);
		final var roundTrip = PersonuppgiftsbehandlingMapper.toDto(entity);

		assertThat(roundTrip.getBehandlingId()).isEqualTo(dto.getBehandlingId());
		assertThat(roundTrip.getName()).isEqualTo(dto.getName());
		assertThat(roundTrip.getStatus()).isEqualTo(dto.getStatus());
		assertThat(roundTrip.getLegalBasis()).isEqualTo(dto.getLegalBasis());
	}
}
