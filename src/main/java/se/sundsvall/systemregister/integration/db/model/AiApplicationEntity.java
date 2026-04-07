package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

@Entity
@Table(name = "ai_applications")
public class AiApplicationEntity extends AbstractAuditableEntity {

	@Column(name = "ai_application_id")
	private String aiApplicationId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private AIApplicationStatus status;

	@Column(name = "risk_category")
	@Enumerated(EnumType.STRING)
	private AIRiskCategory riskCategory;

	@Column(name = "high_risk_area")
	private String highRiskArea;

	@Lob
	@Column(name = "classification_justification")
	private String classificationJustification;

	@Column(name = "classification_date")
	private LocalDate classificationDate;

	@Column(name = "classification_responsible_id")
	private String classificationResponsibleId;

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "owner_organization_id")
	private String ownerOrganizationId;

	@Column(name = "contact_person_id")
	private String contactPersonId;

	@Column(name = "oversight_responsible_id")
	private String oversightResponsibleId;

	@Column(name = "incident_contact_id")
	private String incidentContactId;

	@Lob
	@Column(name = "usage_description")
	private String usageDescription;

	@Column(name = "fria_required")
	private Boolean friaRequired;

	@Column(name = "fria_date")
	private LocalDate friaDate;

	@Lob
	@Column(name = "fria_results")
	private String friaResults;

	@Column(name = "transparency_type")
	private String transparencyType;

	@Lob
	@Column(name = "transparency_measures")
	private String transparencyMeasures;

	@Lob
	@Column(name = "monitoring_measures")
	private String monitoringMeasures;

	@Column(name = "registration_status")
	private String registrationStatus;

	@Column(name = "registration_date")
	private LocalDate registrationDate;

	public static AiApplicationEntity create() {
		return new AiApplicationEntity();
	}

	public String getAiApplicationId() {
		return this.aiApplicationId;
	}

	public void setAiApplicationId(final String aiApplicationId) {
		this.aiApplicationId = aiApplicationId;
	}

	public AiApplicationEntity withAiApplicationId(final String aiApplicationId) {
		this.aiApplicationId = aiApplicationId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public AiApplicationEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public AiApplicationEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public AIApplicationStatus getStatus() {
		return this.status;
	}

	public void setStatus(final AIApplicationStatus status) {
		this.status = status;
	}

	public AiApplicationEntity withStatus(final AIApplicationStatus status) {
		this.status = status;
		return this;
	}

	public AIRiskCategory getRiskCategory() {
		return this.riskCategory;
	}

	public void setRiskCategory(final AIRiskCategory riskCategory) {
		this.riskCategory = riskCategory;
	}

	public AiApplicationEntity withRiskCategory(final AIRiskCategory riskCategory) {
		this.riskCategory = riskCategory;
		return this;
	}

	public String getHighRiskArea() {
		return this.highRiskArea;
	}

	public void setHighRiskArea(final String highRiskArea) {
		this.highRiskArea = highRiskArea;
	}

	public AiApplicationEntity withHighRiskArea(final String highRiskArea) {
		this.highRiskArea = highRiskArea;
		return this;
	}

	public String getClassificationJustification() {
		return this.classificationJustification;
	}

	public void setClassificationJustification(final String classificationJustification) {
		this.classificationJustification = classificationJustification;
	}

	public AiApplicationEntity withClassificationJustification(final String classificationJustification) {
		this.classificationJustification = classificationJustification;
		return this;
	}

	public LocalDate getClassificationDate() {
		return this.classificationDate;
	}

	public void setClassificationDate(final LocalDate classificationDate) {
		this.classificationDate = classificationDate;
	}

	public AiApplicationEntity withClassificationDate(final LocalDate classificationDate) {
		this.classificationDate = classificationDate;
		return this;
	}

	public String getClassificationResponsibleId() {
		return this.classificationResponsibleId;
	}

	public void setClassificationResponsibleId(final String classificationResponsibleId) {
		this.classificationResponsibleId = classificationResponsibleId;
	}

	public AiApplicationEntity withClassificationResponsibleId(final String classificationResponsibleId) {
		this.classificationResponsibleId = classificationResponsibleId;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public AiApplicationEntity withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public AiApplicationEntity withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getContactPersonId() {
		return this.contactPersonId;
	}

	public void setContactPersonId(final String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public AiApplicationEntity withContactPersonId(final String contactPersonId) {
		this.contactPersonId = contactPersonId;
		return this;
	}

	public String getOversightResponsibleId() {
		return this.oversightResponsibleId;
	}

	public void setOversightResponsibleId(final String oversightResponsibleId) {
		this.oversightResponsibleId = oversightResponsibleId;
	}

	public AiApplicationEntity withOversightResponsibleId(final String oversightResponsibleId) {
		this.oversightResponsibleId = oversightResponsibleId;
		return this;
	}

	public String getIncidentContactId() {
		return this.incidentContactId;
	}

	public void setIncidentContactId(final String incidentContactId) {
		this.incidentContactId = incidentContactId;
	}

	public AiApplicationEntity withIncidentContactId(final String incidentContactId) {
		this.incidentContactId = incidentContactId;
		return this;
	}

	public String getUsageDescription() {
		return this.usageDescription;
	}

	public void setUsageDescription(final String usageDescription) {
		this.usageDescription = usageDescription;
	}

	public AiApplicationEntity withUsageDescription(final String usageDescription) {
		this.usageDescription = usageDescription;
		return this;
	}

	public Boolean getFriaRequired() {
		return this.friaRequired;
	}

	public void setFriaRequired(final Boolean friaRequired) {
		this.friaRequired = friaRequired;
	}

	public AiApplicationEntity withFriaRequired(final Boolean friaRequired) {
		this.friaRequired = friaRequired;
		return this;
	}

	public LocalDate getFriaDate() {
		return this.friaDate;
	}

	public void setFriaDate(final LocalDate friaDate) {
		this.friaDate = friaDate;
	}

	public AiApplicationEntity withFriaDate(final LocalDate friaDate) {
		this.friaDate = friaDate;
		return this;
	}

	public String getFriaResults() {
		return this.friaResults;
	}

	public void setFriaResults(final String friaResults) {
		this.friaResults = friaResults;
	}

	public AiApplicationEntity withFriaResults(final String friaResults) {
		this.friaResults = friaResults;
		return this;
	}

	public String getTransparencyType() {
		return this.transparencyType;
	}

	public void setTransparencyType(final String transparencyType) {
		this.transparencyType = transparencyType;
	}

	public AiApplicationEntity withTransparencyType(final String transparencyType) {
		this.transparencyType = transparencyType;
		return this;
	}

	public String getTransparencyMeasures() {
		return this.transparencyMeasures;
	}

	public void setTransparencyMeasures(final String transparencyMeasures) {
		this.transparencyMeasures = transparencyMeasures;
	}

	public AiApplicationEntity withTransparencyMeasures(final String transparencyMeasures) {
		this.transparencyMeasures = transparencyMeasures;
		return this;
	}

	public String getMonitoringMeasures() {
		return this.monitoringMeasures;
	}

	public void setMonitoringMeasures(final String monitoringMeasures) {
		this.monitoringMeasures = monitoringMeasures;
	}

	public AiApplicationEntity withMonitoringMeasures(final String monitoringMeasures) {
		this.monitoringMeasures = monitoringMeasures;
		return this;
	}

	public String getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(final String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public AiApplicationEntity withRegistrationStatus(final String registrationStatus) {
		this.registrationStatus = registrationStatus;
		return this;
	}

	public LocalDate getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(final LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public AiApplicationEntity withRegistrationDate(final LocalDate registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final AiApplicationEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.aiApplicationId, that.aiApplicationId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			this.status == that.status &&
			this.riskCategory == that.riskCategory &&
			Objects.equals(this.highRiskArea, that.highRiskArea) &&
			Objects.equals(this.classificationJustification, that.classificationJustification) &&
			Objects.equals(this.classificationDate, that.classificationDate) &&
			Objects.equals(this.classificationResponsibleId, that.classificationResponsibleId) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.contactPersonId, that.contactPersonId) &&
			Objects.equals(this.oversightResponsibleId, that.oversightResponsibleId) &&
			Objects.equals(this.incidentContactId, that.incidentContactId) &&
			Objects.equals(this.usageDescription, that.usageDescription) &&
			Objects.equals(this.friaRequired, that.friaRequired) &&
			Objects.equals(this.friaDate, that.friaDate) &&
			Objects.equals(this.friaResults, that.friaResults) &&
			Objects.equals(this.transparencyType, that.transparencyType) &&
			Objects.equals(this.transparencyMeasures, that.transparencyMeasures) &&
			Objects.equals(this.monitoringMeasures, that.monitoringMeasures) &&
			Objects.equals(this.registrationStatus, that.registrationStatus) &&
			Objects.equals(this.registrationDate, that.registrationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.aiApplicationId, this.name, this.description, this.status,
			this.riskCategory, this.highRiskArea, this.classificationJustification, this.classificationDate,
			this.classificationResponsibleId, this.systemId, this.ownerOrganizationId, this.contactPersonId,
			this.oversightResponsibleId, this.incidentContactId, this.usageDescription, this.friaRequired,
			this.friaDate, this.friaResults, this.transparencyType, this.transparencyMeasures,
			this.monitoringMeasures, this.registrationStatus, this.registrationDate);
	}

	@Override
	public String toString() {
		return "AiApplicationEntity{" +
			"id='" + this.getId() + '\'' +
			", aiApplicationId='" + this.aiApplicationId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status=" + this.status +
			", riskCategory=" + this.riskCategory +
			", highRiskArea='" + this.highRiskArea + '\'' +
			", classificationJustification='" + this.classificationJustification + '\'' +
			", classificationDate=" + this.classificationDate +
			", classificationResponsibleId='" + this.classificationResponsibleId + '\'' +
			", systemId='" + this.systemId + '\'' +
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", contactPersonId='" + this.contactPersonId + '\'' +
			", oversightResponsibleId='" + this.oversightResponsibleId + '\'' +
			", incidentContactId='" + this.incidentContactId + '\'' +
			", usageDescription='" + this.usageDescription + '\'' +
			", friaRequired=" + this.friaRequired +
			", friaDate=" + this.friaDate +
			", friaResults='" + this.friaResults + '\'' +
			", transparencyType='" + this.transparencyType + '\'' +
			", transparencyMeasures='" + this.transparencyMeasures + '\'' +
			", monitoringMeasures='" + this.monitoringMeasures + '\'' +
			", registrationStatus='" + this.registrationStatus + '\'' +
			", registrationDate=" + this.registrationDate +
			'}';
	}
}
