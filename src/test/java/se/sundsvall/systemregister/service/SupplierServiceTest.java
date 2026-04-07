package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.integration.db.SupplierRepository;
import se.sundsvall.systemregister.integration.db.model.SupplierEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

	@Mock
	private SupplierRepository supplierRepository;

	@InjectMocks
	private SupplierService supplierService;

	@Test
	void create() {
		final var model = Supplier.create()
			.withName("Tech Solutions")
			.withOrgNumber("5565123456");
		final var savedEntity = SupplierEntity.create()
			.withName("Tech Solutions")
			.withOrgNumber("5565123456");
		savedEntity.withId("id-1");

		when(supplierRepository.save(any())).thenReturn(savedEntity);

		final var result = supplierService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(supplierRepository).save(any(SupplierEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = SupplierEntity.create()
			.withName("Tech Solutions");
		entity.withId("id-1");

		when(supplierRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = supplierService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(supplierRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(supplierRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> supplierService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAll() {
		final var entity1 = SupplierEntity.create()
			.withName("Supplier 1");
		entity1.withId("id-1");
		final var entity2 = SupplierEntity.create()
			.withName("Supplier 2");
		entity2.withId("id-2");

		when(supplierRepository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = supplierService.getAll();

		assertThat(result).hasSize(2);
		verify(supplierRepository).findAll();
	}

	@Test
	void updateFound() {
		final var existingEntity = SupplierEntity.create()
			.withName("Old Name");
		existingEntity.withId("id-1");
		final var updateModel = Supplier.create()
			.withName("New Name");
		final var savedEntity = SupplierEntity.create()
			.withName("New Name");
		savedEntity.withId("id-1");

		when(supplierRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(supplierRepository.save(any())).thenReturn(savedEntity);

		final var result = supplierService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("New Name");
		verify(supplierRepository).findById("id-1");
		verify(supplierRepository).save(any(SupplierEntity.class));
	}

	@Test
	void deleteFound() {
		when(supplierRepository.existsById("id-1")).thenReturn(true);

		supplierService.delete("id-1");

		verify(supplierRepository).existsById("id-1");
		verify(supplierRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(supplierRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> supplierService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
