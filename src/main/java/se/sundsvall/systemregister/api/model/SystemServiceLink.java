package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "System Service Link")
public class SystemServiceLink {

	@Schema(description = "Link ID", example = "link-1")
	private String id;

	@Schema(description = "System ID", example = "system-1")
	@NotBlank
	private String systemId;

	@Schema(description = "Service ID", example = "service-1")
	@NotBlank
	private String serviceId;

	@Schema(description = "Service direction", allowableValues = {
		"PROVIDED", "CONSUMED"
	})
	private String direction;

	@Schema(description = "Link description", example = "System provides email service")
	private String description;

	public static SystemServiceLink create() {
		return new SystemServiceLink();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public SystemServiceLink withId(final String id) {
		this.id = id;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public SystemServiceLink withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(final String serviceId) {
		this.serviceId = serviceId;
	}

	public SystemServiceLink withServiceId(final String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(final String direction) {
		this.direction = direction;
	}

	public SystemServiceLink withDirection(final String direction) {
		this.direction = direction;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SystemServiceLink withDescription(final String description) {
		this.description = description;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final SystemServiceLink that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.serviceId, that.serviceId) &&
			Objects.equals(this.direction, that.direction) &&
			Objects.equals(this.description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.systemId, this.serviceId, this.direction, this.description);
	}

	@Override
	public String toString() {
		return "SystemServiceLink{" +
			"id='" + this.id + '\'' +
			", systemId='" + this.systemId + '\'' +
			", serviceId='" + this.serviceId + '\'' +
			", direction='" + this.direction + '\'' +
			", description='" + this.description + '\'' +
			'}';
	}
}
