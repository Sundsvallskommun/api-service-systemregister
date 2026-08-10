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

	@Column(name = "konfidentialitet_motivering")
	private String konfidentialitetMotivering;

	@Column(name = "riktighet")
	private Integer riktighet;

	@Column(name = "riktighet_motivering")
	private String riktighetMotivering;

	@Column(name = "tillganglighet")
	private Integer tillganglighet;

	@Column(name = "tillganglighet_motivering")
	private String tillganglighetMotivering;

	@Column(name = "sparbarhet")
	private Integer sparbarhet;

	@Column(name = "klassning_datum")
	private LocalDate klassningDatum;

	@Column(name = "klassad_av")
	private String klassadAv;

	@Column(name = "samhallsviktigt")
	private Boolean samhallsviktigt;

	@Column(name = "samhallsviktigt_motivering")
	private String samhallsviktigtMotivering;

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

	public String getKonfidentialitetMotivering() {
		return this.konfidentialitetMotivering;
	}

	public void setKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
	}

	public InformationsklassningEntity withKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
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

	public String getRiktighetMotivering() {
		return this.riktighetMotivering;
	}

	public void setRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
	}

	public InformationsklassningEntity withRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
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

	public String getTillganglighetMotivering() {
		return this.tillganglighetMotivering;
	}

	public void setTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
	}

	public InformationsklassningEntity withTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
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

	public Boolean getSamhallsviktigt() {
		return this.samhallsviktigt;
	}

	public void setSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
	}

	public InformationsklassningEntity withSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
		return this;
	}

	public String getSamhallsviktigtMotivering() {
		return this.samhallsviktigtMotivering;
	}

	public void setSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
	}

	public InformationsklassningEntity withSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
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
			Objects.equals(this.konfidentialitetMotivering, that.konfidentialitetMotivering) &&
			Objects.equals(this.riktighet, that.riktighet) &&
			Objects.equals(this.riktighetMotivering, that.riktighetMotivering) &&
			Objects.equals(this.tillganglighet, that.tillganglighet) &&
			Objects.equals(this.tillganglighetMotivering, that.tillganglighetMotivering) &&
			Objects.equals(this.sparbarhet, that.sparbarhet) &&
			Objects.equals(this.klassningDatum, that.klassningDatum) &&
			Objects.equals(this.klassadAv, that.klassadAv) &&
			Objects.equals(this.samhallsviktigt, that.samhallsviktigt) &&
			Objects.equals(this.samhallsviktigtMotivering, that.samhallsviktigtMotivering);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.handlingstypId, this.konfidentialitet, this.konfidentialitetMotivering,
			this.riktighet, this.riktighetMotivering, this.tillganglighet, tillganglighetMotivering,
			this.sparbarhet, this.klassningDatum, this.klassadAv, this.samhallsviktigt, this.samhallsviktigtMotivering);
	}

	@Override
	public String toString() {
		return "InformationsklassningEntity{" +
			"id='" + this.getId() + '\'' +
			", handlingstypId='" + this.handlingstypId + '\'' +
			", konfidentialitet=" + this.konfidentialitet +
			", konfidentialitetMotivering=" + this.konfidentialitetMotivering +
			", riktighet=" + this.riktighet +
			", riktighetMotivering=" + this.riktighetMotivering +
			", tillganglighet=" + this.tillganglighet +
			", tillganglighetMotivering=" + this.tillganglighetMotivering +
			", sparbarhet=" + this.sparbarhet +
			", klassningDatum=" + this.klassningDatum +
			", klassadAv='" + this.klassadAv + '\'' +
			", samhallsviktigt='" + this.samhallsviktigt + '\'' +
			", samhallsviktigtMotivering='" + this.samhallsviktigtMotivering + '\'' +
			'}';
	}
}
