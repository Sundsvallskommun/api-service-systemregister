package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Foreskrift;
import se.sundsvall.systemregister.integration.db.ForeskriftRepository;
import se.sundsvall.systemregister.integration.db.model.ForeskriftEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForeskriftServiceTest {

	@Mock
	private ForeskriftRepository repository;

	@InjectMocks
	private ForeskriftService service;

	@Test
	void create() {
		final var dto = Foreskrift.create()
			.withBeteckning("SOU 2024:1")
			.withNamn("Test Foreskrift");

		final var savedEntity = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:1")
			.withNamn("Test Foreskrift");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getBeteckning()).isEqualTo("SOU 2024:1");
		assertThat(result.getNamn()).isEqualTo("Test Foreskrift");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:1")
			.withNamn("Test Foreskrift");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getBeteckning()).isEqualTo("SOU 2024:1");
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
	void getByBeteckning() {
		final var entity = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:1")
			.withNamn("Test Foreskrift");

		when(repository.findByBeteckning("SOU 2024:1")).thenReturn(Optional.of(entity));

		final var result = service.getByBeteckning("SOU 2024:1");

		assertThat(result).isNotNull();
		assertThat(result.getBeteckning()).isEqualTo("SOU 2024:1");
		verify(repository).findByBeteckning("SOU 2024:1");
	}

	@Test
	void getByBeteckningNotFound() {
		when(repository.findByBeteckning("SOU 2024:1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getByBeteckning("SOU 2024:1"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findByBeteckning("SOU 2024:1");
	}

	@Test
	void getAll() {
		final var entity1 = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:1");
		final var entity2 = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:2");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getBeteckning()).isEqualTo("SOU 2024:1");
		assertThat(result.get(1).getBeteckning()).isEqualTo("SOU 2024:2");
		verify(repository).findAll();
	}

	@Test
	void update() {
		final var entity = ForeskriftEntity.create()
			.withBeteckning("SOU 2024:1")
			.withNamn("Old Name");

		final var dto = Foreskrift.create()
			.withBeteckning("SOU 2024:1")
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
		final var dto = Foreskrift.create()
			.withBeteckning("SOU 2024:1");

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
