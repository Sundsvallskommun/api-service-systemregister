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
import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.service.InformationshanteringsplanService;

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
class InformationshanteringsplanResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private InformationshanteringsplanService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/informationshanteringsplaner";

	@Test
	void create() {
		final var dto = Informationshanteringsplan.create()
			.withNamn("Plan 2024")
			.withVersion("1.0")
			.withStatus("DRAFT");
		final var created = Informationshanteringsplan.create()
			.withNamn("Plan 2024")
			.withVersion("1.0")
			.withStatus("DRAFT");
		created.withId("ih-plan-1");

		when(serviceMock.create(any(Informationshanteringsplan.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("ih-plan-1");
		assertThat(response.getNamn()).isEqualTo("Plan 2024");
		verify(serviceMock).create(any(Informationshanteringsplan.class));
	}

	@Test
	void getById() {
		final var plan = Informationshanteringsplan.create()
			.withNamn("Plan 2024")
			.withVersion("1.0");
		plan.withId("ih-plan-1");

		when(serviceMock.getById("ih-plan-1")).thenReturn(plan);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "ih-plan-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("ih-plan-1");
		verify(serviceMock).getById("ih-plan-1");
	}

	@Test
	void getAllWithoutFilter() {
		final var plan1 = Informationshanteringsplan.create().withNamn("Plan 1");
		plan1.withId("id-1");
		final var plan2 = Informationshanteringsplan.create().withNamn("Plan 2");
		plan2.withId("id-2");

		when(serviceMock.getAll()).thenReturn(List.of(plan1, plan2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByOrganisationId() {
		final var plan = Informationshanteringsplan.create()
			.withNamn("Plan 2024")
			.withOrganisationId("org-1");
		plan.withId("ih-plan-1");

		when(serviceMock.getByOrganisationId("org-1")).thenReturn(List.of(plan));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("organisationId", "org-1")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getByOrganisationId("org-1");
	}

	@Test
	void getAllFilteredByStatus() {
		final var plan = Informationshanteringsplan.create()
			.withNamn("Plan 2024")
			.withStatus("ACTIVE");
		plan.withId("ih-plan-1");

		when(serviceMock.getByStatus("ACTIVE")).thenReturn(List.of(plan));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "ACTIVE")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getByStatus("ACTIVE");
	}

	@Test
	void update() {
		final var dto = Informationshanteringsplan.create()
			.withNamn("Updated Plan")
			.withVersion("2.0");
		final var updated = Informationshanteringsplan.create()
			.withNamn("Updated Plan")
			.withVersion("2.0");
		updated.withId("ih-plan-1");

		when(serviceMock.update(eq("ih-plan-1"), any(Informationshanteringsplan.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "ih-plan-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Informationshanteringsplan.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getNamn()).isEqualTo("Updated Plan");
		verify(serviceMock).update(eq("ih-plan-1"), any(Informationshanteringsplan.class));
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "ih-plan-1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("ih-plan-1");
	}
}
