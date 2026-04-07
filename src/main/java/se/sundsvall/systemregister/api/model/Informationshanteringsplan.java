package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Informationshanteringsplan (IH-plan)")
public class Informationshanteringsplan {

	@Schema(description = "IH-plan ID", example = "ih-plan-1")
	private String id;

	@Schema(description = "IH-plan name", example = "Plan 2024")
	@NotBlank
	private String namn;

	@Schema(description = "IH-plan version", example = "1.0")
	private String version;

	@Schema(description = "IH-plan description", example = "Information management plan for 2024")
	private String beskrivning;

	@Schema(description = "Organisation ID", example = "org-1")
	private String organisationId;

	@Schema(description = "Valid from date")
	private LocalDate giltigFran;

	@Schema(description = "Valid until date")
	private LocalDate giltigTom;

	@Schema(description = "Decision date")
	private LocalDate beslutsdatum;

	@Schema(description = "Decision paragraph reference", example = "§ 123")
	private String beslutsparagraf;

	@Schema(description = "IH-plan status", allowableValues = {
		"DRAFT", "ACTIVE", "SUPERSEDED", "ARCHIVED"
	})
	private String status;

	public static Informationshanteringsplan create() {
		return new Informationshanteringsplan();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Informationshanteringsplan withId(final String id) {
		this.id = id;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public Informationshanteringsplan withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public Informationshanteringsplan withVersion(final String version) {
		this.version = version;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public Informationshanteringsplan withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
	}

	public Informationshanteringsplan withOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
		return this;
	}

	public LocalDate getGiltigFran() {
		return this.giltigFran;
	}

	public void setGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
	}

	public Informationshanteringsplan withGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
		return this;
	}

	public LocalDate getGiltigTom() {
		return this.giltigTom;
	}

	public void setGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
	}

	public Informationshanteringsplan withGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
		return this;
	}

	public LocalDate getBeslutsdatum() {
		return this.beslutsdatum;
	}

	public void setBeslutsdatum(final LocalDate beslutsdatum) {
		this.beslutsdatum = beslutsdatum;
	}

	public Informationshanteringsplan withBeslutsdatum(final LocalDate beslutsdatum) {
		this.beslutsdatum = beslutsdatum;
		return this;
	}

	public String getBeslutsparagraf() {
		return this.beslutsparagraf;
	}

	public void setBeslutsparagraf(final String beslutsparagraf) {
		this.beslutsparagraf = beslutsparagraf;
	}

	public Informationshanteringsplan withBeslutsparagraf(final String beslutsparagraf) {
		this.beslutsparagraf = beslutsparagraf;
		return this;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Informationshanteringsplan withStatus(final String status) {
		this.status = status;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Informationshanteringsplan that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.version, that.version) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.organisationId, that.organisationId) &&
			Objects.equals(this.giltigFran, that.giltigFran) &&
			Objects.equals(this.giltigTom, that.giltigTom) &&
			Objects.equals(this.beslutsdatum, that.beslutsdatum) &&
			Objects.equals(this.beslutsparagraf, that.beslutsparagraf) &&
			Objects.equals(this.status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.namn, this.version, this.beskrivning, this.organisationId,
			this.giltigFran, this.giltigTom, this.beslutsdatum, this.beslutsparagraf, this.status);
	}

	@Override
	public String toString() {
		return "Informationshanteringsplan{" +
			"id='" + this.id + '\'' +
			", namn='" + this.namn + '\'' +
			", version='" + this.version + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", organisationId='" + this.organisationId + '\'' +
			", giltigFran=" + this.giltigFran +
			", giltigTom=" + this.giltigTom +
			", beslutsdatum=" + this.beslutsdatum +
			", beslutsparagraf='" + this.beslutsparagraf + '\'' +
			", status='" + this.status + '\'' +
			'}';
	}
}
