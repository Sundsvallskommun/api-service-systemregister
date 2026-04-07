package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "System Dependency")
public class SystemDependency {

	@Schema(description = "Dependency ID", example = "dependency-1")
	private String id;

	@Schema(description = "Source system ID", example = "system-1")
	@NotBlank
	private String sourceSystemId;

	@Schema(description = "Target system ID", example = "system-2")
	@NotBlank
	private String targetSystemId;

	@Schema(description = "Dependency type", allowableValues = {
		"FUNCTIONAL", "TECHNICAL", "DATA", "PROCESS"
	})
	private String dependencyType;

	@Schema(description = "Dependency description", example = "System depends on database cluster")
	private String description;

	@Schema(description = "Is this a critical dependency", example = "true")
	private Boolean isCritical;

	@Schema(description = "Documentation URL", example = "https://docs.example.com/dependency-info")
	private String documentationUrl;

	@Schema(description = "Contract file path", example = "/contracts/system-dependency.pdf")
	private String contractFilePath;

	public static SystemDependency create() {
		return new SystemDependency();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public SystemDependency withId(final String id) {
		this.id = id;
		return this;
	}

	public String getSourceSystemId() {
		return this.sourceSystemId;
	}

	public void setSourceSystemId(final String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public SystemDependency withSourceSystemId(final String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
		return this;
	}

	public String getTargetSystemId() {
		return this.targetSystemId;
	}

	public void setTargetSystemId(final String targetSystemId) {
		this.targetSystemId = targetSystemId;
	}

	public SystemDependency withTargetSystemId(final String targetSystemId) {
		this.targetSystemId = targetSystemId;
		return this;
	}

	public String getDependencyType() {
		return this.dependencyType;
	}

	public void setDependencyType(final String dependencyType) {
		this.dependencyType = dependencyType;
	}

	public SystemDependency withDependencyType(final String dependencyType) {
		this.dependencyType = dependencyType;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SystemDependency withDescription(final String description) {
		this.description = description;
		return this;
	}

	public Boolean getIsCritical() {
		return this.isCritical;
	}

	public void setIsCritical(final Boolean isCritical) {
		this.isCritical = isCritical;
	}

	public SystemDependency withIsCritical(final Boolean isCritical) {
		this.isCritical = isCritical;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public SystemDependency withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public String getContractFilePath() {
		return this.contractFilePath;
	}

	public void setContractFilePath(final String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}

	public SystemDependency withContractFilePath(final String contractFilePath) {
		this.contractFilePath = contractFilePath;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SystemDependency that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.sourceSystemId, that.sourceSystemId) &&
			Objects.equals(this.targetSystemId, that.targetSystemId) &&
			Objects.equals(this.dependencyType, that.dependencyType) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.isCritical, that.isCritical) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			Objects.equals(this.contractFilePath, that.contractFilePath);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.sourceSystemId, this.targetSystemId, this.dependencyType,
			this.description, this.isCritical, this.documentationUrl, this.contractFilePath);
	}

	@Override
	public String toString() {
		return "SystemDependency{" +
			"id='" + this.id + '\'' +
			", sourceSystemId='" + this.sourceSystemId + '\'' +
			", targetSystemId='" + this.targetSystemId + '\'' +
			", dependencyType='" + this.dependencyType + '\'' +
			", description='" + this.description + '\'' +
			", isCritical=" + this.isCritical +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", contractFilePath='" + this.contractFilePath + '\'' +
			'}';
	}
}
