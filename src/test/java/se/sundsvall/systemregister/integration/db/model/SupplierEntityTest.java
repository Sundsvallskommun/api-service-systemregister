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

class SupplierEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(SupplierEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var name = "name";
		final var orgNumber = "orgNumber";
		final var description = "description";
		final var website = "website";
		final var contactEmail = "contactEmail";
		final var isActive = true;

		final var result = SupplierEntity.create()
			.withName(name)
			.withOrgNumber(orgNumber)
			.withDescription(description)
			.withWebsite(website)
			.withContactEmail(contactEmail)
			.withIsActive(isActive);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getOrgNumber()).isEqualTo(orgNumber);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getWebsite()).isEqualTo(website);
		assertThat(result.getContactEmail()).isEqualTo(contactEmail);
		assertThat(result.getIsActive()).isEqualTo(isActive);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(SupplierEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new SupplierEntity()).hasAllNullFieldsOrProperties();
	}
}
