package se.sundsvall.systemregister.service.mapper;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.integration.db.model.AiApplicationEntity;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

import static org.assertj.core.api.Assertions.assertThat;

class AiApplicationMapperTest {

	@Test
	void toDtoWithNull() {
		assertThat(AiApplicationMapper.toDto(null)).isNull();
	}

	@Test
	void toDto() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withName("Risk Assessment")
			.withDescription("Customer risk assessment")
			.withStatus(AIApplicationStatus.ACTIVE)
			.withRiskCategory(AIRiskCategory.HIGH_RISK)
			.withHighRiskArea("Credit Decision")
			.withClassificationJustification("Automated decisions")
			.withClassificationDate(LocalDate.of(2024, 1, 15))
			.withClassificationResponsibleId("person-001")
			.withSystemId("system-001")
			.withOwnerOrganizationId("org-001")
			.withContactPersonId("person-002")
			.withOversightResponsibleId("person-003")
			.withIncidentContactId("person-004")
			.withUsageDescription("Risk scoring")
			.withFriaRequired(true)
			.withFriaDate(LocalDate.of(2024, 2, 1))
			.withFriaResults("Completed")
			.withTransparencyType("Notice")
			.withTransparencyMeasures("Notification")
			.withMonitoringMeasures("Review")
			.withRegistrationStatus("Registered")
			.withRegistrationDate(LocalDate.of(2024, 3, 1));

		final var dto = AiApplicationMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(dto.getName()).isEqualTo("Risk Assessment");
		assertThat(dto.getDescription()).isEqualTo("Customer risk assessment");
		assertThat(dto.getStatus()).isEqualTo("ACTIVE");
		assertThat(dto.getRiskCategory()).isEqualTo("HIGH_RISK");
		assertThat(dto.getHighRiskArea()).isEqualTo("Credit Decision");
		assertThat(dto.getClassificationJustification()).isEqualTo("Automated decisions");
		assertThat(dto.getClassificationDate()).isEqualTo(LocalDate.of(2024, 1, 15));
		assertThat(dto.getClassificationResponsibleId()).isEqualTo("person-001");
		assertThat(dto.getSystemId()).isEqualTo("system-001");
		assertThat(dto.getOwnerOrganizationId()).isEqualTo("org-001");
		assertThat(dto.getContactPersonId()).isEqualTo("person-002");
		assertThat(dto.getOversightResponsibleId()).isEqualTo("person-003");
		assertThat(dto.getIncidentContactId()).isEqualTo("person-004");
		assertThat(dto.getUsageDescription()).isEqualTo("Risk scoring");
		assertThat(dto.getFriaRequired()).isTrue();
		assertThat(dto.getFriaDate()).isEqualTo(LocalDate.of(2024, 2, 1));
		assertThat(dto.getFriaResults()).isEqualTo("Completed");
		assertThat(dto.getTransparencyType()).isEqualTo("Notice");
		assertThat(dto.getTransparencyMeasures()).isEqualTo("Notification");
		assertThat(dto.getMonitoringMeasures()).isEqualTo("Review");
		assertThat(dto.getRegistrationStatus()).isEqualTo("Registered");
		assertThat(dto.getRegistrationDate()).isEqualTo(LocalDate.of(2024, 3, 1));
	}

	@Test
	void toDtoWithNullEnums() {
		final var entity = AiApplicationEntity.create()
			.withStatus(null)
			.withRiskCategory(null);

		final var dto = AiApplicationMapper.toDto(entity);

		assertThat(dto).isNotNull();
		assertThat(dto.getStatus()).isNull();
		assertThat(dto.getRiskCategory()).isNull();
	}

	@Test
	void toEntityWithNull() {
		assertThat(AiApplicationMapper.toEntity(null)).isNull();
	}

	@Test
	void toEntity() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Risk Assessment")
			.withDescription("Customer risk assessment")
			.withStatus("ACTIVE")
			.withRiskCategory("HIGH_RISK")
			.withHighRiskArea("Credit Decision")
			.withClassificationJustification("Automated decisions")
			.withClassificationDate(LocalDate.of(2024, 1, 15))
			.withClassificationResponsibleId("person-001")
			.withSystemId("system-001")
			.withOwnerOrganizationId("org-001")
			.withContactPersonId("person-002")
			.withOversightResponsibleId("person-003")
			.withIncidentContactId("person-004")
			.withUsageDescription("Risk scoring")
			.withFriaRequired(true)
			.withFriaDate(LocalDate.of(2024, 2, 1))
			.withFriaResults("Completed")
			.withTransparencyType("Notice")
			.withTransparencyMeasures("Notification")
			.withMonitoringMeasures("Review")
			.withRegistrationStatus("Registered")
			.withRegistrationDate(LocalDate.of(2024, 3, 1));

		final var entity = AiApplicationMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(entity.getName()).isEqualTo("Risk Assessment");
		assertThat(entity.getDescription()).isEqualTo("Customer risk assessment");
		assertThat(entity.getStatus()).isEqualTo(AIApplicationStatus.ACTIVE);
		assertThat(entity.getRiskCategory()).isEqualTo(AIRiskCategory.HIGH_RISK);
		assertThat(entity.getHighRiskArea()).isEqualTo("Credit Decision");
		assertThat(entity.getClassificationJustification()).isEqualTo("Automated decisions");
		assertThat(entity.getClassificationDate()).isEqualTo(LocalDate.of(2024, 1, 15));
		assertThat(entity.getClassificationResponsibleId()).isEqualTo("person-001");
		assertThat(entity.getSystemId()).isEqualTo("system-001");
		assertThat(entity.getOwnerOrganizationId()).isEqualTo("org-001");
		assertThat(entity.getContactPersonId()).isEqualTo("person-002");
		assertThat(entity.getOversightResponsibleId()).isEqualTo("person-003");
		assertThat(entity.getIncidentContactId()).isEqualTo("person-004");
		assertThat(entity.getUsageDescription()).isEqualTo("Risk scoring");
		assertThat(entity.getFriaRequired()).isTrue();
		assertThat(entity.getFriaDate()).isEqualTo(LocalDate.of(2024, 2, 1));
		assertThat(entity.getFriaResults()).isEqualTo("Completed");
		assertThat(entity.getTransparencyType()).isEqualTo("Notice");
		assertThat(entity.getTransparencyMeasures()).isEqualTo("Notification");
		assertThat(entity.getMonitoringMeasures()).isEqualTo("Review");
		assertThat(entity.getRegistrationStatus()).isEqualTo("Registered");
		assertThat(entity.getRegistrationDate()).isEqualTo(LocalDate.of(2024, 3, 1));
	}

	@Test
	void toEntityWithNullEnums() {
		final var dto = AiApplication.create()
			.withStatus(null)
			.withRiskCategory(null);

		final var entity = AiApplicationMapper.toEntity(dto);

		assertThat(entity).isNotNull();
		assertThat(entity.getStatus()).isNull();
		assertThat(entity.getRiskCategory()).isNull();
	}

	@Test
	void roundTrip() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Test")
			.withStatus("ACTIVE")
			.withRiskCategory("HIGH_RISK");

		final var entity = AiApplicationMapper.toEntity(dto);
		final var roundTrip = AiApplicationMapper.toDto(entity);

		assertThat(roundTrip.getAiApplicationId()).isEqualTo(dto.getAiApplicationId());
		assertThat(roundTrip.getName()).isEqualTo(dto.getName());
		assertThat(roundTrip.getStatus()).isEqualTo(dto.getStatus());
		assertThat(roundTrip.getRiskCategory()).isEqualTo(dto.getRiskCategory());
	}
}
