package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.integration.db.SecurityLevelDefinitionRepository;
import se.sundsvall.systemregister.service.mapper.SecurityLevelDefinitionMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class SecurityLevelDefinitionService {

	private static final String ENTITY_NOT_FOUND = "SecurityLevelDefinition not found with id: %s";

	private final SecurityLevelDefinitionRepository repository;

	public SecurityLevelDefinitionService(final SecurityLevelDefinitionRepository repository) {
		this.repository = repository;
	}

	public SecurityLevelDefinition create(final SecurityLevelDefinition dto) {
		final var entity = SecurityLevelDefinitionMapper.toSecurityLevelDefinitionEntity(dto);
		final var savedEntity = this.repository.save(entity);
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinition(savedEntity);
	}

	public SecurityLevelDefinition getById(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinition(entity);
	}

	public List<SecurityLevelDefinition> getAll() {
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(this.repository.findAll());
	}

	public List<SecurityLevelDefinition> getByDimensionAndLevel(final String dimension, final Integer level) {
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(
			this.repository.findByDimensionAndLevel(dimension, level));
	}

	public List<SecurityLevelDefinition> getByDimension(final String dimension) {
		return this.repository.findAll().stream()
			.filter(e -> dimension.equals(e.getDimension()))
			.map(SecurityLevelDefinitionMapper::toSecurityLevelDefinition)
			.toList();
	}

	public List<SecurityLevelDefinition> getActive() {
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinitions(
			this.repository.findByIsActive(Boolean.TRUE));
	}

	public SecurityLevelDefinition update(final String id, final SecurityLevelDefinition dto) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));

		final var updated = SecurityLevelDefinitionMapper.updateEntity(entity, dto);

		final var savedEntity = this.repository.save(updated);
		return SecurityLevelDefinitionMapper.toSecurityLevelDefinition(savedEntity);
	}

	public void delete(final String id) {
		if (!this.repository.existsById(id)) {
			throw valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		this.repository.deleteById(id);
	}
}
