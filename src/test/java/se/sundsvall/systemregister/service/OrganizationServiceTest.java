package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.integration.db.OrganizationRepository;
import se.sundsvall.systemregister.integration.db.model.OrganizationEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

	@Mock
	private OrganizationRepository organizationRepository;

	@InjectMocks
	private OrganizationService organizationService;

	@Test
	void create() {
		final var model = Organization.create()
			.withName("IT Department")
			.withOrgNumber("2021001234");
		final var savedEntity = OrganizationEntity.create()
			.withName("IT Department")
			.withOrgNumber("2021001234");
		savedEntity.withId("id-1");

		when(organizationRepository.save(any())).thenReturn(savedEntity);

		final var result = organizationService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(organizationRepository).save(any(OrganizationEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = OrganizationEntity.create()
			.withName("IT Department");
		entity.withId("id-1");

		when(organizationRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = organizationService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(organizationRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(organizationRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> organizationService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAll() {
		final var entity1 = OrganizationEntity.create()
			.withName("Organization 1");
		entity1.withId("id-1");
		final var entity2 = OrganizationEntity.create()
			.withName("Organization 2");
		entity2.withId("id-2");

		when(organizationRepository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = organizationService.getAll();

		assertThat(result).hasSize(2);
		verify(organizationRepository).findAll();
	}

	@Test
	void updateFound() {
		final var existingEntity = OrganizationEntity.create()
			.withName("Old Name");
		existingEntity.withId("id-1");
		final var updateModel = Organization.create()
			.withName("New Name");
		final var savedEntity = OrganizationEntity.create()
			.withName("New Name");
		savedEntity.withId("id-1");

		when(organizationRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(organizationRepository.save(any())).thenReturn(savedEntity);

		final var result = organizationService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("New Name");
		verify(organizationRepository).findById("id-1");
		verify(organizationRepository).save(any(OrganizationEntity.class));
	}

	@Test
	void getAllByLevel() {
		final var entity = OrganizationEntity.create()
			.withName("Top Level Org");
		entity.withId("id-1");

		when(organizationRepository.findByLevel(1)).thenReturn(List.of(entity));

		final var result = organizationService.getAllByLevel(1);

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getName()).isEqualTo("Top Level Org");
		verify(organizationRepository).findByLevel(1);
	}

	@Test
	void getChildren() {
		final var child = OrganizationEntity.create()
			.withName("Child Org");
		child.withId("id-2");

		when(organizationRepository.findByParentId("id-1")).thenReturn(List.of(child));

		final var result = organizationService.getChildren("id-1");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getName()).isEqualTo("Child Org");
		verify(organizationRepository).findByParentId("id-1");
	}

	@Test
	void updateNotFound() {
		final var updateModel = Organization.create()
			.withName("New Name");

		when(organizationRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> organizationService.update("id-1", updateModel))
			.isInstanceOf(ThrowableProblem.class);
		verify(organizationRepository).findById("id-1");
	}

	@Test
	void deleteFound() {
		when(organizationRepository.existsById("id-1")).thenReturn(true);

		organizationService.delete("id-1");

		verify(organizationRepository).existsById("id-1");
		verify(organizationRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(organizationRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> organizationService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
