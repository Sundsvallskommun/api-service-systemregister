package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.integration.db.SupplierRepository;
import se.sundsvall.systemregister.integration.db.model.SupplierEntity;
import se.sundsvall.systemregister.service.mapper.SupplierMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SupplierService {

	private static final String ENTITY_NOT_FOUND = "Supplier with ID %s not found";

	private final SupplierRepository supplierRepository;

	public SupplierService(final SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	public Supplier create(final Supplier supplier) {
		final SupplierEntity entity = SupplierMapper.toSupplierEntity(supplier);
		final SupplierEntity saved = supplierRepository.save(entity);
		return SupplierMapper.toSupplier(saved);
	}

	public Supplier getById(final String id) {
		final SupplierEntity entity = supplierRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return SupplierMapper.toSupplier(entity);
	}

	public List<Supplier> getAll() {
		final List<SupplierEntity> entities = supplierRepository.findAll();
		return SupplierMapper.toSupplierList(entities);
	}

	public List<Supplier> getAllActive() {
		final List<SupplierEntity> entities = supplierRepository.findByIsActive(true);
		return SupplierMapper.toSupplierList(entities);
	}

	public Supplier update(final String id, final Supplier supplier) {
		final SupplierEntity entity = supplierRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		SupplierMapper.updateSupplierEntity(entity, supplier);
		final SupplierEntity saved = supplierRepository.save(entity);
		return SupplierMapper.toSupplier(saved);
	}

	public void delete(final String id) {
		if (!supplierRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		supplierRepository.deleteById(id);
	}
}
