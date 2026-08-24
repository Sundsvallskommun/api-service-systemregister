package se.sundsvall.systemregister.integration.db.specification;

import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static java.util.Objects.nonNull;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.NAME;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.OWNER_ORGANIZATION_ID;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.STATUS;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.SYSTEM_ID;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.SYSTEM_MANAGER_ID;

public interface SystemSpecification {

	SpecificationBuilder<SystemEntity> BUILDER = new SpecificationBuilder<>();

	static Specification<SystemEntity> createSpecification(final SystemSearchParameters parameters) {
		return Specification.allOf(
			withStatus(parameters.getStatus()),
			withSearch(parameters.getSearch()),
			withSystemManagerId(parameters.getSystemManagerId()),
			withOwnerOrganizationId(parameters.getOwnerOrganizationId()));
	}

	static Specification<SystemEntity> withStatus(final String status) {
		return BUILDER.buildEqualFilter(STATUS, nonNull(status) ? SystemStatus.valueOf(status.toUpperCase()) : null);
	}

	static Specification<SystemEntity> withSystemManagerId(final String systemManagerId) {
		return BUILDER.buildEqualFilter(SYSTEM_MANAGER_ID, systemManagerId);
	}

	static Specification<SystemEntity> withOwnerOrganizationId(final String ownerOrganizationId) {
		return BUILDER.buildEqualFilter(OWNER_ORGANIZATION_ID, ownerOrganizationId);
	}

	static Specification<SystemEntity> withSearch(final String search) {
		return (root, _, cb) -> {
			if (search == null) {
				return cb.and();
			}
			final var pattern = "%" + search.toLowerCase() + "%";
			return cb.or(
				cb.like(cb.lower(root.get(NAME)), pattern),
				cb.like(cb.lower(root.get(SYSTEM_ID)), pattern));

		};
	}
}
