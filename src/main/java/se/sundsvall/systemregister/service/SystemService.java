package se.sundsvall.systemregister.service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.models.api.paging.PagingMetaData;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.PagedSystemsResponse;
import se.sundsvall.systemregister.api.model.System;
import se.sundsvall.systemregister.integration.db.SystemRepository;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;
import se.sundsvall.systemregister.service.mapper.SystemMapper;

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
		return SystemMapper.toSystem(saved);
	}

	public System getById(final String id) {
		final SystemEntity entity = systemRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return SystemMapper.toSystem(entity);
	}

	public PagedSystemsResponse search(final String status, final String search, final int page, final int limit) {
		final Specification<SystemEntity> spec = buildSpecification(status, search);
		final var result = systemRepository.findAll(spec, PageRequest.of(page - 1, limit, Sort.by("name")));
		final var systems = result.getContent().stream().map(SystemMapper::toSystem).toList();
		return PagedSystemsResponse.create()
			.withSystems(systems)
			.withMetadata(PagingMetaData.create()
				.withPage(page)
				.withLimit(limit)
				.withCount(systems.size())
				.withTotalRecords(result.getTotalElements())
				.withTotalPages(result.getTotalPages()));
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

	private Specification<SystemEntity> buildSpecification(final String status, final String search) {
		return (root, _, cb) -> {
			final var predicates = new ArrayList<Predicate>();
			Optional.ofNullable(status).ifPresent(s -> predicates.add(cb.equal(root.get("status"), SystemStatus.valueOf(s.toUpperCase()))));
			Optional.ofNullable(search).ifPresent(s -> {
				final var pattern = "%" + s.toLowerCase() + "%";
				predicates.add(cb.or(
					cb.like(cb.lower(root.get("name")), pattern),
					cb.like(cb.lower(root.get("systemId")), pattern)));
			});
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
