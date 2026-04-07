package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class PersonEntity extends AbstractAuditableEntity {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "title")
	private String title;

	@Column(name = "username")
	private String username;

	@Column(name = "organization_id")
	private String organizationId;

	public static PersonEntity create() {
		return new PersonEntity();
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public PersonEntity withFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public PersonEntity withLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public PersonEntity withEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public PersonEntity withPhone(final String phone) {
		this.phone = phone;
		return this;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public PersonEntity withTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public PersonEntity withUsername(final String username) {
		this.username = username;
		return this;
	}

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(final String organizationId) {
		this.organizationId = organizationId;
	}

	public PersonEntity withOrganizationId(final String organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final PersonEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.firstName, that.firstName) &&
			Objects.equals(this.lastName, that.lastName) &&
			Objects.equals(this.email, that.email) &&
			Objects.equals(this.phone, that.phone) &&
			Objects.equals(this.title, that.title) &&
			Objects.equals(this.username, that.username) &&
			Objects.equals(this.organizationId, that.organizationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.firstName, this.lastName, this.email, this.phone, this.title, this.username, this.organizationId);
	}

	@Override
	public String toString() {
		return "PersonEntity{" +
			"id='" + this.getId() + '\'' +
			", firstName='" + this.firstName + '\'' +
			", lastName='" + this.lastName + '\'' +
			", email='" + this.email + '\'' +
			", phone='" + this.phone + '\'' +
			", title='" + this.title + '\'' +
			", username='" + this.username + '\'' +
			", organizationId='" + this.organizationId + '\'' +
			'}';
	}
}
