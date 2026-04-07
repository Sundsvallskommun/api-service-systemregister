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
import se.sundsvall.systemregister.api.model.Process;
import se.sundsvall.systemregister.service.ProcessService;

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
class ProcessResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/processer";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private ProcessService serviceMock;

	@Test
	void create() {
		final var body = Process.create()
			.withProcesskod("P001")
			.withNamn("Recruitment");
		final var created = Process.create()
			.withProcesskod("P001")
			.withNamn("Recruitment");
		created.withId("process-1");

		when(serviceMock.create(any(Process.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Process.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("process-1");
		assertThat(response.getProcesskod()).isEqualTo("P001");
		verify(serviceMock).create(any(Process.class));
	}

	@Test
	void getById() {
		final var process = Process.create()
			.withProcesskod("P001")
			.withNamn("Recruitment");
		process.withId("process-1");

		when(serviceMock.getById("process-1")).thenReturn(process);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "process-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Process.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("process-1");
		assertThat(response.getNamn()).isEqualTo("Recruitment");
		verify(serviceMock).getById("process-1");
	}

	@Test
	void getAll() {
		final var p1 = Process.create().withNamn("Process1");
		p1.withId("process-1");
		final var p2 = Process.create().withNamn("Process2");
		p2.withId("process-2");

		when(serviceMock.getAll()).thenReturn(List.of(p1, p2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Process.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByProcesskod() {
		final var process = Process.create()
			.withProcesskod("P001")
			.withNamn("Recruitment");
		process.withId("process-1");

		when(serviceMock.getByProcesskod("P001")).thenReturn(process);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("processkod", "P001")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Process.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getProcesskod()).isEqualTo("P001");
		verify(serviceMock).getByProcesskod("P001");
	}

	@Test
	void update() {
		final var body = Process.create().withProcesskod("P001").withNamn("Updated");
		final var updated = Process.create().withProcesskod("P001").withNamn("Updated");
		updated.withId("process-1");

		when(serviceMock.update(eq("process-1"), any(Process.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "process-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Process.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("process-1");
		assertThat(response.getNamn()).isEqualTo("Updated");
		verify(serviceMock).update(eq("process-1"), any(Process.class));
	}

	@Test
	void deleteById() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "process-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("process-1");
	}
}
