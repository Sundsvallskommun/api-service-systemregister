package se.sundsvall.systemregister.service;

import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.integration.db.InformationsklassningRepository;
import se.sundsvall.systemregister.service.mapper.InformationsklassningMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class InformationsklassningService {

	private final InformationsklassningRepository repository;

	public InformationsklassningService(final InformationsklassningRepository repository) {
		this.repository = repository;
	}

	public Informationsklassning createOrUpdate(final String handlingstypId, final Informationsklassning model) {
		final var existing = this.repository.findByHandlingstypId(handlingstypId);

		final var existingId = existing.isEmpty() ? null : existing.getFirst().getId();
		final var entity = InformationsklassningMapper.toEntityForUpsert(model, handlingstypId, existingId);
		final var savedEntity = this.repository.save(entity);
		return InformationsklassningMapper.toModel(savedEntity);
	}

	public Informationsklassning getByHandlingstypId(final String handlingstypId) {
		final var existing = this.repository.findByHandlingstypId(handlingstypId);
		if (existing.isEmpty()) {
			throw valueOf(NOT_FOUND, "Informationsklassning not found");
		}
		return InformationsklassningMapper.toModel(existing.getFirst());
	}

	public void delete(final String handlingstypId) {
		final var existing = this.repository.findByHandlingstypId(handlingstypId);
		if (!existing.isEmpty()) {
			this.repository.deleteById(existing.getFirst().getId());
		}
	}
}
