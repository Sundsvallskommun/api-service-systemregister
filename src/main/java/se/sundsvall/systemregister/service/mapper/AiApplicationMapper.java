package se.sundsvall.systemregister.service.mapper;

import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.integration.db.model.AiApplicationEntity;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

import static java.util.Optional.ofNullable;

public final class AiApplicationMapper {

	private AiApplicationMapper() {
		// Private constructor to prevent instantiation
	}

	public static AiApplication toDto(final AiApplicationEntity entity) {
		return ofNullable(entity)
			.map(e -> AiApplication.create()
				.withAiApplicationId(e.getAiApplicationId())
				.withName(e.getName())
				.withDescription(e.getDescription())
				.withStatus(ofNullable(e.getStatus()).map(Enum::name).orElse(null))
				.withRiskCategory(ofNullable(e.getRiskCategory()).map(Enum::name).orElse(null))
				.withHighRiskArea(e.getHighRiskArea())
				.withClassificationJustification(e.getClassificationJustification())
				.withClassificationDate(e.getClassificationDate())
				.withClassificationResponsibleId(e.getClassificationResponsibleId())
				.withSystemId(e.getSystemId())
				.withOwnerOrganizationId(e.getOwnerOrganizationId())
				.withContactPersonId(e.getContactPersonId())
				.withOversightResponsibleId(e.getOversightResponsibleId())
				.withIncidentContactId(e.getIncidentContactId())
				.withUsageDescription(e.getUsageDescription())
				.withFriaRequired(e.getFriaRequired())
				.withFriaDate(e.getFriaDate())
				.withFriaResults(e.getFriaResults())
				.withTransparencyType(e.getTransparencyType())
				.withTransparencyMeasures(e.getTransparencyMeasures())
				.withMonitoringMeasures(e.getMonitoringMeasures())
				.withRegistrationStatus(e.getRegistrationStatus())
				.withRegistrationDate(e.getRegistrationDate()))
			.orElse(null);
	}

	public static void updateEntity(final AiApplicationEntity entity, final AiApplication dto) {
		ofNullable(dto.getAiApplicationId()).ifPresent(entity::withAiApplicationId);
		ofNullable(dto.getName()).ifPresent(entity::withName);
		ofNullable(dto.getDescription()).ifPresent(entity::withDescription);
		ofNullable(dto.getStatus()).map(AIApplicationStatus::valueOf).ifPresent(entity::withStatus);
		ofNullable(dto.getRiskCategory()).map(AIRiskCategory::valueOf).ifPresent(entity::withRiskCategory);
		ofNullable(dto.getHighRiskArea()).ifPresent(entity::withHighRiskArea);
		ofNullable(dto.getClassificationJustification()).ifPresent(entity::withClassificationJustification);
		ofNullable(dto.getClassificationDate()).ifPresent(entity::withClassificationDate);
		ofNullable(dto.getClassificationResponsibleId()).ifPresent(entity::withClassificationResponsibleId);
		ofNullable(dto.getSystemId()).ifPresent(entity::withSystemId);
		ofNullable(dto.getOwnerOrganizationId()).ifPresent(entity::withOwnerOrganizationId);
		ofNullable(dto.getContactPersonId()).ifPresent(entity::withContactPersonId);
		ofNullable(dto.getOversightResponsibleId()).ifPresent(entity::withOversightResponsibleId);
		ofNullable(dto.getIncidentContactId()).ifPresent(entity::withIncidentContactId);
		ofNullable(dto.getUsageDescription()).ifPresent(entity::withUsageDescription);
		ofNullable(dto.getFriaRequired()).ifPresent(entity::withFriaRequired);
		ofNullable(dto.getFriaDate()).ifPresent(entity::withFriaDate);
		ofNullable(dto.getFriaResults()).ifPresent(entity::withFriaResults);
		ofNullable(dto.getTransparencyType()).ifPresent(entity::withTransparencyType);
		ofNullable(dto.getTransparencyMeasures()).ifPresent(entity::withTransparencyMeasures);
		ofNullable(dto.getMonitoringMeasures()).ifPresent(entity::withMonitoringMeasures);
		ofNullable(dto.getRegistrationStatus()).ifPresent(entity::withRegistrationStatus);
		ofNullable(dto.getRegistrationDate()).ifPresent(entity::withRegistrationDate);
	}

	public static AiApplicationEntity toEntity(final AiApplication dto) {
		return ofNullable(dto)
			.map(d -> AiApplicationEntity.create()
				.withAiApplicationId(d.getAiApplicationId())
				.withName(d.getName())
				.withDescription(d.getDescription())
				.withStatus(ofNullable(d.getStatus()).map(AIApplicationStatus::valueOf).orElse(null))
				.withRiskCategory(ofNullable(d.getRiskCategory()).map(AIRiskCategory::valueOf).orElse(null))
				.withHighRiskArea(d.getHighRiskArea())
				.withClassificationJustification(d.getClassificationJustification())
				.withClassificationDate(d.getClassificationDate())
				.withClassificationResponsibleId(d.getClassificationResponsibleId())
				.withSystemId(d.getSystemId())
				.withOwnerOrganizationId(d.getOwnerOrganizationId())
				.withContactPersonId(d.getContactPersonId())
				.withOversightResponsibleId(d.getOversightResponsibleId())
				.withIncidentContactId(d.getIncidentContactId())
				.withUsageDescription(d.getUsageDescription())
				.withFriaRequired(d.getFriaRequired())
				.withFriaDate(d.getFriaDate())
				.withFriaResults(d.getFriaResults())
				.withTransparencyType(d.getTransparencyType())
				.withTransparencyMeasures(d.getTransparencyMeasures())
				.withMonitoringMeasures(d.getMonitoringMeasures())
				.withRegistrationStatus(d.getRegistrationStatus())
				.withRegistrationDate(d.getRegistrationDate()))
			.orElse(null);
	}
}
