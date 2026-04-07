package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.KlassaProcessgrupp;
import se.sundsvall.systemregister.integration.db.KlassaProcessgruppRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaProcessgruppEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KlassaProcessgruppServiceTest {

	@Mock
	private KlassaProcessgruppRepository repository;

	@InjectMocks
	private KlassaProcessgruppService service;

	@Test
	void create() {
		final var dto = KlassaProcessgrupp.create()
			.withKod("PG01")
			.withNamn("Kärnprocesser")
			.withVerksamhetsomradeId(1)
			.withAktiv(true);

		final var savedEntity = KlassaProcessgruppEntity.create()
			.withKod("PG01")
			.withNamn("Kärnprocesser")
			.withVerksamhetsomradeId(1)
			.withAktiv(true);

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("PG01");
		assertThat(result.getNamn()).isEqualTo("Kärnprocesser");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = KlassaProcessgruppEntity.create()
			.withKod("PG01")
			.withNamn("Kärnprocesser");

		when(repository.findById(1)).thenReturn(Optional.of(entity));

		final var result = service.getById(1);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("PG01");
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
		final var entity1 = KlassaProcessgruppEntity.create()
			.withKod("PG01");
		final var entity2 = KlassaProcessgruppEntity.create()
			.withKod("PG02");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getKod()).isEqualTo("PG01");
		assertThat(result.get(1).getKod()).isEqualTo("PG02");
		verify(repository).findAll();
	}

	@Test
	void getByVerksamhetsomradeId() {
		final var entity = KlassaProcessgruppEntity.create()
			.withKod("PG01")
			.withVerksamhetsomradeId(1);

		when(repository.findByVerksamhetsomradeId(1)).thenReturn(List.of(entity));

		final var result = service.getByVerksamhetsomradeId(1);

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getVerksamhetsomradeId()).isEqualTo(1);
		verify(repository).findByVerksamhetsomradeId(1);
	}

	@Test
	void update() {
		final var entity = KlassaProcessgruppEntity.create()
			.withKod("PG01")
			.withNamn("Old Name");

		final var dto = KlassaProcessgrupp.create()
			.withKod("PG01")
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
		final var dto = KlassaProcessgrupp.create()
			.withKod("PG01");

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
