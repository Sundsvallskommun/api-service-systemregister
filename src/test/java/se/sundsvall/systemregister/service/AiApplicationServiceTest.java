package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.integration.db.AiApplicationRepository;
import se.sundsvall.systemregister.integration.db.model.AiApplicationEntity;
import se.sundsvall.systemregister.integration.db.model.enums.AIApplicationStatus;
import se.sundsvall.systemregister.integration.db.model.enums.AIRiskCategory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AiApplicationServiceTest {

	@Mock
	private AiApplicationRepository repository;

	@InjectMocks
	private AiApplicationService service;

	@Test
	void create() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Risk Assessment");

		final var savedEntity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withName("Risk Assessment");

		when(repository.save(any())).thenReturn(savedEntity);

		final var result = service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(result.getName()).isEqualTo("Risk Assessment");
		verify(repository).save(any());
	}

	@Test
	void getById() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withName("Test");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));

		final var result = service.getById("id-001");

		assertThat(result).isNotNull();
		assertThat(result.getAiApplicationId()).isEqualTo("AI-001");
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
	void getByAiApplicationId() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withName("Test");

		when(repository.findByAiApplicationId("AI-001")).thenReturn(List.of(entity));

		final var result = service.getByAiApplicationId("AI-001");

		assertThat(result).isNotNull();
		assertThat(result.getAiApplicationId()).isEqualTo("AI-001");
		verify(repository).findByAiApplicationId("AI-001");
	}

	@Test
	void getByAiApplicationIdNotFound() {
		when(repository.findByAiApplicationId("AI-001")).thenReturn(List.of());

		assertThatThrownBy(() -> service.getByAiApplicationId("AI-001"))
			.isInstanceOf(ThrowableProblem.class);

		verify(repository).findByAiApplicationId("AI-001");
	}

	@Test
	void getAll() {
		final var entity1 = AiApplicationEntity.create()
			.withAiApplicationId("AI-001");
		final var entity2 = AiApplicationEntity.create()
			.withAiApplicationId("AI-002");

		when(repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = service.getAll();

		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		assertThat(result.get(1).getAiApplicationId()).isEqualTo("AI-002");
		verify(repository).findAll();
	}

	@Test
	void getAllByStatus() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withStatus(AIApplicationStatus.ACTIVE);

		when(repository.findByStatus(AIApplicationStatus.ACTIVE)).thenReturn(List.of(entity));

		final var result = service.getAllByStatus("ACTIVE");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		verify(repository).findByStatus(AIApplicationStatus.ACTIVE);
	}

	@Test
	void getAllByRiskCategory() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withRiskCategory(AIRiskCategory.HIGH_RISK);

		when(repository.findByRiskCategory(AIRiskCategory.HIGH_RISK)).thenReturn(List.of(entity));

		final var result = service.getAllByRiskCategory("HIGH_RISK");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		verify(repository).findByRiskCategory(AIRiskCategory.HIGH_RISK);
	}

	@Test
	void getAllBySystemId() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withSystemId("system-001");

		when(repository.findBySystemId("system-001")).thenReturn(List.of(entity));

		final var result = service.getAllBySystemId("system-001");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		verify(repository).findBySystemId("system-001");
	}

	@Test
	void update() {
		final var entity = AiApplicationEntity.create()
			.withAiApplicationId("AI-001")
			.withName("Old Name")
			.withStatus(AIApplicationStatus.ACTIVE)
			.withRiskCategory(AIRiskCategory.HIGH_RISK);

		final var dto = AiApplication.create()
			.withName("New Name")
			.withStatus("ACTIVE")
			.withRiskCategory("HIGH_RISK");

		when(repository.findById("id-001")).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		final var result = service.update("id-001", dto);

		assertThat(result).isNotNull();
		verify(repository).findById("id-001");
		verify(repository).save(any());
	}

	@Test
	void updateNotFound() {
		final var dto = AiApplication.create()
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
