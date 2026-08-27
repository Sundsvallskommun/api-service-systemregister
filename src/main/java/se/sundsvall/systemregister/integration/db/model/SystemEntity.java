package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

import java.util.Objects;

import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

@Entity
@Table(name = "systems")
public class SystemEntity extends AbstractAuditableEntity {

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private SystemStatus status;

	@Column(name = "version")
	private String version;

	@Column(name = "documentation_url")
	private String documentationUrl;

	@Column(name = "criticality_level_id")
	private String criticalityLevelId;

	@Column(name = "konfidentialitet")
	private Integer konfidentialitet;

	@Column(name = "konfidentialitet_motivering")
	private String konfidentialitetMotivering;

	@Column(name = "riktighet")
	private Integer riktighet;

	@Column(name = "riktighet_motivering")
	private String riktighetMotivering;

	@Column(name = "tillganglighet")
	private Integer tillganglighet;

	@Column(name = "tillganglighet_motivering")
	private String tillganglighetMotivering;

	@Column(name = "samhallsviktigt", columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean samhallsviktigt;

	@Column(name = "samhallsviktigt_motivering")
	private String samhallsviktigtMotivering;

	@Column(name = "klassningsdatum")
	private LocalDate klassningsdatum;

	@Column(name = "owner_organization_id")
	private String ownerOrganizationId;

	@Column(name = "system_owner_id")
	private String systemOwnerId;

	@Column(name = "system_manager_id")
	private String systemManagerId;

	@Column(name = "technical_contact_id")
	private String technicalContactId;

	@Column(name = "hosting_type")
	@Enumerated(EnumType.STRING)
	private HostingType hostingType;

	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "risk_analysed", columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean riskAnalysed;

	@Column(name = "risk_analysed_date")
	private LocalDate riskAnalysedDate;

	public static SystemEntity create() {
		return new SystemEntity();
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public SystemEntity withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public SystemEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SystemEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public SystemStatus getStatus() {
		return this.status;
	}

	public void setStatus(final SystemStatus status) {
		this.status = status;
	}

	public SystemEntity withStatus(final SystemStatus status) {
		this.status = status;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public SystemEntity withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public SystemEntity withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public String getCriticalityLevelId() {
		return this.criticalityLevelId;
	}

	public void setCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
	}

	public SystemEntity withCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public SystemEntity withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public String getKonfidentialitetMotivering() {
		return this.konfidentialitetMotivering;
	}

	public void setKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
	}

	public SystemEntity withKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public SystemEntity withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public String getRiktighetMotivering() {
		return this.riktighetMotivering;
	}

	public void setRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
	}

	public SystemEntity withRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public SystemEntity withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public String getTillganglighetMotivering() {
		return this.tillganglighetMotivering;
	}

