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

class SupplierTest {

	@Test
	void testBean() {
		assertThat(Supplier.class, allOf(
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
		final var orgNumber = "orgNumber";
		final var description = "description";
		final var website = "website";
		final var contactEmail = "contactEmail";
		final var isActive = true;

		final var result = Supplier.create()
			.withId(id)
			.withName(name)
			.withOrgNumber(orgNumber)
			.withDescription(description)
			.withWebsite(website)
			.withContactEmail(contactEmail)
			.withIsActive(isActive);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getOrgNumber()).isEqualTo(orgNumber);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getWebsite()).isEqualTo(website);
		assertThat(result.getContactEmail()).isEqualTo(contactEmail);
		assertThat(result.getIsActive()).isEqualTo(isActive);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Supplier.create()).hasAllNullFieldsOrProperties();
		assertThat(new Supplier()).hasAllNullFieldsOrProperties();
	}
}
