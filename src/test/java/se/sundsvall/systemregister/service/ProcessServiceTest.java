package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Process;
import se.sundsvall.systemregister.integration.db.ProcessRepository;
import se.sundsvall.systemregister.integration.db.model.ProcessEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessServiceTest {

	@Mock
	private ProcessRepository repository;

	@InjectMocks
	private ProcessService service;

	@Test
	void create() {
		final var dto = Process.create()
			.withProcesskod("PROC-001")
			.withNamn("Test Process")
			.withAktiv(true);

		final var savedEntity = ProcessEntity.create()
			.withProcesskod("PROC-001")
			.withNamn("Test Process")
			.withAktiv(true);

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getProcesskod()).isEqualTo("PROC-001");
		assertThat(result.getNamn()).isEqualTo("Test Process");
		assertThat(result.getAktiv()).isTrue();
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = ProcessEntity.create()
			.withProcesskod("PROC-001")
			.withNamn("Test Process");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getProcesskod()).isEqualTo("PROC-001");
		verify(repository).findById("id-001");
	}

	@Test
	void getByIdNotFound() {
		when(repository.findById("id-001")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getById("id-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById("id-001");
	}

	@Test
	void getAll() {
		final var entity1 = ProcessEntity.create()
			.withProcesskod("PROC-001");
		final var entity2 = ProcessEntity.create()
			.withProcesskod("PROC-002");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getProcesskod()).isEqualTo("PROC-001");
		assertThat(result.get(1).getProcesskod()).isEqualTo("PROC-002");
		verify(repository).findAll();
	}

	@Test
	void getByProcesskod() {
		final var entity = ProcessEntity.create()
			.withProcesskod("PROC-001")
			.withNamn("Test Process");

		when(repository.findByProcesskod("PROC-001")).thenReturn(Optional.of(entity));

		final var result = service.getByProcesskod("PROC-001");

		assertThat(result).isNotNull();
		assertThat(result.getProcesskod()).isEqualTo("PROC-001");
		verify(repository).findByProcesskod("PROC-001");
	}

	@Test
	void getByProcesskodNotFound() {
		when(repository.findByProcesskod("PROC-001")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getByProcesskod("PROC-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findByProcesskod("PROC-001");
	}

	@Test
	void update() {
		final var entity = ProcessEntity.create()
			.withProcesskod("PROC-001")
			.withNamn("Old Name");

		final var dto = Process.create()
			.withProcesskod("PROC-001")
			.withNamn("New Name");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update("id-001", dto);

		assertThat(result).isNotNull();
		verify(repository).findById("id-001");
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = Process.create()
			.withProcesskod("PROC-001");

		when(repository.findById("id-001")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.update("id-001", dto))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById("id-001");
	}

	@Test
	void delete() {
		service.delete("id-001");

		verify(repository).deleteById("id-001");
	}
}
