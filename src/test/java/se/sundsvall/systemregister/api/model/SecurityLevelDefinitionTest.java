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

class SecurityLevelDefinitionTest {

	@Test
	void testBean() {
		assertThat(SecurityLevelDefinition.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id-1";
		final var dimension = "dimension";
		final var level = 2;
		final var label = "label";
		final var description = "description";
		final var consequence = "consequence";
		final var color = "color";
		final var isActive = true;

		final var result = SecurityLevelDefinition.create()
			.withId(id)
			.withDimension(dimension)
			.withLevel(level)
			.withLabel(label)
			.withDescription(description)
			.withConsequence(consequence)
			.withColor(color)
			.withIsActive(isActive);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(SecurityLevelDefinition.create()).hasAllNullFieldsOrProperties();
		assertThat(new SecurityLevelDefinition()).hasAllNullFieldsOrProperties();
	}
}
