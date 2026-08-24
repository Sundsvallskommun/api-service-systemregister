package se.sundsvall.systemregister.api.model.system;

import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;

public class SystemSearchParameters extends AbstractParameterPagingAndSortingBase {
	private String status;
	private String search;
	private String systemManagerId;
	private String ownerOrganizationId;

	public SystemSearchParameters() {
		super(20);
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
}
