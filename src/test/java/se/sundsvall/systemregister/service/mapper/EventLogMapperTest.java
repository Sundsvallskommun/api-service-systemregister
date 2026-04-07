package se.sundsvall.systemregister.service.mapper;

import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.integration.db.model.EventLogEntity;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.integration.db.model.enums.EventType;

import static org.assertj.core.api.Assertions.assertThat;

class EventLogMapperTest {

	@Test
	void toEventLog() {
		final var now = OffsetDateTime.now();
		final var entity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM)
			.withEntityId("entity-id")
			.withEventType(EventType.CREATED)
			.withChanges("changes")
			.withPerformedBy("user@example.com")
			.withPerformedAt(now)
			.withDescription("description")
			.withAcknowledgedAt(now);

		final var result = EventLogMapper.toEventLog(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo("SYSTEM");
		assertThat(result.getEntityId()).isEqualTo("entity-id");
		assertThat(result.getEventType()).isEqualTo("CREATED");
		assertThat(result.getChanges()).isEqualTo("changes");
		assertThat(result.getPerformedBy()).isEqualTo("user@example.com");
		assertThat(result.getPerformedAt()).isEqualTo(now);
		assertThat(result.getDescription()).isEqualTo("description");
		assertThat(result.getAcknowledgedAt()).isEqualTo(now);
	}

	@Test
	void toEventLogNull() {
		final var result = EventLogMapper.toEventLog(null);

		assertThat(result).isNull();
	}

	@Test
	void toEventLogPartialEntity() {
		final var entity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM);

		final var result = EventLogMapper.toEventLog(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo("SYSTEM");
		assertThat(result.getEntityId()).isNull();
		assertThat(result.getEventType()).isNull();
	}

	@Test
	void toEventLogs() {
		final var entity1 = EventLogEntity.create()
			.withId("id-1")
			.withEntityType(EntityType.SYSTEM);
		final var entity2 = EventLogEntity.create()
			.withId("id-2")
			.withEntityType(EntityType.SERVICE);

		final var result = EventLogMapper.toEventLogs(List.of(entity1, entity2));

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.getFirst().getEntityType()).isEqualTo("SYSTEM");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		assertThat(result.get(1).getEntityType()).isEqualTo("SERVICE");
	}

	@Test
	void toEventLogsEmptyList() {
		final var result = EventLogMapper.toEventLogs(List.of());

		assertThat(result).isNotNull().isEmpty();
	}

	@Test
	void toEventLogsNull() {
		final var result = EventLogMapper.toEventLogs(null);

		assertThat(result).isNull();
	}

	@Test
	void toEventLogEntity() {
		final var now = OffsetDateTime.now();
		final var model = EventLog.create()
			.withId("id-123")
			.withEntityType("SYSTEM")
			.withEntityId("entity-id")
			.withEventType("CREATED")
			.withChanges("changes")
			.withPerformedBy("user@example.com")
			.withPerformedAt(now)
			.withDescription("description")
			.withAcknowledgedAt(now);

		final var result = EventLogMapper.toEventLogEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo(EntityType.SYSTEM);
		assertThat(result.getEntityId()).isEqualTo("entity-id");
		assertThat(result.getEventType()).isEqualTo(EventType.CREATED);
		assertThat(result.getChanges()).isEqualTo("changes");
		assertThat(result.getPerformedBy()).isEqualTo("user@example.com");
		assertThat(result.getPerformedAt()).isEqualTo(now);
		assertThat(result.getDescription()).isEqualTo("description");
		assertThat(result.getAcknowledgedAt()).isEqualTo(now);
	}

	@Test
	void toEventLogEntityNull() {
		final var result = EventLogMapper.toEventLogEntity(null);

		assertThat(result).isNull();
	}

	@Test
	void toEventLogEntityInvalidEnumValues() {
		final var model = EventLog.create()
			.withId("id-123")
			.withEntityType("INVALID_TYPE")
			.withEventType("INVALID_EVENT");

		final var result = EventLogMapper.toEventLogEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isNull();
		assertThat(result.getEventType()).isNull();
	}

	@Test
	void toEventLogEntityPartialModel() {
		final var model = EventLog.create()
			.withId("id-123")
			.withEntityType("SYSTEM");

		final var result = EventLogMapper.toEventLogEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo(EntityType.SYSTEM);
		assertThat(result.getEntityId()).isNull();
		assertThat(result.getEventType()).isNull();
	}
}
