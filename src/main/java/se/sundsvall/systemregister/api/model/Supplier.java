package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Supplier")
public class Supplier {

	@Schema(description = "Supplier ID", example = "supplier-1")
	private String id;

	@Schema(description = "Supplier name", example = "Tech Solutions Inc")
	@NotBlank
	private String name;

	@Schema(description = "Organization number", example = "5565123456")
	private String orgNumber;

	@Schema(description = "Supplier description", example = "Provider of cloud infrastructure services")
	private String description;

	@Schema(description = "Website URL", example = "https://www.techsolutions.com")
	private String website;

	@Schema(description = "Contact email", example = "contact@techsolutions.com")
	private String contactEmail;

	@Schema(description = "Is supplier active", example = "true")
	private Boolean isActive;

	public static Supplier create() {
		return new Supplier();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Supplier withId(final String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Supplier withName(final String name) {
		this.name = name;
		return this;
	}

	public String getOrgNumber() {
		return this.orgNumber;
	}

	public void setOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Supplier withOrgNumber(final String orgNumber) {
		this.orgNumber = orgNumber;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Supplier withDescription(final String description) {
		this.description = description;
		return this;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(final String website) {
		this.website = website;
	}

	public Supplier withWebsite(final String website) {
		this.website = website;
		return this;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Supplier withContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
		return this;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public Supplier withIsActive(final Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Supplier that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.name, that.name) &&
			Objects.equals(this.orgNumber, that.orgNumber) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.website, that.website) &&
			Objects.equals(this.contactEmail, that.contactEmail) &&
			Objects.equals(this.isActive, that.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.orgNumber, this.description, this.website, this.contactEmail, this.isActive);
	}

	@Override
	public String toString() {
		return "Supplier{" +
			"id='" + this.id + '\'' +
			", name='" + this.name + '\'' +
			", orgNumber='" + this.orgNumber + '\'' +
			", description='" + this.description + '\'' +
			", website='" + this.website + '\'' +
			", contactEmail='" + this.contactEmail + '\'' +
			", isActive=" + this.isActive +
			'}';
	}
}
