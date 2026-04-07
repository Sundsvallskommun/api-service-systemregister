package se.sundsvall.systemregister.api;

import java.time.OffsetDateTime;
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
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.service.EventLogService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class EventLogResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private EventLogService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/event-logs";

	@Test
	void createEventLog() {
		final var dto = EventLog.create()
			.withEntityType("SYSTEM")
			.withEntityId("entity-id")
			.withEventType("CREATED");
		final var created = EventLog.create()
			.withId("id-123")
			.withEntityType("SYSTEM")
			.withEntityId("entity-id")
			.withEventType("CREATED");

		when(serviceMock.create(any(EventLog.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(EventLog.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-123");
		assertThat(response.getEntityType()).isEqualTo("SYSTEM");
		assertThat(response.getEntityId()).isEqualTo("entity-id");
		verify(serviceMock).create(any(EventLog.class));
	}

	@Test
	void getEventLogById() {
		final var eventLog = EventLog.create()
			.withId("id-123")
			.withEntityType("SYSTEM")
			.withEntityId("entity-id");

		when(serviceMock.getById("id-123")).thenReturn(eventLog);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-123")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(EventLog.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-123");
		assertThat(response.getEntityType()).isEqualTo("SYSTEM");
		verify(serviceMock).getById("id-123");
	}

	@Test
	void getEventLogs() {
		final var logs = List.of(
			EventLog.create().withId("id-1").withEntityType("SYSTEM"),
			EventLog.create().withId("id-2").withEntityType("SERVICE"));

		when(serviceMock.getAll()).thenReturn(logs);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(EventLog.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		assertThat(response).extracting(EventLog::getId).containsExactly("id-1", "id-2");
		verify(serviceMock).getAll();
	}

	@Test
	void getEventLogsFilteredByEntityTypeAndId() {
		final var logs = List.of(
			EventLog.create().withId("id-1").withEntityType("SYSTEM").withEntityId("entity-id"));

		when(serviceMock.getByEntityTypeAndEntityId("SYSTEM", "entity-id")).thenReturn(logs);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("entityType", "SYSTEM")
				.queryParam("entityId", "entity-id")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(EventLog.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getId()).isEqualTo("id-1");
		verify(serviceMock).getByEntityTypeAndEntityId("SYSTEM", "entity-id");
	}

	@Test
	void acknowledgeEventLog() {
		final var acknowledged = EventLog.create()
			.withId("id-123")
			.withEntityType("SYSTEM")
			.withAcknowledgedAt(OffsetDateTime.now());

		when(serviceMock.acknowledge("id-123")).thenReturn(acknowledged);

		final var response = webTestClient.patch()
			.uri(builder -> builder.path(PATH + "/{id}/acknowledge").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-123")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(EventLog.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-123");
		assertThat(response.getAcknowledgedAt()).isNotNull();
		verify(serviceMock).acknowledge("id-123");
	}

	@Test
	void deleteEventLog() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-123")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("id-123");
	}
}
