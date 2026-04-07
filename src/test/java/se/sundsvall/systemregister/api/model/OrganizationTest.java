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

class OrganizationTest {

	@Test
	void testBean() {
		assertThat(Organization.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var name = "name";
		final var description = "description";
		final var orgNumber = "orgNumber";
		final var email = "email";
		final var phone = "phone";
		final var parentId = "parentId";
		final var level = 1;

		final var result = Organization.create()
			.withId(id)
			.withName(name)
			.withDescription(description)
			.withOrgNumber(orgNumber)
			.withEmail(email)
			.withPhone(phone)
			.withParentId(parentId)
			.withLevel(level);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getOrgNumber()).isEqualTo(orgNumber);
		assertThat(result.getEmail()).isEqualTo(email);
		assertThat(result.getPhone()).isEqualTo(phone);
		assertThat(result.getParentId()).isEqualTo(parentId);
		assertThat(result.getLevel()).isEqualTo(level);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Organization.create()).hasAllNullFieldsOrProperties();
		assertThat(new Organization()).hasAllNullFieldsOrProperties();
	}
}
