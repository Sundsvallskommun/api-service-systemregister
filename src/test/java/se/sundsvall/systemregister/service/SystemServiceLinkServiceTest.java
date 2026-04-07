package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.SystemServiceLink;
import se.sundsvall.systemregister.integration.db.SystemServiceLinkRepository;
import se.sundsvall.systemregister.integration.db.model.SystemServiceLinkEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemServiceLinkServiceTest {

	@Mock
	private SystemServiceLinkRepository systemServiceLinkRepository;

	@InjectMocks
	private SystemServiceLinkService systemServiceLinkService;

	@Test
	void create() {
		final var model = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		final var savedEntity = SystemServiceLinkEntity.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		savedEntity.withId("id-1");

		when(systemServiceLinkRepository.save(any())).thenReturn(savedEntity);

		final var result = systemServiceLinkService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(systemServiceLinkRepository).save(any(SystemServiceLinkEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = SystemServiceLinkEntity.create()
			.withSystemId("system-1");
		entity.withId("id-1");

		when(systemServiceLinkRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = systemServiceLinkService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(systemServiceLinkRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(systemServiceLinkRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> systemServiceLinkService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getBySystemId() {
		final var entity1 = SystemServiceLinkEntity.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		entity1.withId("id-1");

		when(systemServiceLinkRepository.findBySystemId("system-1")).thenReturn(List.of(entity1));

		final var result = systemServiceLinkService.getBySystemId("system-1");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getSystemId()).isEqualTo("system-1");
		verify(systemServiceLinkRepository).findBySystemId("system-1");
	}

	@Test
	void getByServiceId() {
		final var entity1 = SystemServiceLinkEntity.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		entity1.withId("id-1");

		when(systemServiceLinkRepository.findByServiceId("service-1")).thenReturn(List.of(entity1));

		final var result = systemServiceLinkService.getByServiceId("service-1");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getServiceId()).isEqualTo("service-1");
		verify(systemServiceLinkRepository).findByServiceId("service-1");
	}

	@Test
	void deleteFound() {
		when(systemServiceLinkRepository.existsById("id-1")).thenReturn(true);

		systemServiceLinkService.delete("id-1");

		verify(systemServiceLinkRepository).existsById("id-1");
		verify(systemServiceLinkRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(systemServiceLinkRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> systemServiceLinkService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
