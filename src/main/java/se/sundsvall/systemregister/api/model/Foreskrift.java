package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Foreskrift (Regulation/Statute)")
public class Foreskrift {

	@Schema(description = "Foreskrift ID", example = "foreskrift-1")
	private String id;

	@Schema(description = "Beteckning (designation/code)", example = "FÖR 2023:456")
	@NotBlank
	private String beteckning;

	@Schema(description = "Foreskrift name", example = "Förordning om dokumenthantering")
	@NotBlank
	private String namn;

	@Schema(description = "Foreskrift description")
	private String beskrivning;

	@Schema(description = "Issuing authority", allowableValues = {
		"REGERINGEN", "MYNDIGHET", "KOMMUN", "EU", "ÖVRIG"
	})
	private String utfardare;

	@Schema(description = "Valid from date")
	private LocalDate giltigFran;

	@Schema(description = "Valid until date")
	private LocalDate giltigTom;

	@Schema(description = "Reference URL", example = "https://example.se/foreskrift")
	private String url;

	public static Foreskrift create() {
		return new Foreskrift();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Foreskrift withId(final String id) {
		this.id = id;
		return this;
	}

	public String getBeteckning() {
		return this.beteckning;
	}

	public void setBeteckning(final String beteckning) {
		this.beteckning = beteckning;
	}

	public Foreskrift withBeteckning(final String beteckning) {
		this.beteckning = beteckning;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public Foreskrift withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public Foreskrift withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getUtfardare() {
		return this.utfardare;
	}

	public void setUtfardare(final String utfardare) {
		this.utfardare = utfardare;
	}

	public Foreskrift withUtfardare(final String utfardare) {
		this.utfardare = utfardare;
		return this;
	}

	public LocalDate getGiltigFran() {
		return this.giltigFran;
	}

	public void setGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
	}

	public Foreskrift withGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
		return this;
	}

	public LocalDate getGiltigTom() {
		return this.giltigTom;
	}

	public void setGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
	}

	public Foreskrift withGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
		return this;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Foreskrift withUrl(final String url) {
		this.url = url;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Foreskrift that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.beteckning, that.beteckning) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.utfardare, that.utfardare) &&
			Objects.equals(this.giltigFran, that.giltigFran) &&
			Objects.equals(this.giltigTom, that.giltigTom) &&
			Objects.equals(this.url, that.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.beteckning, this.namn, this.beskrivning, this.utfardare,
			this.giltigFran, this.giltigTom, this.url);
	}

	@Override
	public String toString() {
		return "Foreskrift{" +
			"id='" + this.id + '\'' +
			", beteckning='" + this.beteckning + '\'' +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", utfardare='" + this.utfardare + '\'' +
			", giltigFran=" + this.giltigFran +
			", giltigTom=" + this.giltigTom +
			", url='" + this.url + '\'' +
			'}';
	}
}
