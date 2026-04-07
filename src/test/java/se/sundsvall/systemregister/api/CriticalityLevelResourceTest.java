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
import se.sundsvall.systemregister.api.model.CriticalityLevel;
import se.sundsvall.systemregister.service.CriticalityLevelService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class CriticalityLevelResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private CriticalityLevelService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/criticality-levels";

	@Test
	void createCriticalityLevel() {
		final var dto = CriticalityLevel.create()
			.withName("Critical")
			.withCode("CRITICAL")
			.withLevel(1);
		final var created = CriticalityLevel.create()
			.withId("critical-1")
			.withName("Critical")
			.withCode("CRITICAL")
			.withLevel(1);

		when(serviceMock.create(any(CriticalityLevel.class))).thenReturn(created);

		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/criticality-levels/critical-1");

		verify(serviceMock).create(any(CriticalityLevel.class));
	}

	@Test
	void getCriticalityLevelById() {
		final var criticalityLevel = CriticalityLevel.create()
			.withId("critical-1")
			.withName("Critical")
			.withCode("CRITICAL")
			.withLevel(1);

		when(serviceMock.getById("critical-1")).thenReturn(criticalityLevel);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "critical-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(CriticalityLevel.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("critical-1");
		assertThat(response.getName()).isEqualTo("Critical");
		verify(serviceMock).getById("critical-1");
	}

	@Test
	void getAll() {
		final var level1 = CriticalityLevel.create().withId("id-1").withName("Critical").withCode("CRITICAL");
		final var level2 = CriticalityLevel.create().withId("id-2").withName("High").withCode("HIGH");

		when(serviceMock.getAll()).thenReturn(List.of(level1, level2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(CriticalityLevel.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void updateCriticalityLevel() {
		final var dto = CriticalityLevel.create()
			.withName("Updated Critical")
			.withCode("CRITICAL")
			.withLevel(1);
		final var updated = CriticalityLevel.create()
			.withId("critical-1")
			.withName("Updated Critical")
			.withCode("CRITICAL")
			.withLevel(1);

		when(serviceMock.update(any(String.class), any(CriticalityLevel.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "critical-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(CriticalityLevel.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getName()).isEqualTo("Updated Critical");
		verify(serviceMock).update(any(String.class), any(CriticalityLevel.class));
	}

	@Test
	void deleteCriticalityLevel() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "critical-1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("critical-1");
	}
}
