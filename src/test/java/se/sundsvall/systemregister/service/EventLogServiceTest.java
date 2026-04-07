package se.sundsvall.systemregister.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.integration.db.EventLogRepository;
import se.sundsvall.systemregister.integration.db.model.EventLogEntity;
import se.sundsvall.systemregister.integration.db.model.enums.EntityType;
import se.sundsvall.systemregister.integration.db.model.enums.EventType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventLogServiceTest {

	@Mock
	private EventLogRepository repository;

	@InjectMocks
	private EventLogService service;

	@Test
	void create() {
		final var dto = EventLog.create()
			.withEntityType("SYSTEM")
			.withEntityId("entity-id")
			.withEventType("CREATED");
		final var entity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM)
			.withEntityId("entity-id")
			.withEventType(EventType.CREATED);

		when(this.repository.save(any(EventLogEntity.class))).thenReturn(entity);

		final var result = this.service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo("SYSTEM");
		assertThat(result.getEntityId()).isEqualTo("entity-id");
		assertThat(result.getEventType()).isEqualTo("CREATED");
		verify(this.repository).save(any(EventLogEntity.class));
	}

	@Test
	void getById() {
		final var entity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM)
			.withEntityId("entity-id");

		when(this.repository.findById("id-123")).thenReturn(Optional.of(entity));

		final var result = this.service.getById("id-123");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getEntityType()).isEqualTo("SYSTEM");
		verify(this.repository).findById("id-123");
	}

	@Test
	void getByIdNotFound() {
		when(this.repository.findById("id-123")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> this.service.getById("id-123"))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).findById("id-123");
	}

	@Test
	void getByEntityTypeAndEntityId() {
		final var entity1 = EventLogEntity.create()
			.withId("id-1")
			.withEntityType(EntityType.SYSTEM)
			.withEntityId("entity-id");
		final var entity2 = EventLogEntity.create()
			.withId("id-2")
			.withEntityType(EntityType.SYSTEM)
			.withEntityId("entity-id");

		when(this.repository.findByEntityTypeAndEntityIdOrderByPerformedAtDesc(EntityType.SYSTEM, "entity-id"))
			.thenReturn(List.of(entity1, entity2));

		final var result = this.service.getByEntityTypeAndEntityId("SYSTEM", "entity-id");

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		verify(this.repository).findByEntityTypeAndEntityIdOrderByPerformedAtDesc(EntityType.SYSTEM, "entity-id");
	}

	@Test
	void getAll() {
		final var entity1 = EventLogEntity.create()
			.withId("id-1")
			.withEntityType(EntityType.SYSTEM);
		final var entity2 = EventLogEntity.create()
			.withId("id-2")
			.withEntityType(EntityType.SERVICE);

		when(this.repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = this.service.getAll();

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		verify(this.repository).findAll();
	}

	@Test
	void acknowledge() {
		final var entity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM)
			.withAcknowledgedAt(null);
		final var acknowledgedEntity = EventLogEntity.create()
			.withId("id-123")
			.withEntityType(EntityType.SYSTEM)
			.withAcknowledgedAt(OffsetDateTime.now());

		when(this.repository.findById("id-123")).thenReturn(Optional.of(entity));
		when(this.repository.save(any(EventLogEntity.class))).thenReturn(acknowledgedEntity);

		final var result = this.service.acknowledge("id-123");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-123");
		assertThat(result.getAcknowledgedAt()).isNotNull();
		verify(this.repository).findById("id-123");
		verify(this.repository).save(any(EventLogEntity.class));
	}

	@Test
	void acknowledgeNotFound() {
		when(this.repository.findById("id-123")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> this.service.acknowledge("id-123"))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).findById("id-123");
	}

	@Test
	void delete() {
		when(this.repository.existsById("id-123")).thenReturn(true);

		this.service.delete("id-123");

		verify(this.repository).existsById("id-123");
		verify(this.repository).deleteById("id-123");
	}

	@Test
	void deleteNotFound() {
		when(this.repository.existsById("id-123")).thenReturn(false);

		assertThatThrownBy(() -> this.service.delete("id-123"))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).existsById("id-123");
	}
}
