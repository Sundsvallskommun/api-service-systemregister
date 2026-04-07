package se.sundsvall.systemregister.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.integration.db.model.SupplierEntity;

import static org.assertj.core.api.Assertions.assertThat;

class SupplierMapperTest {

	@Test
	void toSupplier() {
		final var entity = SupplierEntity.create()
			.withName("Tech Solutions Inc")
			.withOrgNumber("5565123456")
			.withDescription("Provider of cloud infrastructure services")
			.withWebsite("https://www.techsolutions.com")
			.withContactEmail("contact@techsolutions.com")
			.withIsActive(true);
		entity.withId("supplier-1");

		final var result = SupplierMapper.toSupplier(entity);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("supplier-1");
		assertThat(result.getName()).isEqualTo("Tech Solutions Inc");
		assertThat(result.getOrgNumber()).isEqualTo("5565123456");
		assertThat(result.getDescription()).isEqualTo("Provider of cloud infrastructure services");
		assertThat(result.getWebsite()).isEqualTo("https://www.techsolutions.com");
		assertThat(result.getContactEmail()).isEqualTo("contact@techsolutions.com");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toSupplierNull() {
		assertThat(SupplierMapper.toSupplier(null)).isNull();
	}

	@Test
	void toSupplierEntity() {
		final var model = Supplier.create()
			.withName("Tech Solutions Inc")
			.withOrgNumber("5565123456")
			.withDescription("Provider of cloud infrastructure services")
			.withWebsite("https://www.techsolutions.com")
			.withContactEmail("contact@techsolutions.com")
			.withIsActive(true);

		final var result = SupplierMapper.toSupplierEntity(model);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Tech Solutions Inc");
		assertThat(result.getOrgNumber()).isEqualTo("5565123456");
		assertThat(result.getDescription()).isEqualTo("Provider of cloud infrastructure services");
		assertThat(result.getWebsite()).isEqualTo("https://www.techsolutions.com");
		assertThat(result.getContactEmail()).isEqualTo("contact@techsolutions.com");
		assertThat(result.getIsActive()).isTrue();
	}

	@Test
	void toSupplierEntityNull() {
		assertThat(SupplierMapper.toSupplierEntity(null)).isNull();
	}

	@Test
	void toSupplierList() {
		final var entity1 = SupplierEntity.create()
			.withName("Supplier 1");
		entity1.withId("supplier-1");
		final var entity2 = SupplierEntity.create()
			.withName("Supplier 2");
		entity2.withId("supplier-2");
		final var entities = List.of(entity1, entity2);

		final var result = SupplierMapper.toSupplierList(entities);

		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.getFirst().getName()).isEqualTo("Supplier 1");
		assertThat(result.get(1).getName()).isEqualTo("Supplier 2");
	}

	@Test
	void toSupplierListNull() {
		assertThat(SupplierMapper.toSupplierList(null)).isNull();
	}

	@Test
	void updateSupplierEntity() {
		final var entity = SupplierEntity.create()
			.withName("Old Name")
			.withOrgNumber("5565123456");

		final var model = Supplier.create()
			.withName("New Name")
			.withWebsite("https://newsite.com")
			.withContactEmail("newemail@supplier.com");

		SupplierMapper.updateSupplierEntity(entity, model);

		assertThat(entity.getName()).isEqualTo("New Name");
		assertThat(entity.getOrgNumber()).isEqualTo("5565123456");
		assertThat(entity.getWebsite()).isEqualTo("https://newsite.com");
		assertThat(entity.getContactEmail()).isEqualTo("newemail@supplier.com");
	}
}
