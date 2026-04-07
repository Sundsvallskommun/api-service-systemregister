package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Service")
public class ServiceModel {

	@Schema(description = "Service ID", example = "service-1")
	private String id;

	@Schema(description = "Service identifier", example = "SVC-001")
	@NotBlank
	private String serviceId;

	@Schema(description = "Service name", example = "Email Service")
	@NotBlank
	private String name;

	@Schema(description = "Service description", example = "Email delivery and management service")
	private String description;

	@Schema(description = "Service version", example = "1.5.0")
	private String version;

	@Schema(description = "Endpoint URL", example = "https://api.example.com/email")
	private String endpointUrl;

	@Schema(description = "Documentation URL", example = "https://docs.example.com/email-service")
	private String documentationUrl;

	@Schema(description = "Service type", allowableValues = {
		"REST", "SOAP", "GRAPHQL", "MESSAGE_QUEUE"
	})
	private String serviceType;

	@Schema(description = "Criticality level ID", example = "critical-1")
	private String criticalityLevelId;

	@Schema(description = "Konfidentialitet level", example = "HIGH")
	private Integer konfidentialitet;

	@Schema(description = "Riktighet level", example = "MEDIUM")
	private Integer riktighet;

	@Schema(description = "Tillganglighet level", example = "HIGH")
	private Integer tillganglighet;

	@Schema(description = "Owner organization ID", example = "org-1")
	private String ownerOrganizationId;

	@Schema(description = "Service owner person ID", example = "person-1")
	private String serviceOwnerId;

	@Schema(description = "Technical contact person ID", example = "person-2")
	private String technicalContactId;

	@Schema(description = "Hosting type", allowableValues = {
		"ON_PREMISE", "CLOUD", "HYBRID"
	})
	private String hostingType;

	@Schema(description = "Supplier ID", example = "supplier-1")
	private String supplierId;

	public static ServiceModel create() {
		return new ServiceModel();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ServiceModel withId(final String id) {
		this.id = id;
		return this;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceModel withServiceId(final String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ServiceModel withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public ServiceModel withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public ServiceModel withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getEndpointUrl() {
		return this.endpointUrl;
	}

	public void setEndpointUrl(final String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	public ServiceModel withEndpointUrl(final String endpointUrl) {
		this.endpointUrl = endpointUrl;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public ServiceModel withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(final String serviceType) {
		this.serviceType = serviceType;
	}

	public ServiceModel withServiceType(final String serviceType) {
		this.serviceType = serviceType;
		return this;
	}

	public String getCriticalityLevelId() {
		return this.criticalityLevelId;
	}

	public void setCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
	}

	public ServiceModel withCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public ServiceModel withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public ServiceModel withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public ServiceModel withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public ServiceModel withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getServiceOwnerId() {
		return this.serviceOwnerId;
	}

	public void setServiceOwnerId(final String serviceOwnerId) {
		this.serviceOwnerId = serviceOwnerId;
	}

	public ServiceModel withServiceOwnerId(final String serviceOwnerId) {
		this.serviceOwnerId = serviceOwnerId;
		return this;
	}

	public String getTechnicalContactId() {
		return this.technicalContactId;
	}

	public void setTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
	}

	public ServiceModel withTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
		return this;
	}

	public String getHostingType() {
		return this.hostingType;
	}

	public void setHostingType(final String hostingType) {
		this.hostingType = hostingType;
	}

	public ServiceModel withHostingType(final String hostingType) {
		this.hostingType = hostingType;
		return this;
	}

	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(final String supplierId) {
		this.supplierId = supplierId;
	}

	public ServiceModel withSupplierId(final String supplierId) {
		this.supplierId = supplierId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final ServiceModel that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.serviceId, that.serviceId) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.endpointUrl, that.endpointUrl) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			Objects.equals(this.serviceType, that.serviceType) &&
			Objects.equals(this.criticalityLevelId, that.criticalityLevelId) &&
			Objects.equals(this.konfidentialitet, that.konfidentialitet) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.serviceOwnerId, that.serviceOwnerId) &&
			Objects.equals(this.technicalContactId, that.technicalContactId) &&
			Objects.equals(this.hostingType, that.hostingType) &&
			Objects.equals(this.supplierId, that.supplierId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.serviceId, this.name, this.description, this.version, this.endpointUrl,
			this.documentationUrl, this.serviceType, this.criticalityLevelId, this.konfidentialitet, this.riktighet,
			this.tillganglighet, this.ownerOrganizationId, this.serviceOwnerId, this.technicalContactId,
			this.hostingType, this.supplierId);
	}

	@Override
	public String toString() {
		return "ServiceModel{" +
			"id='" + this.id + '\'' +
			", serviceId='" + this.serviceId + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", version='" + this.version + '\'' +
			", endpointUrl='" + this.endpointUrl + '\'' +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", serviceType='" + this.serviceType + '\'' +
			", criticalityLevelId='" + this.criticalityLevelId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", riktighet=" + this.riktighet +
			", tillganglighet=" + this.tillganglighet +
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", serviceOwnerId='" + this.serviceOwnerId + '\'' +
			", technicalContactId='" + this.technicalContactId + '\'' +
			", hostingType='" + this.hostingType + '\'' +
			", supplierId='" + this.supplierId + '\'' +
			'}';
	}
}
