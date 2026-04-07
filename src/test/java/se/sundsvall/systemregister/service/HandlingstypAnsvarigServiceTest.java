package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.HandlingstypAnsvarig;
import se.sundsvall.systemregister.integration.db.HandlingstypAnsvarigRepository;
import se.sundsvall.systemregister.integration.db.model.HandlingstypAnsvarigEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HandlingstypAnsvarigServiceTest {

	@Mock
	private HandlingstypAnsvarigRepository repository;

	@InjectMocks
	private HandlingstypAnsvarigService service;

	@Test
	void create() {
		final var dto = HandlingstypAnsvarig.create()
			.withHandlingstypId("handlingstyp-123")
			.withOrganisationId("org-456")
			.withRoll("Manager");

		final var savedEntity = HandlingstypAnsvarigEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withOrganisationId("org-456")
			.withRoll("Manager");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp-123");
		assertThat(result.getRoll()).isEqualTo("Manager");
		verify(repository).save(any());
	}

	@Test
	void getByHandlingstypId() {
		final var entity = HandlingstypAnsvarigEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withOrganisationId("org-456");

		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of(entity));

		final var result = service.getByHandlingstypId("handlingstyp-123");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getHandlingstypId()).isEqualTo("handlingstyp-123");
		verify(repository).findByHandlingstypId("handlingstyp-123");
	}

	@Test
	void getByHandlingstypIdEmpty() {
		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of());

		final var result = service.getByHandlingstypId("handlingstyp-123");

		assertThat(result).isEmpty();
		verify(repository).findByHandlingstypId("handlingstyp-123");
	}

	@Test
	void delete() {
		final var entity = HandlingstypAnsvarigEntity.create()
			.withHandlingstypId("handlingstyp-123");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		service.delete("id-001");

		verify(repository).findById("id-001");
		verify(repository).deleteById("id-001");
	}

	@Test
	void deleteNotFound() {
		when(repository.findById("id-001")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.delete("id-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findById("id-001");
	}
}
