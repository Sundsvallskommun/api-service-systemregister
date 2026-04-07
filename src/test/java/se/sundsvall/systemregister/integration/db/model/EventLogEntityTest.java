package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.integration.db.model.enums.EventType;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class EventLogEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(EventLogEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var entityType = EntityType.SYSTEM;
		final var entityId = "entityId";
		final var eventType = EventType.CREATED;
		final var changes = "changes";
		final var performedBy = "performedBy";
		final var performedAt = now();
		final var description = "description";
		final var acknowledgedAt = now().plusDays(1);

		final var result = EventLogEntity.create()
			.withId(id)
			.withEntityType(entityType)
			.withEntityId(entityId)
			.withEventType(eventType)
			.withChanges(changes)
			.withPerformedBy(performedBy)
			.withPerformedAt(performedAt)
			.withDescription(description)
			.withAcknowledgedAt(acknowledgedAt);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getEntityType()).isEqualTo(entityType);
		assertThat(result.getEntityId()).isEqualTo(entityId);
		assertThat(result.getEventType()).isEqualTo(eventType);
		assertThat(result.getChanges()).isEqualTo(changes);
		assertThat(result.getPerformedBy()).isEqualTo(performedBy);
		assertThat(result.getPerformedAt()).isEqualTo(performedAt);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getAcknowledgedAt()).isEqualTo(acknowledgedAt);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(EventLogEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new EventLogEntity()).hasAllNullFieldsOrProperties();
	}
}
