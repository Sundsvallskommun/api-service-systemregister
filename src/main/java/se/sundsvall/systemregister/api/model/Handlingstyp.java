package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "Handlingstyp (Document type)")
public class Handlingstyp {

	@Schema(description = "Handlingstyp ID", example = "handlingstyp-1")
	private String id;

	@Schema(description = "IH-plan ID", example = "ih-plan-1")
	@NotBlank
	private String ihPlanId;

	@Schema(description = "KLASSA Process ID")
	private Integer klassakProcessId;

	@Schema(description = "Handlingstyp name", example = "Personuppgiftsbeslut")
	@NotBlank
	private String namn;

	@Schema(description = "Handlingstyp description")
	private String beskrivning;

	@Schema(description = "Format ID")
	private Integer formatId;

	@Schema(description = "Bevarande/Gallring ID")
	private Integer bevarandeGallringId;

	@Schema(description = "Gallringsfrist years")
	private Integer gallringsfristAr;

	@Schema(description = "Gallringsfrist type ID")
	private Integer gallringsfristTypId;

	@Schema(description = "Gallringsforeskrift ID")
	private String gallringsforeskriftId;

	@Schema(description = "Bevarande lagstöd ID")
	private Integer bevarandeLagstodId;

	@Schema(description = "Registrering ID")
	private Integer registreringId;

	@Schema(description = "IT System ID")
	private String itSystemId;

	@Schema(description = "Förvaring (storage location)")
	private String forvaring;

	@Schema(description = "Leverans till arkiv (archival delivery)")
	private String leveransTillArkiv;

	@Schema(description = "Leveransfrist years")
	private Integer leveransfristAr;

	@Schema(description = "Contains secrets/confidential data")
	private Boolean sekretess;

	@Schema(description = "Sekretess lagrum ID")
	private Integer sekretessLagrumId;

	@Schema(description = "Contains personal data")
	private Boolean innehallerPersonuppgifter;

	@Schema(description = "Personuppgift ID")
	private Integer personuppgiftId;

	@Schema(description = "Notes/remarks")
	private String anmarkning;

	public static Handlingstyp create() {
		return new Handlingstyp();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Handlingstyp withId(final String id) {
		this.id = id;
		return this;
	}

	public String getIhPlanId() {
		return this.ihPlanId;
	}

	public void setIhPlanId(final String ihPlanId) {
		this.ihPlanId = ihPlanId;
	}

	public Handlingstyp withIhPlanId(final String ihPlanId) {
		this.ihPlanId = ihPlanId;
		return this;
	}

	public Integer getKlassakProcessId() {
		return this.klassakProcessId;
	}

	public void setKlassakProcessId(final Integer klassakProcessId) {
		this.klassakProcessId = klassakProcessId;
	}

	public Handlingstyp withKlassakProcessId(final Integer klassakProcessId) {
		this.klassakProcessId = klassakProcessId;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public Handlingstyp withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public Handlingstyp withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public Integer getFormatId() {
		return this.formatId;
	}

	public void setFormatId(final Integer formatId) {
		this.formatId = formatId;
	}

	public Handlingstyp withFormatId(final Integer formatId) {
		this.formatId = formatId;
		return this;
	}

	public Integer getBevarandeGallringId() {
		return this.bevarandeGallringId;
	}

	public void setBevarandeGallringId(final Integer bevarandeGallringId) {
		this.bevarandeGallringId = bevarandeGallringId;
	}

	public Handlingstyp withBevarandeGallringId(final Integer bevarandeGallringId) {
		this.bevarandeGallringId = bevarandeGallringId;
		return this;
	}

	public Integer getGallringsfristAr() {
		return this.gallringsfristAr;
	}

	public void setGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
	}

	public Handlingstyp withGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
		return this;
	}

	public Integer getGallringsfristTypId() {
		return this.gallringsfristTypId;
	}

