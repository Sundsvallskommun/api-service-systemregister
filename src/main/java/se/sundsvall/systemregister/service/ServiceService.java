package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.integration.db.ServiceRepository;
import se.sundsvall.systemregister.integration.db.model.ServiceEntity;
import se.sundsvall.systemregister.service.mapper.ServiceMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class ServiceService {

	private static final String ENTITY_NOT_FOUND = "Service with ID %s not found";

	private final ServiceRepository serviceRepository;

	public ServiceService(final ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	public ServiceModel create(final ServiceModel service) {
		final ServiceEntity entity = ServiceMapper.toServiceEntity(service);
		final ServiceEntity saved = serviceRepository.save(entity);
		return ServiceMapper.toService(saved);
	}

	public ServiceModel getById(final String id) {
		final ServiceEntity entity = serviceRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return ServiceMapper.toService(entity);
	}

	public List<ServiceModel> getAll() {
		final List<ServiceEntity> entities = serviceRepository.findAll();
		return ServiceMapper.toServiceList(entities);
	}

	public List<ServiceModel> getAllByServiceType(final String serviceType) {
		final List<ServiceEntity> entities = serviceRepository.findByServiceType(serviceType);
		return ServiceMapper.toServiceList(entities);
	}

	public ServiceModel update(final String id, final ServiceModel service) {
		final ServiceEntity entity = serviceRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		ServiceMapper.updateServiceEntity(entity, service);
		final ServiceEntity saved = serviceRepository.save(entity);
		return ServiceMapper.toService(saved);
	}

	public void delete(final String id) {
		if (!serviceRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		serviceRepository.deleteById(id);
	}
}
