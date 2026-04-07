package se.sundsvall.systemregister.service;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.integration.db.InformationsklassningRepository;
import se.sundsvall.systemregister.integration.db.model.InformationsklassningEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InformationsklassningServiceTest {

	@Mock
	private InformationsklassningRepository repository;

	@InjectMocks
	private InformationsklassningService service;

	@Test
	void createOrUpdateNew() {
		final var dto = Informationsklassning.create()
			.withKonfidentialitet(3)
			.withRiktighet(2);

		final var savedEntity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withKonfidentialitet(3)
			.withRiktighet(2);

		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of());
		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.createOrUpdate("handlingstyp-123", dto);

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp-123");
		assertThat(result.getKonfidentialitet()).isEqualTo(3);
		verify(repository).findByHandlingstypId("handlingstyp-123");
		verify(repository).save(any());
	}

	@Test
	void createOrUpdateExisting() {
		final var existingEntity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withKonfidentialitet(1);

		final var dto = Informationsklassning.create()
			.withKonfidentialitet(3);

		final var updatedEntity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withKonfidentialitet(3);

		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of(existingEntity));
		when(repository.save(any())).thenReturn(updatedEntity);

		final var result = service.createOrUpdate("handlingstyp-123", dto);

		assertThat(result).isNotNull();
		assertThat(result.getKonfidentialitet()).isEqualTo(3);
		verify(repository).findByHandlingstypId("handlingstyp-123");
		verify(repository).save(any());
	}

	@Test
	void getByHandlingstypId() {
		final var entity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp-123")
			.withKonfidentialitet(3);

		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of(entity));

		final var result = service.getByHandlingstypId("handlingstyp-123");

		assertThat(result).isNotNull();
		assertThat(result.getHandlingstypId()).isEqualTo("handlingstyp-123");
		verify(repository).findByHandlingstypId("handlingstyp-123");
	}

	@Test
	void getByHandlingstypIdNotFound() {
		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of());

		assertThatThrownBy(() -> service.getByHandlingstypId("handlingstyp-123"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findByHandlingstypId("handlingstyp-123");
	}

	@Test
	void deleteByHandlingstypId() {
		final var entity = InformationsklassningEntity.create()
			.withHandlingstypId("handlingstyp-123");

		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of(entity));

		service.delete("handlingstyp-123");

		verify(repository).findByHandlingstypId("handlingstyp-123");
		verify(repository).deleteById(entity.getId());
	}

	@Test
	void deleteByHandlingstypIdNotFound() {
		when(repository.findByHandlingstypId("handlingstyp-123")).thenReturn(List.of());

		service.delete("handlingstyp-123");

		verify(repository).findByHandlingstypId("handlingstyp-123");
	}
}
