package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Process (Legacy process definition)")
public class Process {

	@Schema(description = "Process ID", example = "process-1")
	private String id;

	@Schema(description = "Process code/identifier", example = "P001")
	@NotBlank
	private String processkod;

	@Schema(description = "Process name", example = "Recruitment process")
	@NotBlank
	private String namn;

	@Schema(description = "Process description")
	private String beskrivning;

	@Schema(description = "Activity type/verksamhetstyp")
	private String verksamhetstyp;

	@Schema(description = "KLASSA Process group ID")
	private String processgrupId;

	@Schema(description = "Process is active")
	private Boolean aktiv;

	public static Process create() {
		return new Process();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Process withId(final String id) {
		this.id = id;
		return this;
	}

	public String getProcesskod() {
		return this.processkod;
	}

	public void setProcesskod(final String processkod) {
		this.processkod = processkod;
	}

	public Process withProcesskod(final String processkod) {
		this.processkod = processkod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public Process withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public Process withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getVerksamhetstyp() {
		return this.verksamhetstyp;
	}

	public void setVerksamhetstyp(final String verksamhetstyp) {
		this.verksamhetstyp = verksamhetstyp;
	}

	public Process withVerksamhetstyp(final String verksamhetstyp) {
		this.verksamhetstyp = verksamhetstyp;
		return this;
	}

	public String getProcessgrupId() {
		return this.processgrupId;
	}

	public void setProcessgrupId(final String processgrupId) {
		this.processgrupId = processgrupId;
	}

	public Process withProcessgrupId(final String processgrupId) {
		this.processgrupId = processgrupId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public Process withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Process that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.processkod, that.processkod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.verksamhetstyp, that.verksamhetstyp) &&
			Objects.equals(this.processgrupId, that.processgrupId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.processkod, this.namn, this.beskrivning, this.verksamhetstyp, this.processgrupId, this.aktiv);
	}

	@Override
	public String toString() {
		return "Process{" +
			"id='" + this.id + '\'' +
			", processkod='" + this.processkod + '\'' +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", verksamhetstyp='" + this.verksamhetstyp + '\'' +
			", processgrupId='" + this.processgrupId + '\'' +
			", aktiv=" + this.aktiv +
			'}';
	}
}
