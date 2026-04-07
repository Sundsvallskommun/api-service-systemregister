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
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.service.PersonuppgiftsbehandlingService;

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
class PersonuppgiftsbehandlingResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/personuppgiftsbehandlingar";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private PersonuppgiftsbehandlingService serviceMock;

	@Test
	void createPersonuppgiftsbehandling() {
		final var body = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Customer Processing");

		when(serviceMock.create(any(Personuppgiftsbehandling.class))).thenReturn(body);

		final var response = webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Personuppgiftsbehandling.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(response.getName()).isEqualTo("Customer Processing");
		verify(serviceMock).create(any(Personuppgiftsbehandling.class));
	}

	@Test
	void getPersonuppgiftsbehandlingById() {
		final var dto = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Test");

		when(serviceMock.getById("id-001")).thenReturn(dto);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Personuppgiftsbehandling.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(response.getName()).isEqualTo("Test");
		verify(serviceMock).getById("id-001");
	}

	@Test
	void getAllPersonuppgiftsbehandlingar() {
		final var dto1 = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001");
		final var dto2 = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-002");

		when(serviceMock.getAll()).thenReturn(List.of(dto1, dto2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Personuppgiftsbehandling.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getPersonuppgiftsbehandlingarByStatus() {
		final var dto = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withStatus("ACTIVE");

		when(serviceMock.getAllByStatus("ACTIVE")).thenReturn(List.of(dto));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "ACTIVE")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Personuppgiftsbehandling.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getAllByStatus("ACTIVE");
	}

	@Test
	void updatePersonuppgiftsbehandling() {
		final var body = Personuppgiftsbehandling.create()
			.withBehandlingId("PPB-001")
			.withName("Updated Name");

		when(serviceMock.update(eq("id-001"), any(Personuppgiftsbehandling.class))).thenReturn(body);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Personuppgiftsbehandling.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getBehandlingId()).isEqualTo("PPB-001");
		assertThat(response.getName()).isEqualTo("Updated Name");
		verify(serviceMock).update(eq("id-001"), any(Personuppgiftsbehandling.class));
	}

	@Test
	void deletePersonuppgiftsbehandling() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-001")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("id-001");
	}
}
