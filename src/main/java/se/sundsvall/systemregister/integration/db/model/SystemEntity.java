package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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

	@Column(name = "riktighet")
	private Integer riktighet;

	@Column(name = "tillganglighet")
	private Integer tillganglighet;

	@Column(name = "owner_organization_id")
	private String ownerOrganizationId;

	@Column(name = "system_owner_id")
	private String systemOwnerId;

	@Column(name = "technical_contact_id")
	private String technicalContactId;

	@Column(name = "hosting_type")
	@Enumerated(EnumType.STRING)
	private HostingType hostingType;

	@Column(name = "supplier_id")
	private String supplierId;

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
			this.status == that.status &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			Objects.equals(this.criticalityLevelId, that.criticalityLevelId) &&
			Objects.equals(this.konfidentialitet, that.konfidentialitet) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.systemOwnerId, that.systemOwnerId) &&
			Objects.equals(this.technicalContactId, that.technicalContactId) &&
			this.hostingType == that.hostingType &&
			Objects.equals(this.supplierId, that.supplierId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.systemId, this.name, this.description, this.status, this.version,
			this.documentationUrl, this.criticalityLevelId, this.konfidentialitet, this.riktighet,
			this.tillganglighet, this.ownerOrganizationId, this.systemOwnerId, this.technicalContactId,
			this.hostingType, this.supplierId);
	}

	@Override
	public String toString() {
		return "SystemEntity{" +
			"id='" + this.getId() + '\'' +
			", systemId='" + this.systemId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", status=" + this.status +
			", version='" + this.version + '\'' +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", criticalityLevelId='" + this.criticalityLevelId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", riktighet=" + this.riktighet +
			", tillganglighet=" + this.tillganglighet +
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", systemOwnerId='" + this.systemOwnerId + '\'' +
			", technicalContactId='" + this.technicalContactId + '\'' +
			", hostingType=" + this.hostingType +
			", supplierId='" + this.supplierId + '\'' +
			'}';
	}
}
