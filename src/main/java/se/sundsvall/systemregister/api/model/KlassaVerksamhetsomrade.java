package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "KLASSA Verksamhetsomrade (Activity area)")
public class KlassaVerksamhetsomrade {

	@Schema(description = "Verksamhetsomrade ID", example = "omrade-1")
	private Integer id;

	@Schema(description = "Activity area code", example = "101")
	private String kod;

	@Schema(description = "Activity area name", example = "Personadministration")
	@NotBlank
	private String namn;

	@Schema(description = "Associated Verksamhetstyp ID")
	private Integer verksamhetstypId;

	@Schema(description = "Activity area is active")
	private Boolean aktiv;

	public static KlassaVerksamhetsomrade create() {
		return new KlassaVerksamhetsomrade();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaVerksamhetsomrade withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KlassaVerksamhetsomrade withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaVerksamhetsomrade withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getVerksamhetstypId() {
		return this.verksamhetstypId;
	}

	public void setVerksamhetstypId(final Integer verksamhetstypId) {
		this.verksamhetstypId = verksamhetstypId;
	}

	public KlassaVerksamhetsomrade withVerksamhetstypId(final Integer verksamhetstypId) {
		this.verksamhetstypId = verksamhetstypId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public KlassaVerksamhetsomrade withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaVerksamhetsomrade that)) {
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
		return "KlassaVerksamhetsomrade{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", verksamhetstypId=" + this.verksamhetstypId +
			", aktiv=" + this.aktiv +
			'}';
	}
}
