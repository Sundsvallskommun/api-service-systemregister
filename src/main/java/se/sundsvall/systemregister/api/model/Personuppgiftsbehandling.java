package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Schema(description = "GDPR processing activity (Personuppgiftsbehandling)")
public class Personuppgiftsbehandling {

	@Schema(description = "Unique identifier for the processing activity", example = "behandling-001")
	private String behandlingId;

	@Schema(description = "Name of the processing activity", example = "Customer Database Management")
	private String name;

	@Schema(description = "Description of the processing activity")
	private String description;

	@Schema(description = "Current status of the processing activity", allowableValues = {
		"ACTIVE", "INACTIVE"
	}, example = "ACTIVE")
	private String status;

	@Schema(description = "Business area (Verksamhetsområde)", example = "Citizen Services")
	private String verksamhetsomrade;

	@Schema(description = "Organization ID of the data controller", example = "org-001")
	private String controllerOrganizationId;

	@Schema(description = "Data Protection Officer ID", example = "dpo-001")
	private String dpoId;

	@Schema(description = "Processing responsible person ID", example = "person-001")
	private String processingResponsibleId;

	@Schema(description = "Description of the processing purpose")
	private String purposeDescription;

	@Schema(description = "Detailed purposes of processing (JSON array)")
	private List<String> detailedPurposes;

	@Schema(description = "Legal basis for processing", allowableValues = {
		"SAMTYCKE", "AVTAL", "RATTSLIG_FORPLIKTELSE", "VITALT_INTRESSE", "ALLMANT_INTRESSE", "BERATTIGAT_INTRESSE"
	}, example = "AVTAL")
	private String legalBasis;

	@Schema(description = "Legal reference or law citation")
	private String legalReference;

	@Schema(description = "Basis for sensitive data processing")
	private String sensitiveDataBasis;

	@Schema(description = "Categories of data subjects (JSON array)")
	private List<String> dataSubjectCategories;

	@Schema(description = "Estimated number of data subjects")
	private Long estimatedDataSubjectCount;

	@Schema(description = "Standard data categories (JSON array)")
	private List<String> standardDataCategories;

	@Schema(description = "Whether sensitive personal data is processed")
	private Boolean processesSensitiveData;

	@Schema(description = "Sensitive data categories (JSON array)")
	private List<String> sensitiveDataCategories;

	@Schema(description = "Whether criminal data is processed")
	private Boolean processesCriminalData;

	@Schema(description = "Whether SSN is processed")
	private Boolean processesSsn;

	@Schema(description = "Internal recipients of data (JSON array)")
	private List<String> internalRecipients;

	@Schema(description = "External recipients of data (JSON array)")
	private List<String> externalRecipients;

	@Schema(description = "Data processors (JSON array)")
	private List<String> dataProcessors;

	@Schema(description = "Whether data is transferred to third countries")
	private Boolean transfersToThirdCountry;

	@Schema(description = "Third country destinations (JSON array)")
	private List<String> thirdCountryDestinations;

	@Schema(description = "Transfer protection mechanism(s)")
	private String transferProtectionMechanism;

	@Schema(description = "Retention period", example = "3 years")
	private String retentionPeriod;

	@Schema(description = "Basis for retention period")
	private String retentionBasis;

	@Schema(description = "Whether there is an archiving obligation")
	private Boolean hasArchivingObligation;

	@Schema(description = "Technical security measures (JSON array)")
	private List<String> technicalMeasures;

	@Schema(description = "Organizational security measures (JSON array)")
	private List<String> organizationalMeasures;

	@Schema(description = "Information method for data subjects")
	private String informationMethod;

	@Schema(description = "URL to privacy policy")
	private String privacyPolicyUrl;

	@Schema(description = "Whether DPIA is required")
	private Boolean dpiaRequired;

	@Schema(description = "Date when DPIA was conducted")
	private LocalDate dpiaDate;

	@Schema(description = "Results of DPIA")
	private String dpiaResults;

	@Schema(description = "Data source(s) (JSON array)")
	private List<String> dataSource;

