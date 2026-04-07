package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "kod_format")
public class KodFormatEntity implements KodEntity {

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

	public static KodFormatEntity create() {
		return new KodFormatEntity();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KodFormatEntity withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KodFormatEntity withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KodFormatEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getOrdning() {
		return this.ordning;
	}

	public void setOrdning(final Integer ordning) {
		this.ordning = ordning;
	}

	public KodFormatEntity withOrdning(final Integer ordning) {
		this.ordning = ordning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KodFormatEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.ordning, that.ordning);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.ordning);
	}

	@Override
	public String toString() {
		return "KodFormatEntity{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", ordning=" + this.ordning +
			'}';
	}
}
