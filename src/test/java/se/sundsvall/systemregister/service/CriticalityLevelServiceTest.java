package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.CriticalityLevel;
import se.sundsvall.systemregister.integration.db.CriticalityLevelRepository;
import se.sundsvall.systemregister.integration.db.model.CriticalityLevelEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriticalityLevelServiceTest {

	@Mock
	private CriticalityLevelRepository criticalityLevelRepository;

	@InjectMocks
	private CriticalityLevelService criticalityLevelService;

	@Test
	void create() {
		final var model = CriticalityLevel.create()
			.withName("Critical")
			.withCode("CRITICAL");
		final var savedEntity = CriticalityLevelEntity.create()
			.withName("Critical")
			.withCode("CRITICAL");
		savedEntity.withId("id-1");

		when(criticalityLevelRepository.save(any())).thenReturn(savedEntity);

		final var result = criticalityLevelService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(criticalityLevelRepository).save(any(CriticalityLevelEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = CriticalityLevelEntity.create()
			.withName("Critical");
		entity.withId("id-1");

		when(criticalityLevelRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = criticalityLevelService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(criticalityLevelRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(criticalityLevelRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> criticalityLevelService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAll() {
		final var entity1 = CriticalityLevelEntity.create()
			.withName("Critical");
		entity1.withId("id-1");
		final var entity2 = CriticalityLevelEntity.create()
			.withName("High");
		entity2.withId("id-2");

		when(criticalityLevelRepository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = criticalityLevelService.getAll();

		assertThat(result).hasSize(2);
		verify(criticalityLevelRepository).findAll();
	}

	@Test
	void updateFound() {
		final var existingEntity = CriticalityLevelEntity.create()
			.withName("Old Name");
		existingEntity.withId("id-1");
		final var updateModel = CriticalityLevel.create()
			.withName("New Name");
		final var savedEntity = CriticalityLevelEntity.create()
			.withName("New Name");
		savedEntity.withId("id-1");

		when(criticalityLevelRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(criticalityLevelRepository.save(any())).thenReturn(savedEntity);

		final var result = criticalityLevelService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("New Name");
		verify(criticalityLevelRepository).findById("id-1");
		verify(criticalityLevelRepository).save(any(CriticalityLevelEntity.class));
	}

	@Test
	void deleteFound() {
		when(criticalityLevelRepository.existsById("id-1")).thenReturn(true);

		criticalityLevelService.delete("id-1");

		verify(criticalityLevelRepository).existsById("id-1");
		verify(criticalityLevelRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(criticalityLevelRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> criticalityLevelService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
