package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "personuppgiftsbehandling_system_links")
public class PersonuppgiftsbehandlingSystemLinkEntity extends AbstractAuditableEntity {

	@Column(name = "behandling_id")
	private String behandlingId;

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "description")
	private String description;

	public static PersonuppgiftsbehandlingSystemLinkEntity create() {
		return new PersonuppgiftsbehandlingSystemLinkEntity();
	}

	public String getBehandlingId() {
		return this.behandlingId;
	}

	public void setBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
	}

	public PersonuppgiftsbehandlingSystemLinkEntity withBehandlingId(final String behandlingId) {
		this.behandlingId = behandlingId;
		return this;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(final String systemId) {
		this.systemId = systemId;
	}

	public PersonuppgiftsbehandlingSystemLinkEntity withSystemId(final String systemId) {
		this.systemId = systemId;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public PersonuppgiftsbehandlingSystemLinkEntity withDescription(final String description) {
		this.description = description;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final PersonuppgiftsbehandlingSystemLinkEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.behandlingId, that.behandlingId) &&
			Objects.equals(this.systemId, that.systemId) &&
			Objects.equals(this.description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.behandlingId, this.systemId, this.description);
	}

	@Override
	public String toString() {
		return "PersonuppgiftsbehandlingSystemLinkEntity{" +
			"id='" + this.getId() + '\'' +
			", behandlingId='" + this.behandlingId + '\'' +
			", systemId='" + this.systemId + '\'' +
			", description='" + this.description + '\'' +
			'}';
	}
}