	public void setGallringsfristTypId(final Integer gallringsfristTypId) {
		this.gallringsfristTypId = gallringsfristTypId;
	}

	public Handlingstyp withGallringsfristTypId(final Integer gallringsfristTypId) {
		this.gallringsfristTypId = gallringsfristTypId;
		return this;
	}

	public String getGallringsforeskriftId() {
		return this.gallringsforeskriftId;
	}

	public void setGallringsforeskriftId(final String gallringsforeskriftId) {
		this.gallringsforeskriftId = gallringsforeskriftId;
	}

	public Handlingstyp withGallringsforeskriftId(final String gallringsforeskriftId) {
		this.gallringsforeskriftId = gallringsforeskriftId;
		return this;
	}

	public Integer getBevarandeLagstodId() {
		return this.bevarandeLagstodId;
	}

	public void setBevarandeLagstodId(final Integer bevarandeLagstodId) {
		this.bevarandeLagstodId = bevarandeLagstodId;
	}

	public Handlingstyp withBevarandeLagstodId(final Integer bevarandeLagstodId) {
		this.bevarandeLagstodId = bevarandeLagstodId;
		return this;
	}

	public Integer getRegistreringId() {
		return this.registreringId;
	}

	public void setRegistreringId(final Integer registreringId) {
		this.registreringId = registreringId;
	}

	public Handlingstyp withRegistreringId(final Integer registreringId) {
		this.registreringId = registreringId;
		return this;
	}

	public String getItSystemId() {
		return this.itSystemId;
	}

	public void setItSystemId(final String itSystemId) {
		this.itSystemId = itSystemId;
	}

	public Handlingstyp withItSystemId(final String itSystemId) {
		this.itSystemId = itSystemId;
		return this;
	}

	public String getForvaring() {
		return this.forvaring;
	}

	public void setForvaring(final String forvaring) {
		this.forvaring = forvaring;
	}

	public Handlingstyp withForvaring(final String forvaring) {
		this.forvaring = forvaring;
		return this;
	}

	public String getLeveransTillArkiv() {
		return this.leveransTillArkiv;
	}

	public void setLeveransTillArkiv(final String leveransTillArkiv) {
		this.leveransTillArkiv = leveransTillArkiv;
	}

	public Handlingstyp withLeveransTillArkiv(final String leveransTillArkiv) {
		this.leveransTillArkiv = leveransTillArkiv;
		return this;
	}

	public Integer getLeveransfristAr() {
		return this.leveransfristAr;
	}

	public void setLeveransfristAr(final Integer leveransfristAr) {
		this.leveransfristAr = leveransfristAr;
	}

	public Handlingstyp withLeveransfristAr(final Integer leveransfristAr) {
		this.leveransfristAr = leveransfristAr;
		return this;
	}

	public Boolean getSekretess() {
		return this.sekretess;
	}

	public void setSekretess(final Boolean sekretess) {
		this.sekretess = sekretess;
	}

	public Handlingstyp withSekretess(final Boolean sekretess) {
		this.sekretess = sekretess;
		return this;
	}

	public Integer getSekretessLagrumId() {
		return this.sekretessLagrumId;
	}

	public void setSekretessLagrumId(final Integer sekretessLagrumId) {
		this.sekretessLagrumId = sekretessLagrumId;
	}

	public Handlingstyp withSekretessLagrumId(final Integer sekretessLagrumId) {
		this.sekretessLagrumId = sekretessLagrumId;
		return this;
	}

	public Boolean getInnehallerPersonuppgifter() {
		return this.innehallerPersonuppgifter;
	}

	public void setInnehallerPersonuppgifter(final Boolean innehallerPersonuppgifter) {
		this.innehallerPersonuppgifter = innehallerPersonuppgifter;
	}

	public Handlingstyp withInnehallerPersonuppgifter(final Boolean innehallerPersonuppgifter) {
		this.innehallerPersonuppgifter = innehallerPersonuppgifter;
		return this;
	}

