package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.integration.db.SecurityLevelDefinitionRepository;
import se.sundsvall.systemregister.integration.db.model.SecurityLevelDefinitionEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityLevelDefinitionServiceTest {

	@Mock
	private SecurityLevelDefinitionRepository repository;

	@InjectMocks
	private SecurityLevelDefinitionService service;

	@Test
	void create() {
		final var dto = SecurityLevelDefinition.create()
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium");
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium");
		entity.withId("id-1");

		when(this.repository.save(any(SecurityLevelDefinitionEntity.class))).thenReturn(entity);

		final var result = this.service.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		assertThat(result.getLevel()).isEqualTo(2);
		verify(this.repository).save(any(SecurityLevelDefinitionEntity.class));
	}

	@Test
	void getById() {
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(2);
		entity.withId("id-1");

		when(this.repository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = this.service.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("K");
		verify(this.repository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(this.repository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> this.service.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).findById("id-1");
	}

	@Test
	void getAll() {
		final var entity1 = SecurityLevelDefinitionEntity.create()
			.withDimension("K");
		entity1.withId("id-1");
		final var entity2 = SecurityLevelDefinitionEntity.create()
			.withDimension("R");
		entity2.withId("id-2");

		when(this.repository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = this.service.getAll();

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		verify(this.repository).findAll();
	}

	@Test
	void getByDimensionAndLevel() {
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(2);
		entity.withId("id-1");

		when(this.repository.findByDimensionAndLevel("K", 2)).thenReturn(List.of(entity));

		final var result = this.service.getByDimensionAndLevel("K", 2);

		assertThat(result).isNotNull().hasSize(1);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		verify(this.repository).findByDimensionAndLevel("K", 2);
	}

	@Test
	void getByDimension() {
		final var entity1 = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(2);
		entity1.withId("id-1");
		final var entity2 = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLevel(3);
		entity2.withId("id-2");
		final var entity3 = SecurityLevelDefinitionEntity.create()
			.withDimension("R")
			.withLevel(1);
		entity3.withId("id-3");

		when(this.repository.findAll()).thenReturn(List.of(entity1, entity2, entity3));

		final var result = this.service.getByDimension("K");

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getId()).isEqualTo("id-1");
		assertThat(result.get(1).getId()).isEqualTo("id-2");
		verify(this.repository).findAll();
	}

	@Test
	void getActive() {
		final var entity1 = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withIsActive(true);
		entity1.withId("id-1");
		final var entity2 = SecurityLevelDefinitionEntity.create()
			.withDimension("R")
			.withIsActive(true);
		entity2.withId("id-2");

		when(this.repository.findByIsActive(Boolean.TRUE)).thenReturn(List.of(entity1, entity2));

		final var result = this.service.getActive();

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getIsActive()).isTrue();
		assertThat(result.get(1).getIsActive()).isTrue();
		verify(this.repository).findByIsActive(Boolean.TRUE);
	}

	@Test
	void update() {
		final var entity = SecurityLevelDefinitionEntity.create()
			.withDimension("K")
			.withLabel("Old Label");
		entity.withId("id-1");
		final var dto = SecurityLevelDefinition.create()
			.withDimension("R")
			.withLabel("New Label");
		final var updated = SecurityLevelDefinitionEntity.create()
			.withDimension("R")
			.withLabel("New Label");
		updated.withId("id-1");

		when(this.repository.findById("id-1")).thenReturn(Optional.of(entity));
		when(this.repository.save(any(SecurityLevelDefinitionEntity.class))).thenReturn(updated);

		final var result = this.service.update("id-1", dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getDimension()).isEqualTo("R");
		assertThat(result.getLabel()).isEqualTo("New Label");
		verify(this.repository).findById("id-1");
		verify(this.repository).save(any(SecurityLevelDefinitionEntity.class));
	}

	@Test
	void updateNotFound() {
		final var dto = SecurityLevelDefinition.create()
			.withDimension("K");

		when(this.repository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> this.service.update("id-1", dto))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).findById("id-1");
	}

	@Test
	void delete() {
		when(this.repository.existsById("id-1")).thenReturn(true);

		this.service.delete("id-1");

		verify(this.repository).existsById("id-1");
		verify(this.repository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(this.repository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> this.service.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
		verify(this.repository).existsById("id-1");
	}
}
