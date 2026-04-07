package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "KLASSA Verksamhetstyp (Activity type)")
public class KlassaVerksamhetstyp {

	@Schema(description = "Verksamhetstyp ID", example = "1")
	private Integer id;

	@Schema(description = "Activity type code", example = "100")
	private Integer kod;

	@Schema(description = "Activity type name", example = "Allmän verksamhet")
	@NotBlank
	private String namn;

	@Schema(description = "Activity type description")
	private String beskrivning;

	public static KlassaVerksamhetstyp create() {
		return new KlassaVerksamhetstyp();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaVerksamhetstyp withId(final Integer id) {
		this.id = id;
		return this;
	}

	public Integer getKod() {
		return this.kod;
	}

	public void setKod(final Integer kod) {
		this.kod = kod;
	}

	public KlassaVerksamhetstyp withKod(final Integer kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaVerksamhetstyp withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public KlassaVerksamhetstyp withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaVerksamhetstyp that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.beskrivning);
	}

	@Override
	public String toString() {
		return "KlassaVerksamhetstyp{" +
			"id=" + this.id +
			", kod=" + this.kod +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			'}';
	}
}
