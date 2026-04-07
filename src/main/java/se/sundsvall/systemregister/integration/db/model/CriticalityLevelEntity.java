package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "criticality_levels")
public class CriticalityLevelEntity extends AbstractAuditableEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "level")
	private Integer level;

	@Column(name = "color")
	private String color;

	@Column(name = "is_active")
	private Boolean isActive;

	public static CriticalityLevelEntity create() {
		return new CriticalityLevelEntity();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CriticalityLevelEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public CriticalityLevelEntity withCode(final String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public CriticalityLevelEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public CriticalityLevelEntity withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	public CriticalityLevelEntity withColor(final String color) {
		this.color = color;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public CriticalityLevelEntity withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final CriticalityLevelEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.code, that.code) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.level, that.level) &&
			Objects.equals(this.color, that.color) &&
			Objects.equals(this.isActive, that.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.name, this.code, this.description, this.level, this.color, this.isActive);
	}

	@Override
	public String toString() {
		return "CriticalityLevelEntity{" +
			"id='" + this.getId() + '\'' +
			", name='" + this.name + '\'' +
			", code='" + this.code + '\'' +
			", description='" + this.description + '\'' +
			", level=" + this.level +
			", color='" + this.color + '\'' +
			", isActive=" + this.isActive +
			'}';
	}
}
