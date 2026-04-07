package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;

@Entity
@Table(name = "informationshanteringsplaner")
public class InformationshanteringsplanEntity extends AbstractAuditableEntity {

	@Column(name = "namn")
	private String namn;

	@Column(name = "version")
	private String version;

	@Column(name = "beskrivning")
	private String beskrivning;

	@Column(name = "organisation_id")
	private String organisationId;

	@Column(name = "giltig_fran")
	private LocalDate giltigFran;

	@Column(name = "giltig_tom")
	private LocalDate giltigTom;

	@Column(name = "beslutsdatum")
	private LocalDate beslutsdatum;

	@Column(name = "beslutsparagraf")
	private String beslutsparagraf;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private IHPlanStatus status;

	public static InformationshanteringsplanEntity create() {
		return new InformationshanteringsplanEntity();
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public InformationshanteringsplanEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public InformationshanteringsplanEntity withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public InformationshanteringsplanEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
	}

	public InformationshanteringsplanEntity withOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
		return this;
	}

	public LocalDate getGiltigFran() {
		return this.giltigFran;
	}

	public void setGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
	}

	public InformationshanteringsplanEntity withGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
		return this;
	}

	public LocalDate getGiltigTom() {
		return this.giltigTom;
	}

	public void setGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
	}

	public InformationshanteringsplanEntity withGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
		return this;
	}

	public LocalDate getBeslutsdatum() {
		return this.beslutsdatum;
	}

	public void setBeslutsdatum(final LocalDate beslutsdatum) {
		this.beslutsdatum = beslutsdatum;
	}

	public InformationshanteringsplanEntity withBeslutsdatum(final LocalDate beslutsdatum) {
		this.beslutsdatum = beslutsdatum;
		return this;
	}

	public String getBeslutsparagraf() {
		return this.beslutsparagraf;
	}

	public void setBeslutsparagraf(final String beslutsparagraf) {
		this.beslutsparagraf = beslutsparagraf;
	}

	public InformationshanteringsplanEntity withBeslutsparagraf(final String beslutsparagraf) {
		this.beslutsparagraf = beslutsparagraf;
		return this;
	}

	public IHPlanStatus getStatus() {
		return this.status;
	}

	public void setStatus(final IHPlanStatus status) {
		this.status = status;
	}

	public InformationshanteringsplanEntity withStatus(final IHPlanStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final InformationshanteringsplanEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.organisationId, that.organisationId) &&
			Objects.equals(this.giltigFran, that.giltigFran) &&
			Objects.equals(this.giltigTom, that.giltigTom) &&
			Objects.equals(this.beslutsdatum, that.beslutsdatum) &&
			Objects.equals(this.beslutsparagraf, that.beslutsparagraf) &&
			this.status == that.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.namn, this.version, this.beskrivning, this.organisationId,
			this.giltigFran, this.giltigTom, this.beslutsdatum, this.beslutsparagraf, this.status);
	}

	@Override
	public String toString() {
		return "InformationshanteringsplanEntity{" +
			"id='" + this.getId() + '\'' +
			", namn='" + this.namn + '\'' +
			", version='" + this.version + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", organisationId='" + this.organisationId + '\'' +
			", giltigFran=" + this.giltigFran +
			", giltigTom=" + this.giltigTom +
			", beslutsdatum=" + this.beslutsdatum +
			", beslutsparagraf='" + this.beslutsparagraf + '\'' +
			", status=" + this.status +
			'}';
	}
}
