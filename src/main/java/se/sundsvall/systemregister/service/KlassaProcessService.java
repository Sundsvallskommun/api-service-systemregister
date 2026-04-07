package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.KlassaProcess;
import se.sundsvall.systemregister.integration.db.KlassaProcessRepository;
import se.sundsvall.systemregister.service.mapper.KlassaProcessMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class KlassaProcessService {

	private final KlassaProcessRepository repository;

	public KlassaProcessService(final KlassaProcessRepository repository) {
		this.repository = repository;
	}

	public KlassaProcess create(final KlassaProcess model) {
		final var entity = KlassaProcessMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return KlassaProcessMapper.toModel(savedEntity);
	}

	public KlassaProcess getById(final Integer id) {
		return this.repository.findById(id)
			.map(KlassaProcessMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaProcess not found"));
	}

	public List<KlassaProcess> getAll() {
		return this.repository.findAll().stream()
			.map(KlassaProcessMapper::toModel)
			.toList();
	}

	public List<KlassaProcess> getByProcessgrupId(final Integer processgrupId) {
		return this.repository.findByProcessgrupId(processgrupId).stream()
			.map(KlassaProcessMapper::toModel)
			.toList();
	}

	public KlassaProcess update(final Integer id, final KlassaProcess model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaProcess not found"));

		final var updatedEntity = KlassaProcessMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return KlassaProcessMapper.toModel(savedEntity);
	}

	public void delete(final Integer id) {
		this.repository.deleteById(id);
	}
}
