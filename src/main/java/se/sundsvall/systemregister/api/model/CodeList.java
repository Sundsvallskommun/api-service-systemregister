package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Schema(description = "CodeList (Generic code list item)")
public class CodeList {

	@Schema(description = "Code list item ID", example = "1")
	private Integer id;

	@Schema(description = "Code", example = "ELEKTRONISK")
	@NotBlank
	private String kod;

	@Schema(description = "Name/label", example = "Elektronisk handling")
	@NotBlank
	private String namn;

	@Schema(description = "Sort order", example = "1")
	@NotNull
	private Integer ordning;

	@Schema(description = "Description/details")
	private String beskrivning;

	@Schema(description = "Legal statute reference")
	private String lagrum;

	@Schema(description = "Retention period in years")
	private Integer gallringsfristAr;

	public static CodeList create() {
		return new CodeList();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public CodeList withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public CodeList withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public CodeList withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getOrdning() {
		return this.ordning;
	}

	public void setOrdning(final Integer ordning) {
		this.ordning = ordning;
	}

	public CodeList withOrdning(final Integer ordning) {
		this.ordning = ordning;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public CodeList withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getLagrum() {
		return this.lagrum;
	}

	public void setLagrum(final String lagrum) {
		this.lagrum = lagrum;
	}

	public CodeList withLagrum(final String lagrum) {
		this.lagrum = lagrum;
		return this;
	}

	public Integer getGallringsfristAr() {
		return this.gallringsfristAr;
	}

	public void setGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
	}

	public CodeList withGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final CodeList that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.ordning, that.ordning) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.lagrum, that.lagrum) &&
			Objects.equals(this.gallringsfristAr, that.gallringsfristAr);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.ordning, this.beskrivning, this.lagrum, this.gallringsfristAr);
	}

	@Override
	public String toString() {
		return "CodeList{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", ordning=" + this.ordning +
			", beskrivning='" + this.beskrivning + '\'' +
			", lagrum='" + this.lagrum + '\'' +
			", gallringsfristAr=" + this.gallringsfristAr +
			'}';
	}
}
