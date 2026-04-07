package se.sundsvall.systemregister.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Schema(description = "KLASSA Processgrupp (Process group)")
public class KlassaProcessgrupp {

	@Schema(description = "Processgrupp ID", example = "grupp-1")
	private Integer id;

	@Schema(description = "Process group code", example = "PG001")
	private String kod;

	@Schema(description = "Process group name", example = "Rekrytering")
	@NotBlank
	private String namn;

	@Schema(description = "Associated Verksamhetsomrade ID")
	private Integer verksamhetsomradeId;

	@Schema(description = "Process group is active")
	private Boolean aktiv;

	public static KlassaProcessgrupp create() {
		return new KlassaProcessgrupp();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public KlassaProcessgrupp withId(final Integer id) {
		this.id = id;
		return this;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(final String kod) {
		this.kod = kod;
	}

	public KlassaProcessgrupp withKod(final String kod) {
		this.kod = kod;
		return this;
	}

	public String getNamn() {
		return this.namn;
	}

	public void setNamn(final String namn) {
		this.namn = namn;
	}

	public KlassaProcessgrupp withNamn(final String namn) {
		this.namn = namn;
		return this;
	}

	public Integer getVerksamhetsomradeId() {
		return this.verksamhetsomradeId;
	}

	public void setVerksamhetsomradeId(final Integer verksamhetsomradeId) {
		this.verksamhetsomradeId = verksamhetsomradeId;
	}

	public KlassaProcessgrupp withVerksamhetsomradeId(final Integer verksamhetsomradeId) {
		this.verksamhetsomradeId = verksamhetsomradeId;
		return this;
	}

	public Boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
	}

	public KlassaProcessgrupp withAktiv(final Boolean aktiv) {
		this.aktiv = aktiv;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final KlassaProcessgrupp that)) {
			return false;
		}
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.kod, that.kod) &&
			Objects.equals(this.namn, that.namn) &&
			Objects.equals(this.verksamhetsomradeId, that.verksamhetsomradeId) &&
			Objects.equals(this.aktiv, that.aktiv);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.kod, this.namn, this.verksamhetsomradeId, this.aktiv);
	}

	@Override
	public String toString() {
		return "KlassaProcessgrupp{" +
			"id=" + this.id +
			", kod='" + this.kod + '\'' +
			", namn='" + this.namn + '\'' +
			", verksamhetsomradeId=" + this.verksamhetsomradeId +
			", aktiv=" + this.aktiv +
			'}';
	}
}
