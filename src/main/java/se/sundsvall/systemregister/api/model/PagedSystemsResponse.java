package se.sundsvall.systemregister.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;
import se.sundsvall.dept44.models.api.paging.PagingMetaData;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Schema(description = "Paginated systems response", accessMode = READ_ONLY)
public class PagedSystemsResponse {

	@JsonProperty("_meta")
	@Schema(implementation = PagingMetaData.class)
	private PagingMetaData metadata;

	@ArraySchema(schema = @Schema(implementation = System.class))
	private List<System> systems;

	public static PagedSystemsResponse create() {
		return new PagedSystemsResponse();
	}

	public PagingMetaData getMetadata() {
		return this.metadata;
	}

	public void setMetadata(final PagingMetaData metadata) {
		this.metadata = metadata;
	}

	public PagedSystemsResponse withMetadata(final PagingMetaData metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<System> getSystems() {
		return this.systems;
	}

	public void setSystems(final List<System> systems) {
		this.systems = systems;
	}

	public PagedSystemsResponse withSystems(final List<System> systems) {
		this.systems = systems;
		return this;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PagedSystemsResponse that)) {
			return false;
		}
		return Objects.equals(this.metadata, that.metadata) &&
			Objects.equals(this.systems, that.systems);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.metadata, this.systems);
	}

	@Override
	public String toString() {
		return "PagedSystemsResponse{" +
			"metadata=" + this.metadata +
			", systems=" + this.systems +
			'}';
	}
}
