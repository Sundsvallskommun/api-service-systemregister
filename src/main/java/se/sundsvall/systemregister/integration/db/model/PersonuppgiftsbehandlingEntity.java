package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.LegalBasis;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

@Entity
@Table(name = "personuppgiftsbehandlingar")
public class PersonuppgiftsbehandlingEntity extends AbstractAuditableEntity {

	@Column(name = "behandling_id")
	private String behandlingId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProcessingStatus status;

	@Column(name = "verksamhetsomrade")
	private String verksamhetsomrade;

	@Column(name = "controller_organization_id")
	private String controllerOrganizationId;

	@Column(name = "dpo_id")
	private String dpoId;

	@Column(name = "processing_responsible_id")
	private String processingResponsibleId;

	@Column(name = "purpose_description")
	private String purposeDescription;

	@Lob
	@Column(name = "detailed_purposes")
	private String detailedPurposes;

	@Column(name = "legal_basis")
	@Enumerated(EnumType.STRING)
	private LegalBasis legalBasis;

	@Column(name = "legal_reference")
	private String legalReference;

	@Lob
	@Column(name = "sensitive_data_basis")
	private String sensitiveDataBasis;

	@Lob
	@Column(name = "data_subject_categories")
	private String dataSubjectCategories;

	@Column(name = "estimated_data_subject_count")
	private Long estimatedDataSubjectCount;

	@Lob
	@Column(name = "standard_data_categories")
	private String standardDataCategories;

	@Column(name = "processes_sensitive_data")
	private Boolean processesSensitiveData;

	@Lob
	@Column(name = "sensitive_data_categories")
	private String sensitiveDataCategories;

	@Column(name = "processes_criminal_data")
	private Boolean processesCriminalData;

	@Column(name = "processes_ssn")
	private Boolean processesSsn;

	@Lob
	@Column(name = "internal_recipients")
	private String internalRecipients;

	@Lob
	@Column(name = "external_recipients")
	private String externalRecipients;

	@Lob
	@Column(name = "data_processors")
	private String dataProcessors;

	@Column(name = "transfers_to_third_country")
	private Boolean transfersToThirdCountry;

	@Lob
	@Column(name = "third_country_destinations")
	private String thirdCountryDestinations;

	@Lob
	@Column(name = "transfer_protection_mechanism")
	private String transferProtectionMechanism;

	@Column(name = "retention_period")
	private String retentionPeriod;

	@Column(name = "retention_basis")
	private String retentionBasis;

	@Column(name = "has_archiving_obligation")
	private Boolean hasArchivingObligation;

	@Lob
	@Column(name = "technical_measures")
	private String technicalMeasures;

	@Lob
	@Column(name = "organizational_measures")
	private String organizationalMeasures;

	@Lob
	@Column(name = "information_method")
	private String informationMethod;

	@Column(name = "privacy_policy_url")
	private String privacyPolicyUrl;

	@Column(name = "dpia_required")
	private Boolean dpiaRequired;

	@Column(name = "dpia_date")
	private LocalDate dpiaDate;

	@Column(name = "dpia_results")
	private String dpiaResults;

	@Lob
	@Column(name = "data_source")
	private String dataSource;

	@Column(name = "has_automated_decisions")
	private Boolean hasAutomatedDecisions;

	@Lob
	@Column(name = "automated_decision_logic")
	private String automatedDecisionLogic;

	public static PersonuppgiftsbehandlingEntity create() {
		return new PersonuppgiftsbehandlingEntity();
	}

	public String getBehandlingId() {
		return this.behandlingId;
	}

