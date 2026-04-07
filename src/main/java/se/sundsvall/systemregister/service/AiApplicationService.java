package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.integration.db.AiApplicationRepository;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;
import se.sundsvall.systemregister.service.mapper.AiApplicationMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class AiApplicationService {

	private static final String NOT_FOUND_WITH_ID = "AiApplication not found with id: ";
	private final AiApplicationRepository repository;

	public AiApplicationService(final AiApplicationRepository repository) {
		this.repository = repository;
	}

	public AiApplication create(final AiApplication dto) {
		final var entity = AiApplicationMapper.toEntity(dto);
		final var savedEntity = this.repository.save(entity);
		return AiApplicationMapper.toDto(savedEntity);
	}

	public AiApplication getById(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, NOT_FOUND_WITH_ID + id));
		return AiApplicationMapper.toDto(entity);
	}

	public AiApplication getByAiApplicationId(final String aiApplicationId) {
		final var entities = this.repository.findByAiApplicationId(aiApplicationId);
		if (entities.isEmpty()) {
			throw valueOf(NOT_FOUND, "AiApplication not found with aiApplicationId: " + aiApplicationId);
		}
		return AiApplicationMapper.toDto(entities.getFirst());
	}

	public List<AiApplication> getAll() {
		return this.repository.findAll()
			.stream()
			.map(AiApplicationMapper::toDto)
			.toList();
	}

	public List<AiApplication> getAllByStatus(final String status) {
		return this.repository.findByStatus(AIApplicationStatus.valueOf(status))
			.stream()
			.map(AiApplicationMapper::toDto)
			.toList();
	}

	public List<AiApplication> getAllByRiskCategory(final String riskCategory) {
		return this.repository.findByRiskCategory(AIRiskCategory.valueOf(riskCategory))
			.stream()
			.map(AiApplicationMapper::toDto)
			.toList();
	}

	public List<AiApplication> getAllBySystemId(final String systemId) {
		return this.repository.findBySystemId(systemId)
			.stream()
			.map(AiApplicationMapper::toDto)
			.toList();
	}

	public AiApplication update(final String id, final AiApplication dto) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, NOT_FOUND_WITH_ID + id));

		AiApplicationMapper.updateEntity(entity, dto);
		final var updatedEntity = this.repository.save(entity);
		return AiApplicationMapper.toDto(updatedEntity);
	}

	public void delete(final String id) {
		if (!this.repository.existsById(id)) {
			throw valueOf(NOT_FOUND, NOT_FOUND_WITH_ID + id);
		}
		this.repository.deleteById(id);
	}
}
