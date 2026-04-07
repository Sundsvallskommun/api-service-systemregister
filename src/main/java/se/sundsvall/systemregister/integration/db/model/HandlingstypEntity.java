package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "handlingstyper")
public class HandlingstypEntity extends AbstractAuditableEntity {

	@Column(name = "ih_plan_id")
	private String ihPlanId;

	@Column(name = "klassa_process_id")
	private Integer klassakProcessId;

	@Column(name = "namn")
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	@Column(name = "format_id")
	private Integer formatId;

	@Column(name = "bevara nde_gallring_id")
	private Integer bevarandeGallringId;

	@Column(name = "gallringsfrist_ar")
	private Integer gallringsfristAr;

	@Column(name = "gallringsfrist_typ_id")
	private Integer gallringsfristTypId;

	@Column(name = "gallringsforeskrift_id")
	private String gallringsforeskriftId;

	@Column(name = "bevara nde_lagstod_id")
	private Integer bevarandeLagstodId;

	@Column(name = "registrering_id")
	private Integer registreringId;

	@Column(name = "it_system_id")
	private String itSystemId;

	@Column(name = "forvaring")
	private String forvaring;

	@Column(name = "leverans_till_arkiv")
	private String leveransTillArkiv;

	@Column(name = "leveransfrist_ar")
	private Integer leveransfristAr;

	@Column(name = "sekretess")
	private Boolean sekretess;

	@Column(name = "sekretess_lagrum_id")
	private Integer sekretessLagrumId;

	@Column(name = "innehaller_personuppgifter")
	private Boolean innehallerPersonuppgifter;

	@Column(name = "personuppgift_id")
	private Integer personuppgiftId;

	@Column(name = "anmarkning")
	private String anmarkning;

	public static HandlingstypEntity create() {
		return new HandlingstypEntity();
	}

	public String getIhPlanId() {
		return this.ihPlanId;
	}

	public void setIhPlanId(final String ihPlanId) {
		this.ihPlanId = ihPlanId;
	}

	public HandlingstypEntity withIhPlanId(final String ihPlanId) {
		this.ihPlanId = ihPlanId;
		return this;
	}

	public Integer getKlassakProcessId() {
		return this.klassakProcessId;
	}

	public void setKlassakProcessId(final Integer klassakProcessId) {
		this.klassakProcessId = klassakProcessId;
	}

	public HandlingstypEntity withKlassakProcessId(final Integer klassakProcessId) {
		this.klassakProcessId = klassakProcessId;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public HandlingstypEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public HandlingstypEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public Integer getFormatId() {
		return this.formatId;
	}

	public void setFormatId(final Integer formatId) {
		this.formatId = formatId;
	}

	public HandlingstypEntity withFormatId(final Integer formatId) {
		this.formatId = formatId;
		return this;
	}

	public Integer getBevarandeGallringId() {
		return this.bevarandeGallringId;
	}

	public void setBevarandeGallringId(final Integer bevarandeGallringId) {
		this.bevarandeGallringId = bevarandeGallringId;
	}

	public HandlingstypEntity withBevarandeGallringId(final Integer bevarandeGallringId) {
		this.bevarandeGallringId = bevarandeGallringId;
		return this;
	}

	public Integer getGallringsfristAr() {
		return this.gallringsfristAr;
	}

	public void setGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
	}

	public HandlingstypEntity withGallringsfristAr(final Integer gallringsfristAr) {
		this.gallringsfristAr = gallringsfristAr;
		return this;
	}

	public Integer getGallringsfristTypId() {
		return this.gallringsfristTypId;
	}

	public void setGallringsfristTypId(final Integer gallringsfristTypId) {
		this.gallringsfristTypId = gallringsfristTypId;
	}

	public HandlingstypEntity withGallringsfristTypId(final Integer gallringsfristTypId) {
		this.gallringsfristTypId = gallringsfristTypId;
		return this;
	}

	public String getGallringsforeskriftId() {
		return this.gallringsforeskriftId;
	}

	public void setGallringsforeskriftId(final String gallringsforeskriftId) {
		this.gallringsforeskriftId = gallringsforeskriftId;
	}

	public HandlingstypEntity withGallringsforeskriftId(final String gallringsforeskriftId) {
		this.gallringsforeskriftId = gallringsforeskriftId;
		return this;
	}

	public Integer getBevarandeLagstodId() {
		return this.bevarandeLagstodId;
	}

