package se.sundsvall.systemregister.integration.db.model;

/**
 * Common interface for all code list (kodtabell) entities.
 * All Kod entities share id, kod, namn, ordning.
 * Some have additional fields (beskrivning, lagrum, gallringsfristAr)
 * which default to null.
 */
public interface KodEntity {

	Integer getId();

	void setId(Integer id);

	String getKod();

	void setKod(String kod);

	String getNamn();

	void setNamn(String namn);

	Integer getOrdning();

	void setOrdning(Integer ordning);

	default String getBeskrivning() {
		return null;
	}

	default void setBeskrivning(final String beskrivning) {
		// no-op for entities without this field
	}

	default String getLagrum() {
		return null;
	}

	default void setLagrum(final String lagrum) {
		// no-op for entities without this field
	}

	default Integer getGallringsfristAr() {
		return null;
	}

	default void setGallringsfristAr(final Integer gallringsfristAr) {
		// no-op for entities without this field
	}
}