	public void setBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
	}

	public PersonuppgiftsbehandlingEntity withBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public PersonuppgiftsbehandlingEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public PersonuppgiftsbehandlingEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public ProcessingStatus getStatus() {
		return this.status;
	}

	public void setStatus(final ProcessingStatus status) {
		this.status = status;
	}

	public PersonuppgiftsbehandlingEntity withStatus(final ProcessingStatus status) {
		this.status = status;
		return this;
	}

	public String getVerksamhetsomrade() {
		return this.verksamhetsomrade;
	}

	public void setVerksamhetsomrade(final String verksamhetsomrade) {
		this.verksamhetsomrade = verksamhetsomrade;
	}

	public PersonuppgiftsbehandlingEntity withVerksamhetsomrade(final String verksamhetsomrade) {
		this.verksamhetsomrade = verksamhetsomrade;
		return this;
	}

	public String getControllerOrganizationId() {
		return this.controllerOrganizationId;
	}

	public void setControllerOrganizationId(final String controllerOrganizationId) {
		this.controllerOrganizationId = controllerOrganizationId;
	}

	public PersonuppgiftsbehandlingEntity withControllerOrganizationId(final String controllerOrganizationId) {
		this.controllerOrganizationId = controllerOrganizationId;
		return this;
	}

	public String getDpoId() {
		return this.dpoId;
	}

	public void setDpoId(final String dpoId) {
		this.dpoId = dpoId;
	}

	public PersonuppgiftsbehandlingEntity withDpoId(final String dpoId) {
		this.dpoId = dpoId;
		return this;
	}

	public String getProcessingResponsibleId() {
		return this.processingResponsibleId;
	}

	public void setProcessingResponsibleId(final String processingResponsibleId) {
		this.processingResponsibleId = processingResponsibleId;
	}

	public PersonuppgiftsbehandlingEntity withProcessingResponsibleId(final String processingResponsibleId) {
		this.processingResponsibleId = processingResponsibleId;
		return this;
	}

	public String getPurposeDescription() {
		return this.purposeDescription;
	}

	public void setPurposeDescription(final String purposeDescription) {
		this.purposeDescription = purposeDescription;
	}

	public PersonuppgiftsbehandlingEntity withPurposeDescription(final String purposeDescription) {
		this.purposeDescription = purposeDescription;
		return this;
	}

	public String getDetailedPurposes() {
		return this.detailedPurposes;
	}

	public void setDetailedPurposes(final String detailedPurposes) {
		this.detailedPurposes = detailedPurposes;
	}

	public PersonuppgiftsbehandlingEntity withDetailedPurposes(final String detailedPurposes) {
		this.detailedPurposes = detailedPurposes;
		return this;
	}

	public LegalBasis getLegalBasis() {
		return this.legalBasis;
	}

	public void setLegalBasis(final LegalBasis legalBasis) {
		this.legalBasis = legalBasis;
	}

	public PersonuppgiftsbehandlingEntity withLegalBasis(final LegalBasis legalBasis) {
		this.legalBasis = legalBasis;
		return this;
	}

	public String getLegalReference() {
		return this.legalReference;
	}

	public void setLegalReference(final String legalReference) {
		this.legalReference = legalReference;
	}

	public PersonuppgiftsbehandlingEntity withLegalReference(final String legalReference) {
		this.legalReference = legalReference;
		return this;
	}

	public String getSensitiveDataBasis() {
		return this.sensitiveDataBasis;
	}

	public void setSensitiveDataBasis(final String sensitiveDataBasis) {
		this.sensitiveDataBasis = sensitiveDataBasis;
	}

	public PersonuppgiftsbehandlingEntity withSensitiveDataBasis(final String sensitiveDataBasis) {
		this.sensitiveDataBasis = sensitiveDataBasis;
		return this;
	}

	public String getDataSubjectCategories() {
		return this.dataSubjectCategories;
	}

	public void setDataSubjectCategories(final String dataSubjectCategories) {
		this.dataSubjectCategories = dataSubjectCategories;
	}

	public PersonuppgiftsbehandlingEntity withDataSubjectCategories(final String dataSubjectCategories) {
		this.dataSubjectCategories = dataSubjectCategories;
		return this;
	}

	public Long getEstimatedDataSubjectCount() {
		return this.estimatedDataSubjectCount;
	}

	public void setEstimatedDataSubjectCount(final Long estimatedDataSubjectCount) {
		this.estimatedDataSubjectCount = estimatedDataSubjectCount;
	}

	public PersonuppgiftsbehandlingEntity withEstimatedDataSubjectCount(final Long estimatedDataSubjectCount) {
		this.estimatedDataSubjectCount = estimatedDataSubjectCount;
		return this;
	}

	public String getStandardDataCategories() {
		return this.standardDataCategories;
	}

	public void setStandardDataCategories(final String standardDataCategories) {
		this.standardDataCategories = standardDataCategories;
	}

	public PersonuppgiftsbehandlingEntity withStandardDataCategories(final String standardDataCategories) {
		this.standardDataCategories = standardDataCategories;
		return this;
	}

	public Boolean getProcessesSensitiveData() {
		return this.processesSensitiveData;
	}

	public void setProcessesSensitiveData(final Boolean processesSensitiveData) {
		this.processesSensitiveData = processesSensitiveData;
	}

	public PersonuppgiftsbehandlingEntity withProcessesSensitiveData(final Boolean processesSensitiveData) {
		this.processesSensitiveData = processesSensitiveData;
		return this;
	}

	public String getSensitiveDataCategories() {
		return this.sensitiveDataCategories;
	}

	public void setSensitiveDataCategories(final String sensitiveDataCategories) {
		this.sensitiveDataCategories = sensitiveDataCategories;
	}

	public PersonuppgiftsbehandlingEntity withSensitiveDataCategories(final String sensitiveDataCategories) {
		this.sensitiveDataCategories = sensitiveDataCategories;
		return this;
	}

	public Boolean getProcessesCriminalData() {
		return this.processesCriminalData;
	}

	public void setProcessesCriminalData(final Boolean processesCriminalData) {
		this.processesCriminalData = processesCriminalData;
	}

	public PersonuppgiftsbehandlingEntity withProcessesCriminalData(final Boolean processesCriminalData) {
		this.processesCriminalData = processesCriminalData;
		return this;
	}

	public Boolean getProcessesSsn() {
		return this.processesSsn;
	}

	public void setProcessesSsn(final Boolean processesSsn) {
		this.processesSsn = processesSsn;
	}

	public PersonuppgiftsbehandlingEntity withProcessesSsn(final Boolean processesSsn) {
		this.processesSsn = processesSsn;
		return this;
	}

	public String getInternalRecipients() {
		return this.internalRecipients;
	}

	public void setInternalRecipients(final String internalRecipients) {
		this.internalRecipients = internalRecipients;
	}

	public PersonuppgiftsbehandlingEntity withInternalRecipients(final String internalRecipients) {
		this.internalRecipients = internalRecipients;
		return this;
	}

	public String getExternalRecipients() {
		return this.externalRecipients;
	}

	public void setExternalRecipients(final String externalRecipients) {
		this.externalRecipients = externalRecipients;
	}

	public PersonuppgiftsbehandlingEntity withExternalRecipients(final String externalRecipients) {
		this.externalRecipients = externalRecipients;
		return this;
	}

	public String getDataProcessors() {
		return this.dataProcessors;
	}

	public void setDataProcessors(final String dataProcessors) {
		this.dataProcessors = dataProcessors;
	}

	public PersonuppgiftsbehandlingEntity withDataProcessors(final String dataProcessors) {
		this.dataProcessors = dataProcessors;
		return this;
	}

	public Boolean getTransfersToThirdCountry() {
		return this.transfersToThirdCountry;
	}

	public void setTransfersToThirdCountry(final Boolean transfersToThirdCountry) {
		this.transfersToThirdCountry = transfersToThirdCountry;
	}

	public PersonuppgiftsbehandlingEntity withTransfersToThirdCountry(final Boolean transfersToThirdCountry) {
		this.transfersToThirdCountry = transfersToThirdCountry;
		return this;
	}

	public String getThirdCountryDestinations() {
		return this.thirdCountryDestinations;
	}

	public void setThirdCountryDestinations(final String thirdCountryDestinations) {
		this.thirdCountryDestinations = thirdCountryDestinations;
	}

	public PersonuppgiftsbehandlingEntity withThirdCountryDestinations(final String thirdCountryDestinations) {
		this.thirdCountryDestinations = thirdCountryDestinations;
		return this;
	}

	public String getTransferProtectionMechanism() {
		return this.transferProtectionMechanism;
	}

	public void setTransferProtectionMechanism(final String transferProtectionMechanism) {
		this.transferProtectionMechanism = transferProtectionMechanism;
	}

	public PersonuppgiftsbehandlingEntity withTransferProtectionMechanism(final String transferProtectionMechanism) {
		this.transferProtectionMechanism = transferProtectionMechanism;
		return this;
	}

	public String getRetentionPeriod() {
		return this.retentionPeriod;
	}

	public void setRetentionPeriod(final String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
	}

	public PersonuppgiftsbehandlingEntity withRetentionPeriod(final String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
		return this;
	}

	public String getRetentionBasis() {
		return this.retentionBasis;
	}

	public void setRetentionBasis(final String retentionBasis) {
		this.retentionBasis = retentionBasis;
	}

	public PersonuppgiftsbehandlingEntity withRetentionBasis(final String retentionBasis) {
		this.retentionBasis = retentionBasis;
		return this;
	}

	public Boolean getHasArchivingObligation() {
		return this.hasArchivingObligation;
	}

	public void setHasArchivingObligation(final Boolean hasArchivingObligation) {
		this.hasArchivingObligation = hasArchivingObligation;
	}

	public PersonuppgiftsbehandlingEntity withHasArchivingObligation(final Boolean hasArchivingObligation) {
		this.hasArchivingObligation = hasArchivingObligation;
		return this;
	}

	public String getTechnicalMeasures() {
		return this.technicalMeasures;
	}

	public void setTechnicalMeasures(final String technicalMeasures) {
		this.technicalMeasures = technicalMeasures;
	}

	public PersonuppgiftsbehandlingEntity withTechnicalMeasures(final String technicalMeasures) {
		this.technicalMeasures = technicalMeasures;
		return this;
	}

	public String getOrganizationalMeasures() {
		return this.organizationalMeasures;
	}

	public void setOrganizationalMeasures(final String organizationalMeasures) {
		this.organizationalMeasures = organizationalMeasures;
	}

	public PersonuppgiftsbehandlingEntity withOrganizationalMeasures(final String organizationalMeasures) {
		this.organizationalMeasures = organizationalMeasures;
		return this;
	}

	public String getInformationMethod() {
		return this.informationMethod;
	}

	public void setInformationMethod(final String informationMethod) {
		this.informationMethod = informationMethod;
	}

	public PersonuppgiftsbehandlingEntity withInformationMethod(final String informationMethod) {
		this.informationMethod = informationMethod;
		return this;
	}

	public String getPrivacyPolicyUrl() {
		return this.privacyPolicyUrl;
	}

	public void setPrivacyPolicyUrl(final String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}

	public PersonuppgiftsbehandlingEntity withPrivacyPolicyUrl(final String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
		return this;
	}

	public Boolean getDpiaRequired() {
		return this.dpiaRequired;
	}

	public void setDpiaRequired(final Boolean dpiaRequired) {
		this.dpiaRequired = dpiaRequired;
	}

	public PersonuppgiftsbehandlingEntity withDpiaRequired(final Boolean dpiaRequired) {
		this.dpiaRequired = dpiaRequired;
		return this;
	}

	public LocalDate getDpiaDate() {
		return this.dpiaDate;
	}

	public void setDpiaDate(final LocalDate dpiaDate) {
		this.dpiaDate = dpiaDate;
	}

	public PersonuppgiftsbehandlingEntity withDpiaDate(final LocalDate dpiaDate) {
		this.dpiaDate = dpiaDate;
		return this;
	}

	public String getDpiaResults() {
		return this.dpiaResults;
	}

	public void setDpiaResults(final String dpiaResults) {
		this.dpiaResults = dpiaResults;
	}

	public PersonuppgiftsbehandlingEntity withDpiaResults(final String dpiaResults) {
		this.dpiaResults = dpiaResults;
		return this;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(final String dataSource) {
		this.dataSource = dataSource;
	}

	public PersonuppgiftsbehandlingEntity withDataSource(final String dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	public Boolean getHasAutomatedDecisions() {
		return this.hasAutomatedDecisions;
	}

	public void setHasAutomatedDecisions(final Boolean hasAutomatedDecisions) {
		this.hasAutomatedDecisions = hasAutomatedDecisions;
	}

	public PersonuppgiftsbehandlingEntity withHasAutomatedDecisions(final Boolean hasAutomatedDecisions) {
		this.hasAutomatedDecisions = hasAutomatedDecisions;
		return this;
	}

	public String getAutomatedDecisionLogic() {
		return this.automatedDecisionLogic;
	}

	public void setAutomatedDecisionLogic(final String automatedDecisionLogic) {
		this.automatedDecisionLogic = automatedDecisionLogic;
	}

	public PersonuppgiftsbehandlingEntity withAutomatedDecisionLogic(final String automatedDecisionLogic) {
		this.automatedDecisionLogic = automatedDecisionLogic;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final PersonuppgiftsbehandlingEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.behandlingId, that.behandlingId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			this.status == that.status &&
			Objects.equals(this.verksamhetsomrade, that.verksamhetsomrade) &&
			Objects.equals(this.controllerOrganizationId, that.controllerOrganizationId) &&
			Objects.equals(this.dpoId, that.dpoId) &&
			Objects.equals(this.processingResponsibleId, that.processingResponsibleId) &&
			Objects.equals(this.purposeDescription, that.purposeDescription) &&
			Objects.equals(this.detailedPurposes, that.detailedPurposes) &&
			this.legalBasis == that.legalBasis &&
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
		return Objects.hash(this.getId(), this.behandlingId, this.name, this.description, this.status,
			this.verksamhetsomrade, this.controllerOrganizationId, this.dpoId, this.processingResponsibleId,
			this.purposeDescription, this.detailedPurposes, this.legalBasis, this.legalReference,
			this.sensitiveDataBasis, this.dataSubjectCategories, this.estimatedDataSubjectCount,
			this.standardDataCategories, this.processesSensitiveData, this.sensitiveDataCategories,
			this.processesCriminalData, this.processesSsn, this.internalRecipients, this.externalRecipients,
			this.dataProcessors, this.transfersToThirdCountry, this.thirdCountryDestinations,
			this.transferProtectionMechanism, this.retentionPeriod, this.retentionBasis,
			this.hasArchivingObligation, this.technicalMeasures, this.organizationalMeasures,
			this.informationMethod, this.privacyPolicyUrl, this.dpiaRequired, this.dpiaDate, this.dpiaResults,
			this.dataSource, this.hasAutomatedDecisions, this.automatedDecisionLogic);
	}

	@Override
	public String toString() {
		return "PersonuppgiftsbehandlingEntity{" +
			"id='" + this.getId() + '\'' +
			", behandlingId='" + this.behandlingId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status=" + this.status +
			", verksamhetsomrade='" + this.verksamhetsomrade + '\'' +
			", controllerOrganizationId='" + this.controllerOrganizationId + '\'' +
			", dpoId='" + this.dpoId + '\'' +
			", processingResponsibleId='" + this.processingResponsibleId + '\'' +
			", purposeDescription='" + this.purposeDescription + '\'' +
			", detailedPurposes='" + this.detailedPurposes + '\'' +
			", legalBasis=" + this.legalBasis +
			", legalReference='" + this.legalReference + '\'' +
			", sensitiveDataBasis='" + this.sensitiveDataBasis + '\'' +
			", dataSubjectCategories='" + this.dataSubjectCategories + '\'' +
			", estimatedDataSubjectCount=" + this.estimatedDataSubjectCount +
			", standardDataCategories='" + this.standardDataCategories + '\'' +
			", processesSensitiveData=" + this.processesSensitiveData +
			", sensitiveDataCategories='" + this.sensitiveDataCategories + '\'' +
			", processesCriminalData=" + this.processesCriminalData +
			", processesSsn=" + this.processesSsn +
			", internalRecipients='" + this.internalRecipients + '\'' +
			", externalRecipients='" + this.externalRecipients + '\'' +
			", dataProcessors='" + this.dataProcessors + '\'' +
			", transfersToThirdCountry=" + this.transfersToThirdCountry +
			", thirdCountryDestinations='" + this.thirdCountryDestinations + '\'' +
			", transferProtectionMechanism='" + this.transferProtectionMechanism + '\'' +
			", retentionPeriod='" + this.retentionPeriod + '\'' +
			", retentionBasis='" + this.retentionBasis + '\'' +
			", hasArchivingObligation=" + this.hasArchivingObligation +
			", technicalMeasures='" + this.technicalMeasures + '\'' +
			", organizationalMeasures='" + this.organizationalMeasures + '\'' +
			", informationMethod='" + this.informationMethod + '\'' +
			", privacyPolicyUrl='" + this.privacyPolicyUrl + '\'' +
			", dpiaRequired=" + this.dpiaRequired +
			", dpiaDate=" + this.dpiaDate +
			", dpiaResults='" + this.dpiaResults + '\'' +
			", dataSource='" + this.dataSource + '\'' +
			", hasAutomatedDecisions=" + this.hasAutomatedDecisions +
			", automatedDecisionLogic='" + this.automatedDecisionLogic + '\'' +
			'}';
	}
}
