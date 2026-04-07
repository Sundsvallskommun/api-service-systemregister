package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.integration.db.PersonRepository;
import se.sundsvall.systemregister.integration.db.model.PersonEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	void create() {
		final var model = Person.create()
			.withFirstName("John")
			.withLastName("Doe");
		final var savedEntity = PersonEntity.create()
			.withFirstName("John")
			.withLastName("Doe");
		savedEntity.withId("id-1");

		when(personRepository.save(any())).thenReturn(savedEntity);

		final var result = personService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(personRepository).save(any(PersonEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = PersonEntity.create()
			.withFirstName("John")
			.withLastName("Doe");
		entity.withId("id-1");

		when(personRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = personService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		verify(personRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(personRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> personService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAll() {
		final var entity1 = PersonEntity.create()
			.withFirstName("John");
		entity1.withId("id-1");
		final var entity2 = PersonEntity.create()
			.withFirstName("Jane");
		entity2.withId("id-2");

		when(personRepository.findAll()).thenReturn(List.of(entity1, entity2));

		final var result = personService.getAll();

		assertThat(result).hasSize(2);
		verify(personRepository).findAll();
	}

	@Test
	void updateFound() {
		final var existingEntity = PersonEntity.create()
			.withFirstName("John")
			.withLastName("Doe");
		existingEntity.withId("id-1");
		final var updateModel = Person.create()
			.withFirstName("Jane");
		final var savedEntity = PersonEntity.create()
			.withFirstName("Jane")
			.withLastName("Doe");
		savedEntity.withId("id-1");

		when(personRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(personRepository.save(any())).thenReturn(savedEntity);

		final var result = personService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getFirstName()).isEqualTo("Jane");
		verify(personRepository).findById("id-1");
		verify(personRepository).save(any(PersonEntity.class));
	}

	@Test
	void deleteFound() {
		when(personRepository.existsById("id-1")).thenReturn(true);

		personService.delete("id-1");

		verify(personRepository).existsById("id-1");
		verify(personRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(personRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> personService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
	}
}
