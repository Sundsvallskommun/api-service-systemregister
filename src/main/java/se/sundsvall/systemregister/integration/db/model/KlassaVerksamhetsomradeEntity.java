package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "klassa_verksamhetsomraden")
public class KlassaVerksamhetsomradeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "kod")
	private String kod;

	@Column(name = "namn")
	private String namn;

	@Column(name = "verksamhetstyp_id")
	private Integer verksamhetstypId;

	@Column(name = "aktiv")
	private Boolean aktiv;

	public static KlassaVerksamhetsomradeEntity create() {
		return new KlassaVerksamhetsomradeEntity();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaVerksamhetsomradeEntity withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KlassaVerksamhetsomradeEntity withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaVerksamhetsomradeEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getVerksamhetstypId() {
		return this.verksamhetstypId;
	}

	public void setVerksamhetstypId(final Integer verksamhetstypId) {
		this.verksamhetstypId = verksamhetstypId;
	}

	public KlassaVerksamhetsomradeEntity withVerksamhetstypId(final Integer verksamhetstypId) {
		this.verksamhetstypId = verksamhetstypId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public KlassaVerksamhetsomradeEntity withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaVerksamhetsomradeEntity that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.verksamhetstypId, that.verksamhetstypId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.verksamhetstypId, this.aktiv);
	}

	@Override
	public String toString() {
		return "KlassaVerksamhetsomradeEntity{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", verksamhetstypId=" + this.verksamhetstypId +
			", aktiv=" + this.aktiv +
			'}';
	}
}
