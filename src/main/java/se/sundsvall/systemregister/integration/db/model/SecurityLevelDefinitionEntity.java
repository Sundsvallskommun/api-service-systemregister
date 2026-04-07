package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "security_level_definitions")
public class SecurityLevelDefinitionEntity extends AbstractAuditableEntity {

	@Column(name = "dimension")
	private String dimension;

	@Column(name = "level")
	private Integer level;

	@Column(name = "label")
	private String label;

	@Column(name = "description")
	private String description;

	@Column(name = "consequence")
	private String consequence;

	@Column(name = "color")
	private String color;

	@Column(name = "is_active")
	private Boolean isActive;

	public static SecurityLevelDefinitionEntity create() {
		return new SecurityLevelDefinitionEntity();
	}

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(final String dimension) {
		this.dimension = dimension;
	}

	public SecurityLevelDefinitionEntity withDimension(final String dimension) {
		this.dimension = dimension;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public SecurityLevelDefinitionEntity withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public SecurityLevelDefinitionEntity withLabel(final String label) {
		this.label = label;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SecurityLevelDefinitionEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getConsequence() {
		return this.consequence;
	}

	public void setConsequence(final String consequence) {
		this.consequence = consequence;
	}

	public SecurityLevelDefinitionEntity withConsequence(final String consequence) {
		this.consequence = consequence;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	public SecurityLevelDefinitionEntity withColor(final String color) {
		this.color = color;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public SecurityLevelDefinitionEntity withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SecurityLevelDefinitionEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.dimension, that.dimension) &&
			Objects.equals(this.level, that.level) &&
			Objects.equals(this.label, that.label) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.consequence, that.consequence) &&
			Objects.equals(this.color, that.color) &&
			Objects.equals(this.isActive, that.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.dimension, this.level, this.label, this.description, this.consequence,
			this.color, this.isActive);
	}

	@Override
	public String toString() {
		return "SecurityLevelDefinitionEntity{" +
			"id='" + this.getId() + '\'' +
			", dimension='" + this.dimension + '\'' +
			", level=" + this.level +
			", label='" + this.label + '\'' +
			", description='" + this.description + '\'' +
			", consequence='" + this.consequence + '\'' +
			", color='" + this.color + '\'' +
			", isActive=" + this.isActive +
			'}';
	}
}
