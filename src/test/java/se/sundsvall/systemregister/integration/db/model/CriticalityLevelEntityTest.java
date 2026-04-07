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

class CriticalityLevelEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(CriticalityLevelEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var name = "name";
		final var code = "code";
		final var description = "description";
		final var level = 1;
		final var color = "color";
		final var isActive = true;

		final var result = CriticalityLevelEntity.create()
			.withName(name)
			.withCode(code)
			.withDescription(description)
			.withLevel(level)
			.withColor(color)
			.withIsActive(isActive);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getCode()).isEqualTo(code);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getLevel()).isEqualTo(level);
		assertThat(result.getColor()).isEqualTo(color);
		assertThat(result.getIsActive()).isEqualTo(isActive);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CriticalityLevelEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new CriticalityLevelEntity()).hasAllNullFieldsOrProperties();
	}
}
