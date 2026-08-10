package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "informationsklassningar")
public class InformationsklassningEntity extends AbstractAuditableEntity {

	@Column(name = "handlingstyp_id")
	private String handlingstypId;

	@Column(name = "konfidentialitet")
	private Integer konfidentialitet;

	@Column(name = "riktighet")
	private Integer riktighet;

	@Column(name = "tillganglighet")
	private Integer tillganglighet;

	@Column(name = "sparbarhet")
	private Integer sparbarhet;

	@Column(name = "klassning_datum")
	private LocalDate klassningDatum;

	@Column(name = "klassad_av")
	private String klassadAv;

	@Column(name = "motivering")
	private String motivering;

	public static InformationsklassningEntity create() {
		return new InformationsklassningEntity();
	}

	public String getHandlingstypId() {
		return this.handlingstypId;
	}

	public void setHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
	}

	public InformationsklassningEntity withHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public InformationsklassningEntity withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public InformationsklassningEntity withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public InformationsklassningEntity withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public Integer getSparbarhet() {
		return this.sparbarhet;
	}

	public void setSparbarhet(final Integer sparbarhet) {
		this.sparbarhet = sparbarhet;
	}

	public InformationsklassningEntity withSparbarhet(final Integer sparbarhet) {
		this.sparbarhet = sparbarhet;
		return this;
	}

	public LocalDate getKlassningDatum() {
		return this.klassningDatum;
	}

	public void setKlassningDatum(final LocalDate klassningDatum) {
		this.klassningDatum = klassningDatum;
	}

	public InformationsklassningEntity withKlassningDatum(final LocalDate klassningDatum) {
		this.klassningDatum = klassningDatum;
		return this;
	}

	public String getKlassadAv() {
		return this.klassadAv;
	}

	public void setKlassadAv(final String klassadAv) {
		this.klassadAv = klassadAv;
	}

	public InformationsklassningEntity withKlassadAv(final String klassadAv) {
		this.klassadAv = klassadAv;
		return this;
	}

	public String getMotivering() {
		return this.motivering;
	}

	public void setMotivering(final String motivering) {
		this.motivering = motivering;
	}

	public InformationsklassningEntity withMotivering(final String motivering) {
		this.motivering = motivering;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final InformationsklassningEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.handlingstypId, that.handlingstypId) &&
			Objects.equals(this.konfidentialitet, that.konfidentialitet) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.sparbarhet, that.sparbarhet) &&
			Objects.equals(this.klassningDatum, that.klassningDatum) &&
			Objects.equals(this.klassadAv, that.klassadAv) &&
			Objects.equals(this.motivering, that.motivering);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.handlingstypId, this.konfidentialitet, this.riktighet,
			this.tillganglighet, this.sparbarhet, this.klassningDatum, this.klassadAv, this.motivering);
	}

	@Override
	public String toString() {
		return "InformationsklassningEntity{" +
			"id='" + this.getId() + '\'' +
			", handlingstypId='" + this.handlingstypId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", riktighet=" + this.riktighet +
			", tillganglighet=" + this.tillganglighet +
			", sparbarhet=" + this.sparbarhet +
			", klassningDatum=" + this.klassningDatum +
			", klassadAv='" + this.klassadAv + '\'' +
			", motivering='" + this.motivering + '\'' +
			'}';
	}
}
