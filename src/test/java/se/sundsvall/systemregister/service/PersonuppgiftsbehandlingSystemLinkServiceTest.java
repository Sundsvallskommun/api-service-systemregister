package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.integration.db.PersonuppgiftsbehandlingSystemLinkRepository;
import se.sundsvall.systemregister.integration.db.model.PersonuppgiftsbehandlingSystemLinkEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonuppgiftsbehandlingSystemLinkServiceTest {

	@Mock
	private PersonuppgiftsbehandlingSystemLinkRepository repository;

	@InjectMocks
	private PersonuppgiftsbehandlingSystemLinkService service;

	@Test
	void create() {
		final var dto = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001");

		final var savedEntity = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.getSystemId()).isEqualTo("system-001");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.getSystemId()).isEqualTo("system-001");
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
		final var entity1 = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001");
		final var entity2 = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-002");

		when(repository.findByBehandlingId("PPB-001"))
			.thenReturn(List.of(entity1, entity2));

		final var result = service.getByBehandlingId("PPB-001");

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getSystemId()).isEqualTo("system-001");
		assertThat(result.get(1).getSystemId()).isEqualTo("system-002");
		verify(repository).findByBehandlingId("PPB-001");
	}

	@Test
	void getByBehandlingIdEmpty() {
		when(repository.findByBehandlingId("PPB-001"))
			.thenReturn(List.of());

		final var result = service.getByBehandlingId("PPB-001");

		assertThat(result).isEmpty();
		verify(repository).findByBehandlingId("PPB-001");
	}

	@Test
	void getBySystemId() {
		final var entity1 = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-001")
			.withSystemId("system-001");
		final var entity2 = PersonuppgiftsbehandlingSystemLinkEntity.create()
			.withBehandlingId("PPB-002")
			.withSystemId("system-001");

		when(repository.findBySystemId("system-001"))
			.thenReturn(List.of(entity1, entity2));

		final var result = service.getBySystemId("system-001");

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getBehandlingId()).isEqualTo("PPB-001");
		assertThat(result.get(1).getBehandlingId()).isEqualTo("PPB-002");
		verify(repository).findBySystemId("system-001");
	}

	@Test
	void getBySystemIdEmpty() {
		when(repository.findBySystemId("system-001"))
			.thenReturn(List.of());

		final var result = service.getBySystemId("system-001");

		assertThat(result).isEmpty();
		verify(repository).findBySystemId("system-001");
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
