package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Organization")
public class Organization {

	@Schema(description = "Organization ID", example = "org-1")
	private String id;

	@Schema(description = "Organization name", example = "IT Department")
	@NotBlank
	private String name;

	@Schema(description = "Organization description", example = "Central IT department responsible for systems")
	private String description;

	@Schema(description = "Organization number", example = "2021001234")
	private String orgNumber;

	@Schema(description = "Email address", example = "it@example.com")
	private String email;

	@Schema(description = "Phone number", example = "+46701234567")
	private String phone;

	@Schema(description = "Parent organization ID", example = "org-parent")
	private String parentId;

	@Schema(description = "Organization level in hierarchy", example = "1")
	private Integer level;

	public static Organization create() {
		return new Organization();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Organization withId(final String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Organization withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Organization withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getOrgNumber() {
		return this.orgNumber;
	}

	public void setOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Organization withOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Organization withEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Organization withPhone(final String phone) {
		this.phone = phone;
		return this;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}

	public Organization withParentId(final String parentId) {
		this.parentId = parentId;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public Organization withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Organization that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.orgNumber, that.orgNumber) &&
			Objects.equals(this.email, that.email) &&
			Objects.equals(this.phone, that.phone) &&
			Objects.equals(this.parentId, that.parentId) &&
			Objects.equals(this.level, that.level);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description, this.orgNumber, this.email, this.phone, this.parentId, this.level);
	}

	@Override
	public String toString() {
		return "Organization{" +
			"id='" + this.id + '\'' +
			", name='" + this.name + '\'' +
			", description='" + this.description + '\'' +
			", orgNumber='" + this.orgNumber + '\'' +
			", email='" + this.email + '\'' +
			", phone='" + this.phone + '\'' +
			", parentId='" + this.parentId + '\'' +
			", level=" + this.level +
			'}';
	}
}
