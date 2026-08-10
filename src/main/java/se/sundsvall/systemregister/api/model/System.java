package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "System")
public class System {

	@Schema(description = "System ID", example = "system-1")
	private String id;

	@Schema(description = "System identifier", example = "SYS-001")
	@NotBlank
	private String systemId;

	@Schema(description = "System name", example = "HR Management System")
	@NotBlank
	private String name;

	@Schema(description = "System description", example = "Manages human resources and payroll")
	private String description;

	@Schema(description = "System status", allowableValues = {
		"PLANNED", "DEVELOPMENT", "PRODUCTION", "DEPRECATED", "RETIRED"
	})
	private String status;

	@Schema(description = "System version", example = "2.1.0")
	private String version;

	@Schema(description = "Documentation URL", example = "https://docs.example.com/hr-system")
	private String documentationUrl;

	@Schema(description = "Criticality level ID", example = "critical-1")
	private String criticalityLevelId;

	@Schema(description = "Konfidentialitet level", example = "HIGH")
	private Integer konfidentialitet;

	@Schema(description = "Motivation of Confidentiality level", example = "This level of Confidentiality level was set because of reason XYZ")
	private String konfidentialitetMotivering;

	@Schema(description = "Riktighet level", example = "MEDIUM")
	private Integer riktighet;

	@Schema(description = "Motivation of Correctness level", example = "This level of Correctness level was set because of reason XYZ")
	private String riktighetMotivering;

	@Schema(description = "Tillganglighet level", example = "HIGH")
	private Integer tillganglighet;

	@Schema(description = "Motivation of Availability level", example = "This level of Availability level was set because of reason XYZ")
	private String tillganglighetMotivering;

	@Schema(description = "Is system a essential service ", example = "true")
	private Boolean samhallsviktigt;

	@Schema(description = "Essential status motivation/reasoning")
	private String samhallsviktigtMotivering;

	@Schema(description = "Owner organization ID", example = "org-1")
	private String ownerOrganizationId;

	@Schema(description = "System owner person ID", example = "person-1")
	private String systemOwnerId;

	@Schema(description = "Technical contact person ID", example = "person-2")
	private String technicalContactId;

	@Schema(description = "Hosting type", allowableValues = {
		"ON_PREMISE", "CLOUD", "HYBRID"
	})
	private String hostingType;

	@Schema(description = "Supplier ID", example = "supplier-1")
	private String supplierId;

	public static System create() {
		return new System();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public System withId(final String id) {
		this.id = id;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public System withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public System withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public System withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public System withStatus(final String status) {
		this.status = status;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public System withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public System withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public String getCriticalityLevelId() {
		return this.criticalityLevelId;
	}

	public void setCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
	}

	public System withCriticalityLevelId(final String criticalityLevelId) {
		this.criticalityLevelId = criticalityLevelId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public System withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public String getKonfidentialitetMotivering() {
		return this.konfidentialitetMotivering;
	}

	public void setKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
	}

	public System withKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public System withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public String getRiktighetMotivering() {
		return this.riktighetMotivering;
	}

	public void setRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
	}

	public System withRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public System withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public String getTillganglighetMotivering() {
		return this.tillganglighetMotivering;
	}

	public void setTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
	}

	public System withTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
		return this;
	}

	public Boolean getSamhallsviktigt() {
		return this.samhallsviktigt;
	}

	public void setSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
	}

	public System withSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
		return this;
	}

	public String getSamhallsviktigtMotivering() {
		return this.samhallsviktigtMotivering;
	}

	public void setSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
	}

	public System withSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
		return this;
	}

	public String getOwnerOrganizationId() {
		return this.ownerOrganizationId;
	}

	public void setOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	public System withOwnerOrganizationId(final String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
		return this;
	}

	public String getSystemOwnerId() {
		return this.systemOwnerId;
	}

	public void setSystemOwnerId(final String systemOwnerId) {
		this.systemOwnerId = systemOwnerId;
	}

	public System withSystemOwnerId(final String systemOwnerId) {
		this.systemOwnerId = systemOwnerId;
		return this;
	}

	public String getTechnicalContactId() {
		return this.technicalContactId;
	}

	public void setTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
	}

	public System withTechnicalContactId(final String technicalContactId) {
		this.technicalContactId = technicalContactId;
		return this;
	}

	public String getHostingType() {
		return this.hostingType;
	}

	public void setHostingType(final String hostingType) {
		this.hostingType = hostingType;
	}

	public System withHostingType(final String hostingType) {
		this.hostingType = hostingType;
		return this;
	}

	public String getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(final String supplierId) {
		this.supplierId = supplierId;
	}

	public System withSupplierId(final String supplierId) {
		this.supplierId = supplierId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final System that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
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
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId) &&
			Objects.equals(this.systemOwnerId, that.systemOwnerId) &&
			Objects.equals(this.technicalContactId, that.technicalContactId) &&
			Objects.equals(this.hostingType, that.hostingType) &&
			Objects.equals(this.supplierId, that.supplierId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.systemId, this.name, this.description, this.status, this.version,
			this.documentationUrl, this.criticalityLevelId, this.konfidentialitet, this.konfidentialitetMotivering,
			this.riktighet, this.tillganglighet, this.riktighetMotivering, this.tillganglighetMotivering,
			this.ownerOrganizationId, this.systemOwnerId, this.technicalContactId, this.hostingType,
			this.supplierId, this.samhallsviktigt, this.samhallsviktigtMotivering);
	}

	@Override
	public String toString() {
		return "System{" +
			"id='" + this.id + '\'' +
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
			", ownerOrganizationId='" + this.ownerOrganizationId + '\'' +
			", systemOwnerId='" + this.systemOwnerId + '\'' +
			", technicalContactId='" + this.technicalContactId + '\'' +
			", hostingType='" + this.hostingType + '\'' +
			", supplierId='" + this.supplierId + '\'' +
			'}';
	}
}
