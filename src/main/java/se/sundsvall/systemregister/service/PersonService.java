package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.integration.db.PersonRepository;
import se.sundsvall.systemregister.integration.db.model.PersonEntity;
import se.sundsvall.systemregister.service.mapper.PersonMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class PersonService {

	private static final String ENTITY_NOT_FOUND = "Person with ID %s not found";

	private final PersonRepository personRepository;

	public PersonService(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person create(final Person person) {
		final PersonEntity entity = PersonMapper.toPersonEntity(person);
		final PersonEntity saved = personRepository.save(entity);
		return PersonMapper.toPerson(saved);
	}

	public Person getById(final String id) {
		final PersonEntity entity = personRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		return PersonMapper.toPerson(entity);
	}

	public List<Person> getAll() {
		return personRepository.findAll().stream()
			.map(PersonMapper::toPerson)
			.toList();
	}

	public Optional<Person> findByEmail(final String email) {
		return personRepository.findByEmail(email)
			.map(PersonMapper::toPerson);
	}

	public Person update(final String id, final Person person) {
		final PersonEntity entity = personRepository.findById(id)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id)));
		PersonMapper.updatePersonEntity(entity, person);
		final PersonEntity saved = personRepository.save(entity);
		return PersonMapper.toPerson(saved);
	}

	public void delete(final String id) {
		if (!personRepository.existsById(id)) {
			throw Problem.valueOf(NOT_FOUND, ENTITY_NOT_FOUND.formatted(id));
		}
		personRepository.deleteById(id);
	}
}
