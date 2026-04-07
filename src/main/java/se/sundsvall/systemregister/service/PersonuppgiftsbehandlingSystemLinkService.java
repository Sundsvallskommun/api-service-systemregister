package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.integration.db.PersonuppgiftsbehandlingSystemLinkRepository;
import se.sundsvall.systemregister.service.mapper.PersonuppgiftsbehandlingSystemLinkMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class PersonuppgiftsbehandlingSystemLinkService {

	private final PersonuppgiftsbehandlingSystemLinkRepository repository;

	public PersonuppgiftsbehandlingSystemLinkService(final PersonuppgiftsbehandlingSystemLinkRepository repository) {
		this.repository = repository;
	}

	public PersonuppgiftsbehandlingSystemLink create(final PersonuppgiftsbehandlingSystemLink dto) {
		final var entity = PersonuppgiftsbehandlingSystemLinkMapper.toEntity(dto);
		final var savedEntity = this.repository.save(entity);
		return PersonuppgiftsbehandlingSystemLinkMapper.toDto(savedEntity);
	}

	public PersonuppgiftsbehandlingSystemLink getById(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "PersonuppgiftsbehandlingSystemLink not found with id: " + id));
		return PersonuppgiftsbehandlingSystemLinkMapper.toDto(entity);
	}

	public List<PersonuppgiftsbehandlingSystemLink> getByBehandlingId(final String behandlingId) {
		return this.repository.findByBehandlingId(behandlingId)
			.stream()
			.map(PersonuppgiftsbehandlingSystemLinkMapper::toDto)
			.toList();
	}

	public List<PersonuppgiftsbehandlingSystemLink> getBySystemId(final String systemId) {
		return this.repository.findBySystemId(systemId)
			.stream()
			.map(PersonuppgiftsbehandlingSystemLinkMapper::toDto)
			.toList();
	}

	public void delete(final String id) {
		if (!this.repository.existsById(id)) {
			throw valueOf(NOT_FOUND, "PersonuppgiftsbehandlingSystemLink not found with id: " + id);
		}
		this.repository.deleteById(id);
	}
}
