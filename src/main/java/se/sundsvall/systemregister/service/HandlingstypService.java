package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Handlingstyp;
import se.sundsvall.systemregister.integration.db.HandlingstypRepository;
import se.sundsvall.systemregister.service.mapper.HandlingstypMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class HandlingstypService {

	private final HandlingstypRepository repository;

	public HandlingstypService(final HandlingstypRepository repository) {
		this.repository = repository;
	}

	public Handlingstyp create(final Handlingstyp model) {
		final var entity = HandlingstypMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return HandlingstypMapper.toModel(savedEntity);
	}

	public Handlingstyp getById(final String id) {
		return this.repository.findById(id)
			.map(HandlingstypMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "Handlingstyp not found"));
	}

	public List<Handlingstyp> getAll() {
		return this.repository.findAll().stream()
			.map(HandlingstypMapper::toModel)
			.toList();
	}

	public List<Handlingstyp> getByIhPlanId(final String ihPlanId) {
		return this.repository.findByIhPlanId(ihPlanId).stream()
			.map(HandlingstypMapper::toModel)
			.toList();
	}

	public Handlingstyp update(final String id, final Handlingstyp model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "Handlingstyp not found"));

		final var updatedEntity = HandlingstypMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return HandlingstypMapper.toModel(savedEntity);
	}

	public void delete(final String id) {
		this.repository.deleteById(id);
	}
}
