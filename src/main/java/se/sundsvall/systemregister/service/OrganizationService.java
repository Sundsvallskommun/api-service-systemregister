package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.integration.db.OrganizationRepository;
import se.sundsvall.systemregister.integration.db.model.OrganizationEntity;
import se.sundsvall.systemregister.service.mapper.OrganizationMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class OrganizationService {

	private static final String ENTITY_NOT_FOUND = "Organization with ID %s not found";

	private final OrganizationRepository organizationRepository;

	public OrganizationService(final OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public Organization create(final Organization organization) {
		final OrganizationEntity entity = OrganizationMapper.toOrganizationEntity(organization);
		final OrganizationEntity saved = organizationRepository.save(entity);
		return OrganizationMapper.toOrganization(saved);
	}

	public Organization getById(final String id) {
		final OrganizationEntity entity = organizationRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return OrganizationMapper.toOrganization(entity);
	}

	public List<Organization> getAll() {
		final List<OrganizationEntity> entities = organizationRepository.findAll();
		return OrganizationMapper.toOrganizationList(entities);
	}

	public List<Organization> getAllByLevel(final Integer level) {
		final List<OrganizationEntity> entities = organizationRepository.findByLevel(level);
		return OrganizationMapper.toOrganizationList(entities);
	}

	public List<Organization> getChildren(final String parentId) {
		final List<OrganizationEntity> entities = organizationRepository.findByParentId(parentId);
		return OrganizationMapper.toOrganizationList(entities);
	}

	public Organization update(final String id, final Organization organization) {
		final OrganizationEntity entity = organizationRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		OrganizationMapper.updateOrganizationEntity(entity, organization);
		final OrganizationEntity saved = organizationRepository.save(entity);
		return OrganizationMapper.toOrganization(saved);
	}

	public void delete(final String id) {
		if (!organizationRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		organizationRepository.deleteById(id);
	}
}