	public Integer getPersonuppgiftId() {
		return this.personuppgiftId;
	}

	public void setPersonuppgiftId(final Integer personuppgiftId) {
		this.personuppgiftId = personuppgiftId;
	}

	public Handlingstyp withPersonuppgiftId(final Integer personuppgiftId) {
		this.personuppgiftId = personuppgiftId;
		return this;
	}

	public String getAnmarkning() {
		return this.anmarkning;
	}

	public void setAnmarkning(final String anmarkning) {
		this.anmarkning = anmarkning;
	}

	public Handlingstyp withAnmarkning(final String anmarkning) {
		this.anmarkning = anmarkning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Handlingstyp that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.ihPlanId, that.ihPlanId) &&
			Objects.equals(this.klassakProcessId, that.klassakProcessId) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.formatId, that.formatId) &&
			Objects.equals(this.bevarandeGallringId, that.bevarandeGallringId) &&
			Objects.equals(this.gallringsfristAr, that.gallringsfristAr) &&
			Objects.equals(this.gallringsfristTypId, that.gallringsfristTypId) &&
			Objects.equals(this.gallringsforeskriftId, that.gallringsforeskriftId) &&
			Objects.equals(this.bevarandeLagstodId, that.bevarandeLagstodId) &&
			Objects.equals(this.registreringId, that.registreringId) &&
			Objects.equals(this.itSystemId, that.itSystemId) &&
			Objects.equals(this.forvaring, that.forvaring) &&
			Objects.equals(this.leveransTillArkiv, that.leveransTillArkiv) &&
			Objects.equals(this.leveransfristAr, that.leveransfristAr) &&
			Objects.equals(this.sekretess, that.sekretess) &&
			Objects.equals(this.sekretessLagrumId, that.sekretessLagrumId) &&
			Objects.equals(this.innehallerPersonuppgifter, that.innehallerPersonuppgifter) &&
			Objects.equals(this.personuppgiftId, that.personuppgiftId) &&
			Objects.equals(this.anmarkning, that.anmarkning);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.ihPlanId, this.klassakProcessId, this.namn, this.beskrivning,
			this.formatId, this.bevarandeGallringId, this.gallringsfristAr, this.gallringsfristTypId,
			this.gallringsforeskriftId, this.bevarandeLagstodId, this.registreringId, this.itSystemId,
			this.forvaring, this.leveransTillArkiv, this.leveransfristAr, this.sekretess,
			this.sekretessLagrumId, this.innehallerPersonuppgifter, this.personuppgiftId, this.anmarkning);
	}

	@Override
	public String toString() {
		return "Handlingstyp{" +
			"id='" + this.id + '\'' +
			", ihPlanId='" + this.ihPlanId + '\'' +
			", klassakProcessId=" + this.klassakProcessId +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", formatId=" + this.formatId +
			", bevarandeGallringId=" + this.bevarandeGallringId +
			", gallringsfristAr=" + this.gallringsfristAr +
			", gallringsfristTypId=" + this.gallringsfristTypId +
			", gallringsforeskriftId='" + this.gallringsforeskriftId + '\'' +
			", bevarandeLagstodId=" + this.bevarandeLagstodId +
			", registreringId=" + this.registreringId +
			", itSystemId='" + this.itSystemId + '\'' +
			", forvaring='" + this.forvaring + '\'' +
			", leveransTillArkiv='" + this.leveransTillArkiv + '\'' +
			", leveransfristAr=" + this.leveransfristAr +
			", sekretess=" + this.sekretess +
			", sekretessLagrumId=" + this.sekretessLagrumId +
			", innehallerPersonuppgifter=" + this.innehallerPersonuppgifter +
			", personuppgiftId=" + this.personuppgiftId +
			", anmarkning='" + this.anmarkning + '\'' +
			'}';
	}
}
