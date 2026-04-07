package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.SystemDependency;
import se.sundsvall.systemregister.integration.db.SystemDependencyRepository;
import se.sundsvall.systemregister.integration.db.model.SystemDependencyEntity;
import se.sundsvall.systemregister.service.mapper.SystemDependencyMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SystemDependencyService {

	private final SystemDependencyRepository systemDependencyRepository;

	public SystemDependencyService(final SystemDependencyRepository systemDependencyRepository) {
		this.systemDependencyRepository = systemDependencyRepository;
	}

	public SystemDependency create(final SystemDependency dependency) {
		final SystemDependencyEntity entity = SystemDependencyMapper.toSystemDependencyEntity(dependency);
		final SystemDependencyEntity saved = systemDependencyRepository.save(entity);
		return SystemDependencyMapper.toSystemDependency(saved);
	}

	public SystemDependency getById(final String id) {
		final SystemDependencyEntity entity = systemDependencyRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "System dependency with ID " + id + " not found"));
		return SystemDependencyMapper.toSystemDependency(entity);
	}

	public List<SystemDependency> getBySourceSystemId(final String sourceSystemId) {
		final List<SystemDependencyEntity> entities = systemDependencyRepository.findBySourceSystemId(sourceSystemId);
		return SystemDependencyMapper.toSystemDependencyList(entities);
	}

	public List<SystemDependency> getByTargetSystemId(final String targetSystemId) {
		final List<SystemDependencyEntity> entities = systemDependencyRepository.findByTargetSystemId(targetSystemId);
		return SystemDependencyMapper.toSystemDependencyList(entities);
	}

	public void delete(final String id) {
		if (!systemDependencyRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, "System dependency with ID " + id + " not found");
		}
		systemDependencyRepository.deleteById(id);
	}
}
