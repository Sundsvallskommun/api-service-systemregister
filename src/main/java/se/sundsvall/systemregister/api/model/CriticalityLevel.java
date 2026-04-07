package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Criticality Level")
public class CriticalityLevel {

	@Schema(description = "Criticality level ID", example = "critical-1")
	private String id;

	@Schema(description = "Level name", example = "Critical")
	@NotBlank
	private String name;

	@Schema(description = "Level code", example = "CRITICAL")
	private String code;

	@Schema(description = "Level description", example = "Systems critical to operations")
	private String description;

	@Schema(description = "Numeric level", example = "1")
	private Integer level;

	@Schema(description = "Color code for UI representation", example = "FF0000")
	private String color;

	@Schema(description = "Is level active", example = "true")
	private Boolean isActive;

	public static CriticalityLevel create() {
		return new CriticalityLevel();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public CriticalityLevel withId(final String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CriticalityLevel withName(final String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public CriticalityLevel withCode(final String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public CriticalityLevel withDescription(final String description) {
		this.description = description;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public CriticalityLevel withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	public CriticalityLevel withColor(final String color) {
		this.color = color;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public CriticalityLevel withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final CriticalityLevel that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.code, that.code) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.level, that.level) &&
			Objects.equals(this.color, that.color) &&
			Objects.equals(this.isActive, that.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.code, this.description, this.level, this.color, this.isActive);
	}

	@Override
	public String toString() {
		return "CriticalityLevel{" +
			"id='" + this.id + '\'' +
			", name='" + this.name + '\'' +
			", code='" + this.code + '\'' +
			", description='" + this.description + '\'' +
			", level=" + this.level +
			", color='" + this.color + '\'' +
			", isActive=" + this.isActive +
			'}';
	}
}
