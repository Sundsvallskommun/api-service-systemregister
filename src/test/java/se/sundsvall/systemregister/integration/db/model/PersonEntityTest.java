package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(PersonEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var firstName = "firstName";
		final var lastName = "lastName";
		final var email = "email";
		final var phone = "phone";
		final var title = "title";
		final var username = "username";
		final var organizationId = "organizationId";

		final var result = PersonEntity.create()
			.withFirstName(firstName)
			.withLastName(lastName)
			.withEmail(email)
			.withPhone(phone)
			.withTitle(title)
			.withUsername(username)
			.withOrganizationId(organizationId);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
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
		assertThat(PersonEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new PersonEntity()).hasAllNullFieldsOrProperties();
	}
}
