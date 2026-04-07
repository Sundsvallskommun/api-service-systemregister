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

class CriticalityLevelTest {

	@Test
	void testBean() {
		assertThat(CriticalityLevel.class, allOf(
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
		final var code = "code";
		final var description = "description";
		final var level = 1;
		final var color = "color";
		final var isActive = true;

		final var result = CriticalityLevel.create()
			.withId(id)
			.withName(name)
			.withCode(code)
			.withDescription(description)
			.withLevel(level)
			.withColor(color)
			.withIsActive(isActive);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getCode()).isEqualTo(code);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getLevel()).isEqualTo(level);
		assertThat(result.getColor()).isEqualTo(color);
		assertThat(result.getIsActive()).isEqualTo(isActive);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CriticalityLevel.create()).hasAllNullFieldsOrProperties();
		assertThat(new CriticalityLevel()).hasAllNullFieldsOrProperties();
	}
}
