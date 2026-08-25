package se.sundsvall.systemregister.api.model.system;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import se.sundsvall.dept44.common.validators.annotation.MemberOf;
import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

@Schema(description = "System search parameters model")
@ParameterObject
public class SystemSearchParameters extends AbstractParameterPagingAndSortingBase {
	private static final int DEFAULT_LIMIT = 20;
	private static final List<String> DEFAULT_SORT = List.of("name");

	@MemberOf(value = SystemStatus.class, nullable = true, caseSensitive = false)
	@Schema(description = "Filter by status", allowableValues = {
		"PLANNED", "DEVELOPMENT", "PRODUCTION", "DEPRECATED", "RETIRED"
	}, example = "PRODUCTION")
	private String status;

	@Schema(description = "search parameters")
	private String search;

	@Schema(description = "managerId")
	private String systemManagerId;

	@Schema(description = "ownerOrganizationId")
	private String ownerOrganizationId;

	public SystemSearchParameters() {
		super(DEFAULT_LIMIT);
		setSortBy(DEFAULT_SORT);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSystemManagerId() {
		return systemManagerId;
	}

	public void setSystemManagerId(String systemManagerId) {
		this.systemManagerId = systemManagerId;
	}

	public String getOwnerOrganizationId() {
		return ownerOrganizationId;
	}

	public void setOwnerOrganizationId(String ownerOrganizationId) {
		this.ownerOrganizationId = ownerOrganizationId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!super.equals(o)) {
			return false;
		}
		if (!(o instanceof final SystemSearchParameters that)) {
			return false;
		}
		return Objects.equals(this.status, that.status) &&
			Objects.equals(this.search, that.search) &&
			Objects.equals(this.systemManagerId, that.systemManagerId) &&
			Objects.equals(this.ownerOrganizationId, that.ownerOrganizationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.status, this.search, this.systemManagerId, this.ownerOrganizationId);
	}

	@Override
	public String toString() {
		return "SystemSearchParameters{" +
			"status='" + status + '\'' +
			", search='" + search + '\'' +
			", systemManagerId='" + systemManagerId + '\'' +
			", ownerOrganizationId='" + ownerOrganizationId + '\'' +
			", page=" + page +
			", limit=" + limit +
			", sortBy=" + sortBy +
			", sortDirection=" + sortDirection +
			'}';
	}
}
