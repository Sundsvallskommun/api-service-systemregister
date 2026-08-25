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
import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.systemregister.Application;
import se.sundsvall.systemregister.api.model.system.PagedSystemsResponse;
import se.sundsvall.systemregister.api.model.system.System;
import se.sundsvall.systemregister.api.model.system.SystemSearchParameters;
import se.sundsvall.systemregister.service.SystemService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class SystemResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/systems";
	private static final String PATH_BY_ID = "/{municipalityId}/systems/{id}";

	@MockitoBean
	private SystemService serviceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createSystem() {
		final var system = System.create()
			.withSystemId("SYS-001")
			.withName("Test System")
			.withStatus("PRODUCTION");
		final var created = System.create()
			.withId("id-1")
			.withSystemId("SYS-001")
			.withName("Test System")
			.withStatus("PRODUCTION");

		when(serviceMock.create(any(System.class))).thenReturn(created);

		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(system)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/systems/id-1");

		verify(serviceMock).create(any(System.class));
	}

	@Test
	void getSystemById() {
		final var system = System.create()
			.withId("id-1")
			.withSystemId("SYS-001")
			.withName("Test System");

		when(serviceMock.getById("id-1")).thenReturn(system);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.exchange()
			.expectStatus().isOk()
			.expectBody(System.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("id-1");
		verify(serviceMock).getById("id-1");
	}

	@Test
	void getAllSystemsWithDefaults() {
		final var pagedResponse = PagedSystemsResponse.create()
			.withSystems(List.of(
				System.create().withId("id-1").withSystemId("SYS-001"),
				System.create().withId("id-2").withSystemId("SYS-002")))
			.withMetadata(PagingAndSortingMetaData.create().withPage(1).withLimit(20).withCount(2).withTotalRecords(2).withTotalPages(1));

		final var searchParameters = new SystemSearchParameters();
		when(serviceMock.search(eq(searchParameters))).thenReturn(pagedResponse);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBody(PagedSystemsResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getSystems()).hasSize(2);
		assertThat(response.getMetadata().getPage()).isEqualTo(1);
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(2);
		verify(serviceMock).search(eq(searchParameters));
	}

	@Test
	void getAllSystemsWithStatusFilter() {
		final var pagedResponse = PagedSystemsResponse.create()
			.withSystems(List.of(System.create().withId("id-1").withSystemId("SYS-001").withStatus("PRODUCTION")))
			.withMetadata(PagingAndSortingMetaData.create().withPage(1).withLimit(20).withCount(1).withTotalRecords(1).withTotalPages(1));

		final var searchParameters = new SystemSearchParameters();
		searchParameters.setStatus("PRODUCTION");
		when(serviceMock.search(eq(searchParameters))).thenReturn(pagedResponse);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "PRODUCTION")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBody(PagedSystemsResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getSystems()).hasSize(1);
		verify(serviceMock).search(eq(searchParameters));
	}

	@Test
	void getAllSystemsWithInvalidStatus() {
		webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "NOT_A_REAL_STATUS")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getAllSystemsWithEmptyStatus() {
		webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("status", "")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isBadRequest();

		verifyNoInteractions(serviceMock);
	}

	@Test
	void getAllSystemsWithSearchAndPagination() {
		final var pagedResponse = PagedSystemsResponse.create()
			.withSystems(List.of(System.create().withId("id-1").withSystemId("SYS-001").withName("HR System")))
			.withMetadata(PagingAndSortingMetaData.create().withPage(2).withLimit(10).withCount(1).withTotalRecords(11).withTotalPages(2));

		final var searchParameters = new SystemSearchParameters();
		searchParameters.setSearch("HR");
		searchParameters.setPage(2);
		searchParameters.setLimit(10);
		when(serviceMock.search(eq(searchParameters))).thenReturn(pagedResponse);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH)
				.queryParam("search", "HR")
				.queryParam("page", "2")
				.queryParam("limit", "10")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectBody(PagedSystemsResponse.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getSystems()).hasSize(1);
		assertThat(response.getMetadata().getPage()).isEqualTo(2);
		assertThat(response.getMetadata().getTotalRecords()).isEqualTo(11);
		verify(serviceMock).search(eq(searchParameters));
	}

	@Test
	void updateSystem() {
		final var updateModel = System.create()
			.withSystemId("SYS-001")
			.withName("Updated System")
			.withDescription("Updated description");
		final var updated = System.create()
			.withId("id-1")
			.withSystemId("SYS-001")
			.withName("Updated System")
			.withDescription("Updated description");

		when(serviceMock.update(eq("id-1"), any(System.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(updateModel)
			.exchange()
			.expectStatus().isOk()
			.expectBody(System.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getName()).isEqualTo("Updated System");
		verify(serviceMock).update(eq("id-1"), any(System.class));
	}

	@Test
	void deleteSystem() {
		doNothing().when(serviceMock).delete("id-1");

		webTestClient.delete()
			.uri(builder -> builder.path(PATH_BY_ID).build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "id-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("id-1");
	}
}
