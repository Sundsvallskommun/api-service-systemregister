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
import se.sundsvall.systemregister.api.model.KlassaVerksamhetsomrade;
import se.sundsvall.systemregister.service.KlassaVerksamhetsomradeService;

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
class KlassaVerksamhetsomradeResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/klassa/verksamhetsomraden";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private KlassaVerksamhetsomradeService serviceMock;

	@Test
	void create() {
		final var body = KlassaVerksamhetsomrade.create()
			.withNamn("Personadministration")
			.withKod("101");
		final var created = KlassaVerksamhetsomrade.create()
			.withNamn("Personadministration")
			.withKod("101");
		created.withId(1);

		when(serviceMock.create(any(KlassaVerksamhetsomrade.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaVerksamhetsomrade.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Personadministration");
		verify(serviceMock).create(any(KlassaVerksamhetsomrade.class));
	}

	@Test
	void getById() {
		final var entity = KlassaVerksamhetsomrade.create()
			.withNamn("Personadministration")
			.withKod("101");
		entity.withId(1);

		when(serviceMock.getById(1)).thenReturn(entity);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaVerksamhetsomrade.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Personadministration");
		verify(serviceMock).getById(1);
	}

	@Test
	void getAll() {
		final var item1 = KlassaVerksamhetsomrade.create().withNamn("Area1");
		item1.withId(1);
		final var item2 = KlassaVerksamhetsomrade.create().withNamn("Area2");
		item2.withId(2);

		when(serviceMock.getAll()).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaVerksamhetsomrade.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByVerksamhetstypId() {
		final var item = KlassaVerksamhetsomrade.create()
			.withNamn("Area1")
			.withVerksamhetstypId(5);
		item.withId(1);

		when(serviceMock.getByVerksamhetstypId(5)).thenReturn(List.of(item));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("verksamhetstypId", 5)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaVerksamhetsomrade.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getByVerksamhetstypId(5);
	}

	@Test
	void update() {
		final var body = KlassaVerksamhetsomrade.create()
			.withNamn("Updated");
		final var updated = KlassaVerksamhetsomrade.create()
			.withNamn("Updated");
		updated.withId(1);

		when(serviceMock.update(eq(1), any(KlassaVerksamhetsomrade.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaVerksamhetsomrade.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Updated");
		verify(serviceMock).update(eq(1), any(KlassaVerksamhetsomrade.class));
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
