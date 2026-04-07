package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "kod_bevarande_lagstod")
public class KodBevarandeLagstodEntity implements KodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "kod")
	private String kod;

	@Column(name = "namn")
	private String namn;

	@Column(name = "ordning")
	private Integer ordning;

	@Column(name = "gallringsfrist_ar")
	private Integer gallringsfristAr;

	@Column(name = "lagrum")
	private String lagrum;

	@Column(name = "beskrivning")
	private String beskrivning;

	public static KodBevarandeLagstodEntity create() {
		return new KodBevarandeLagstodEntity();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KodBevarandeLagstodEntity withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KodBevarandeLagstodEntity withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KodBevarandeLagstodEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getOrdning() {
		return this.ordning;
	}

	public void setOrdning(final Integer ordning) {
		this.ordning = ordning;
	}

	public KodBevarandeLagstodEntity withOrdning(final Integer ordning) {
		this.ordning = ordning;
		return this;
	}

	public Integer getGallringsfristAr() {
		return this.gallringsfristAr;
	}

	public void setGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
	}

	public KodBevarandeLagstodEntity withGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
		return this;
	}

	public String getLagrum() {
		return this.lagrum;
	}

	public void setLagrum(final String lagrum) {
		this.lagrum = lagrum;
	}

	public KodBevarandeLagstodEntity withLagrum(final String lagrum) {
		this.lagrum = lagrum;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public KodBevarandeLagstodEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KodBevarandeLagstodEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.ordning, that.ordning) &&
			Objects.equals(this.gallringsfristAr, that.gallringsfristAr) &&
			Objects.equals(this.lagrum, that.lagrum) &&
			Objects.equals(this.beskrivning, that.beskrivning);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.ordning, this.gallringsfristAr, this.lagrum, this.beskrivning);
	}

	@Override
	public String toString() {
		return "KodBevarandeLagstodEntity{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", ordning=" + this.ordning +
			", gallringsfristAr=" + this.gallringsfristAr +
			", lagrum='" + this.lagrum + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			'}';
	}
}
