package se.sundsvall.systemregister.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingEntity;
import se.sundsvall.systemregister.integration.db.model.enums.LegalBasis;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public final class PersonuppgiftsbehandlingMapper {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private PersonuppgiftsbehandlingMapper() {
		// Private constructor to prevent instantiation
	}

	public static Personuppgiftsbehandling toDto(final PersonuppgiftsbehandlingEntity entity) {
		return ofNullable(entity)
			.map(e -> Personuppgiftsbehandling.create()
				.withBehandlingId(e.getBehandlingId())
				.withName(e.getName())
				.withDescription(e.getDescription())
				.withStatus(ofNullable(e.getStatus()).map(Enum::name).orElse(null))
				.withVerksamhetsomrade(e.getVerksamhetsomrade())
				.withControllerOrganizationId(e.getControllerOrganizationId())
				.withDpoId(e.getDpoId())
				.withProcessingResponsibleId(e.getProcessingResponsibleId())
				.withPurposeDescription(e.getPurposeDescription())
				.withDetailedPurposes(parseJson(e.getDetailedPurposes()))
				.withLegalBasis(ofNullable(e.getLegalBasis()).map(Enum::name).orElse(null))
				.withLegalReference(e.getLegalReference())
				.withSensitiveDataBasis(e.getSensitiveDataBasis())
				.withDataSubjectCategories(parseJson(e.getDataSubjectCategories()))
				.withEstimatedDataSubjectCount(e.getEstimatedDataSubjectCount())
				.withStandardDataCategories(parseJson(e.getStandardDataCategories()))
				.withProcessesSensitiveData(e.getProcessesSensitiveData())
				.withSensitiveDataCategories(parseJson(e.getSensitiveDataCategories()))
				.withProcessesCriminalData(e.getProcessesCriminalData())
				.withProcessesSsn(e.getProcessesSsn())
				.withInternalRecipients(parseJson(e.getInternalRecipients()))
				.withExternalRecipients(parseJson(e.getExternalRecipients()))
				.withDataProcessors(parseJson(e.getDataProcessors()))
				.withTransfersToThirdCountry(e.getTransfersToThirdCountry())
				.withThirdCountryDestinations(parseJson(e.getThirdCountryDestinations()))
				.withTransferProtectionMechanism(e.getTransferProtectionMechanism())
				.withRetentionPeriod(e.getRetentionPeriod())
				.withRetentionBasis(e.getRetentionBasis())
				.withHasArchivingObligation(e.getHasArchivingObligation())
				.withTechnicalMeasures(parseJson(e.getTechnicalMeasures()))
				.withOrganizationalMeasures(parseJson(e.getOrganizationalMeasures()))
				.withInformationMethod(e.getInformationMethod())
				.withPrivacyPolicyUrl(e.getPrivacyPolicyUrl())
				.withDpiaRequired(e.getDpiaRequired())
				.withDpiaDate(e.getDpiaDate())
				.withDpiaResults(e.getDpiaResults())
				.withDataSource(parseJson(e.getDataSource()))
				.withHasAutomatedDecisions(e.getHasAutomatedDecisions())
				.withAutomatedDecisionLogic(e.getAutomatedDecisionLogic()))
			.orElse(null);
	}

	public static PersonuppgiftsbehandlingEntity toEntity(final Personuppgiftsbehandling dto) {
		return ofNullable(dto)
			.map(d -> PersonuppgiftsbehandlingEntity.create()
				.withBehandlingId(d.getBehandlingId())
				.withName(d.getName())
				.withDescription(d.getDescription())
				.withStatus(ofNullable(d.getStatus()).map(ProcessingStatus::valueOf).orElse(null))
				.withVerksamhetsomrade(d.getVerksamhetsomrade())
				.withControllerOrganizationId(d.getControllerOrganizationId())
				.withDpoId(d.getDpoId())
				.withProcessingResponsibleId(d.getProcessingResponsibleId())
				.withPurposeDescription(d.getPurposeDescription())
				.withDetailedPurposes(toJsonString(d.getDetailedPurposes()))
				.withLegalBasis(ofNullable(d.getLegalBasis()).map(LegalBasis::valueOf).orElse(null))
				.withLegalReference(d.getLegalReference())
				.withSensitiveDataBasis(d.getSensitiveDataBasis())
				.withDataSubjectCategories(toJsonString(d.getDataSubjectCategories()))
				.withEstimatedDataSubjectCount(d.getEstimatedDataSubjectCount())
				.withStandardDataCategories(toJsonString(d.getStandardDataCategories()))
				.withProcessesSensitiveData(d.getProcessesSensitiveData())
				.withSensitiveDataCategories(toJsonString(d.getSensitiveDataCategories()))
				.withProcessesCriminalData(d.getProcessesCriminalData())
				.withProcessesSsn(d.getProcessesSsn())
				.withInternalRecipients(toJsonString(d.getInternalRecipients()))
				.withExternalRecipients(toJsonString(d.getExternalRecipients()))
				.withDataProcessors(toJsonString(d.getDataProcessors()))
				.withTransfersToThirdCountry(d.getTransfersToThirdCountry())
				.withThirdCountryDestinations(toJsonString(d.getThirdCountryDestinations()))
				.withTransferProtectionMechanism(d.getTransferProtectionMechanism())
				.withRetentionPeriod(d.getRetentionPeriod())
				.withRetentionBasis(d.getRetentionBasis())
				.withHasArchivingObligation(d.getHasArchivingObligation())
				.withTechnicalMeasures(toJsonString(d.getTechnicalMeasures()))
				.withOrganizationalMeasures(toJsonString(d.getOrganizationalMeasures()))
				.withInformationMethod(d.getInformationMethod())
				.withPrivacyPolicyUrl(d.getPrivacyPolicyUrl())
				.withDpiaRequired(d.getDpiaRequired())
				.withDpiaDate(d.getDpiaDate())
				.withDpiaResults(d.getDpiaResults())
				.withDataSource(toJsonString(d.getDataSource()))
				.withHasAutomatedDecisions(d.getHasAutomatedDecisions())
				.withAutomatedDecisionLogic(d.getAutomatedDecisionLogic()))
			.orElse(null);
	}

	public static void updateEntity(final PersonuppgiftsbehandlingEntity entity, final Personuppgiftsbehandling dto) {
		ofNullable(dto.getBehandlingId()).ifPresent(entity::withBehandlingId);
		ofNullable(dto.getName()).ifPresent(entity::withName);
		ofNullable(dto.getDescription()).ifPresent(entity::withDescription);
		ofNullable(dto.getStatus()).map(ProcessingStatus::valueOf).ifPresent(entity::withStatus);
		ofNullable(dto.getVerksamhetsomrade()).ifPresent(entity::withVerksamhetsomrade);
		ofNullable(dto.getControllerOrganizationId()).ifPresent(entity::withControllerOrganizationId);
		ofNullable(dto.getDpoId()).ifPresent(entity::withDpoId);
		ofNullable(dto.getProcessingResponsibleId()).ifPresent(entity::withProcessingResponsibleId);
		ofNullable(dto.getPurposeDescription()).ifPresent(entity::withPurposeDescription);
		ofNullable(dto.getLegalBasis()).map(LegalBasis::valueOf).ifPresent(entity::withLegalBasis);
		ofNullable(dto.getLegalReference()).ifPresent(entity::withLegalReference);
		ofNullable(dto.getSensitiveDataBasis()).ifPresent(entity::withSensitiveDataBasis);
		ofNullable(dto.getEstimatedDataSubjectCount()).ifPresent(entity::withEstimatedDataSubjectCount);
		ofNullable(dto.getProcessesSensitiveData()).ifPresent(entity::withProcessesSensitiveData);
		ofNullable(dto.getProcessesCriminalData()).ifPresent(entity::withProcessesCriminalData);
		ofNullable(dto.getProcessesSsn()).ifPresent(entity::withProcessesSsn);
		ofNullable(dto.getTransfersToThirdCountry()).ifPresent(entity::withTransfersToThirdCountry);
		ofNullable(dto.getTransferProtectionMechanism()).ifPresent(entity::withTransferProtectionMechanism);
		ofNullable(dto.getRetentionPeriod()).ifPresent(entity::withRetentionPeriod);
		ofNullable(dto.getRetentionBasis()).ifPresent(entity::withRetentionBasis);
		ofNullable(dto.getHasArchivingObligation()).ifPresent(entity::withHasArchivingObligation);
		ofNullable(dto.getInformationMethod()).ifPresent(entity::withInformationMethod);
		ofNullable(dto.getPrivacyPolicyUrl()).ifPresent(entity::withPrivacyPolicyUrl);
		ofNullable(dto.getDpiaRequired()).ifPresent(entity::withDpiaRequired);
		ofNullable(dto.getDpiaResults()).ifPresent(entity::withDpiaResults);
		ofNullable(dto.getHasAutomatedDecisions()).ifPresent(entity::withHasAutomatedDecisions);
		ofNullable(dto.getAutomatedDecisionLogic()).ifPresent(entity::withAutomatedDecisionLogic);
	}

	private static List<String> parseJson(final String json) {
		if (json == null) {
			return List.of();
		}
		try {
			return MAPPER.readValue(json, new TypeReference<>() {
			});
		} catch (final JsonProcessingException _) {
			throw Problem.valueOf(INTERNAL_SERVER_ERROR, "Failed to parse JSON");
		}
	}

	private static String toJsonString(final List<String> list) {
		if (list == null) {
			return null;
		}
		try {
			return MAPPER.writeValueAsString(list);
		} catch (final JsonProcessingException _) {
			throw Problem.valueOf(INTERNAL_SERVER_ERROR, "Failed to serialize JSON");
		}
	}
}
