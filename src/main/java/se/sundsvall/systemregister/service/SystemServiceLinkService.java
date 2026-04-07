package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.SystemServiceLink;
import se.sundsvall.systemregister.integration.db.SystemServiceLinkRepository;
import se.sundsvall.systemregister.integration.db.model.SystemServiceLinkEntity;
import se.sundsvall.systemregister.service.mapper.SystemServiceLinkMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SystemServiceLinkService {

	private final SystemServiceLinkRepository systemServiceLinkRepository;

	public SystemServiceLinkService(final SystemServiceLinkRepository systemServiceLinkRepository) {
		this.systemServiceLinkRepository = systemServiceLinkRepository;
	}

	public SystemServiceLink create(final SystemServiceLink link) {
		final SystemServiceLinkEntity entity = SystemServiceLinkMapper.toSystemServiceLinkEntity(link);
		final SystemServiceLinkEntity saved = systemServiceLinkRepository.save(entity);
		return SystemServiceLinkMapper.toSystemServiceLink(saved);
	}

	public SystemServiceLink getById(final String id) {
		final SystemServiceLinkEntity entity = systemServiceLinkRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, "System service link with ID " + id + " not found"));
		return SystemServiceLinkMapper.toSystemServiceLink(entity);
	}

	public List<SystemServiceLink> getBySystemId(final String systemId) {
		final List<SystemServiceLinkEntity> entities = systemServiceLinkRepository.findBySystemId(systemId);
		return SystemServiceLinkMapper.toSystemServiceLinkList(entities);
	}

	public List<SystemServiceLink> getByServiceId(final String serviceId) {
		final List<SystemServiceLinkEntity> entities = systemServiceLinkRepository.findByServiceId(serviceId);
		return SystemServiceLinkMapper.toSystemServiceLinkList(entities);
	}

	public void delete(final String id) {
		if (!systemServiceLinkRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, "System service link with ID " + id + " not found");
		}
		systemServiceLinkRepository.deleteById(id);
	}
}
