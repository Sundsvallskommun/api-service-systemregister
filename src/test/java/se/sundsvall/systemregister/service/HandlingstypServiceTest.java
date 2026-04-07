package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Handlingstyp;
import se.sundsvall.systemregister.integration.db.HandlingstypRepository;
import se.sundsvall.systemregister.integration.db.model.HandlingstypEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HandlingstypServiceTest {

	@Mock
	private HandlingstypRepository repository;

	@InjectMocks
	private HandlingstypService service;

	@Test
	void create() {
		final var dto = Handlingstyp.create()
			.withNamn("Test Handlingstyp")
			.withBeskrivning("Test description");

		final var savedEntity = HandlingstypEntity.create()
			.withNamn("Test Handlingstyp")
			.withBeskrivning("Test description");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Handlingstyp");
		assertThat(result.getBeskrivning()).isEqualTo("Test description");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = HandlingstypEntity.create()
			.withNamn("Test Handlingstyp")
			.withIhPlanId("plan-123");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Handlingstyp");
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
		final var entity1 = HandlingstypEntity.create()
			.withNamn("Handlingstyp 1");
		final var entity2 = HandlingstypEntity.create()
			.withNamn("Handlingstyp 2");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getNamn()).isEqualTo("Handlingstyp 1");
		assertThat(result.get(1).getNamn()).isEqualTo("Handlingstyp 2");
		verify(repository).findAll();
	}

	@Test
	void getByIhPlanId() {
		final var entity = HandlingstypEntity.create()
			.withNamn("Test Handlingstyp")
			.withIhPlanId("plan-123");

		when(repository.findByIhPlanId("plan-123")).thenReturn(List.of(entity));

		final var result = service.getByIhPlanId("plan-123");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getIhPlanId()).isEqualTo("plan-123");
		verify(repository).findByIhPlanId("plan-123");
	}

	@Test
	void update() {
		final var entity = HandlingstypEntity.create()
			.withNamn("Old Name")
			.withBeskrivning("Old description");

		final var dto = Handlingstyp.create()
			.withNamn("New Name")
			.withBeskrivning("New description");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update("id-001", dto);

		assertThat(result).isNotNull();
		verify(repository).findById("id-001");
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = Handlingstyp.create()
			.withNamn("New Name");

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
