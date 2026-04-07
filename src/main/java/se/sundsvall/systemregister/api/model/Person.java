package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Person")
public class Person {

	@Schema(description = "Person ID", example = "person-1")
	private String id;

	@Schema(description = "First name", example = "John")
	@NotBlank
	private String firstName;

	@Schema(description = "Last name", example = "Doe")
	@NotBlank
	private String lastName;

	@Schema(description = "Email address", example = "john.doe@example.com")
	@Email
	private String email;

	@Schema(description = "Phone number", example = "+46701234567")
	private String phone;

	@Schema(description = "Job title", example = "System Administrator")
	private String title;

	@Schema(description = "Username", example = "jdoe")
	private String username;

	@Schema(description = "Organization ID", example = "org-1")
	private String organizationId;

	public static Person create() {
		return new Person();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Person withId(final String id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public Person withFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Person withLastName(final String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Person withEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Person withPhone(final String phone) {
		this.phone = phone;
		return this;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Person withTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Person withUsername(final String username) {
		this.username = username;
		return this;
	}

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(final String organizationId) {
		this.organizationId = organizationId;
	}

	public Person withOrganizationId(final String organizationId) {
		this.organizationId = organizationId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Person that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
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
		return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.phone, this.title, this.username, this.organizationId);
	}

	@Override
	public String toString() {
		return "Person{" +
			"id='" + this.id + '\'' +
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
