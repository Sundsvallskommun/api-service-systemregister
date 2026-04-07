package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Process;
import se.sundsvall.systemregister.integration.db.ProcessRepository;
import se.sundsvall.systemregister.service.mapper.ProcessMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class ProcessService {

	private static final String ENTITY_NOT_FOUND = "Process not found";

	private final ProcessRepository repository;

	public ProcessService(final ProcessRepository repository) {
		this.repository = repository;
	}

	public Process create(final Process model) {
		final var entity = ProcessMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return ProcessMapper.toModel(savedEntity);
	}

	public Process getById(final String id) {
		return this.repository.findById(id)
			.map(ProcessMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));
	}

	public Process getByProcesskod(final String processkod) {
		return this.repository.findByProcesskod(processkod)
			.map(ProcessMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));
	}

	public List<Process> getAll() {
		return this.repository.findAll().stream()
			.map(ProcessMapper::toModel)
			.toList();
	}

	public Process update(final String id, final Process model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));

		final var updatedEntity = ProcessMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return ProcessMapper.toModel(savedEntity);
	}

	public void delete(final String id) {
		this.repository.deleteById(id);
	}
}
