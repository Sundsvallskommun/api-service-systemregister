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
import se.sundsvall.systemregister.api.model.SystemServiceLink;
import se.sundsvall.systemregister.service.SystemServiceLinkService;

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
class SystemServiceLinkResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/system-service-links";
	private static final String PATH_BY_ID = "/{municipalityId}/system-service-links/{id}";

	@MockitoBean
	private SystemServiceLinkService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createSystemServiceLink() {
		// Arrange
		final var body = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		final var created = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		created.withId("link-1");

		when(serviceMock.create(any(SystemServiceLink.class))).thenReturn(created);

		// Act & Assert
		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/system-service-links/link-1");

		verify(serviceMock).create(any(SystemServiceLink.class));
	}

	@Test
	void getSystemServiceLinkById() {
		// Arrange
		final var link = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		link.withId("link-1");

		when(serviceMock.getById("link-1")).thenReturn(link);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "link-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(SystemServiceLink.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("link-1");
		assertThat(response.getSystemId()).isEqualTo("system-1");
		verify(serviceMock).getById("link-1");
	}

	@Test
	void getLinksBySystemId() {
		// Arrange
		final var link = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		link.withId("link-1");

		when(serviceMock.getBySystemId("system-1")).thenReturn(List.of(link));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("systemId", "system-1")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemServiceLink.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getBySystemId("system-1");
	}

	@Test
	void getLinksByServiceId() {
		// Arrange
		final var link = SystemServiceLink.create()
			.withSystemId("system-1")
			.withServiceId("service-1");
		link.withId("link-1");

		when(serviceMock.getByServiceId("service-1")).thenReturn(List.of(link));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("serviceId", "service-1")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemServiceLink.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getByServiceId("service-1");
	}

	@Test
	void getLinksNoFilter() {
		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(SystemServiceLink.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isEmpty();
	}

	@Test
	void deleteSystemServiceLink() {
		// Arrange
		doNothing().when(serviceMock).delete("link-1");

		// Act & Assert
		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "link-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("link-1");
	}
}
