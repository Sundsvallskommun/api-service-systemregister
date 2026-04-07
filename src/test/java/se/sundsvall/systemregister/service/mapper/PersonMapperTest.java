package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.integration.db.model.PersonEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PersonMapperTest {

	@Test
	void toPerson() {
		final var entity = PersonEntity.create()
			.withFirstName("John")
			.withLastName("Doe")
			.withEmail("john.doe@example.com")
			.withPhone("+46701234567")
			.withTitle("System Administrator")
			.withUsername("jdoe")
			.withOrganizationId("org-1");
		entity.withId("person-1");

		final var result = PersonMapper.toPerson(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("person-1");
		assertThat(result.getFirstName()).isEqualTo("John");
		assertThat(result.getLastName()).isEqualTo("Doe");
		assertThat(result.getEmail()).isEqualTo("john.doe@example.com");
		assertThat(result.getPhone()).isEqualTo("+46701234567");
		assertThat(result.getTitle()).isEqualTo("System Administrator");
		assertThat(result.getUsername()).isEqualTo("jdoe");
		assertThat(result.getOrganizationId()).isEqualTo("org-1");
	}

	@Test
	void toPersonNull() {
		assertThat(PersonMapper.toPerson(null)).isNull();
	}

	@Test
	void toPersonEntity() {
		final var model = Person.create()
			.withFirstName("John")
			.withLastName("Doe")
			.withEmail("john.doe@example.com")
			.withPhone("+46701234567")
			.withTitle("System Administrator")
			.withUsername("jdoe")
			.withOrganizationId("org-1");

		final var result = PersonMapper.toPersonEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getFirstName()).isEqualTo("John");
		assertThat(result.getLastName()).isEqualTo("Doe");
		assertThat(result.getEmail()).isEqualTo("john.doe@example.com");
		assertThat(result.getPhone()).isEqualTo("+46701234567");
		assertThat(result.getTitle()).isEqualTo("System Administrator");
		assertThat(result.getUsername()).isEqualTo("jdoe");
	}

	@Test
	void toPersonEntityNull() {
		assertThat(PersonMapper.toPersonEntity(null)).isNull();
	}

	@Test
	void toPersonList() {
		final var entity1 = PersonEntity.create()
			.withFirstName("John");
		entity1.withId("person-1");
		final var entity2 = PersonEntity.create()
			.withFirstName("Jane");
		entity2.withId("person-2");
		final var entities = List.of(entity1, entity2);

		final var result = PersonMapper.toPersonList(entities);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getFirstName()).isEqualTo("John");
		assertThat(result.get(1).getFirstName()).isEqualTo("Jane");
	}

	@Test
	void toPersonListNull() {
		assertThat(PersonMapper.toPersonList(null)).isNull();
	}

	@Test
	void updatePersonEntity() {
		final var entity = PersonEntity.create()
			.withFirstName("John")
			.withLastName("Doe");

		final var model = Person.create()
			.withLastName("Smith")
			.withEmail("john.smith@example.com")
			.withPhone("+46709876543");

		PersonMapper.updatePersonEntity(entity, model);

		assertThat(entity.getFirstName()).isEqualTo("John");
		assertThat(entity.getLastName()).isEqualTo("Smith");
		assertThat(entity.getEmail()).isEqualTo("john.smith@example.com");
		assertThat(entity.getPhone()).isEqualTo("+46709876543");
	}
}
