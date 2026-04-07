package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "handlingstyp_ansvariga")
public class HandlingstypAnsvarigEntity extends AbstractAuditableEntity {

	@Column(name = "handlingstyp_id")
	private String handlingstypId;

	@Column(name = "organisation_id")
	private String organisationId;

	@Column(name = "roll")
	private String roll;

	@Column(name = "ansvar_typ_id")
	private Integer ansvarTypId;

	public static HandlingstypAnsvarigEntity create() {
		return new HandlingstypAnsvarigEntity();
	}

	public String getHandlingstypId() {
		return this.handlingstypId;
	}

	public void setHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
	}

	public HandlingstypAnsvarigEntity withHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
		return this;
	}

	public String getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
	}

	public HandlingstypAnsvarigEntity withOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
		return this;
	}

	public String getRoll() {
		return this.roll;
	}

	public void setRoll(final String roll) {
		this.roll = roll;
	}

	public HandlingstypAnsvarigEntity withRoll(final String roll) {
		this.roll = roll;
		return this;
	}

	public Integer getAnsvarTypId() {
		return this.ansvarTypId;
	}

	public void setAnsvarTypId(final Integer ansvarTypId) {
		this.ansvarTypId = ansvarTypId;
	}

	public HandlingstypAnsvarigEntity withAnsvarTypId(final Integer ansvarTypId) {
		this.ansvarTypId = ansvarTypId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final HandlingstypAnsvarigEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.handlingstypId, that.handlingstypId) &&
			Objects.equals(this.organisationId, that.organisationId) &&
			Objects.equals(this.roll, that.roll) &&
			Objects.equals(this.ansvarTypId, that.ansvarTypId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.handlingstypId, this.organisationId, this.roll, this.ansvarTypId);
	}

	@Override
	public String toString() {
		return "HandlingstypAnsvarigEntity{" +
			"id='" + this.getId() + '\'' +
			", handlingstypId='" + this.handlingstypId + '\'' +
			", organisationId='" + this.organisationId + '\'' +
			", roll='" + this.roll + '\'' +
			", ansvarTypId=" + this.ansvarTypId +
			'}';
	}
}
