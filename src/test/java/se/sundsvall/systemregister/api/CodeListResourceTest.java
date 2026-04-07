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
import se.sundsvall.systemregister.api.model.CodeList;
import se.sundsvall.systemregister.service.CodeListService;

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
class CodeListResourceTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private CodeListService serviceMock;

	private static final String MUNICIPALITY_ID = "2281";
	private static final String TABLE_NAME = "format";
	private static final String PATH = "/{municipalityId}/kodtabeller/{tableName}";

	@Test
	void getById() {
		final var codeList = CodeList.create()
			.withId(1)
			.withKod("ELEKTRONISK")
			.withNamn("Elektronisk handling")
			.withOrdning(1);

		when(serviceMock.getByTableNameAndId(TABLE_NAME, 1)).thenReturn(codeList);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "tableName", TABLE_NAME, "id", "1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(CodeList.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getKod()).isEqualTo("ELEKTRONISK");
		verify(serviceMock).getByTableNameAndId(TABLE_NAME, 1);
	}

	@Test
	void getAll() {
		final var item1 = CodeList.create().withId(1).withKod("ELEKTRONISK").withNamn("Elektronisk").withOrdning(1);
		final var item2 = CodeList.create().withId(2).withKod("PAPPER").withNamn("Papper").withOrdning(2);

		when(serviceMock.getAllByTableName(TABLE_NAME)).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "tableName", TABLE_NAME)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(CodeList.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAllByTableName(TABLE_NAME);
	}

	@Test
	void create() {
		final var model = CodeList.create()
			.withKod("NY_KOD")
			.withNamn("Ny kod")
			.withOrdning(3);
		final var created = CodeList.create()
			.withId(10)
			.withKod("NY_KOD")
			.withNamn("Ny kod")
			.withOrdning(3);

		when(serviceMock.createByTableName(eq(TABLE_NAME), any(CodeList.class))).thenReturn(created);

		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID, "tableName", TABLE_NAME)))
			.contentType(APPLICATION_JSON)
			.bodyValue(model)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().location("/" + MUNICIPALITY_ID + "/kodtabeller/" + TABLE_NAME + "/10");

		verify(serviceMock).createByTableName(eq(TABLE_NAME), any(CodeList.class));
	}

	@Test
	void update() {
		final var model = CodeList.create()
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(1);
		final var updated = CodeList.create()
			.withId(1)
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(1);

		when(serviceMock.updateByTableName(eq(TABLE_NAME), eq(1), any(CodeList.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "tableName", TABLE_NAME, "id", "1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(model)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(CodeList.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getKod()).isEqualTo("UPD_KOD");
		verify(serviceMock).updateByTableName(eq(TABLE_NAME), eq(1), any(CodeList.class));
	}

	@Test
	void delete() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "tableName", TABLE_NAME, "id", "1")))
			.exchange()
			.expectStatus().isNoContent()
			.expectBody().isEmpty();

		verify(serviceMock).deleteByTableName(TABLE_NAME, 1);
	}
}
