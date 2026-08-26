package se.sundsvall.systemregister.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.system.PagedSystemsResponse;
import se.sundsvall.systemregister.api.model.system.System;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.integration.db.SystemRepository;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.specification.SystemSpecification;
import se.sundsvall.systemregister.service.mapper.SystemMapper;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SystemService {

	private static final String ENTITY_NOT_FOUND = "System with ID %s not found";

	private final SystemRepository systemRepository;

	public SystemService(final SystemRepository systemRepository) {
		this.systemRepository = systemRepository;
	}

	public System create(final System system) {
		final SystemEntity entity = SystemMapper.toSystemEntity(system);
		final SystemEntity saved = systemRepository.save(entity);
		final System result = SystemMapper.toSystem(saved);
		if (result == null) {
			throw Problem.valueOf(INTERNAL_SERVER_ERROR, "failed to create system");
		}
		return result;
	}

	public System getById(final String id) {
		final SystemEntity entity = systemRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return SystemMapper.toSystem(entity);
	}

	public PagedSystemsResponse search(final SystemSearchParameters searchParameters) {
		var pageable = PageRequest.of(searchParameters.getPage() - 1, searchParameters.getLimit(), searchParameters.sort());
		final Specification<SystemEntity> spec = SystemSpecification.createSpecification(searchParameters);
		final var result = systemRepository.findAll(spec, pageable);
		final var systems = result.getContent().stream().map(SystemMapper::toSystem).toList();
		return PagedSystemsResponse.create()
			.withSystems(systems)
			.withMetadata(PagingAndSortingMetaData.create()
				.withPageData(result));
	}

	public System update(final String id, final System system) {
		final SystemEntity entity = systemRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		SystemMapper.updateSystemEntity(entity, system);
		final SystemEntity saved = systemRepository.save(entity);
		return SystemMapper.toSystem(saved);
	}

	public void delete(final String id) {
		if (!systemRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		systemRepository.deleteById(id);
	}
}
