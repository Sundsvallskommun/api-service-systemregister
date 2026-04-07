package se.sundsvall.systemregister.api;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.systemregister.Application;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.service.InformationsklassningService;

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
class InformationsklassningResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private InformationsklassningService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String HANDLINGSTYP_ID = "handlingstyp-1";
	private static final String PATH = "/{municipalityId}/handlingstyper/{handlingstypId}/klassning";

	@Test
	void createOrUpdate() {
		final var dto = Informationsklassning.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withKonfidentialitet(2)
			.withRiktighet(2)
			.withTillganglighet(3);
		final var created = Informationsklassning.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withKonfidentialitet(2)
			.withRiktighet(2)
			.withTillganglighet(3);
		created.withId("klassning-1");

		when(serviceMock.createOrUpdate(eq(HANDLINGSTYP_ID), any(Informationsklassning.class))).thenReturn(created);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(dto)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Informationsklassning.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("klassning-1");
		assertThat(response.getKonfidentialitet()).isEqualTo(2);
		verify(serviceMock).createOrUpdate(eq(HANDLINGSTYP_ID), any(Informationsklassning.class));
	}

	@Test
	void getByHandlingstypId() {
		final var klassning = Informationsklassning.create()
			.withHandlingstypId(HANDLINGSTYP_ID)
			.withKonfidentialitet(2)
			.withRiktighet(2);
		klassning.withId("klassning-1");

		when(serviceMock.getByHandlingstypId(HANDLINGSTYP_ID)).thenReturn(klassning);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Informationsklassning.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("klassning-1");
		assertThat(response.getHandlingstypId()).isEqualTo(HANDLINGSTYP_ID);
		verify(serviceMock).getByHandlingstypId(HANDLINGSTYP_ID);
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "handlingstypId", HANDLINGSTYP_ID)))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).delete(HANDLINGSTYP_ID);
	}
}
