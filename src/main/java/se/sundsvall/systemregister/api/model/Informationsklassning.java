package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Informationsklassning (Information classification)")
public class Informationsklassning {

	@Schema(description = "Informationsklassning ID", example = "klassning-1")
	private String id;

	@Schema(description = "Handlingstyp ID", example = "handlingstyp-1")
	@NotBlank
	private String handlingstypId;

	@Schema(description = "Confidentiality level", allowableValues = {
		"OKÄND", "ÖPPEN", "INTERN", "KONFIDENTIELL", "HEMLIG"
	})
	private Integer konfidentialitet;

	@Schema(description = "Motivation of Confidentiality level", example = "This level of Confidentiality level was set because of reason XYZ")
	private String konfidentialitetMotivering;

	@Schema(description = "Correctness level", allowableValues = {
		"OKÄND", "LAG", "MEDEL", "HÖG"
	})
	private Integer riktighet;

	@Schema(description = "Motivation of Correctness level", example = "This level of Correctness level was set because of reason XYZ")
	private String riktighetMotivering;

	@Schema(description = "Availability level", allowableValues = {
		"OKÄND", "LAG", "MEDEL", "HÖG"
	})
	private Integer tillganglighet;

	@Schema(description = "Motivation of Availability level", example = "This level of Availability level was set because of reason XYZ")
	private String tillganglighetMotivering;

	@Schema(description = "Durability/Longevity level", allowableValues = {
		"OKÄND", "KORT", "MEDEL", "LANG"
	})
	private Integer sparbarhet;

	@Schema(description = "Classification date")
	private LocalDate klassningDatum;

	@Schema(description = "Classified by (person/role)")
	private String klassadAv;

	@Schema(description = "Is system a essential service ", example = "true")
	private Boolean samhallsviktigt;

	@Schema(description = "Essential status motivation/reasoning")
	private String samhallsviktigtMotivering;

	public static Informationsklassning create() {
		return new Informationsklassning();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Informationsklassning withId(final String id) {
		this.id = id;
		return this;
	}

	public String getHandlingstypId() {
		return this.handlingstypId;
	}

	public void setHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
	}

	public Informationsklassning withHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
		return this;
	}

	public Integer getKonfidentialitet() {
		return this.konfidentialitet;
	}

	public void setKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
	}

	public Informationsklassning withKonfidentialitet(final Integer konfidentialitet) {
		this.konfidentialitet = konfidentialitet;
		return this;
	}

	public String getKonfidentialitetMotivering() {
		return this.konfidentialitetMotivering;
	}

	public void setKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
	}

	public Informationsklassning withKonfidentialitetMotivering(final String konfidentialitetMotivering) {
		this.konfidentialitetMotivering = konfidentialitetMotivering;
		return this;
	}

	public Integer getRiktighet() {
		return this.riktighet;
	}

	public void setRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
	}

	public Informationsklassning withRiktighet(final Integer riktighet) {
		this.riktighet = riktighet;
		return this;
	}

	public String getRiktighetMotivering() {
		return this.riktighetMotivering;
	}

	public void setRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
	}

	public Informationsklassning withRiktighetMotivering(final String riktighetMotivering) {
		this.riktighetMotivering = riktighetMotivering;
		return this;
	}

	public Integer getTillganglighet() {
		return this.tillganglighet;
	}

	public void setTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
	}

	public Informationsklassning withTillganglighet(final Integer tillganglighet) {
		this.tillganglighet = tillganglighet;
		return this;
	}

	public String getTillganglighetMotivering() {
		return this.tillganglighetMotivering;
	}

	public void setTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
	}

	public Informationsklassning withTillganglighetMotivering(final String tillganglighetMotivering) {
		this.tillganglighetMotivering = tillganglighetMotivering;
		return this;
	}

	public Integer getSparbarhet() {
		return this.sparbarhet;
	}

	public void setSparbarhet(final Integer sparbarhet) {
		this.sparbarhet = sparbarhet;
	}

	public Informationsklassning withSparbarhet(final Integer sparbarhet) {
		this.sparbarhet = sparbarhet;
		return this;
	}

	public LocalDate getKlassningDatum() {
		return this.klassningDatum;
	}

	public void setKlassningDatum(final LocalDate klassningDatum) {
		this.klassningDatum = klassningDatum;
	}

	public Informationsklassning withKlassningDatum(final LocalDate klassningDatum) {
		this.klassningDatum = klassningDatum;
		return this;
	}

	public String getKlassadAv() {
		return this.klassadAv;
	}

	public void setKlassadAv(final String klassadAv) {
		this.klassadAv = klassadAv;
	}

	public Informationsklassning withKlassadAv(final String klassadAv) {
		this.klassadAv = klassadAv;
		return this;
	}

	public Boolean getSamhallsviktigt() {
		return this.samhallsviktigt;
	}

	public void setSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
	}

	public Informationsklassning withSamhallsviktigt(final Boolean samhallsviktigt) {
		this.samhallsviktigt = samhallsviktigt;
		return this;
	}

	public String getSamhallsviktigtMotivering() {
		return this.samhallsviktigtMotivering;
	}

	public void setSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
	}

	public Informationsklassning withSamhallsviktigtMotivering(final String samhallsviktigtMotivering) {
		this.samhallsviktigtMotivering = samhallsviktigtMotivering;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Informationsklassning that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
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
		return Objects.hash(this.id, this.handlingstypId, this.konfidentialitet, this.konfidentialitetMotivering,
			this.riktighet, this.riktighetMotivering, this.tillganglighet, tillganglighetMotivering,
			this.sparbarhet, this.klassningDatum, this.klassadAv, this.samhallsviktigt, this.samhallsviktigtMotivering);
	}

	@Override
	public String toString() {
		return "Informationsklassning{" +
			"id='" + this.id + '\'' +
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
