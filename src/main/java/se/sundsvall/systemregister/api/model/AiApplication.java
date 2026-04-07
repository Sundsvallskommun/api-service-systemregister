package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "AI Application (AI Act)")
public class AiApplication {

	@Schema(description = "Unique identifier for the AI application", example = "ai-001")
	private String aiApplicationId;

	@Schema(description = "Name of the AI application", example = "Customer Risk Assessment")
	private String name;

	@Schema(description = "Description of the AI application")
	private String description;

	@Schema(description = "Current status of the AI application", allowableValues = {
		"DRAFT", "ACTIVE", "SUSPENDED", "RETIRED"
	}, example = "ACTIVE")
	private String status;

	@Schema(description = "Risk category of the AI application", allowableValues = {
		"HIGH_RISK", "LIMITED_RISK", "MINIMAL_RISK"
	}, example = "HIGH_RISK")
	private String riskCategory;

	@Schema(description = "High-risk area designation (if applicable)")
	private String highRiskArea;

	@Schema(description = "Justification for risk classification")
	private String classificationJustification;

	@Schema(description = "Date of risk classification")
	private LocalDate classificationDate;

	@Schema(description = "Person ID responsible for classification", example = "person-001")
	private String classificationResponsibleId;

	@Schema(description = "ID of the system using the AI application", example = "system-001")
	private String systemId;

	@Schema(description = "ID of the organization owning the AI application", example = "org-001")
	private String ownerOrganizationId;

	@Schema(description = "Contact person ID", example = "person-002")
	private String contactPersonId;

	@Schema(description = "Person ID responsible for oversight", example = "person-003")
	private String oversightResponsibleId;

	@Schema(description = "Incident contact person ID", example = "person-004")
	private String incidentContactId;

	@Schema(description = "Description of AI application usage")
	private String usageDescription;

	@Schema(description = "Whether FRIA (AI Act impact assessment) is required")
	private Boolean friaRequired;

	@Schema(description = "Date of FRIA assessment")
	private LocalDate friaDate;

	@Schema(description = "Results of FRIA assessment")
	private String friaResults;

	@Schema(description = "Type of transparency measures")
	private String transparencyType;

	@Schema(description = "Details of transparency measures")
	private String transparencyMeasures;

	@Schema(description = "Monitoring and assessment measures")
	private String monitoringMeasures;

	@Schema(description = "Registration status")
	private String registrationStatus;

	@Schema(description = "Date of registration in AI registry")
	private LocalDate registrationDate;

	public static AiApplication create() {
		return new AiApplication();
	}

	public String getAiApplicationId() {
		return this.aiApplicationId;
	}

	public void setAiApplicationId(final String aiApplicationId) {
		this.aiApplicationId = aiApplicationId;
	}

