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
import se.sundsvall.systemregister.api.model.Handlingstyp;
import se.sundsvall.systemregister.service.HandlingstypService;

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
class HandlingstypResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private HandlingstypService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String IH_PLAN_ID = "ih-plan-1";
	private static final String PATH = "/{municipalityId}/informationshanteringsplaner/{ihPlanId}/handlingstyper";

	@Test
	void create() {
		final var dto = Handlingstyp.create()
			.withNamn("Personuppgiftsbeslut")
			.withIhPlanId(IH_PLAN_ID);
		final var created = Handlingstyp.create()
			.withNamn("Personuppgiftsbeslut")
			.withIhPlanId(IH_PLAN_ID);
		created.withId("handlingstyp-1");

		when(serviceMock.create(any(Handlingstyp.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "ihPlanId", IH_PLAN_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Handlingstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("handlingstyp-1");
		assertThat(response.getNamn()).isEqualTo("Personuppgiftsbeslut");
		assertThat(response.getIhPlanId()).isEqualTo(IH_PLAN_ID);
		verify(serviceMock).create(any(Handlingstyp.class));
	}

	@Test
	void getById() {
		final var handlingstyp = Handlingstyp.create()
			.withNamn("Personuppgiftsbeslut")
			.withIhPlanId(IH_PLAN_ID);
		handlingstyp.withId("handlingstyp-1");

		when(serviceMock.getById("handlingstyp-1")).thenReturn(handlingstyp);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "ihPlanId", IH_PLAN_ID, "id", "handlingstyp-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Handlingstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("handlingstyp-1");
		verify(serviceMock).getById("handlingstyp-1");
	}

	@Test
	void getByIhPlanId() {
		final var item1 = Handlingstyp.create().withNamn("Type 1").withIhPlanId(IH_PLAN_ID);
		item1.withId("id-1");
		final var item2 = Handlingstyp.create().withNamn("Type 2").withIhPlanId(IH_PLAN_ID);
		item2.withId("id-2");

		when(serviceMock.getByIhPlanId(IH_PLAN_ID)).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "ihPlanId", IH_PLAN_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Handlingstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getByIhPlanId(IH_PLAN_ID);
	}

	@Test
	void update() {
		final var dto = Handlingstyp.create()
			.withNamn("Updated Type")
			.withIhPlanId(IH_PLAN_ID);
		final var updated = Handlingstyp.create()
			.withNamn("Updated Type")
			.withIhPlanId(IH_PLAN_ID);
		updated.withId("handlingstyp-1");

		when(serviceMock.update(eq("handlingstyp-1"), any(Handlingstyp.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "ihPlanId", IH_PLAN_ID, "id", "handlingstyp-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Handlingstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getNamn()).isEqualTo("Updated Type");
		verify(serviceMock).update(eq("handlingstyp-1"), any(Handlingstyp.class));
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "ihPlanId", IH_PLAN_ID, "id", "handlingstyp-1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("handlingstyp-1");
	}
}
