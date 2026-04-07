package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.integration.db.EventLogRepository;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.service.mapper.EventLogMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class EventLogService {

	private static final String ENTITY_NOT_FOUND = "EventLog not found with id: %s";

	private final EventLogRepository repository;

	public EventLogService(final EventLogRepository repository) {
		this.repository = repository;
	}

	public EventLog create(final EventLog dto) {
		final var entity = EventLogMapper.toEventLogEntity(dto);
		final var savedEntity = this.repository.save(entity);
		return EventLogMapper.toEventLog(savedEntity);
	}

	public EventLog getById(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return EventLogMapper.toEventLog(entity);
	}

	public List<EventLog> getByEntityTypeAndEntityId(final String entityType, final String entityId) {
		return EventLogMapper.toEventLogs(
			this.repository.findByEntityTypeAndEntityIdOrderByPerformedAtDesc(EntityType.valueOf(entityType), entityId));
	}

	public List<EventLog> getAll() {
		return EventLogMapper.toEventLogs(this.repository.findAll());
	}

	public EventLog acknowledge(final String id) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));

		final var updated = EventLogMapper.acknowledgeEntity(entity);
		final var savedEntity = this.repository.save(updated);
		return EventLogMapper.toEventLog(savedEntity);
	}

	public void delete(final String id) {
		if (!this.repository.existsById(id)) {
			throw valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		this.repository.deleteById(id);
	}
}
