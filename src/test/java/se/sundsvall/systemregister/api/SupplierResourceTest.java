package se.sundsvall.systemregister.api;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.systemregister.Application;
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.service.SupplierService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class SupplierResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/suppliers";
	private static final String PATH_BY_ID = "/{municipalityId}/suppliers/{id}";

	@MockitoBean
	private SupplierService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createSupplier() {
		// Arrange
		final var body = Supplier.create()
			.withName("Tech Solutions Inc");
		final var created = Supplier.create()
			.withName("Tech Solutions Inc");
		created.withId("supplier-1");

		when(serviceMock.create(any(Supplier.class))).thenReturn(created);

		// Act & Assert
		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/suppliers/supplier-1");

		verify(serviceMock).create(any(Supplier.class));
	}

	@Test
	void getSupplierById() {
		// Arrange
		final var supplier = Supplier.create()
			.withName("Tech Solutions Inc");
		supplier.withId("supplier-1");

		when(serviceMock.getById("supplier-1")).thenReturn(supplier);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "supplier-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Supplier.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("supplier-1");
		assertThat(response.getName()).isEqualTo("Tech Solutions Inc");
		verify(serviceMock).getById("supplier-1");
	}

	@Test
	void getAll() {
		// Arrange
		final var s1 = Supplier.create().withName("Supplier1");
		s1.withId("supplier-1");
		final var s2 = Supplier.create().withName("Supplier2");
		s2.withId("supplier-2");

		when(serviceMock.getAll()).thenReturn(List.of(s1, s2));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Supplier.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllActiveOnly() {
		// Arrange
		final var s = Supplier.create()
			.withName("Active Supplier")
			.withIsActive(true);
		s.withId("supplier-1");

		when(serviceMock.getAllActive()).thenReturn(List.of(s));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("activeOnly", true)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Supplier.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getAllActive();
	}

	@Test
	void updateSupplier() {
		// Arrange
		final var body = Supplier.create().withName("Updated");
		final var updated = Supplier.create().withName("Updated");
		updated.withId("supplier-1");

		when(serviceMock.update(eq("supplier-1"), any(Supplier.class))).thenReturn(updated);

		// Act
		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "supplier-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Supplier.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("supplier-1");
		assertThat(response.getName()).isEqualTo("Updated");
		verify(serviceMock).update(eq("supplier-1"), any(Supplier.class));
	}

	@Test
	void deleteSupplier() {
		// Arrange
		doNothing().when(serviceMock).delete("supplier-1");

		// Act & Assert
		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "supplier-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("supplier-1");
	}
}
