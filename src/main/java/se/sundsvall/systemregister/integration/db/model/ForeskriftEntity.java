package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import se.sundsvall.systemregister.integration.db.model.enums.ForeskriftUtfardare;

@Entity
@Table(name = "foreskrifter")
public class ForeskriftEntity extends AbstractAuditableEntity {

	@Column(name = "beteckning")
	private String beteckning;

	@Column(name = "namn")
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	@Column(name = "utfardare")
	@Enumerated(EnumType.STRING)
	private ForeskriftUtfardare utfardare;

	@Column(name = "giltig_fran")
	private LocalDate giltigFran;

	@Column(name = "giltig_tom")
	private LocalDate giltigTom;

	@Column(name = "url")
	private String url;

	public static ForeskriftEntity create() {
		return new ForeskriftEntity();
	}

	public String getBeteckning() {
		return this.beteckning;
	}

	public void setBeteckning(final String beteckning) {
		this.beteckning = beteckning;
	}

	public ForeskriftEntity withBeteckning(final String beteckning) {
		this.beteckning = beteckning;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public ForeskriftEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public ForeskriftEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public ForeskriftUtfardare getUtfardare() {
		return this.utfardare;
	}

	public void setUtfardare(final ForeskriftUtfardare utfardare) {
		this.utfardare = utfardare;
	}

	public ForeskriftEntity withUtfardare(final ForeskriftUtfardare utfardare) {
		this.utfardare = utfardare;
		return this;
	}

	public LocalDate getGiltigFran() {
		return this.giltigFran;
	}

	public void setGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
	}

	public ForeskriftEntity withGiltigFran(final LocalDate giltigFran) {
		this.giltigFran = giltigFran;
		return this;
	}

	public LocalDate getGiltigTom() {
		return this.giltigTom;
	}

	public void setGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
	}

	public ForeskriftEntity withGiltigTom(final LocalDate giltigTom) {
		this.giltigTom = giltigTom;
		return this;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public ForeskriftEntity withUrl(final String url) {
		this.url = url;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final ForeskriftEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.beteckning, that.beteckning) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			this.utfardare == that.utfardare &&
			Objects.equals(this.giltigFran, that.giltigFran) &&
			Objects.equals(this.giltigTom, that.giltigTom) &&
			Objects.equals(this.url, that.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.beteckning, this.namn, this.beskrivning, this.utfardare,
			this.giltigFran, this.giltigTom, this.url);
	}

	@Override
	public String toString() {
		return "ForeskriftEntity{" +
			"id='" + this.getId() + '\'' +
			", beteckning='" + this.beteckning + '\'' +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", utfardare=" + this.utfardare +
			", giltigFran=" + this.giltigFran +
			", giltigTom=" + this.giltigTom +
			", url='" + this.url + '\'' +
			'}';
	}
}
