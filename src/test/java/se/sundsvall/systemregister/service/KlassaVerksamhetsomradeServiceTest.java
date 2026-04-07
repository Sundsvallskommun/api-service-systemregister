package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.KlassaVerksamhetsomrade;
import se.sundsvall.systemregister.integration.db.KlassaVerksamhetsomradeRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetsomradeEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KlassaVerksamhetsomradeServiceTest {

	@Mock
	private KlassaVerksamhetsomradeRepository repository;

	@InjectMocks
	private KlassaVerksamhetsomradeService service;

	@Test
	void create() {
		final var dto = KlassaVerksamhetsomrade.create()
			.withKod("V01")
			.withNamn("Hälsa och sjukvård")
			.withVerksamhetstypId(1)
			.withAktiv(true);

		final var savedEntity = KlassaVerksamhetsomradeEntity.create()
			.withKod("V01")
			.withNamn("Hälsa och sjukvård")
			.withVerksamhetstypId(1)
			.withAktiv(true);

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("V01");
		assertThat(result.getNamn()).isEqualTo("Hälsa och sjukvård");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = KlassaVerksamhetsomradeEntity.create()
			.withKod("V01")
			.withNamn("Hälsa och sjukvård");

		when(repository.findById(1)).thenReturn(Optional.of(entity));

		final var result = service.getById(1);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo("V01");
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
		final var entity1 = KlassaVerksamhetsomradeEntity.create()
			.withKod("V01");
		final var entity2 = KlassaVerksamhetsomradeEntity.create()
			.withKod("V02");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getKod()).isEqualTo("V01");
		assertThat(result.get(1).getKod()).isEqualTo("V02");
		verify(repository).findAll();
	}

	@Test
	void getByVerksamhetstypId() {
		final var entity = KlassaVerksamhetsomradeEntity.create()
			.withKod("V01")
			.withVerksamhetstypId(1);

		when(repository.findByVerksamhetstypId(1)).thenReturn(List.of(entity));

		final var result = service.getByVerksamhetstypId(1);

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getVerksamhetstypId()).isEqualTo(1);
		verify(repository).findByVerksamhetstypId(1);
	}

	@Test
	void update() {
		final var entity = KlassaVerksamhetsomradeEntity.create()
			.withKod("V01")
			.withNamn("Old Name");

		final var dto = KlassaVerksamhetsomrade.create()
			.withKod("V01")
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
		final var dto = KlassaVerksamhetsomrade.create()
			.withKod("V01");

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
