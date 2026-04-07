package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.CriticalityLevel;
import se.sundsvall.systemregister.integration.db.CriticalityLevelRepository;
import se.sundsvall.systemregister.integration.db.model.CriticalityLevelEntity;
import se.sundsvall.systemregister.service.mapper.CriticalityLevelMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class CriticalityLevelService {

	private static final String ENTITY_NOT_FOUND = "Criticality level with ID %s not found";

	private final CriticalityLevelRepository criticalityLevelRepository;

	public CriticalityLevelService(final CriticalityLevelRepository criticalityLevelRepository) {
		this.criticalityLevelRepository = criticalityLevelRepository;
	}

	public CriticalityLevel create(final CriticalityLevel criticalityLevel) {
		final CriticalityLevelEntity entity = CriticalityLevelMapper.toCriticalityLevelEntity(criticalityLevel);
		final CriticalityLevelEntity saved = criticalityLevelRepository.save(entity);
		return CriticalityLevelMapper.toCriticalityLevel(saved);
	}

	public CriticalityLevel getById(final String id) {
		final CriticalityLevelEntity entity = criticalityLevelRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return CriticalityLevelMapper.toCriticalityLevel(entity);
	}

	public List<CriticalityLevel> getAll() {
		final List<CriticalityLevelEntity> entities = criticalityLevelRepository.findAll();
		return CriticalityLevelMapper.toCriticalityLevelList(entities);
	}

	public CriticalityLevel update(final String id, final CriticalityLevel criticalityLevel) {
		final CriticalityLevelEntity entity = criticalityLevelRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		CriticalityLevelMapper.updateCriticalityLevelEntity(entity, criticalityLevel);
		final CriticalityLevelEntity saved = criticalityLevelRepository.save(entity);
		return CriticalityLevelMapper.toCriticalityLevel(saved);
	}

	public void delete(final String id) {
		if (!criticalityLevelRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		criticalityLevelRepository.deleteById(id);
	}
}
