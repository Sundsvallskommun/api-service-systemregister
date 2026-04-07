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
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.service.SecurityLevelDefinitionService;

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
class SecurityLevelDefinitionResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/security-level-definitions";
	private static final String PATH_BY_ID = "/{municipalityId}/security-level-definitions/{id}";

	@MockitoBean
	private SecurityLevelDefinitionService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createSecurityLevelDefinition() {
		// Arrange
		final var dto = SecurityLevelDefinition.create()
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium");
		final var created = SecurityLevelDefinition.create()
			.withId("id-1")
			.withDimension("K")
			.withLevel(2)
			.withLabel("Medium");

		when(serviceMock.create(any(SecurityLevelDefinition.class))).thenReturn(created);

		// Act
		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-1");
		assertThat(response.getDimension()).isEqualTo("K");
		assertThat(response.getLevel()).isEqualTo(2);
		verify(serviceMock).create(any(SecurityLevelDefinition.class));
	}

	@Test
	void getSecurityLevelDefinitionById() {
		// Arrange
		final var definition = SecurityLevelDefinition.create()
			.withId("id-1")
			.withDimension("K")
			.withLevel(2);

		when(serviceMock.getById("id-1")).thenReturn(definition);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-1");
		assertThat(response.getDimension()).isEqualTo("K");
		verify(serviceMock).getById("id-1");
	}

	@Test
	void getSecurityLevelDefinitions() {
		// Arrange
		final var definitions = List.of(
			SecurityLevelDefinition.create().withId("id-1").withDimension("K"),
			SecurityLevelDefinition.create().withId("id-2").withDimension("R"));

		when(serviceMock.getAll()).thenReturn(definitions);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getSecurityLevelDefinitionsFilteredByDimensionAndLevel() {
		// Arrange
		final var definitions = List.of(
			SecurityLevelDefinition.create().withId("id-1").withDimension("K").withLevel(2));

		when(serviceMock.getByDimensionAndLevel("K", 2)).thenReturn(definitions);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("dimension", "K")
				.queryParam("level", 2)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getId()).isEqualTo("id-1");
		verify(serviceMock).getByDimensionAndLevel("K", 2);
	}

	@Test
	void getSecurityLevelDefinitionsFilteredByDimension() {
		// Arrange
		final var definitions = List.of(
			SecurityLevelDefinition.create().withId("id-1").withDimension("K"),
			SecurityLevelDefinition.create().withId("id-2").withDimension("K"));

		when(serviceMock.getByDimension("K")).thenReturn(definitions);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("dimension", "K")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(2);
		verify(serviceMock).getByDimension("K");
	}

	@Test
	void updateSecurityLevelDefinition() {
		// Arrange
		final var dto = SecurityLevelDefinition.create()
			.withDimension("R")
			.withLevel(3)
			.withLabel("New Label");
		final var updated = SecurityLevelDefinition.create()
			.withId("id-1")
			.withDimension("R")
			.withLevel(3)
			.withLabel("New Label");

		when(serviceMock.update(eq("id-1"), any(SecurityLevelDefinition.class))).thenReturn(updated);

		// Act
		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectBody(SecurityLevelDefinition.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-1");
		assertThat(response.getDimension()).isEqualTo("R");
		verify(serviceMock).update(eq("id-1"), any(SecurityLevelDefinition.class));
	}

	@Test
	void deleteSecurityLevelDefinition() {
		// Arrange
		doNothing().when(serviceMock).delete("id-1");

		// Act & Assert
		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("id-1");
	}
}
