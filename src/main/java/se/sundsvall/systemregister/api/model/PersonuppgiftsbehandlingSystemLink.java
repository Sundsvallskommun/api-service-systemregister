package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(description = "Link between processing activity and system")
public class PersonuppgiftsbehandlingSystemLink {

	@Schema(description = "Unique identifier for the processing activity", example = "behandling-001")
	private String behandlingId;

	@Schema(description = "System ID linked to this processing activity", example = "system-001")
	private String systemId;

	@Schema(description = "Description of the system link")
	private String description;

	public static PersonuppgiftsbehandlingSystemLink create() {
		return new PersonuppgiftsbehandlingSystemLink();
	}

	public String getBehandlingId() {
		return this.behandlingId;
	}

	public void setBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
	}

	public PersonuppgiftsbehandlingSystemLink withBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public PersonuppgiftsbehandlingSystemLink withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public PersonuppgiftsbehandlingSystemLink withDescription(final String description) {
		this.description = description;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final PersonuppgiftsbehandlingSystemLink that)) {
			return false;
		}
		return Objects.equals(this.behandlingId, that.behandlingId) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.behandlingId, this.systemId, this.description);
	}

	@Override
	public String toString() {
		return "PersonuppgiftsbehandlingSystemLink{" +
			"behandlingId='" + this.behandlingId + '\'' +
			", systemId='" + this.systemId + '\'' +
			", description='" + this.description + '\'' +
			'}';
	}
}
