package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "klassa_verksamhetstyper")
public class KlassaVerksamhetstypEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "kod")
	private Integer kod;

	@Column(name = "namn")
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	public static KlassaVerksamhetstypEntity create() {
		return new KlassaVerksamhetstypEntity();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaVerksamhetstypEntity withId(final Integer id) {
		this.id = id;
		return this;
	}

	public Integer getKod() {
		return this.kod;
	}

	public void setKod(final Integer kod) {
		this.kod = kod;
	}

	public KlassaVerksamhetstypEntity withKod(final Integer kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaVerksamhetstypEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public KlassaVerksamhetstypEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaVerksamhetstypEntity that)) {
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
		return "KlassaVerksamhetstypEntity{" +
			"id=" + this.id +
			", kod=" + this.kod +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			'}';
	}
}
