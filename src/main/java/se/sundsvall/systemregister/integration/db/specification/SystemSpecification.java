package se.sundsvall.systemregister.integration.db.specification;

import java.util.Locale;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.NAME;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.OWNER_ORGANIZATION_ID;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.STATUS;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.SYSTEM_ID;
import static se.sundsvall.systemregister.integration.db.model.SystemEntity_.SYSTEM_MANAGER_ID;

public interface SystemSpecification {
	char ESCAPE_CHAR = '\\';

	static Specification<SystemEntity> createSpecification(final SystemSearchParameters parameters) {
		return Specification.allOf(
			withStatus(parameters.getStatus()),
			withSearch(parameters.getSearch()),
			withSystemManagerId(parameters.getSystemManagerId()),
			withOwnerOrganizationId(parameters.getOwnerOrganizationId()));
	}

	static Specification<SystemEntity> withStatus(final String status) {
		return SpecificationBuilder.buildEqualFilter(STATUS, Optional.ofNullable(status)
			.map(s -> SystemStatus.valueOf(s.toUpperCase(Locale.ROOT)))
			.orElse(null));
	}

	static Specification<SystemEntity> withSystemManagerId(final String systemManagerId) {
		return SpecificationBuilder.buildEqualFilter(SYSTEM_MANAGER_ID, systemManagerId);
	}

	static Specification<SystemEntity> withOwnerOrganizationId(final String ownerOrganizationId) {
		return SpecificationBuilder.buildEqualFilter(OWNER_ORGANIZATION_ID, ownerOrganizationId);
	}

	static Specification<SystemEntity> withSearch(final String search) {
		return (root, _, cb) -> {
			if (search == null) {
				return cb.and();
			}
			final var escaped = search.toLowerCase(Locale.ROOT)
				.replace("\\", "\\\\")
				.replace("%", "\\%")
				.replace("_", "\\_");
			final var pattern = "%" + escaped + "%";
			return cb.or(
				cb.like(cb.lower(root.get(NAME)), pattern, ESCAPE_CHAR),
				cb.like(cb.lower(root.get(SYSTEM_ID)), pattern, ESCAPE_CHAR));

		};
	}
}
