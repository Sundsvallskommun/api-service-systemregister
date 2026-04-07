package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Schema(description = "Security level definition")
public class SecurityLevelDefinition {

	@Schema(description = "Definition ID", example = "cb20c51f-fcf3-42c0-b613-de563634a8ec")
	private String id;

	@Schema(description = "Security dimension", example = "K", allowableValues = {
		"K", "R", "T"
	})
	@NotBlank
	private String dimension;

	@Schema(description = "Security level (0-4)", example = "2")
	@NotNull
	private Integer level;

	@Schema(description = "Display label", example = "Medium")
	private String label;

	@Schema(description = "Description of the security level", example = "Medium security protection")
	private String description;

	@Schema(description = "Consequence of this level", example = "Moderate risk of system compromise")
	private String consequence;

	@Schema(description = "Color code for visualization", example = "#FFAA00")
	private String color;

	@Schema(description = "Whether this definition is active")
	private Boolean isActive;

	public static SecurityLevelDefinition create() {
		return new SecurityLevelDefinition();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public SecurityLevelDefinition withId(final String id) {
		this.id = id;
		return this;
	}

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(final String dimension) {
		this.dimension = dimension;
	}

	public SecurityLevelDefinition withDimension(final String dimension) {
		this.dimension = dimension;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public SecurityLevelDefinition withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public SecurityLevelDefinition withLabel(final String label) {
		this.label = label;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SecurityLevelDefinition withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getConsequence() {
		return this.consequence;
	}

	public void setConsequence(final String consequence) {
		this.consequence = consequence;
	}

	public SecurityLevelDefinition withConsequence(final String consequence) {
		this.consequence = consequence;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	public SecurityLevelDefinition withColor(final String color) {
		this.color = color;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public SecurityLevelDefinition withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SecurityLevelDefinition that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
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
		return Objects.hash(this.id, this.dimension, this.level, this.label, this.description, this.consequence,
			this.color, this.isActive);
	}

	@Override
	public String toString() {
		return "SecurityLevelDefinition{" +
			"id=" + this.id +
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
