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
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.service.ServiceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class ServiceResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/services";
	private static final String PATH_BY_ID = "/{municipalityId}/services/{id}";

	@MockitoBean
	private ServiceService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createService() {
		// Arrange
		final var body = ServiceModel.create()
			.withServiceId("SVC-001")
			.withName("Email Service");
		final var created = ServiceModel.create()
			.withServiceId("SVC-001")
			.withName("Email Service");
		created.withId("service-1");

		when(serviceMock.create(any(ServiceModel.class))).thenReturn(created);

		// Act & Assert
		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/services/service-1");

		verify(serviceMock).create(any(ServiceModel.class));
	}

	@Test
	void getServiceById() {
		// Arrange
		final var serviceModel = ServiceModel.create()
			.withServiceId("SVC-001")
			.withName("Email Service");
		serviceModel.withId("service-1");

		when(serviceMock.getById("service-1")).thenReturn(serviceModel);

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "service-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(ServiceModel.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("service-1");
		assertThat(response.getName()).isEqualTo("Email Service");
		verify(serviceMock).getById("service-1");
	}

	@Test
	void getAll() {
		// Arrange
		final var s1 = ServiceModel.create().withName("Service1");
		s1.withId("service-1");
		final var s2 = ServiceModel.create().withName("Service2");
		s2.withId("service-2");

		when(serviceMock.getAll()).thenReturn(List.of(s1, s2));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(ServiceModel.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByServiceType() {
		// Arrange
		final var s = ServiceModel.create()
			.withName("REST Service")
			.withServiceType("REST");
		s.withId("service-1");

		when(serviceMock.getAllByServiceType("REST")).thenReturn(List.of(s));

		// Act
		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("serviceType", "REST")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(ServiceModel.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).hasSize(1);
		verify(serviceMock).getAllByServiceType("REST");
	}

	@Test
	void updateService() {
		// Arrange
		final var body = ServiceModel.create().withServiceId("SVC-001").withName("Updated");
		final var updated = ServiceModel.create().withServiceId("SVC-001").withName("Updated");
		updated.withId("service-1");

		when(serviceMock.update(eq("service-1"), any(ServiceModel.class))).thenReturn(updated);

		// Act
		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "service-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectBody(ServiceModel.class)
			.returnResult()
			.getResponseBody();

		// Assert
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("service-1");
		assertThat(response.getName()).isEqualTo("Updated");
		verify(serviceMock).update(eq("service-1"), any(ServiceModel.class));
	}

	@Test
	void deleteService() {
		// Arrange
		doNothing().when(serviceMock).delete("service-1");

		// Act & Assert
		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "service-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("service-1");
	}
}