	public void setTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
	}

	public SystemEntity withTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
		return this;
	}

	public Boolean getSamhallsviktigt() {
		return this.samhallsviktigt;
	}

	public void setSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
	}

	public SystemEntity withSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
		return this;
	}

	public String getSamhallsviktigtMotivering() {
		return this.samhallsviktigtMotivering;
	}

	public void setSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
	}

	public SystemEntity withSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
		return this;
	}

	public LocalDate getKlassningsdatum() {
		return this.klassningsdatum;
	}

	public void setKlassningsdatum(final LocalDate klassningsdatum) {
		this.klassningsdatum = klassningsdatum;
	}

	public SystemEntity withKlassningsdatum(final LocalDate klassningsdatum) {
		this.klassningsdatum = klassningsdatum;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public SystemEntity withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getSystemOwnerId() {
		return this.systemOwnerId;
	}

	public void setSystemOwnerId(final String systemOwnerId) {
		this.systemOwnerId = systemOwnerId;
	}

	public SystemEntity withSystemOwnerId(final String systemOwnerId) {
		this.systemOwnerId = systemOwnerId;
		return this;
	}

	public String getSystemManagerId() {
		return this.systemManagerId;
	}

	public void setSystemManagerId(final String systemManagerId) {
		this.systemManagerId = systemManagerId;
	}

	public SystemEntity withSystemManagerId(final String systemManagerId) {
		this.systemManagerId = systemManagerId;
		return this;
	}

	public String getTechnicalContactId() {
		return this.technicalContactId;
	}

	public void setTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
	}

	public SystemEntity withTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
		return this;
	}

	public HostingType getHostingType() {
		return this.hostingType;
	}

	public void setHostingType(final HostingType hostingType) {
		this.hostingType = hostingType;
	}

	public SystemEntity withHostingType(final HostingType hostingType) {
		this.hostingType = hostingType;
		return this;
	}

	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(final String supplierId) {
		this.supplierId = supplierId;
	}

	public SystemEntity withSupplierId(final String supplierId) {
		this.supplierId = supplierId;
		return this;
	}

	public Boolean getRiskAnalysed() {
		return this.riskAnalysed;
	}

	public void setRiskAnalysed(final Boolean riskAnalysed) {
		this.riskAnalysed = riskAnalysed;
	}

	public SystemEntity withRiskAnalysed(final Boolean riskAnalysed) {
		this.riskAnalysed = riskAnalysed;
		return this;
	}

	public LocalDate getRiskAnalysedDate() {
		return this.riskAnalysedDate;
	}

	public void setRiskAnalysedDate(final LocalDate riskAnalysedDate) {
		this.riskAnalysedDate = riskAnalysedDate;
	}

	public SystemEntity withRiskAnalysedDate(final LocalDate riskAnalysedDate) {
		this.riskAnalysedDate = riskAnalysedDate;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SystemEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.status, that.status) &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			Objects.equals(this.criticalityLevelId, that.criticalityLevelId) &&
			Objects.equals(this.konfidentialitet, that.konfidentialitet) &&
			Objects.equals(this.konfidentialitetMotivering, that.konfidentialitetMotivering) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.riktighetMotivering, that.riktighetMotivering) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.tillganglighetMotivering, that.tillganglighetMotivering) &&
			Objects.equals(this.samhallsviktigt, that.samhallsviktigt) &&
			Objects.equals(this.samhallsviktigtMotivering, that.samhallsviktigtMotivering) &&
			Objects.equals(this.klassningsdatum, that.klassningsdatum) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.systemOwnerId, that.systemOwnerId) &&
			Objects.equals(this.systemManagerId, that.systemManagerId) &&
			Objects.equals(this.technicalContactId, that.technicalContactId) &&
			Objects.equals(this.hostingType, that.hostingType) &&
			Objects.equals(this.supplierId, that.supplierId) &&
			Objects.equals(this.riskAnalysed, that.riskAnalysed) &&
			Objects.equals(this.riskAnalysedDate, that.riskAnalysedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.systemId, this.name, this.description, this.status, this.version,
			this.documentationUrl, this.criticalityLevelId, this.konfidentialitet, this.riktighet,
			this.tillganglighet, this.ownerOrganizationId, this.systemOwnerId, this.technicalContactId,
			this.hostingType, this.supplierId, this.systemManagerId, this.riskAnalysed, this.riskAnalysedDate);
			this.documentationUrl, this.criticalityLevelId, this.konfidentialitet, this.konfidentialitetMotivering,
			this.riktighet, this.tillganglighet, this.riktighetMotivering, this.tillganglighetMotivering,
			this.ownerOrganizationId, this.systemOwnerId, this.technicalContactId, this.hostingType,
			this.supplierId, this.samhallsviktigt, this.samhallsviktigtMotivering, klassningsdatum);
	}

	@Override
	public String toString() {
		return "SystemEntity{" +
			"id='" + this.getId() + '\'' +
			", systemId='" + this.systemId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status='" + this.status + '\'' +
			", version='" + this.version + '\'' +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", criticalityLevelId='" + this.criticalityLevelId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", konfidentialitetMotivering=" + this.konfidentialitetMotivering +
			", riktighet=" + this.riktighet +
			", riktighetMotivering=" + this.riktighetMotivering +
			", tillganglighet=" + this.tillganglighet +
			", tillganglighetMotivering=" + this.tillganglighetMotivering +
			", samhallsviktigt=" + this.samhallsviktigt +
			", samhallsviktigtMotivering=" + this.samhallsviktigtMotivering +
			", klassningsdatum=" + this.klassningsdatum +
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", systemOwnerId='" + this.systemOwnerId + '\'' +
			", systemManagerId='" + this.systemManagerId + '\'' +
			", technicalContactId='" + this.technicalContactId + '\'' +
			", hostingType='" + this.hostingType + '\'' +
			", supplierId='" + this.supplierId + '\'' +
			", riskAnalysed='" + this.riskAnalysed +
			", riskAnalysedDate='" + this.riskAnalysedDate +
			'}';
	}
}
