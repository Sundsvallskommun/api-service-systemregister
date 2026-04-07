package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.integration.db.PersonuppgiftsbehandlingRepository;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingEntity;
import se.sundsvall.systemregister.integration.db.model.enums.LegalBasis;
import se.sundsvall.systemregister.integration.db.model.enums.ProcessingStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonuppgiftsbehandlingServiceTest {

	@Mock
	private PersonuppgiftsbehandlingRepository repository;

	@InjectMocks
	private PersonuppgiftsbehandlingService service;

	@Test
	void create() {
		final var dto = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Customer Processing");

		final var savedEntity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withName("Customer Processing");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.getName()).isEqualTo("Customer Processing");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withName("Test");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.getName()).isEqualTo("Test");
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
	void getByBehandlingId() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withName("Test");

		when(repository.findByBehandlingId("PPB-001")).thenReturn(List.of(entity));

		final var result = service.getByBehandlingId("PPB-001");

		assertThat(result).isNotNull();
		assertThat(result.getBehandlingId()).isEqualTo("PPB-001");
		verify(repository).findByBehandlingId("PPB-001");
	}

	@Test
	void getByBehandlingIdNotFound() {
		when(repository.findByBehandlingId("PPB-001")).thenReturn(List.of());

		assertThatThrownBy(() -> service.getByBehandlingId("PPB-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findByBehandlingId("PPB-001");
	}

	@Test
	void getAll() {
		final var entity1 = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001");
		final var entity2 = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-002");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.get(1).getBehandlingId()).isEqualTo("PPB-002");
		verify(repository).findAll();
	}

	@Test
	void getAllByStatus() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withStatus(ProcessingStatus.ACTIVE);

		when(repository.findByStatus(ProcessingStatus.ACTIVE)).thenReturn(List.of(entity));

		final var result = service.getAllByStatus("ACTIVE");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getBehandlingId()).isEqualTo("PPB-001");
		verify(repository).findByStatus(ProcessingStatus.ACTIVE);
	}

	@Test
	void update() {
		final var entity = PersonuppgiftsbehandlingEntity.create()
			.withBehandlingId("PPB-001")
			.withName("Old Name")
			.withStatus(ProcessingStatus.ACTIVE)
			.withLegalBasis(LegalBasis.AVTAL);

		final var dto = Personuppgiftsbehandling.create()
			.withName("New Name")
			.withStatus("ACTIVE");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update("id-001", dto);

		assertThat(result).isNotNull();
		verify(repository).findById("id-001");
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = Personuppgiftsbehandling.create()
			.withName("New Name");

		when(repository.findById("id-001")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.update("id-001", dto))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById("id-001");
	}

	@Test
	void delete() {
		when(repository.existsById("id-001")).thenReturn(true);

		service.delete("id-001");

		verify(repository).existsById("id-001");
		verify(repository).deleteById("id-001");
	}

	@Test
	void deleteNotFound() {
		when(repository.existsById("id-001")).thenReturn(false);

		assertThatThrownBy(() -> service.delete("id-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).existsById("id-001");
	}
}
