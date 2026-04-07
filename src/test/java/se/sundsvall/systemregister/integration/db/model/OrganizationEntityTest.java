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

class OrganizationEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(OrganizationEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var name = "name";
		final var description = "description";
		final var orgNumber = "orgNumber";
		final var email = "email";
		final var phone = "phone";
		final var parentId = "parentId";
		final var level = 1;

		final var result = OrganizationEntity.create()
			.withName(name)
			.withDescription(description)
			.withOrgNumber(orgNumber)
			.withEmail(email)
			.withPhone(phone)
			.withParentId(parentId)
			.withLevel(level);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
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
		assertThat(OrganizationEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new OrganizationEntity()).hasAllNullFieldsOrProperties();
	}
}