	public AiApplication withAiApplicationId(final String aiApplicationId) {
		this.aiApplicationId = aiApplicationId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public AiApplication withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public AiApplication withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public AiApplication withStatus(final String status) {
		this.status = status;
		return this;
	}

	public String getRiskCategory() {
		return this.riskCategory;
	}

	public void setRiskCategory(final String riskCategory) {
		this.riskCategory = riskCategory;
	}

	public AiApplication withRiskCategory(final String riskCategory) {
		this.riskCategory = riskCategory;
		return this;
	}

	public String getHighRiskArea() {
		return this.highRiskArea;
	}

	public void setHighRiskArea(final String highRiskArea) {
		this.highRiskArea = highRiskArea;
	}

	public AiApplication withHighRiskArea(final String highRiskArea) {
		this.highRiskArea = highRiskArea;
		return this;
	}

	public String getClassificationJustification() {
		return this.classificationJustification;
	}

	public void setClassificationJustification(final String classificationJustification) {
		this.classificationJustification = classificationJustification;
	}

	public AiApplication withClassificationJustification(final String classificationJustification) {
		this.classificationJustification = classificationJustification;
		return this;
	}

	public LocalDate getClassificationDate() {
		return this.classificationDate;
	}

	public void setClassificationDate(final LocalDate classificationDate) {
		this.classificationDate = classificationDate;
	}

	public AiApplication withClassificationDate(final LocalDate classificationDate) {
		this.classificationDate = classificationDate;
		return this;
	}

	public String getClassificationResponsibleId() {
		return this.classificationResponsibleId;
	}

	public void setClassificationResponsibleId(final String classificationResponsibleId) {
		this.classificationResponsibleId = classificationResponsibleId;
	}

	public AiApplication withClassificationResponsibleId(final String classificationResponsibleId) {
		this.classificationResponsibleId = classificationResponsibleId;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public AiApplication withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public AiApplication withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getContactPersonId() {
		return this.contactPersonId;
	}

	public void setContactPersonId(final String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public AiApplication withContactPersonId(final String contactPersonId) {
		this.contactPersonId = contactPersonId;
		return this;
	}

	public String getOversightResponsibleId() {
		return this.oversightResponsibleId;
	}

	public void setOversightResponsibleId(final String oversightResponsibleId) {
		this.oversightResponsibleId = oversightResponsibleId;
	}

	public AiApplication withOversightResponsibleId(final String oversightResponsibleId) {
		this.oversightResponsibleId = oversightResponsibleId;
		return this;
	}

	public String getIncidentContactId() {
		return this.incidentContactId;
	}

	public void setIncidentContactId(final String incidentContactId) {
		this.incidentContactId = incidentContactId;
	}

	public AiApplication withIncidentContactId(final String incidentContactId) {
		this.incidentContactId = incidentContactId;
		return this;
	}

	public String getUsageDescription() {
		return this.usageDescription;
	}

	public void setUsageDescription(final String usageDescription) {
		this.usageDescription = usageDescription;
	}

	public AiApplication withUsageDescription(final String usageDescription) {
		this.usageDescription = usageDescription;
		return this;
	}

	public Boolean getFriaRequired() {
		return this.friaRequired;
	}

	public void setFriaRequired(final Boolean friaRequired) {
		this.friaRequired = friaRequired;
	}

	public AiApplication withFriaRequired(final Boolean friaRequired) {
		this.friaRequired = friaRequired;
		return this;
	}

	public LocalDate getFriaDate() {
		return this.friaDate;
	}

	public void setFriaDate(final LocalDate friaDate) {
		this.friaDate = friaDate;
	}

	public AiApplication withFriaDate(final LocalDate friaDate) {
		this.friaDate = friaDate;
		return this;
	}

	public String getFriaResults() {
		return this.friaResults;
	}

	public void setFriaResults(final String friaResults) {
		this.friaResults = friaResults;
	}

	public AiApplication withFriaResults(final String friaResults) {
		this.friaResults = friaResults;
		return this;
	}

	public String getTransparencyType() {
		return this.transparencyType;
	}

	public void setTransparencyType(final String transparencyType) {
		this.transparencyType = transparencyType;
	}

	public AiApplication withTransparencyType(final String transparencyType) {
		this.transparencyType = transparencyType;
		return this;
	}

	public String getTransparencyMeasures() {
		return this.transparencyMeasures;
	}

	public void setTransparencyMeasures(final String transparencyMeasures) {
		this.transparencyMeasures = transparencyMeasures;
	}

	public AiApplication withTransparencyMeasures(final String transparencyMeasures) {
		this.transparencyMeasures = transparencyMeasures;
		return this;
	}

	public String getMonitoringMeasures() {
		return this.monitoringMeasures;
	}

	public void setMonitoringMeasures(final String monitoringMeasures) {
		this.monitoringMeasures = monitoringMeasures;
	}

	public AiApplication withMonitoringMeasures(final String monitoringMeasures) {
		this.monitoringMeasures = monitoringMeasures;
		return this;
	}

	public String getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(final String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public AiApplication withRegistrationStatus(final String registrationStatus) {
		this.registrationStatus = registrationStatus;
		return this;
	}

	public LocalDate getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(final LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public AiApplication withRegistrationDate(final LocalDate registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final AiApplication that)) {
			return false;
		}
		return Objects.equals(this.aiApplicationId, that.aiApplicationId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.status, that.status) &&
			Objects.equals(this.riskCategory, that.riskCategory) &&
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
		return Objects.hash(this.aiApplicationId, this.name, this.description, this.status, this.riskCategory,
			this.highRiskArea, this.classificationJustification, this.classificationDate,
			this.classificationResponsibleId, this.systemId, this.ownerOrganizationId, this.contactPersonId,
			this.oversightResponsibleId, this.incidentContactId, this.usageDescription, this.friaRequired,
			this.friaDate, this.friaResults, this.transparencyType, this.transparencyMeasures,
			this.monitoringMeasures, this.registrationStatus, this.registrationDate);
	}

	@Override
	public String toString() {
		return "AiApplication{" +
			"aiApplicationId='" + this.aiApplicationId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status='" + this.status + '\'' +
			", riskCategory='" + this.riskCategory + '\'' +
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
