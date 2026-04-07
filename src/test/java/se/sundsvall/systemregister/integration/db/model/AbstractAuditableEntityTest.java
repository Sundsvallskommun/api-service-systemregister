package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class AbstractAuditableEntityTest {

	private static class TestEntity extends AbstractAuditableEntity {

		public static TestEntity create() {
			return new TestEntity();
		}
	}

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(TestEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id-1";
		final var createdAt = now();
		final var updatedAt = now().plusDays(1);
		final var createdBy = "createdBy";
		final var updatedBy = "updatedBy";

		final var result = TestEntity.create();
		result.withId(id);
		result.withCreatedAt(createdAt);
		result.withUpdatedAt(updatedAt);
		result.withCreatedBy(createdBy);
		result.withUpdatedBy(updatedBy);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getCreatedAt()).isEqualTo(createdAt);
		assertThat(result.getUpdatedAt()).isEqualTo(updatedAt);
		assertThat(result.getCreatedBy()).isEqualTo(createdBy);
		assertThat(result.getUpdatedBy()).isEqualTo(updatedBy);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(TestEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new TestEntity()).hasAllNullFieldsOrProperties();
	}

	@Test
	void prePersist() {
		final var entity = TestEntity.create();
		entity.prePersist();

		assertThat(entity.getId()).isNotNull();
		assertThat(entity.getCreatedAt()).isNotNull();
		assertThat(entity.getUpdatedAt()).isNotNull();
		assertThat(entity.getCreatedAt()).isEqualTo(entity.getUpdatedAt());
	}

	@Test
	void prePersistWithExistingId() {
		final var entity = TestEntity.create();
		entity.withId("existing-id");
		entity.prePersist();

		assertThat(entity.getId()).isEqualTo("existing-id");
	}

	@Test
	void preUpdate() {
		final var entity = TestEntity.create();
		entity.prePersist();
		final var originalUpdatedAt = entity.getUpdatedAt();

		entity.preUpdate();

		assertThat(entity.getUpdatedAt()).isNotNull();
		assertThat(entity.getUpdatedAt()).isAfterOrEqualTo(originalUpdatedAt);
	}
}
