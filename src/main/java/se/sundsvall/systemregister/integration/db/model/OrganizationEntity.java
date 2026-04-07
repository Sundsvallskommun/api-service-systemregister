package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "organizations")
public class OrganizationEntity extends AbstractAuditableEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "org_number")
	private String orgNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "parent_id")
	private String parentId;

	@Column(name = "level")
	private Integer level;

	public static OrganizationEntity create() {
		return new OrganizationEntity();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public OrganizationEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public OrganizationEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getOrgNumber() {
		return this.orgNumber;
	}

	public void setOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public OrganizationEntity withOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public OrganizationEntity withEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public OrganizationEntity withPhone(final String phone) {
		this.phone = phone;
		return this;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(final String parentId) {
		this.parentId = parentId;
	}

	public OrganizationEntity withParentId(final String parentId) {
		this.parentId = parentId;
		return this;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(final Integer level) {
		this.level = level;
	}

	public OrganizationEntity withLevel(final Integer level) {
		this.level = level;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final OrganizationEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
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
		return Objects.hash(this.getId(), this.name, this.description, this.orgNumber, this.email, this.phone, this.parentId, this.level);
	}

	@Override
	public String toString() {
		return "OrganizationEntity{" +
			"id='" + this.getId() + '\'' +
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
