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

class SecurityLevelDefinitionEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(SecurityLevelDefinitionEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var dimension = "dimension";
		final var level = 2;
		final var label = "label";
		final var description = "description";
		final var consequence = "consequence";
		final var color = "color";
		final var isActive = true;

		final var result = SecurityLevelDefinitionEntity.create()
			.withDimension(dimension)
			.withLevel(level)
			.withLabel(label)
			.withDescription(description)
			.withConsequence(consequence)
			.withColor(color)
			.withIsActive(isActive);

		result.withId("id");

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getId()).isEqualTo("id");
		assertThat(result.getDimension()).isEqualTo(dimension);
		assertThat(result.getLevel()).isEqualTo(level);
		assertThat(result.getLabel()).isEqualTo(label);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getConsequence()).isEqualTo(consequence);
		assertThat(result.getColor()).isEqualTo(color);
		assertThat(result.getIsActive()).isEqualTo(isActive);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(SecurityLevelDefinitionEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new SecurityLevelDefinitionEntity()).hasAllNullFieldsOrProperties();
	}
}
