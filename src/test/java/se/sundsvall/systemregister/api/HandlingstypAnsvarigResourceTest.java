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
import se.sundsvall.systemregister.api.model.HandlingstypAnsvarig;
import se.sundsvall.systemregister.service.HandlingstypAnsvarigService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class HandlingstypAnsvarigResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private HandlingstypAnsvarigService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String HANDLINGSTYP_ID = "handlingstyp-1";
	private static final String PATH = "/{municipalityId}/handlingstyper/{handlingstypId}/ansvariga";

	@Test
	void create() {
		final var dto = HandlingstypAnsvarig.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withOrganisationId("org-1")
			.withRoll("Owner");
		final var created = HandlingstypAnsvarig.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withOrganisationId("org-1")
			.withRoll("Owner");
		created.withId("ansvarig-1");

		when(serviceMock.create(any(HandlingstypAnsvarig.class))).thenReturn(created);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(HandlingstypAnsvarig.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("ansvarig-1");
		assertThat(response.getHandlingstypId()).isEqualTo(HANDLINGSTYP_ID);
		assertThat(response.getOrganisationId()).isEqualTo("org-1");
		verify(serviceMock).create(any(HandlingstypAnsvarig.class));
	}

	@Test
	void getByHandlingstypId() {
		final var item1 = HandlingstypAnsvarig.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withOrganisationId("org-1")
			.withRoll("Owner");
		item1.withId("ansvarig-1");
		final var item2 = HandlingstypAnsvarig.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withOrganisationId("org-2")
			.withRoll("Reviewer");
		item2.withId("ansvarig-2");

		when(serviceMock.getByHandlingstypId(HANDLINGSTYP_ID)).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(HandlingstypAnsvarig.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getByHandlingstypId(HANDLINGSTYP_ID);
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID, "id", "ansvarig-1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete("ansvarig-1");
	}
}
