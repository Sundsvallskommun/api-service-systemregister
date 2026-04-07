package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.integration.db.PersonuppgiftsbehandlingRepository;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;
import se.sundsvall.systemregister.service.mapper.PersonuppgiftsbehandlingMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class PersonuppgiftsbehandlingService {

	private static final String ENTITY_NOT_FOUND = "Personuppgiftsbehandling not found with id: %s";

	private final PersonuppgiftsbehandlingRepository repository;

	public PersonuppgiftsbehandlingService(final PersonuppgiftsbehandlingRepository repository) {
		this.repository = repository;
	}

	public Personuppgiftsbehandling create(final Personuppgiftsbehandling dto) {
		final var entity = PersonuppgiftsbehandlingMapper.toEntity(dto);
		final var savedEntity = this.repository.save(entity);
		return PersonuppgiftsbehandlingMapper.toDto(savedEntity);
	}

	public Personuppgiftsbehandling getById(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return PersonuppgiftsbehandlingMapper.toDto(entity);
	}

	public Personuppgiftsbehandling getByBehandlingId(final String behandlingId) {
		final var entities = this.repository.findByBehandlingId(behandlingId);
		if (entities.isEmpty()) {
			throw valueOf(NOT_FOUND, "Personuppgiftsbehandling not found with behandlingId: " + behandlingId);
		}
		return PersonuppgiftsbehandlingMapper.toDto(entities.getFirst());
	}

	public List<Personuppgiftsbehandling> getAll() {
		return this.repository.findAll()
			.stream()
			.map(PersonuppgiftsbehandlingMapper::toDto)
			.toList();
	}

	public List<Personuppgiftsbehandling> getAllByStatus(final String status) {
		return this.repository.findByStatus(ProcessingStatus.valueOf(status))
			.stream()
			.map(PersonuppgiftsbehandlingMapper::toDto)
			.toList();
	}

	public Personuppgiftsbehandling update(final String id, final Personuppgiftsbehandling dto) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));

		PersonuppgiftsbehandlingMapper.updateEntity(entity, dto);
		final var updatedEntity = this.repository.save(entity);
		return PersonuppgiftsbehandlingMapper.toDto(updatedEntity);
	}

	public void delete(final String id) {
		if (!this.repository.existsById(id)) {
			throw valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		this.repository.deleteById(id);
	}
}
