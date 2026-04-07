package se.sundsvall.systemregister.integration.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "processer")
public class ProcessEntity extends AbstractAuditableEntity {

	@Column(name = "processkod")
	private String processkod;

	@Column(name = "namn")
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	@Column(name = "verksamhetstyp")
	private String verksamhetstyp;

	@Column(name = "processgrup_id")
	private String processgrupId;

	@Column(name = "aktiv")
	private Boolean aktiv;

	public static ProcessEntity create() {
		return new ProcessEntity();
	}

	public String getProcesskod() {
		return this.processkod;
	}

	public void setProcesskod(final String processkod) {
		this.processkod = processkod;
	}

	public ProcessEntity withProcesskod(final String processkod) {
		this.processkod = processkod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public ProcessEntity withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public String getBeskrivning() {
		return this.beskrivning;
	}

	public void setBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public ProcessEntity withBeskrivning(final String beskrivning) {
		this.beskrivning = beskrivning;
		return this;
	}

	public String getVerksamhetstyp() {
		return this.verksamhetstyp;
	}

	public void setVerksamhetstyp(final String verksamhetstyp) {
		this.verksamhetstyp = verksamhetstyp;
	}

	public ProcessEntity withVerksamhetstyp(final String verksamhetstyp) {
		this.verksamhetstyp = verksamhetstyp;
		return this;
	}

	public String getProcessgrupId() {
		return this.processgrupId;
	}

	public void setProcessgrupId(final String processgrupId) {
		this.processgrupId = processgrupId;
	}

	public ProcessEntity withProcessgrupId(final String processgrupId) {
		this.processgrupId = processgrupId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public ProcessEntity withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final ProcessEntity that)) {
			return false;
		}
		return Objects.equals(this.getId(), that.getId()) &&
			Objects.equals(this.processkod, that.processkod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.beskrivning, that.beskrivning) &&
			Objects.equals(this.verksamhetstyp, that.verksamhetstyp) &&
			Objects.equals(this.processgrupId, that.processgrupId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.processkod, this.namn, this.beskrivning, this.verksamhetstyp,
			this.processgrupId, this.aktiv);
	}

	@Override
	public String toString() {
		return "ProcessEntity{" +
			"id='" + this.getId() + '\'' +
			", processkod='" + this.processkod + '\'' +
			", namn='" + this.namn + '\'' +
			", beskrivning='" + this.beskrivning + '\'' +
			", verksamhetstyp='" + this.verksamhetstyp + '\'' +
			", processgrupId='" + this.processgrupId + '\'' +
			", aktiv=" + this.aktiv +
			'}';
	}
}
