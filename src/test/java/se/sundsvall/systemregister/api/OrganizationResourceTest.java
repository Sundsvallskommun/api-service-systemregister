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
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.service.OrganizationService;

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
class OrganizationResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/organizations";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private OrganizationService serviceMock;

	@Test
	void createOrganization() {
		final var body = Organization.create()
			.withName("IT Department");
		final var created = Organization.create()
			.withName("IT Department");
		created.withId("org-1");

		when(serviceMock.create(any(Organization.class))).thenReturn(created);

		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().exists("Location");

		verify(serviceMock).create(any(Organization.class));
	}

	@Test
	void getOrganizationById() {
		final var organization = Organization.create()
			.withName("IT Department");
		organization.withId("org-1");

		when(serviceMock.getById("org-1")).thenReturn(organization);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "org-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Organization.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("org-1");
		assertThat(response.getName()).isEqualTo("IT Department");
		verify(serviceMock).getById("org-1");
	}

	@Test
	void getAll() {
		final var org1 = Organization.create().withName("IT");
		org1.withId("org-1");
		final var org2 = Organization.create().withName("HR");
		org2.withId("org-2");

		when(serviceMock.getAll()).thenReturn(List.of(org1, org2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Organization.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void getAllFilteredByLevel() {
		final var org = Organization.create()
			.withName("IT")
			.withLevel(1);
		org.withId("org-1");

		when(serviceMock.getAllByLevel(1)).thenReturn(List.of(org));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("level", 1)
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Organization.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		verify(serviceMock).getAllByLevel(1);
	}

	@Test
	void getChildren() {
		final var child = Organization.create()
			.withName("Sub-IT")
			.withParentId("org-1");
		child.withId("org-2");

		when(serviceMock.getChildren("org-1")).thenReturn(List.of(child));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}/children").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "org-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Organization.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(1);
		assertThat(response.getFirst().getParentId()).isEqualTo("org-1");
		verify(serviceMock).getChildren("org-1");
	}

	@Test
	void updateOrganization() {
		final var body = Organization.create().withName("Updated IT");
		final var updated = Organization.create().withName("Updated IT");
		updated.withId("org-1");

		when(serviceMock.update(eq("org-1"), any(Organization.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "org-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Organization.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("org-1");
		assertThat(response.getName()).isEqualTo("Updated IT");
		verify(serviceMock).update(eq("org-1"), any(Organization.class));
	}

	@Test
	void deleteOrganization() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "org-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("org-1");
	}
}
