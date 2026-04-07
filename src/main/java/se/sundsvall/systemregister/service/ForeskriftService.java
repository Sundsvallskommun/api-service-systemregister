package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Foreskrift;
import se.sundsvall.systemregister.integration.db.ForeskriftRepository;
import se.sundsvall.systemregister.service.mapper.ForeskriftMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class ForeskriftService {

	private static final String ENTITY_NOT_FOUND = "Foreskrift not found";

	private final ForeskriftRepository repository;

	public ForeskriftService(final ForeskriftRepository repository) {
		this.repository = repository;
	}

	public Foreskrift create(final Foreskrift model) {
		final var entity = ForeskriftMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return ForeskriftMapper.toModel(savedEntity);
	}

	public Foreskrift getById(final String id) {
		return this.repository.findById(id)
			.map(ForeskriftMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));
	}

	public Foreskrift getByBeteckning(final String beteckning) {
		return this.repository.findByBeteckning(beteckning)
			.map(ForeskriftMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));
	}

	public List<Foreskrift> getAll() {
		return this.repository.findAll().stream()
			.map(ForeskriftMapper::toModel)
			.toList();
	}

	public Foreskrift update(final String id, final Foreskrift model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND));

		final var updatedEntity = ForeskriftMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return ForeskriftMapper.toModel(savedEntity);
	}

	public void delete(final String id) {
		this.repository.deleteById(id);
	}
}
