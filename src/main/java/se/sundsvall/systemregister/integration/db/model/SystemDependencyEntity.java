package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.DependencyType;

@Entity
@Table(name = "system_dependencies")
public class SystemDependencyEntity extends AbstractAuditableEntity {

	@Column(name = "source_system_id")
	private String sourceSystemId;

	@Column(name = "target_system_id")
	private String targetSystemId;

	@Column(name = "dependency_type")
	@Enumerated(EnumType.STRING)
	private DependencyType dependencyType;

	@Column(name = "description")
	private String description;

	@Column(name = "is_critical")
	private Boolean isCritical;

	@Column(name = "documentation_url")
	private String documentationUrl;

	@Column(name = "contract_file_path")
	private String contractFilePath;

	public static SystemDependencyEntity create() {
		return new SystemDependencyEntity();
	}

	public String getSourceSystemId() {
		return this.sourceSystemId;
	}

	public void setSourceSystemId(final String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public SystemDependencyEntity withSourceSystemId(final String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
		return this;
	}

	public String getTargetSystemId() {
		return this.targetSystemId;
	}

	public void setTargetSystemId(final String targetSystemId) {
		this.targetSystemId = targetSystemId;
	}

	public SystemDependencyEntity withTargetSystemId(final String targetSystemId) {
		this.targetSystemId = targetSystemId;
		return this;
	}

	public DependencyType getDependencyType() {
		return this.dependencyType;
	}

	public void setDependencyType(final DependencyType dependencyType) {
		this.dependencyType = dependencyType;
	}

	public SystemDependencyEntity withDependencyType(final DependencyType dependencyType) {
		this.dependencyType = dependencyType;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SystemDependencyEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public Boolean getIsCritical() {
		return this.isCritical;
	}

	public void setIsCritical(final Boolean isCritical) {
		this.isCritical = isCritical;
	}

	public SystemDependencyEntity withIsCritical(final Boolean isCritical) {
		this.isCritical = isCritical;
		return this;
	}

	public String getDocumentationUrl() {
		return this.documentationUrl;
	}

	public void setDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public SystemDependencyEntity withDocumentationUrl(final String documentationUrl) {
		this.documentationUrl = documentationUrl;
		return this;
	}

	public String getContractFilePath() {
		return this.contractFilePath;
	}

	public void setContractFilePath(final String contractFilePath) {
		this.contractFilePath = contractFilePath;
	}

	public SystemDependencyEntity withContractFilePath(final String contractFilePath) {
		this.contractFilePath = contractFilePath;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SystemDependencyEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.sourceSystemId, that.sourceSystemId) &&
			Objects.equals(this.targetSystemId, that.targetSystemId) &&
			this.dependencyType == that.dependencyType &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.isCritical, that.isCritical) &&
			Objects.equals(this.documentationUrl, that.documentationUrl) &&
			Objects.equals(this.contractFilePath, that.contractFilePath);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.sourceSystemId, this.targetSystemId, this.dependencyType,
			this.description, this.isCritical, this.documentationUrl, this.contractFilePath);
	}

	@Override
	public String toString() {
		return "SystemDependencyEntity{" +
			"id='" + this.getId() + '\'' +
			", sourceSystemId='" + this.sourceSystemId + '\'' +
			", targetSystemId='" + this.targetSystemId + '\'' +
			", dependencyType=" + this.dependencyType +
			", description='" + this.description + '\'' +
			", isCritical=" + this.isCritical +
			", documentationUrl='" + this.documentationUrl + '\'' +
			", contractFilePath='" + this.contractFilePath + '\'' +
			'}';
	}
}
