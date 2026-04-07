package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.SystemDependency;
import se.sundsvall.systemregister.integration.db.SystemDependencyRepository;
import se.sundsvall.systemregister.integration.db.model.SystemDependencyEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemDependencyServiceTest {

	@Mock
	private SystemDependencyRepository systemDependencyRepository;

	@InjectMocks
	private SystemDependencyService systemDependencyService;

	@Test
	void create() {
		final var model = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		final var savedEntity = SystemDependencyEntity.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		savedEntity.withId("id-1");

		when(systemDependencyRepository.save(any())).thenReturn(savedEntity);

		final var result = systemDependencyService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(systemDependencyRepository).save(any(SystemDependencyEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = SystemDependencyEntity.create()
			.withSourceSystemId("system-1");
		entity.withId("id-1");

		when(systemDependencyRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = systemDependencyService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(systemDependencyRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(systemDependencyRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> systemDependencyService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getBySourceSystemId() {
		final var entity1 = SystemDependencyEntity.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		entity1.withId("id-1");

		when(systemDependencyRepository.findBySourceSystemId("system-1")).thenReturn(List.of(entity1));

		final var result = systemDependencyService.getBySourceSystemId("system-1");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getSourceSystemId()).isEqualTo("system-1");
		verify(systemDependencyRepository).findBySourceSystemId("system-1");
	}

	@Test
	void getByTargetSystemId() {
		final var entity1 = SystemDependencyEntity.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		entity1.withId("id-1");

		when(systemDependencyRepository.findByTargetSystemId("system-2")).thenReturn(List.of(entity1));

		final var result = systemDependencyService.getByTargetSystemId("system-2");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getTargetSystemId()).isEqualTo("system-2");
		verify(systemDependencyRepository).findByTargetSystemId("system-2");
	}

	@Test
	void deleteFound() {
		when(systemDependencyRepository.existsById("id-1")).thenReturn(true);

		systemDependencyService.delete("id-1");

		verify(systemDependencyRepository).existsById("id-1");
		verify(systemDependencyRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(systemDependencyRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> systemDependencyService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
