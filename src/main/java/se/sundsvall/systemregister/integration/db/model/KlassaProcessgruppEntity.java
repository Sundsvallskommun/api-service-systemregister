package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "klassa_processgrupper")
public class KlassaProcessgruppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "kod")
	private String kod;

	@Column(name = "namn")
	private String namn;

	@Column(name = "verksamhetsomrade_id")
	private Integer verksamhetsomradeId;

	@Column(name = "aktiv")
	private Boolean aktiv;

	public static KlassaProcessgruppEntity create() {
		return new KlassaProcessgruppEntity();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaProcessgruppEntity withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KlassaProcessgruppEntity withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaProcessgruppEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getVerksamhetsomradeId() {
		return this.verksamhetsomradeId;
	}

	public void setVerksamhetsomradeId(final Integer verksamhetsomradeId) {
		this.verksamhetsomradeId = verksamhetsomradeId;
	}

	public KlassaProcessgruppEntity withVerksamhetsomradeId(final Integer verksamhetsomradeId) {
		this.verksamhetsomradeId = verksamhetsomradeId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public KlassaProcessgruppEntity withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaProcessgruppEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.verksamhetsomradeId, that.verksamhetsomradeId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.verksamhetsomradeId, this.aktiv);
	}

	@Override
	public String toString() {
		return "KlassaProcessgruppEntity{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", verksamhetsomradeId=" + this.verksamhetsomradeId +
			", aktiv=" + this.aktiv +
			'}';
	}
}
