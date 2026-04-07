package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
public class SupplierEntity extends AbstractAuditableEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "org_number")
	private String orgNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "website")
	private String website;

	@Column(name = "contact_email")
	private String contactEmail;

	@Column(name = "is_active")
	private Boolean isActive;

	public static SupplierEntity create() {
		return new SupplierEntity();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public SupplierEntity withName(final String name) {
		this.name = name;
		return this;
	}

	public String getOrgNumber() {
		return this.orgNumber;
	}

	public void setOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public SupplierEntity withOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SupplierEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(final String website) {
		this.website = website;
	}

	public SupplierEntity withWebsite(final String website) {
		this.website = website;
		return this;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public SupplierEntity withContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public SupplierEntity withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SupplierEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.orgNumber, that.orgNumber) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.website, that.website) &&
			Objects.equals(this.contactEmail, that.contactEmail) &&
			Objects.equals(this.isActive, that.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.name, this.orgNumber, this.description, this.website, this.contactEmail, this.isActive);
	}

	@Override
	public String toString() {
		return "SupplierEntity{" +
			"id='" + this.getId() + '\'' +
			", name='" + this.name + '\'' +
			", orgNumber='" + this.orgNumber + '\'' +
			", description='" + this.description + '\'' +
			", website='" + this.website + '\'' +
			", contactEmail='" + this.contactEmail + '\'' +
			", isActive=" + this.isActive +
			'}';
	}
}
