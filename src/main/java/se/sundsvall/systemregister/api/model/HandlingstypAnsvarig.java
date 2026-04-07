package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "HandlingstypAnsvarig (Document type responsible party)")
public class HandlingstypAnsvarig {

	@Schema(description = "Responsible party ID", example = "ansvarig-1")
	private String id;

	@Schema(description = "Handlingstyp ID", example = "handlingstyp-1")
	@NotBlank
	private String handlingstypId;

	@Schema(description = "Organisation ID", example = "org-1")
	@NotBlank
	private String organisationId;

	@Schema(description = "Role/responsibility description", example = "Owner")
	private String roll;

	@Schema(description = "Responsibility type ID")
	private Integer ansvarTypId;

	public static HandlingstypAnsvarig create() {
		return new HandlingstypAnsvarig();
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public HandlingstypAnsvarig withId(final String id) {
		this.id = id;
		return this;
	}

	public String getHandlingstypId() {
		return this.handlingstypId;
	}

	public void setHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
	}

	public HandlingstypAnsvarig withHandlingstypId(final String handlingstypId) {
		this.handlingstypId = handlingstypId;
		return this;
	}

	public String getOrganisationId() {
		return this.organisationId;
	}

	public void setOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
	}

	public HandlingstypAnsvarig withOrganisationId(final String organisationId) {
		this.organisationId = organisationId;
		return this;
	}

	public String getRoll() {
		return this.roll;
	}

	public void setRoll(final String roll) {
		this.roll = roll;
	}

	public HandlingstypAnsvarig withRoll(final String roll) {
		this.roll = roll;
		return this;
	}

	public Integer getAnsvarTypId() {
		return this.ansvarTypId;
	}

	public void setAnsvarTypId(final Integer ansvarTypId) {
		this.ansvarTypId = ansvarTypId;
	}

	public HandlingstypAnsvarig withAnsvarTypId(final Integer ansvarTypId) {
		this.ansvarTypId = ansvarTypId;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final HandlingstypAnsvarig that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.handlingstypId, that.handlingstypId) &&
			Objects.equals(this.organisationId, that.organisationId) &&
			Objects.equals(this.roll, that.roll) &&
			Objects.equals(this.ansvarTypId, that.ansvarTypId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.handlingstypId, this.organisationId, this.roll, this.ansvarTypId);
	}

	@Override
	public String toString() {
		return "HandlingstypAnsvarig{" +
			"id='" + this.id + '\'' +
			", handlingstypId='" + this.handlingstypId + '\'' +
			", organisationId='" + this.organisationId + '\'' +
			", roll='" + this.roll + '\'' +
			", ansvarTypId=" + this.ansvarTypId +
			'}';
	}
}
