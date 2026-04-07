package se.sundsvall.systemregister.service.mapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.integration.db.model.EventLogEntity;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.integration.db.model.enums.EventType;

public final class EventLogMapper {

	private EventLogMapper() {}

	public static EventLog toEventLog(final EventLogEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> EventLog.create()
				.withId(e.getId())
				.withEntityType(Optional.ofNullable(e.getEntityType()).map(EntityType::toString).orElse(null))
				.withEntityId(e.getEntityId())
				.withEventType(Optional.ofNullable(e.getEventType()).map(EventType::toString).orElse(null))
				.withChanges(e.getChanges())
				.withPerformedBy(e.getPerformedBy())
				.withPerformedAt(e.getPerformedAt())
				.withDescription(e.getDescription())
				.withAcknowledgedAt(e.getAcknowledgedAt()))
			.orElse(null);
	}

	public static List<EventLog> toEventLogs(final List<EventLogEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(EventLogMapper::toEventLog)
				.toList())
			.orElse(null);
	}

	public static EventLogEntity acknowledgeEntity(final EventLogEntity entity) {
		return entity.withAcknowledgedAt(OffsetDateTime.now());
	}

	public static EventLogEntity toEventLogEntity(final EventLog model) {
		return Optional.ofNullable(model)
			.map(m -> EventLogEntity.create()
				.withId(m.getId())
				.withEntityType(Optional.ofNullable(m.getEntityType())
					.flatMap(et -> {
						try {
							return Optional.of(EntityType.valueOf(et));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withEntityId(m.getEntityId())
				.withEventType(Optional.ofNullable(m.getEventType())
					.flatMap(evt -> {
						try {
							return Optional.of(EventType.valueOf(evt));
						} catch (final IllegalArgumentException _) {
							return Optional.empty();
						}
					})
					.orElse(null))
				.withChanges(m.getChanges())
				.withPerformedBy(m.getPerformedBy())
				.withPerformedAt(m.getPerformedAt())
				.withDescription(m.getDescription())
				.withAcknowledgedAt(m.getAcknowledgedAt()))
			.orElse(null);
	}
}
