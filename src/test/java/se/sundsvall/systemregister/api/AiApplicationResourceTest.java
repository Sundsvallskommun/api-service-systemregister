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
import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.service.AiApplicationService;

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
class AiApplicationResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private AiApplicationService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/ai-applications";

	@Test
	void createAiApplication() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Risk Assessment")
			.withStatus("ACTIVE");

		when(serviceMock.create(any(AiApplication.class))).thenReturn(dto);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(response.getName()).isEqualTo("Risk Assessment");
		assertThat(response.getStatus()).isEqualTo("ACTIVE");
		verify(serviceMock).create(any(AiApplication.class));
	}

	@Test
	void getAiApplicationById() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Test");

		when(serviceMock.getById("id-001")).thenReturn(dto);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(response.getName()).isEqualTo("Test");
		verify(serviceMock).getById("id-001");
	}

	@Test
	void getAllAiApplications() {
		final var dto1 = AiApplication.create().withAiApplicationId("AI-001");
		final var dto2 = AiApplication.create().withAiApplicationId("AI-002");

		when(serviceMock.getAll()).thenReturn(List.of(dto1, dto2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		assertThat(response.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		assertThat(response.get(1).getAiApplicationId()).isEqualTo("AI-002");
		verify(serviceMock).getAll();
	}

	@Test
	void getAiApplicationsByStatus() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withStatus("ACTIVE");

		when(serviceMock.getAllByStatus("ACTIVE")).thenReturn(List.of(dto));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "ACTIVE")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		verify(serviceMock).getAllByStatus("ACTIVE");
	}

	@Test
	void getAiApplicationsByRiskCategory() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withRiskCategory("HIGH_RISK");

		when(serviceMock.getAllByRiskCategory("HIGH_RISK")).thenReturn(List.of(dto));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("riskCategory", "HIGH_RISK")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getAiApplicationId()).isEqualTo("AI-001");
		verify(serviceMock).getAllByRiskCategory("HIGH_RISK");
	}

	@Test
	void updateAiApplication() {
		final var dto = AiApplication.create()
			.withAiApplicationId("AI-001")
			.withName("Updated Name");

		when(serviceMock.update(eq("id-001"), any(AiApplication.class))).thenReturn(dto);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(AiApplication.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getAiApplicationId()).isEqualTo("AI-001");
		assertThat(response.getName()).isEqualTo("Updated Name");
		verify(serviceMock).update(eq("id-001"), any(AiApplication.class));
	}

	@Test
	void deleteAiApplication() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("id-001");
	}
}
