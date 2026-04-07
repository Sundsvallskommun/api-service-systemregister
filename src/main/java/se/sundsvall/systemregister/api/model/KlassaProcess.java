package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "KLASSA Process")
public class KlassaProcess {

	@Schema(description = "KLASSA Process ID", example = "kp-1")
	private Integer id;

	@Schema(description = "Process code", example = "P001")
	private String kod;

	@Schema(description = "Process name", example = "Recruitment")
	@NotBlank
	private String namn;

	@Schema(description = "Process description")
	private String beskrivning;

	@Schema(description = "Associated Processgrupp ID")
	private Integer processgrupId;

	@Schema(description = "Process is active")
	private Boolean aktiv;

	public static KlassaProcess create() {
		return new KlassaProcess();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaProcess withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KlassaProcess withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaProcess withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public KlassaProcess withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public Integer getProcessgrupId() {
		return this.processgrupId;
	}

	public void setProcessgrupId(final Integer processgrupId) {
		this.processgrupId = processgrupId;
	}

	public KlassaProcess withProcessgrupId(final Integer processgrupId) {
		this.processgrupId = processgrupId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public KlassaProcess withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaProcess that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.processgrupId, that.processgrupId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.beskrivning, this.processgrupId, this.aktiv);
	}

	@Override
	public String toString() {
		return "KlassaProcess{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", processgrupId=" + this.processgrupId +
			", aktiv=" + this.aktiv +
			'}';
	}
}
