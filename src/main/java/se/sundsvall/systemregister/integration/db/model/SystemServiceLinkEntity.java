package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.ServiceDirection;

@Entity
@Table(name = "system_service_links")
public class SystemServiceLinkEntity extends AbstractAuditableEntity {

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "service_id")
	private String serviceId;

	@Column(name = "direction")
	@Enumerated(EnumType.STRING)
	private ServiceDirection direction;

	@Column(name = "description")
	private String description;

	public static SystemServiceLinkEntity create() {
		return new SystemServiceLinkEntity();
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public SystemServiceLinkEntity withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	public SystemServiceLinkEntity withServiceId(final String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public ServiceDirection getDirection() {
		return this.direction;
	}

	public void setDirection(final ServiceDirection direction) {
		this.direction = direction;
	}

	public SystemServiceLinkEntity withDirection(final ServiceDirection direction) {
		this.direction = direction;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SystemServiceLinkEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SystemServiceLinkEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.serviceId, that.serviceId) &&
			this.direction == that.direction &&
			Objects.equals(this.description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.systemId, this.serviceId, this.direction, this.description);
	}

	@Override
	public String toString() {
		return "SystemServiceLinkEntity{" +
			"id='" + this.getId() + '\'' +
			", systemId='" + this.systemId + '\'' +
			", serviceId='" + this.serviceId + '\'' +
			", direction=" + this.direction +
			", description='" + this.description + '\'' +
			'}';
	}
}
