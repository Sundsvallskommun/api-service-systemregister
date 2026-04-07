package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
public abstract class AbstractAuditableEntity {

	@Id
	@UuidGenerator
	@Column(name = "id")
	private String id;

	@Column(name = "created_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime updatedAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@PrePersist
	protected void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
		this.createdAt = OffsetDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedAt = OffsetDateTime.now();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public AbstractAuditableEntity withId(final String id) {
		this.id = id;
		return this;
	}

	public OffsetDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public AbstractAuditableEntity withCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public OffsetDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public AbstractAuditableEntity withUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public AbstractAuditableEntity withCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public AbstractAuditableEntity withUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final AbstractAuditableEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.createdAt, that.createdAt) &&
			Objects.equals(this.updatedAt, that.updatedAt) &&
			Objects.equals(this.createdBy, that.createdBy) &&
			Objects.equals(this.updatedBy, that.updatedBy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.createdAt, this.updatedAt, this.createdBy, this.updatedBy);
	}

	@Override
	public String toString() {
		return "AbstractAuditableEntity{" +
			"id='" + this.id + '\'' +
			", createdAt=" + this.createdAt +
			", updatedAt=" + this.updatedAt +
			", createdBy='" + this.createdBy + '\'' +
			", updatedBy='" + this.updatedBy + '\'' +
			'}';
	}
}
