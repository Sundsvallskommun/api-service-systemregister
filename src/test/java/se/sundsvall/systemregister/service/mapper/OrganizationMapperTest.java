package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.integration.db.model.OrganizationEntity;

import static org.assertj.core.api.Assertions.assertThat;

class OrganizationMapperTest {

	@Test
	void toOrganization() {
		final var entity = OrganizationEntity.create()
			.withName("IT Department")
			.withDescription("Central IT department")
			.withOrgNumber("2021001234")
			.withEmail("it@example.com")
			.withPhone("+46701234567")
			.withParentId("org-parent")
			.withLevel(1);
		entity.withId("org-1");

		final var result = OrganizationMapper.toOrganization(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("org-1");
		assertThat(result.getName()).isEqualTo("IT Department");
		assertThat(result.getDescription()).isEqualTo("Central IT department");
		assertThat(result.getOrgNumber()).isEqualTo("2021001234");
		assertThat(result.getEmail()).isEqualTo("it@example.com");
		assertThat(result.getPhone()).isEqualTo("+46701234567");
		assertThat(result.getParentId()).isEqualTo("org-parent");
		assertThat(result.getLevel()).isEqualTo(1);
	}

	@Test
	void toOrganizationNull() {
		assertThat(OrganizationMapper.toOrganization(null)).isNull();
	}

	@Test
	void toOrganizationEntity() {
		final var model = Organization.create()
			.withName("IT Department")
			.withDescription("Central IT department")
			.withOrgNumber("2021001234")
			.withEmail("it@example.com")
			.withPhone("+46701234567")
			.withLevel(1);

		final var result = OrganizationMapper.toOrganizationEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("IT Department");
		assertThat(result.getDescription()).isEqualTo("Central IT department");
		assertThat(result.getOrgNumber()).isEqualTo("2021001234");
		assertThat(result.getEmail()).isEqualTo("it@example.com");
		assertThat(result.getLevel()).isEqualTo(1);
	}

	@Test
	void toOrganizationEntityNull() {
		assertThat(OrganizationMapper.toOrganizationEntity(null)).isNull();
	}

	@Test
	void toOrganizationList() {
		final var entity1 = OrganizationEntity.create()
			.withName("Organization 1");
		entity1.withId("org-1");
		final var entity2 = OrganizationEntity.create()
			.withName("Organization 2");
		entity2.withId("org-2");
		final var entities = List.of(entity1, entity2);

		final var result = OrganizationMapper.toOrganizationList(entities);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.getFirst().getName()).isEqualTo("Organization 1");
		assertThat(result.get(1).getName()).isEqualTo("Organization 2");
	}

	@Test
	void toOrganizationListNull() {
		assertThat(OrganizationMapper.toOrganizationList(null)).isNull();
	}

	@Test
	void updateOrganizationEntity() {
		final var entity = OrganizationEntity.create()
			.withName("Old Name")
			.withOrgNumber("2021001234");

		final var model = Organization.create()
			.withName("New Name")
			.withDescription("New Description")
			.withEmail("newemail@example.com");

		OrganizationMapper.updateOrganizationEntity(entity, model);

		assertThat(entity.getName()).isEqualTo("New Name");
		assertThat(entity.getDescription()).isEqualTo("New Description");
		assertThat(entity.getEmail()).isEqualTo("newemail@example.com");
		assertThat(entity.getOrgNumber()).isEqualTo("2021001234");
	}
}
