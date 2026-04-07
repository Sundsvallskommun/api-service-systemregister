package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.HandlingstypAnsvarig;
import se.sundsvall.systemregister.integration.db.HandlingstypAnsvarigRepository;
import se.sundsvall.systemregister.service.mapper.HandlingstypAnsvarigMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class HandlingstypAnsvarigService {

	private final HandlingstypAnsvarigRepository repository;

	public HandlingstypAnsvarigService(final HandlingstypAnsvarigRepository repository) {
		this.repository = repository;
	}

	public HandlingstypAnsvarig create(final HandlingstypAnsvarig model) {
		final var entity = HandlingstypAnsvarigMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return HandlingstypAnsvarigMapper.toModel(savedEntity);
	}

	public List<HandlingstypAnsvarig> getByHandlingstypId(final String handlingstypId) {
		return this.repository.findByHandlingstypId(handlingstypId).stream()
			.map(HandlingstypAnsvarigMapper::toModel)
			.toList();
	}

	public void delete(final String id) {
		this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "HandlingstypAnsvarig not found"));
		this.repository.deleteById(id);
	}
}
