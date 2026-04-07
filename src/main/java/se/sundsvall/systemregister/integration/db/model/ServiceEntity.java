package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.HostingType;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceType;

@Entity
@Table(name = "services")
public class ServiceEntity extends AbstractAuditableEntity {

	@Column(name = "service_id")
	private String serviceId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "version")
	private String version;

	@Column(name = "endpoint_url")
	private String endpointUrl;

	@Column(name = "documentation_url")
	private String documentationUrl;

	@Column(name = "service_type")
	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;

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

	@Column(name = "service_owner_id")
	private String serviceOwnerId;

	@Column(name = "technical_contact_id")
	private String technicalContactId;

	@Column(name = "hosting_type")
	@Enumerated(EnumType.STRING)
	private HostingType hostingType;

	@Column(name = "supplier_id")
	private String supplierId;

	public static ServiceEntity create() {
		return new ServiceEntity();
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceEntity withServiceId(final String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ServiceEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public ServiceEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public ServiceEntity withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getEndpointUrl() {
		return this.endpointUrl;
	}

	public void setEndpointUrl(final String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public ServiceEntity withEndpointUrl(final String endpointUrl) {
		this.endpointUrl = endpointUrl;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public ServiceEntity withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(final ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public ServiceEntity withServiceType(final ServiceType serviceType) {
		this.serviceType = serviceType;
		return this;
	}

	public String getCriticalityLevelId() {
		return this.criticalityLevelId;
	}

	public void setCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
	}

	public ServiceEntity withCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public ServiceEntity withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public ServiceEntity withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public ServiceEntity withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public ServiceEntity withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getServiceOwnerId() {
		return this.serviceOwnerId;
	}

	public void setServiceOwnerId(final String serviceOwnerId) {
		this.serviceOwnerId = serviceOwnerId;
	}

	public ServiceEntity withServiceOwnerId(final String serviceOwnerId) {
		this.serviceOwnerId = serviceOwnerId;
		return this;
	}

	public String getTechnicalContactId() {
		return this.technicalContactId;
	}

	public void setTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
	}

	public ServiceEntity withTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
		return this;
	}

	public HostingType getHostingType() {
		return this.hostingType;
	}

	public void setHostingType(final HostingType hostingType) {
		this.hostingType = hostingType;
	}

	public ServiceEntity withHostingType(final HostingType hostingType) {
		this.hostingType = hostingType;
		return this;
	}

	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(final String supplierId) {
		this.supplierId = supplierId;
	}

	public ServiceEntity withSupplierId(final String supplierId) {
		this.supplierId = supplierId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final ServiceEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.serviceId, that.serviceId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.endpointUrl, that.endpointUrl) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			this.serviceType == that.serviceType &&
			Objects.equals(this.criticalityLevelId, that.criticalityLevelId) &&
			Objects.equals(this.konfidentialitet, that.konfidentialitet) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.serviceOwnerId, that.serviceOwnerId) &&
			Objects.equals(this.technicalContactId, that.technicalContactId) &&
			this.hostingType == that.hostingType &&
			Objects.equals(this.supplierId, that.supplierId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.serviceId, this.name, this.description, this.version, this.endpointUrl,
			this.documentationUrl, this.serviceType, this.criticalityLevelId, this.konfidentialitet, this.riktighet,
			this.tillganglighet, this.ownerOrganizationId, this.serviceOwnerId, this.technicalContactId,
			this.hostingType, this.supplierId);
	}

	@Override
	public String toString() {
		return "ServiceEntity{" +
			"id='" + this.getId() + '\'' +
			", serviceId='" + this.serviceId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", version='" + this.version + '\'' +
			", endpointUrl='" + this.endpointUrl + '\'' +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", serviceType=" + this.serviceType +
			", criticalityLevelId='" + this.criticalityLevelId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", riktighet=" + this.riktighet +
			", tillganglighet=" + this.tillganglighet +
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", serviceOwnerId='" + this.serviceOwnerId + '\'' +
			", technicalContactId='" + this.technicalContactId + '\'' +
			", hostingType=" + this.hostingType +
			", supplierId='" + this.supplierId + '\'' +
			'}';
	}
}
