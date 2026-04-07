package se.sundsvall.systemregister.api.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonTest {

	@Test
	void testBean() {
		assertThat(Person.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var firstName = "firstName";
		final var lastName = "lastName";
		final var email = "email";
		final var phone = "phone";
		final var title = "title";
		final var username = "username";
		final var organizationId = "organizationId";

		final var result = Person.create()
			.withId(id)
			.withFirstName(firstName)
			.withLastName(lastName)
			.withEmail(email)
			.withPhone(phone)
			.withTitle(title)
			.withUsername(username)
			.withOrganizationId(organizationId);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getFirstName()).isEqualTo(firstName);
		assertThat(result.getLastName()).isEqualTo(lastName);
		assertThat(result.getEmail()).isEqualTo(email);
		assertThat(result.getPhone()).isEqualTo(phone);
		assertThat(result.getTitle()).isEqualTo(title);
		assertThat(result.getUsername()).isEqualTo(username);
		assertThat(result.getOrganizationId()).isEqualTo(organizationId);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Person.create()).hasAllNullFieldsOrProperties();
		assertThat(new Person()).hasAllNullFieldsOrProperties();
	}
}
