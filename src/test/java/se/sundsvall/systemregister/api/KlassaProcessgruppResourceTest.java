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
import se.sundsvall.systemregister.api.model.KlassaProcessgrupp;
import se.sundsvall.systemregister.service.KlassaProcessgruppService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class KlassaProcessgruppResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/klassa/processgrupper";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private KlassaProcessgruppService serviceMock;

	@Test
	void create() {
		final var body = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Rekrytering")
			.withVerksamhetsomradeId(1);
		final var created = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Rekrytering")
			.withVerksamhetsomradeId(1);
		created.withId(1);

		when(serviceMock.create(any(KlassaProcessgrupp.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcessgrupp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Rekrytering");
		verify(serviceMock).create(any(KlassaProcessgrupp.class));
	}

	@Test
	void getById() {
		final var grupp = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Rekrytering");
		grupp.withId(1);

		when(serviceMock.getById(1)).thenReturn(grupp);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcessgrupp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getKod()).isEqualTo("PG001");
		verify(serviceMock).getById(1);
	}

	@Test
	void getAllWithoutFilter() {
		final var item1 = KlassaProcessgrupp.create().withKod("PG001").withNamn("Group 1");
		item1.withId(1);
		final var item2 = KlassaProcessgrupp.create().withKod("PG002").withNamn("Group 2");
		item2.withId(2);

		when(serviceMock.getAll()).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaProcessgrupp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByVerksamhetsomradeId() {
		final var grupp = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Rekrytering")
			.withVerksamhetsomradeId(5);
		grupp.withId(1);

		when(serviceMock.getByVerksamhetsomradeId(5)).thenReturn(List.of(grupp));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("verksamhetsomradeId", 5)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaProcessgrupp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getByVerksamhetsomradeId(5);
	}

	@Test
	void update() {
		final var body = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Updated Group");
		final var updated = KlassaProcessgrupp.create()
			.withKod("PG001")
			.withNamn("Updated Group");
		updated.withId(1);

		when(serviceMock.update(eq(1), any(KlassaProcessgrupp.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcessgrupp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getNamn()).isEqualTo("Updated Group");
		verify(serviceMock).update(eq(1), any(KlassaProcessgrupp.class));
	}

	@Test
	void deleteById() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete(1);
	}
}
