package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Objects;

@Schema(description = "Event log entry")
public class EventLog {

	@Schema(description = "Event log ID", example = "550e8400-e29b-41d4-a716-446655440000")
	private String id;

	@Schema(description = "Entity type", example = "SYSTEM", allowableValues = {
		"SYSTEM", "SERVICE", "PERSONUPPGIFTSBEHANDLING", "AI_APPLICATION"
	})
	private String entityType;

	@Schema(description = "Entity ID", example = "sys-123")
	private String entityId;

	@Schema(description = "Event type", example = "CREATED", allowableValues = {
		"CREATED", "UPDATED", "DELETED", "PERSON_UNLINKED"
	})
	private String eventType;

	@Schema(description = "Changes made (JSON format)")
	private String changes;

	@Schema(description = "User who performed the action", example = "user@example.com")
	private String performedBy;

	@Schema(description = "Timestamp when the action was performed")
	private OffsetDateTime performedAt;

	@Schema(description = "Description of the event", example = "System status changed to active")
	private String description;

	@Schema(description = "Timestamp when the event was acknowledged")
	private OffsetDateTime acknowledgedAt;

	public static EventLog create() {
		return new EventLog();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public EventLog withId(final String id) {
		this.id = id;
		return this;
	}

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(final String entityType) {
		this.entityType = entityType;
	}

	public EventLog withEntityType(final String entityType) {
		this.entityType = entityType;
		return this;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(final String entityId) {
		this.entityId = entityId;
	}

	public EventLog withEntityId(final String entityId) {
		this.entityId = entityId;
		return this;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	public EventLog withEventType(final String eventType) {
		this.eventType = eventType;
		return this;
	}

	public String getChanges() {
		return this.changes;
	}

	public void setChanges(final String changes) {
		this.changes = changes;
	}

	public EventLog withChanges(final String changes) {
		this.changes = changes;
		return this;
	}

	public String getPerformedBy() {
		return this.performedBy;
	}

	public void setPerformedBy(final String performedBy) {
		this.performedBy = performedBy;
	}

	public EventLog withPerformedBy(final String performedBy) {
		this.performedBy = performedBy;
		return this;
	}

	public OffsetDateTime getPerformedAt() {
		return this.performedAt;
	}

	public void setPerformedAt(final OffsetDateTime performedAt) {
		this.performedAt = performedAt;
	}

	public EventLog withPerformedAt(final OffsetDateTime performedAt) {
		this.performedAt = performedAt;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public EventLog withDescription(final String description) {
		this.description = description;
		return this;
	}

	public OffsetDateTime getAcknowledgedAt() {
		return this.acknowledgedAt;
	}

	public void setAcknowledgedAt(final OffsetDateTime acknowledgedAt) {
		this.acknowledgedAt = acknowledgedAt;
	}

	public EventLog withAcknowledgedAt(final OffsetDateTime acknowledgedAt) {
		this.acknowledgedAt = acknowledgedAt;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final EventLog that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.entityType, that.entityType) &&
			Objects.equals(this.entityId, that.entityId) &&
			Objects.equals(this.eventType, that.eventType) &&
			Objects.equals(this.changes, that.changes) &&
			Objects.equals(this.performedBy, that.performedBy) &&
			Objects.equals(this.performedAt, that.performedAt) &&
			Objects.equals(this.description, that.description) &&
			Objects.equals(this.acknowledgedAt, that.acknowledgedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.entityType, this.entityId, this.eventType, this.changes,
			this.performedBy, this.performedAt, this.description, this.acknowledgedAt);
	}

	@Override
	public String toString() {
		return "EventLog{" +
			"id='" + this.id + '\'' +
			", entityType='" + this.entityType + '\'' +
			", entityId='" + this.entityId + '\'' +
			", eventType='" + this.eventType + '\'' +
			", changes='" + this.changes + '\'' +
			", performedBy='" + this.performedBy + '\'' +
			", performedAt=" + this.performedAt +
			", description='" + this.description + '\'' +
			", acknowledgedAt=" + this.acknowledgedAt +
			'}';
	}
}
