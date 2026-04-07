package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.integration.db.KlassaVerksamhetstypRepository;
import se.sundsvall.systemregister.integration.db.model.KlassaVerksamhetstypEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KlassaVerksamhetstypServiceTest {

	@Mock
	private KlassaVerksamhetstypRepository repository;

	@InjectMocks
	private KlassaVerksamhetstypService service;

	@Test
	void getById() {
		final var entity = KlassaVerksamhetstypEntity.create()
			.withKod(1)
			.withNamn("Ledning");

		when(repository.findById(1)).thenReturn(Optional.of(entity));

		final var result = service.getById(1);

		assertThat(result).isNotNull();
		assertThat(result.getKod()).isEqualTo(1);
		assertThat(result.getNamn()).isEqualTo("Ledning");
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
		final var entity1 = KlassaVerksamhetstypEntity.create()
			.withKod(1)
			.withNamn("Ledning");
		final var entity2 = KlassaVerksamhetstypEntity.create()
			.withKod(2)
			.withNamn("Service");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getKod()).isEqualTo(1);
		assertThat(result.get(1).getKod()).isEqualTo(2);
		verify(repository).findAll();
	}
}
