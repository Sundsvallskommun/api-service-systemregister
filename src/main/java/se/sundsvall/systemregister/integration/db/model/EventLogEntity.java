package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UuidGenerator;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.integration.db.model.enums.EventType;

@Entity
@Table(name = "event_logs")
public class EventLogEntity {

	@Id
	@UuidGenerator
	@Column(name = "id")
	private String id;

	@Column(name = "entity_type")
	@Enumerated(EnumType.STRING)
	private EntityType entityType;

	@Column(name = "entity_id")
	private String entityId;

	@Column(name = "event_type")
	@Enumerated(EnumType.STRING)
	private EventType eventType;

	@Lob
	@Column(name = "changes")
	private String changes;

	@Column(name = "performed_by")
	private String performedBy;

	@Column(name = "performed_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime performedAt;

	@Column(name = "description")
	private String description;

	@Column(name = "acknowledged_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime acknowledgedAt;

	@PrePersist
	protected void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
		if (this.performedAt == null) {
			this.performedAt = OffsetDateTime.now();
		}
	}

	public static EventLogEntity create() {
		return new EventLogEntity();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public EventLogEntity withId(final String id) {
		this.id = id;
		return this;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public void setEntityType(final EntityType entityType) {
		this.entityType = entityType;
	}

	public EventLogEntity withEntityType(final EntityType entityType) {
		this.entityType = entityType;
		return this;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(final String entityId) {
		this.entityId = entityId;
	}

	public EventLogEntity withEntityId(final String entityId) {
		this.entityId = entityId;
		return this;
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public void setEventType(final EventType eventType) {
		this.eventType = eventType;
	}

	public EventLogEntity withEventType(final EventType eventType) {
		this.eventType = eventType;
		return this;
	}

	public String getChanges() {
		return this.changes;
	}

	public void setChanges(final String changes) {
		this.changes = changes;
	}

	public EventLogEntity withChanges(final String changes) {
		this.changes = changes;
		return this;
	}

	public String getPerformedBy() {
		return this.performedBy;
	}

	public void setPerformedBy(final String performedBy) {
		this.performedBy = performedBy;
	}

	public EventLogEntity withPerformedBy(final String performedBy) {
		this.performedBy = performedBy;
		return this;
	}

	public OffsetDateTime getPerformedAt() {
		return this.performedAt;
	}

	public void setPerformedAt(final OffsetDateTime performedAt) {
		this.performedAt = performedAt;
	}

	public EventLogEntity withPerformedAt(final OffsetDateTime performedAt) {
		this.performedAt = performedAt;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public EventLogEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	public OffsetDateTime getAcknowledgedAt() {
		return this.acknowledgedAt;
	}

	public void setAcknowledgedAt(final OffsetDateTime acknowledgedAt) {
		this.acknowledgedAt = acknowledgedAt;
	}

	public EventLogEntity withAcknowledgedAt(final OffsetDateTime acknowledgedAt) {
		this.acknowledgedAt = acknowledgedAt;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final EventLogEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			this.entityType == that.entityType &&
			Objects.equals(this.entityId, that.entityId) &&
			this.eventType == that.eventType &&
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
		return "EventLogEntity{" +
			"id='" + this.id + '\'' +
			", entityType=" + this.entityType +
			", entityId='" + this.entityId + '\'' +
			", eventType=" + this.eventType +
			", changes='" + this.changes + '\'' +
			", performedBy='" + this.performedBy + '\'' +
			", performedAt=" + this.performedAt +
			", description='" + this.description + '\'' +
			", acknowledgedAt=" + this.acknowledgedAt +
			'}';
	}
}
