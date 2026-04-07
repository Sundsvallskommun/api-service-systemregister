package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.KlassaProcess;
import se.sundsvall.systemregister.integration.db.KlassaProcessRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KlassaProcessServiceTest {

	@Mock
	private KlassaProcessRepository repository;

	@InjectMocks
	private KlassaProcessService service;

	@Test
	void create() {
		final var dto = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Test Process")
			.withBeskrivning("Description")
			.withProcessgrupId(1)
			.withAktiv(true);

		final var savedEntity = KlassaProcessEntity.create()
			.withKod("P001")
			.withNamn("Test Process")
			.withBeskrivning("Description")
			.withProcessgrupId(1)
			.withAktiv(true);

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("P001");
		assertThat(result.getNamn()).isEqualTo("Test Process");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = KlassaProcessEntity.create()
			.withKod("P001")
			.withNamn("Test Process");

		when(repository.findById(1)).thenReturn(Optional.of(entity));

		final var result = service.getById(1);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("P001");
		verify(repository).findById(1);
	}

	@Test
	void getByIdNotFound() {
		when(repository.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getById(1))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById(1);
	}

	@Test
	void getAll() {
		final var entity1 = KlassaProcessEntity.create()
			.withKod("P001");
		final var entity2 = KlassaProcessEntity.create()
			.withKod("P002");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getKod()).isEqualTo("P001");
		assertThat(result.get(1).getKod()).isEqualTo("P002");
		verify(repository).findAll();
	}

	@Test
	void getByProcessgrupId() {
		final var entity = KlassaProcessEntity.create()
			.withKod("P001")
			.withProcessgrupId(1);

		when(repository.findByProcessgrupId(1)).thenReturn(List.of(entity));

		final var result = service.getByProcessgrupId(1);

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getProcessgrupId()).isEqualTo(1);
		verify(repository).findByProcessgrupId(1);
	}

	@Test
	void update() {
		final var entity = KlassaProcessEntity.create()
			.withKod("P001")
			.withNamn("Old Name");

		final var dto = KlassaProcess.create()
			.withKod("P001")
			.withNamn("New Name");

		when(repository.findById(1)).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update(1, dto);

		assertThat(result).isNotNull();
		verify(repository).findById(1);
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = KlassaProcess.create()
			.withKod("P001");

		when(repository.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.update(1, dto))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById(1);
	}

	@Test
	void delete() {
		service.delete(1);

		verify(repository).deleteById(1);
	}
}
