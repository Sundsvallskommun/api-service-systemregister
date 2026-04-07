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
import se.sundsvall.systemregister.api.model.KlassaProcess;
import se.sundsvall.systemregister.service.KlassaProcessService;

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
class KlassaProcessResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/klassa/processer";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private KlassaProcessService serviceMock;

	@Test
	void create() {
		final var body = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Recruitment")
			.withProcessgrupId(1);
		final var created = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Recruitment")
			.withProcessgrupId(1);
		created.withId(1);

		when(serviceMock.create(any(KlassaProcess.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcess.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Recruitment");
		verify(serviceMock).create(any(KlassaProcess.class));
	}

	@Test
	void getById() {
		final var process = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Recruitment");
		process.withId(1);

		when(serviceMock.getById(1)).thenReturn(process);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcess.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getKod()).isEqualTo("P001");
		verify(serviceMock).getById(1);
	}

	@Test
	void getAllWithoutFilter() {
		final var item1 = KlassaProcess.create().withKod("P001").withNamn("Process 1");
		item1.withId(1);
		final var item2 = KlassaProcess.create().withKod("P002").withNamn("Process 2");
		item2.withId(2);

		when(serviceMock.getAll()).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaProcess.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByProcessgrupId() {
		final var process = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Recruitment")
			.withProcessgrupId(5);
		process.withId(1);

		when(serviceMock.getByProcessgrupId(5)).thenReturn(List.of(process));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("processgrupId", 5)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaProcess.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getByProcessgrupId(5);
	}

	@Test
	void update() {
		final var body = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Updated Process");
		final var updated = KlassaProcess.create()
			.withKod("P001")
			.withNamn("Updated Process");
		updated.withId(1);

		when(serviceMock.update(eq(1), any(KlassaProcess.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaProcess.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getNamn()).isEqualTo("Updated Process");
		verify(serviceMock).update(eq(1), any(KlassaProcess.class));
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
