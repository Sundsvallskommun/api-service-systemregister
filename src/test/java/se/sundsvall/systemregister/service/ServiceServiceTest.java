package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.integration.db.ServiceRepository;
import se.sundsvall.systemregister.integration.db.model.ServiceEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

	@Mock
	private ServiceRepository serviceRepository;

	@InjectMocks
	private ServiceService serviceService;

	@Test
	void create() {
		final var model = ServiceModel.create()
			.withServiceId("SVC-001")
			.withName("Email Service");
		final var savedEntity = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("Email Service");
		savedEntity.withId("id-1");

		when(serviceRepository.save(any())).thenReturn(savedEntity);

		final var result = serviceService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getServiceId()).isEqualTo("SVC-001");
		verify(serviceRepository).save(any(ServiceEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = ServiceEntity.create()
			.withServiceId("SVC-001");
		entity.withId("id-1");

		when(serviceRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = serviceService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(serviceRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(serviceRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> serviceService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
		verify(serviceRepository).findById("id-1");
	}

	@Test
	void getAll() {
		final var entity1 = ServiceEntity.create()
			.withServiceId("SVC-001");
		entity1.withId("id-1");
		final var entity2 = ServiceEntity.create()
			.withServiceId("SVC-002");
		entity2.withId("id-2");

		when(serviceRepository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = serviceService.getAll();

		assertThat(result).hasSize(2);
		verify(serviceRepository).findAll();
	}

	@Test
	void updateFound() {
		final var existingEntity = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("Old Name");
		existingEntity.withId("id-1");
		final var updateModel = ServiceModel.create()
			.withName("New Name");
		final var savedEntity = ServiceEntity.create()
			.withServiceId("SVC-001")
			.withName("New Name");
		savedEntity.withId("id-1");

		when(serviceRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(serviceRepository.save(any())).thenReturn(savedEntity);

		final var result = serviceService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("New Name");
		verify(serviceRepository).findById("id-1");
		verify(serviceRepository).save(any(ServiceEntity.class));
	}

	@Test
	void updateNotFound() {
		when(serviceRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> serviceService.update("id-1", ServiceModel.create()))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void deleteFound() {
		when(serviceRepository.existsById("id-1")).thenReturn(true);

		serviceService.delete("id-1");

		verify(serviceRepository).existsById("id-1");
		verify(serviceRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(serviceRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> serviceService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
