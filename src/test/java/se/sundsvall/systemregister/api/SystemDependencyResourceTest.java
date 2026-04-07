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
import se.sundsvall.systemregister.api.model.SystemDependency;
import se.sundsvall.systemregister.service.SystemDependencyService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class SystemDependencyResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/system-dependencies";
	private static final String PATH_BY_ID = "/{municipalityId}/system-dependencies/{id}";

	@MockitoBean
	private SystemDependencyService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createSystemDependency() {
		// Arrange
		final var body = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		final var created = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		created.withId("dependency-1");

		when(serviceMock.create(any(SystemDependency.class))).thenReturn(created);

		// Act & Assert
		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/system-dependencies/dependency-1");

		verify(serviceMock).create(any(SystemDependency.class));
	}

	@Test
	void getSystemDependencyById() {
		// Arrange
		final var dependency = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		dependency.withId("dependency-1");

		when(serviceMock.getById("dependency-1")).thenReturn(dependency);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "dependency-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(SystemDependency.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("dependency-1");
		assertThat(response.getSourceSystemId()).isEqualTo("system-1");
		verify(serviceMock).getById("dependency-1");
	}

	@Test
	void getDependenciesBySourceSystemId() {
		// Arrange
		final var dep = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		dep.withId("dependency-1");

		when(serviceMock.getBySourceSystemId("system-1")).thenReturn(List.of(dep));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("sourceSystemId", "system-1")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemDependency.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getBySourceSystemId("system-1");
	}

	@Test
	void getDependenciesByTargetSystemId() {
		// Arrange
		final var dep = SystemDependency.create()
			.withSourceSystemId("system-1")
			.withTargetSystemId("system-2");
		dep.withId("dependency-1");

		when(serviceMock.getByTargetSystemId("system-2")).thenReturn(List.of(dep));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("targetSystemId", "system-2")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemDependency.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getByTargetSystemId("system-2");
	}

	@Test
	void getDependenciesNoFilter() {
		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemDependency.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isEmpty();
	}

	@Test
	void deleteSystemDependency() {
		// Arrange
		doNothing().when(serviceMock).delete("dependency-1");

		// Act & Assert
		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "dependency-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("dependency-1");
	}
}
