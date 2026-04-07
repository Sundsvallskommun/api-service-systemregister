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
import se.sundsvall.systemregister.api.model.KlassaVerksamhetstyp;
import se.sundsvall.systemregister.service.KlassaVerksamhetstypService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("junit")
class KlassaVerksamhetstypResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/klassa/verksamhetstyper";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private KlassaVerksamhetstypService serviceMock;

	@Test
	void getById() {
		final var entity = KlassaVerksamhetstyp.create()
			.withNamn("Allman verksamhet")
			.withKod(100);
		entity.withId(1);

		when(serviceMock.getById(1)).thenReturn(entity);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(KlassaVerksamhetstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
		assertThat(response.getNamn()).isEqualTo("Allman verksamhet");
		assertThat(response.getKod()).isEqualTo(100);
		verify(serviceMock).getById(1);
	}

	@Test
	void getAll() {
		final var item1 = KlassaVerksamhetstyp.create().withNamn("Type1");
		item1.withId(1);
		final var item2 = KlassaVerksamhetstyp.create().withNamn("Type2");
		item2.withId(2);

		when(serviceMock.getAll()).thenReturn(List.of(item1, item2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(KlassaVerksamhetstyp.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}
}