	@Schema(description = "Whether automated decision-making is used")
	private Boolean hasAutomatedDecisions;

	@Schema(description = "Logic of automated decisions")
	private String automatedDecisionLogic;

	public static Personuppgiftsbehandling create() {
		return new Personuppgiftsbehandling();
	}

	public String getBehandlingId() {
		return this.behandlingId;
	}

	public void setBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
	}

	public Personuppgiftsbehandling withBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Personuppgiftsbehandling withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Personuppgiftsbehandling withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Personuppgiftsbehandling withStatus(final String status) {
		this.status = status;
		return this;
	}

	public String getVerksamhetsomrade() {
		return this.verksamhetsomrade;
	}

	public void setVerksamhetsomrade(final String verksamhetsomrade) {
		this.verksamhetsomrade = verksamhetsomrade;
	}

	public Personuppgiftsbehandling withVerksamhetsomrade(final String verksamhetsomrade) {
		this.verksamhetsomrade = verksamhetsomrade;
		return this;
	}

	public String getControllerOrganizationId() {
		return this.controllerOrganizationId;
	}

	public void setControllerOrganizationId(final String controllerOrganizationId) {
		this.controllerOrganizationId = controllerOrganizationId;
	}

	public Personuppgiftsbehandling withControllerOrganizationId(final String controllerOrganizationId) {
		this.controllerOrganizationId = controllerOrganizationId;
		return this;
	}

	public String getDpoId() {
		return this.dpoId;
	}

	public void setDpoId(final String dpoId) {
		this.dpoId = dpoId;
	}

	public Personuppgiftsbehandling withDpoId(final String dpoId) {
		this.dpoId = dpoId;
		return this;
	}

	public String getProcessingResponsibleId() {
		return this.processingResponsibleId;
	}

	public void setProcessingResponsibleId(final String processingResponsibleId) {
		this.processingResponsibleId = processingResponsibleId;
	}

	public Personuppgiftsbehandling withProcessingResponsibleId(final String processingResponsibleId) {
		this.processingResponsibleId = processingResponsibleId;
		return this;
	}

	public String getPurposeDescription() {
		return this.purposeDescription;
	}

	public void setPurposeDescription(final String purposeDescription) {
		this.purposeDescription = purposeDescription;
	}

	public Personuppgiftsbehandling withPurposeDescription(final String purposeDescription) {
		this.purposeDescription = purposeDescription;
		return this;
	}

	public List<String> getDetailedPurposes() {
		return this.detailedPurposes;
	}

	public void setDetailedPurposes(final List<String> detailedPurposes) {
		this.detailedPurposes = detailedPurposes;
	}

	public Personuppgiftsbehandling withDetailedPurposes(final List<String> detailedPurposes) {
		this.detailedPurposes = detailedPurposes;
		return this;
	}

	public String getLegalBasis() {
		return this.legalBasis;
	}

	public void setLegalBasis(final String legalBasis) {
		this.legalBasis = legalBasis;
	}

	public Personuppgiftsbehandling withLegalBasis(final String legalBasis) {
		this.legalBasis = legalBasis;
		return this;
	}

	public String getLegalReference() {
		return this.legalReference;
	}

	public void setLegalReference(final String legalReference) {
		this.legalReference = legalReference;
	}

	public Personuppgiftsbehandling withLegalReference(final String legalReference) {
		this.legalReference = legalReference;
		return this;
	}

	public String getSensitiveDataBasis() {
		return this.sensitiveDataBasis;
	}

	public void setSensitiveDataBasis(final String sensitiveDataBasis) {
		this.sensitiveDataBasis = sensitiveDataBasis;
	}

	public Personuppgiftsbehandling withSensitiveDataBasis(final String sensitiveDataBasis) {
		this.sensitiveDataBasis = sensitiveDataBasis;
		return this;
	}

	public List<String> getDataSubjectCategories() {
		return this.dataSubjectCategories;
	}

	public void setDataSubjectCategories(final List<String> dataSubjectCategories) {
		this.dataSubjectCategories = dataSubjectCategories;
	}

	public Personuppgiftsbehandling withDataSubjectCategories(final List<String> dataSubjectCategories) {
		this.dataSubjectCategories = dataSubjectCategories;
		return this;
	}

	public Long getEstimatedDataSubjectCount() {
		return this.estimatedDataSubjectCount;
	}

	public void setEstimatedDataSubjectCount(final Long estimatedDataSubjectCount) {
		this.estimatedDataSubjectCount = estimatedDataSubjectCount;
	}

	public Personuppgiftsbehandling withEstimatedDataSubjectCount(final Long estimatedDataSubjectCount) {
		this.estimatedDataSubjectCount = estimatedDataSubjectCount;
		return this;
	}

	public List<String> getStandardDataCategories() {
		return this.standardDataCategories;
	}

	public void setStandardDataCategories(final List<String> standardDataCategories) {
		this.standardDataCategories = standardDataCategories;
	}

	public Personuppgiftsbehandling withStandardDataCategories(final List<String> standardDataCategories) {
		this.standardDataCategories = standardDataCategories;
		return this;
	}

	public Boolean getProcessesSensitiveData() {
		return this.processesSensitiveData;
	}

	public void setProcessesSensitiveData(final Boolean processesSensitiveData) {
		this.processesSensitiveData = processesSensitiveData;
	}

	public Personuppgiftsbehandling withProcessesSensitiveData(final Boolean processesSensitiveData) {
		this.processesSensitiveData = processesSensitiveData;
		return this;
	}

	public List<String> getSensitiveDataCategories() {
		return this.sensitiveDataCategories;
	}

	public void setSensitiveDataCategories(final List<String> sensitiveDataCategories) {
		this.sensitiveDataCategories = sensitiveDataCategories;
	}

	public Personuppgiftsbehandling withSensitiveDataCategories(final List<String> sensitiveDataCategories) {
		this.sensitiveDataCategories = sensitiveDataCategories;
		return this;
	}

	public Boolean getProcessesCriminalData() {
		return this.processesCriminalData;
	}

	public void setProcessesCriminalData(final Boolean processesCriminalData) {
		this.processesCriminalData = processesCriminalData;
	}

	public Personuppgiftsbehandling withProcessesCriminalData(final Boolean processesCriminalData) {
		this.processesCriminalData = processesCriminalData;
		return this;
	}

	public Boolean getProcessesSsn() {
		return this.processesSsn;
	}

	public void setProcessesSsn(final Boolean processesSsn) {
		this.processesSsn = processesSsn;
	}

	public Personuppgiftsbehandling withProcessesSsn(final Boolean processesSsn) {
		this.processesSsn = processesSsn;
		return this;
	}

	public List<String> getInternalRecipients() {
		return this.internalRecipients;
	}

	public void setInternalRecipients(final List<String> internalRecipients) {
		this.internalRecipients = internalRecipients;
	}

	public Personuppgiftsbehandling withInternalRecipients(final List<String> internalRecipients) {
		this.internalRecipients = internalRecipients;
		return this;
	}

	public List<String> getExternalRecipients() {
		return this.externalRecipients;
	}

	public void setExternalRecipients(final List<String> externalRecipients) {
		this.externalRecipients = externalRecipients;
	}

	public Personuppgiftsbehandling withExternalRecipients(final List<String> externalRecipients) {
		this.externalRecipients = externalRecipients;
		return this;
	}

	public List<String> getDataProcessors() {
		return this.dataProcessors;
	}

	public void setDataProcessors(final List<String> dataProcessors) {
		this.dataProcessors = dataProcessors;
	}

	public Personuppgiftsbehandling withDataProcessors(final List<String> dataProcessors) {
		this.dataProcessors = dataProcessors;
		return this;
	}

	public Boolean getTransfersToThirdCountry() {
		return this.transfersToThirdCountry;
	}

	public void setTransfersToThirdCountry(final Boolean transfersToThirdCountry) {
		this.transfersToThirdCountry = transfersToThirdCountry;
	}

	public Personuppgiftsbehandling withTransfersToThirdCountry(final Boolean transfersToThirdCountry) {
		this.transfersToThirdCountry = transfersToThirdCountry;
		return this;
	}

	public List<String> getThirdCountryDestinations() {
		return this.thirdCountryDestinations;
	}

	public void setThirdCountryDestinations(final List<String> thirdCountryDestinations) {
		this.thirdCountryDestinations = thirdCountryDestinations;
	}

	public Personuppgiftsbehandling withThirdCountryDestinations(final List<String> thirdCountryDestinations) {
		this.thirdCountryDestinations = thirdCountryDestinations;
		return this;
	}

	public String getTransferProtectionMechanism() {
		return this.transferProtectionMechanism;
	}

	public void setTransferProtectionMechanism(final String transferProtectionMechanism) {
		this.transferProtectionMechanism = transferProtectionMechanism;
	}

	public Personuppgiftsbehandling withTransferProtectionMechanism(final String transferProtectionMechanism) {
		this.transferProtectionMechanism = transferProtectionMechanism;
		return this;
	}

	public String getRetentionPeriod() {
		return this.retentionPeriod;
	}

	public void setRetentionPeriod(final String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
	}

	public Personuppgiftsbehandling withRetentionPeriod(final String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
		return this;
	}

	public String getRetentionBasis() {
		return this.retentionBasis;
	}

	public void setRetentionBasis(final String retentionBasis) {
		this.retentionBasis = retentionBasis;
	}

	public Personuppgiftsbehandling withRetentionBasis(final String retentionBasis) {
		this.retentionBasis = retentionBasis;
		return this;
	}

	public Boolean getHasArchivingObligation() {
		return this.hasArchivingObligation;
	}

	public void setHasArchivingObligation(final Boolean hasArchivingObligation) {
		this.hasArchivingObligation = hasArchivingObligation;
	}

	public Personuppgiftsbehandling withHasArchivingObligation(final Boolean hasArchivingObligation) {
		this.hasArchivingObligation = hasArchivingObligation;
		return this;
	}

	public List<String> getTechnicalMeasures() {
		return this.technicalMeasures;
	}

	public void setTechnicalMeasures(final List<String> technicalMeasures) {
		this.technicalMeasures = technicalMeasures;
	}

	public Personuppgiftsbehandling withTechnicalMeasures(final List<String> technicalMeasures) {
		this.technicalMeasures = technicalMeasures;
		return this;
	}

	public List<String> getOrganizationalMeasures() {
		return this.organizationalMeasures;
	}

	public void setOrganizationalMeasures(final List<String> organizationalMeasures) {
		this.organizationalMeasures = organizationalMeasures;
	}

	public Personuppgiftsbehandling withOrganizationalMeasures(final List<String> organizationalMeasures) {
		this.organizationalMeasures = organizationalMeasures;
		return this;
	}

	public String getInformationMethod() {
		return this.informationMethod;
	}

	public void setInformationMethod(final String informationMethod) {
		this.informationMethod = informationMethod;
	}

	public Personuppgiftsbehandling withInformationMethod(final String informationMethod) {
		this.informationMethod = informationMethod;
		return this;
	}

	public String getPrivacyPolicyUrl() {
		return this.privacyPolicyUrl;
	}

	public void setPrivacyPolicyUrl(final String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}

	public Personuppgiftsbehandling withPrivacyPolicyUrl(final String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
		return this;
	}

	public Boolean getDpiaRequired() {
		return this.dpiaRequired;
	}

	public void setDpiaRequired(final Boolean dpiaRequired) {
		this.dpiaRequired = dpiaRequired;
	}

	public Personuppgiftsbehandling withDpiaRequired(final Boolean dpiaRequired) {
		this.dpiaRequired = dpiaRequired;
		return this;
	}

	public LocalDate getDpiaDate() {
		return this.dpiaDate;
	}

	public void setDpiaDate(final LocalDate dpiaDate) {
		this.dpiaDate = dpiaDate;
	}

	public Personuppgiftsbehandling withDpiaDate(final LocalDate dpiaDate) {
		this.dpiaDate = dpiaDate;
		return this;
	}

	public String getDpiaResults() {
		return this.dpiaResults;
	}

	public void setDpiaResults(final String dpiaResults) {
		this.dpiaResults = dpiaResults;
	}

	public Personuppgiftsbehandling withDpiaResults(final String dpiaResults) {
		this.dpiaResults = dpiaResults;
		return this;
	}

	public List<String> getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(final List<String> dataSource) {
		this.dataSource = dataSource;
	}

	public Personuppgiftsbehandling withDataSource(final List<String> dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	public Boolean getHasAutomatedDecisions() {
		return this.hasAutomatedDecisions;
	}

	public void setHasAutomatedDecisions(final Boolean hasAutomatedDecisions) {
		this.hasAutomatedDecisions = hasAutomatedDecisions;
	}

	public Personuppgiftsbehandling withHasAutomatedDecisions(final Boolean hasAutomatedDecisions) {
		this.hasAutomatedDecisions = hasAutomatedDecisions;
		return this;
	}

	public String getAutomatedDecisionLogic() {
		return this.automatedDecisionLogic;
	}

	public void setAutomatedDecisionLogic(final String automatedDecisionLogic) {
		this.automatedDecisionLogic = automatedDecisionLogic;
	}

	public Personuppgiftsbehandling withAutomatedDecisionLogic(final String automatedDecisionLogic) {
		this.automatedDecisionLogic = automatedDecisionLogic;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Personuppgiftsbehandling that)) {
			return false;
		}
		return Objects.equals(this.behandlingId, that.behandlingId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.status, that.status) &&
			Objects.equals(this.verksamhetsomrade, that.verksamhetsomrade) &&
			Objects.equals(this.controllerOrganizationId, that.controllerOrganizationId) &&
			Objects.equals(this.dpoId, that.dpoId) &&
			Objects.equals(this.processingResponsibleId, that.processingResponsibleId) &&
			Objects.equals(this.purposeDescription, that.purposeDescription) &&
			Objects.equals(this.detailedPurposes, that.detailedPurposes) &&
			Objects.equals(this.legalBasis, that.legalBasis) &&
			Objects.equals(this.legalReference, that.legalReference) &&
			Objects.equals(this.sensitiveDataBasis, that.sensitiveDataBasis) &&
			Objects.equals(this.dataSubjectCategories, that.dataSubjectCategories) &&
			Objects.equals(this.estimatedDataSubjectCount, that.estimatedDataSubjectCount) &&
			Objects.equals(this.standardDataCategories, that.standardDataCategories) &&
			Objects.equals(this.processesSensitiveData, that.processesSensitiveData) &&
			Objects.equals(this.sensitiveDataCategories, that.sensitiveDataCategories) &&
			Objects.equals(this.processesCriminalData, that.processesCriminalData) &&
			Objects.equals(this.processesSsn, that.processesSsn) &&
			Objects.equals(this.internalRecipients, that.internalRecipients) &&
			Objects.equals(this.externalRecipients, that.externalRecipients) &&
			Objects.equals(this.dataProcessors, that.dataProcessors) &&
			Objects.equals(this.transfersToThirdCountry, that.transfersToThirdCountry) &&
			Objects.equals(this.thirdCountryDestinations, that.thirdCountryDestinations) &&
			Objects.equals(this.transferProtectionMechanism, that.transferProtectionMechanism) &&
			Objects.equals(this.retentionPeriod, that.retentionPeriod) &&
			Objects.equals(this.retentionBasis, that.retentionBasis) &&
			Objects.equals(this.hasArchivingObligation, that.hasArchivingObligation) &&
			Objects.equals(this.technicalMeasures, that.technicalMeasures) &&
			Objects.equals(this.organizationalMeasures, that.organizationalMeasures) &&
			Objects.equals(this.informationMethod, that.informationMethod) &&
			Objects.equals(this.privacyPolicyUrl, that.privacyPolicyUrl) &&
			Objects.equals(this.dpiaRequired, that.dpiaRequired) &&
			Objects.equals(this.dpiaDate, that.dpiaDate) &&
			Objects.equals(this.dpiaResults, that.dpiaResults) &&
			Objects.equals(this.dataSource, that.dataSource) &&
			Objects.equals(this.hasAutomatedDecisions, that.hasAutomatedDecisions) &&
			Objects.equals(this.automatedDecisionLogic, that.automatedDecisionLogic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.behandlingId, this.name, this.description, this.status, this.verksamhetsomrade,
			this.controllerOrganizationId, this.dpoId, this.processingResponsibleId, this.purposeDescription,
			this.detailedPurposes, this.legalBasis, this.legalReference, this.sensitiveDataBasis,
			this.dataSubjectCategories, this.estimatedDataSubjectCount, this.standardDataCategories,
			this.processesSensitiveData, this.sensitiveDataCategories, this.processesCriminalData, this.processesSsn,
			this.internalRecipients, this.externalRecipients, this.dataProcessors, this.transfersToThirdCountry,
			this.thirdCountryDestinations, this.transferProtectionMechanism, this.retentionPeriod,
			this.retentionBasis, this.hasArchivingObligation, this.technicalMeasures, this.organizationalMeasures,
			this.informationMethod, this.privacyPolicyUrl, this.dpiaRequired, this.dpiaDate, this.dpiaResults, this.dataSource,
			this.hasAutomatedDecisions, this.automatedDecisionLogic);
	}

	@Override
	public String toString() {
		return "Personuppgiftsbehandling{" +
			"behandlingId='" + this.behandlingId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status='" + this.status + '\'' +
			", verksamhetsomrade='" + this.verksamhetsomrade + '\'' +
			", controllerOrganizationId='" + this.controllerOrganizationId + '\'' +
			", dpoId='" + this.dpoId + '\'' +
			", processingResponsibleId='" + this.processingResponsibleId + '\'' +
			", purposeDescription='" + this.purposeDescription + '\'' +
			", detailedPurposes=" + this.detailedPurposes +
			", legalBasis='" + this.legalBasis + '\'' +
			", legalReference='" + this.legalReference + '\'' +
			", sensitiveDataBasis='" + this.sensitiveDataBasis + '\'' +
			", dataSubjectCategories=" + this.dataSubjectCategories +
			", estimatedDataSubjectCount=" + this.estimatedDataSubjectCount +
			", standardDataCategories=" + this.standardDataCategories +
			", processesSensitiveData=" + this.processesSensitiveData +
			", sensitiveDataCategories=" + this.sensitiveDataCategories +
			", processesCriminalData=" + this.processesCriminalData +
			", processesSsn=" + this.processesSsn +
			", internalRecipients=" + this.internalRecipients +
			", externalRecipients=" + this.externalRecipients +
			", dataProcessors=" + this.dataProcessors +
			", transfersToThirdCountry=" + this.transfersToThirdCountry +
			", thirdCountryDestinations=" + this.thirdCountryDestinations +
			", transferProtectionMechanism='" + this.transferProtectionMechanism + '\'' +
			", retentionPeriod='" + this.retentionPeriod + '\'' +
			", retentionBasis='" + this.retentionBasis + '\'' +
			", hasArchivingObligation=" + this.hasArchivingObligation +
			", technicalMeasures=" + this.technicalMeasures +
			", organizationalMeasures=" + this.organizationalMeasures +
			", informationMethod='" + this.informationMethod + '\'' +
			", privacyPolicyUrl='" + this.privacyPolicyUrl + '\'' +
			", dpiaRequired=" + this.dpiaRequired +
			", dpiaDate=" + this.dpiaDate +
			", dpiaResults='" + this.dpiaResults + '\'' +
			", dataSource=" + this.dataSource +
			", hasAutomatedDecisions=" + this.hasAutomatedDecisions +
			", automatedDecisionLogic='" + this.automatedDecisionLogic + '\'' +
			'}';
	}
}
