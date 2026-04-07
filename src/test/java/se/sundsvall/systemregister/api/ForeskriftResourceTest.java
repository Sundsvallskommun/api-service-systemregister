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
import se.sundsvall.systemregister.api.model.Foreskrift;
import se.sundsvall.systemregister.service.ForeskriftService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class ForeskriftResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private ForeskriftService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/foreskrifter";

	@Test
	void create() {
		final var dto = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Test Foreskrift");
		final var created = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Test Foreskrift");
		created.withId("foreskrift-1");

		when(serviceMock.create(any(Foreskrift.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Foreskrift.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("foreskrift-1");
		assertThat(response.getBeteckning()).isEqualTo("FOR 2023:456");
		verify(serviceMock).create(any(Foreskrift.class));
	}

	@Test
	void getById() {
		final var foreskrift = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Test Foreskrift");
		foreskrift.withId("foreskrift-1");

		when(serviceMock.getById("foreskrift-1")).thenReturn(foreskrift);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "foreskrift-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Foreskrift.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("foreskrift-1");
		verify(serviceMock).getById("foreskrift-1");
	}

	@Test
	void getAllWithoutFilter() {
		final var item1 = Foreskrift.create().withBeteckning("FOR 2023:1").withNamn("F1");
		item1.withId("id-1");
		final var item2 = Foreskrift.create().withBeteckning("FOR 2023:2").withNamn("F2");
		item2.withId("id-2");

		when(serviceMock.getAll()).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Foreskrift.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByBeteckning() {
		final var foreskrift = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Test Foreskrift");
		foreskrift.withId("foreskrift-1");

		when(serviceMock.getByBeteckning("FOR 2023:456")).thenReturn(foreskrift);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("beteckning", "FOR 2023:456")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Foreskrift.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getBeteckning()).isEqualTo("FOR 2023:456");
		verify(serviceMock).getByBeteckning("FOR 2023:456");
	}

	@Test
	void update() {
		final var dto = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Updated Foreskrift");
		final var updated = Foreskrift.create()
			.withBeteckning("FOR 2023:456")
			.withNamn("Updated Foreskrift");
		updated.withId("foreskrift-1");

		when(serviceMock.update(eq("foreskrift-1"), any(Foreskrift.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "foreskrift-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Foreskrift.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getNamn()).isEqualTo("Updated Foreskrift");
		verify(serviceMock).update(eq("foreskrift-1"), any(Foreskrift.class));
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "foreskrift-1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("foreskrift-1");
	}
}
