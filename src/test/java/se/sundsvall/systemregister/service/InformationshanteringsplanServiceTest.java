package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.integration.db.InformationshanteringsplanRepository;
import se.sundsvall.systemregister.integration.db.model.InformationshanteringsplanEntity;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InformationshanteringsplanServiceTest {

	@Mock
	private InformationshanteringsplanRepository repository;

	@InjectMocks
	private InformationshanteringsplanService service;

	@Test
	void create() {
		final var dto = Informationshanteringsplan.create()
			.withNamn("Test Plan")
			.withVersion("1.0.0")
			.withOrganisationId("org-123");

		final var savedEntity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withVersion("1.0.0")
			.withOrganisationId("org-123");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getVersion()).isEqualTo("1.0.0");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withVersion("1.0.0");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getNamn()).isEqualTo("Test Plan");
		assertThat(result.getVersion()).isEqualTo("1.0.0");
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
		final var entity1 = InformationshanteringsplanEntity.create()
			.withNamn("Plan 1");
		final var entity2 = InformationshanteringsplanEntity.create()
			.withNamn("Plan 2");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getNamn()).isEqualTo("Plan 1");
		assertThat(result.get(1).getNamn()).isEqualTo("Plan 2");
		verify(repository).findAll();
	}

	@Test
	void getByOrganisationId() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withOrganisationId("org-123");

		when(repository.findByOrganisationId("org-123")).thenReturn(List.of(entity));

		final var result = service.getByOrganisationId("org-123");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getOrganisationId()).isEqualTo("org-123");
		verify(repository).findByOrganisationId("org-123");
	}

	@Test
	void getByStatus() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Test Plan")
			.withStatus(IHPlanStatus.BESLUTAD);

		when(repository.findByStatus(IHPlanStatus.BESLUTAD)).thenReturn(List.of(entity));

		final var result = service.getByStatus("BESLUTAD");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getStatus()).isEqualTo("BESLUTAD");
		verify(repository).findByStatus(IHPlanStatus.BESLUTAD);
	}

	@Test
	void update() {
		final var entity = InformationshanteringsplanEntity.create()
			.withNamn("Old Plan")
			.withVersion("1.0.0");

		final var dto = Informationshanteringsplan.create()
			.withNamn("New Plan")
			.withVersion("2.0.0");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update("id-001", dto);

		assertThat(result).isNotNull();
		verify(repository).findById("id-001");
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = Informationshanteringsplan.create()
			.withNamn("New Plan");

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
