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
import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.service.PersonuppgiftsbehandlingSystemLinkService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class PersonuppgiftsbehandlingSystemLinkResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String BEHANDLING_ID = "PPB-001";
	private static final String PATH = "/{municipalityId}/personuppgiftsbehandlingar/{behandlingId}/system-links";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private PersonuppgiftsbehandlingSystemLinkService serviceMock;

	@Test
	void createSystemLink() {
		final var body = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId(BEHANDLING_ID)
			.withSystemId("system-001")
			.withDescription("Customer database");

		when(serviceMock.create(any(PersonuppgiftsbehandlingSystemLink.class))).thenReturn(body);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "behandlingId", BEHANDLING_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(PersonuppgiftsbehandlingSystemLink.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getBehandlingId()).isEqualTo(BEHANDLING_ID);
		assertThat(response.getSystemId()).isEqualTo("system-001");
		assertThat(response.getDescription()).isEqualTo("Customer database");
		verify(serviceMock).create(any(PersonuppgiftsbehandlingSystemLink.class));
	}

	@Test
	void getSystemLinkById() {
		final var dto = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId(BEHANDLING_ID)
			.withSystemId("system-001");

		when(serviceMock.getById("id-001")).thenReturn(dto);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "behandlingId", BEHANDLING_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(PersonuppgiftsbehandlingSystemLink.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getBehandlingId()).isEqualTo(BEHANDLING_ID);
		assertThat(response.getSystemId()).isEqualTo("system-001");
		verify(serviceMock).getById("id-001");
	}

	@Test
	void getSystemLinksForBehandling() {
		final var dto1 = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId(BEHANDLING_ID)
			.withSystemId("system-001");
		final var dto2 = PersonuppgiftsbehandlingSystemLink.create()
			.withBehandlingId(BEHANDLING_ID)
			.withSystemId("system-002");

		when(serviceMock.getByBehandlingId(BEHANDLING_ID)).thenReturn(List.of(dto1, dto2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "behandlingId", BEHANDLING_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(PersonuppgiftsbehandlingSystemLink.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getByBehandlingId(BEHANDLING_ID);
	}

	@Test
	void deleteSystemLink() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "behandlingId", BEHANDLING_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("id-001");
	}
}