	public void setBevarandeLagstodId(final Integer bevarandeLagstodId) {
		this.bevarandeLagstodId = bevarandeLagstodId;
	}

	public HandlingstypEntity withBevarandeLagstodId(final Integer bevarandeLagstodId) {
		this.bevarandeLagstodId = bevarandeLagstodId;
		return this;
	}

	public Integer getRegistreringId() {
		return this.registreringId;
	}

	public void setRegistreringId(final Integer registreringId) {
		this.registreringId = registreringId;
	}

	public HandlingstypEntity withRegistreringId(final Integer registreringId) {
		this.registreringId = registreringId;
		return this;
	}

	public String getItSystemId() {
		return this.itSystemId;
	}

	public void setItSystemId(final String itSystemId) {
		this.itSystemId = itSystemId;
	}

	public HandlingstypEntity withItSystemId(final String itSystemId) {
		this.itSystemId = itSystemId;
		return this;
	}

	public String getForvaring() {
		return this.forvaring;
	}

	public void setForvaring(final String forvaring) {
		this.forvaring = forvaring;
	}

	public HandlingstypEntity withForvaring(final String forvaring) {
		this.forvaring = forvaring;
		return this;
	}

	public String getLeveransTillArkiv() {
		return this.leveransTillArkiv;
	}

	public void setLeveransTillArkiv(final String leveransTillArkiv) {
		this.leveransTillArkiv = leveransTillArkiv;
	}

	public HandlingstypEntity withLeveransTillArkiv(final String leveransTillArkiv) {
		this.leveransTillArkiv = leveransTillArkiv;
		return this;
	}

	public Integer getLeveransfristAr() {
		return this.leveransfristAr;
	}

	public void setLeveransfristAr(final Integer leveransfristAr) {
		this.leveransfristAr = leveransfristAr;
	}

	public HandlingstypEntity withLeveransfristAr(final Integer leveransfristAr) {
		this.leveransfristAr = leveransfristAr;
		return this;
	}

	public Boolean getSekretess() {
		return this.sekretess;
	}

	public void setSekretess(final Boolean sekretess) {
		this.sekretess = sekretess;
	}

	public HandlingstypEntity withSekretess(final Boolean sekretess) {
		this.sekretess = sekretess;
		return this;
	}

	public Integer getSekretessLagrumId() {
		return this.sekretessLagrumId;
	}

	public void setSekretessLagrumId(final Integer sekretessLagrumId) {
		this.sekretessLagrumId = sekretessLagrumId;
	}

	public HandlingstypEntity withSekretessLagrumId(final Integer sekretessLagrumId) {
		this.sekretessLagrumId = sekretessLagrumId;
		return this;
	}

	public Boolean getInnehallerPersonuppgifter() {
		return this.innehallerPersonuppgifter;
	}

	public void setInnehallerPersonuppgifter(final Boolean innehallerPersonuppgifter) {
		this.innehallerPersonuppgifter = innehallerPersonuppgifter;
	}

	public HandlingstypEntity withInnehallerPersonuppgifter(final Boolean innehallerPersonuppgifter) {
		this.innehallerPersonuppgifter = innehallerPersonuppgifter;
		return this;
	}

	public Integer getPersonuppgiftId() {
		return this.personuppgiftId;
	}

	public void setPersonuppgiftId(final Integer personuppgiftId) {
		this.personuppgiftId = personuppgiftId;
	}

	public HandlingstypEntity withPersonuppgiftId(final Integer personuppgiftId) {
		this.personuppgiftId = personuppgiftId;
		return this;
	}

	public String getAnmarkning() {
		return this.anmarkning;
	}

	public void setAnmarkning(final String anmarkning) {
		this.anmarkning = anmarkning;
	}

	public HandlingstypEntity withAnmarkning(final String anmarkning) {
		this.anmarkning = anmarkning;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final HandlingstypEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
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
		return Objects.hash(this.getId(), this.ihPlanId, this.klassakProcessId, this.namn, this.beskrivning,
			this.formatId, this.bevarandeGallringId, this.gallringsfristAr, this.gallringsfristTypId,
			this.gallringsforeskriftId, this.bevarandeLagstodId, this.registreringId, this.itSystemId,
			this.forvaring, this.leveransTillArkiv, this.leveransfristAr, this.sekretess, this.sekretessLagrumId,
			this.innehallerPersonuppgifter, this.personuppgiftId, this.anmarkning);
	}

	@Override
	public String toString() {
		return "HandlingstypEntity{" +
			"id='" + this.getId() + '\'' +
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
