package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import java.util.Optional;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.integration.db.model.PersonEntity;

public final class PersonMapper {

	private PersonMapper() {}

	public static Person toPerson(final PersonEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> Person.create()
				.withId(e.getId())
				.withFirstName(e.getFirstName())
				.withLastName(e.getLastName())
				.withEmail(e.getEmail())
				.withPhone(e.getPhone())
				.withTitle(e.getTitle())
				.withUsername(e.getUsername())
				.withOrganizationId(e.getOrganizationId()))
			.orElse(null);
	}

	public static PersonEntity toPersonEntity(final Person model) {
		return Optional.ofNullable(model)
			.map(m -> PersonEntity.create()
				.withFirstName(m.getFirstName())
				.withLastName(m.getLastName())
				.withEmail(m.getEmail())
				.withPhone(m.getPhone())
				.withTitle(m.getTitle())
				.withUsername(m.getUsername())
				.withOrganizationId(m.getOrganizationId()))
			.orElse(null);
	}

	public static List<Person> toPersonList(final List<PersonEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(PersonMapper::toPerson)
				.toList())
			.orElse(null);
	}

	public static void updatePersonEntity(final PersonEntity entity, final Person model) {
		Optional.ofNullable(model).ifPresent(m -> {
			Optional.ofNullable(m.getFirstName()).ifPresent(entity::withFirstName);
			Optional.ofNullable(m.getLastName()).ifPresent(entity::withLastName);
			Optional.ofNullable(m.getEmail()).ifPresent(entity::withEmail);
			Optional.ofNullable(m.getPhone()).ifPresent(entity::withPhone);
			Optional.ofNullable(m.getTitle()).ifPresent(entity::withTitle);
			Optional.ofNullable(m.getUsername()).ifPresent(entity::withUsername);
			Optional.ofNullable(m.getOrganizationId()).ifPresent(entity::withOrganizationId);
		});
	}
}
