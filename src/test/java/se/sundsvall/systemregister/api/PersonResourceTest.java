package se.sundsvall.systemregister.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.systemregister.Application;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.service.PersonService;

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
class PersonResourceTest {

	private static final String MUNICIPALITY_ID = "2281";
	private static final String PATH = "/{municipalityId}/persons";

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private PersonService serviceMock;

	@Test
	void createPerson() {
		final var body = Person.create()
			.withFirstName("John")
			.withLastName("Doe");
		final var created = Person.create()
			.withFirstName("John")
			.withLastName("Doe");
		created.withId("person-1");

		when(serviceMock.create(any(Person.class))).thenReturn(created);

		webTestClient.post()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().exists("Location");

		verify(serviceMock).create(any(Person.class));
	}

	@Test
	void getPersonById() {
		final var person = Person.create()
			.withFirstName("John")
			.withLastName("Doe");
		person.withId("person-1");

		when(serviceMock.getById("person-1")).thenReturn(person);

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "person-1")))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Person.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("person-1");
		assertThat(response.getFirstName()).isEqualTo("John");
		verify(serviceMock).getById("person-1");
	}

	@Test
	void getAll() {
		final var person1 = Person.create().withFirstName("John");
		person1.withId("person-1");
		final var person2 = Person.create().withFirstName("Jane");
		person2.withId("person-2");

		when(serviceMock.getAll()).thenReturn(List.of(person1, person2));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH).build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBodyList(Person.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).hasSize(2);
		verify(serviceMock).getAll();
	}

	@Test
	void findByEmail() {
		final var person = Person.create()
			.withFirstName("John")
			.withEmail("john@example.com");
		person.withId("person-1");

		when(serviceMock.findByEmail("john@example.com")).thenReturn(Optional.of(person));

		final var response = webTestClient.get()
			.uri(builder -> builder.path(PATH + "/by-email")
				.queryParam("email", "john@example.com")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Person.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getEmail()).isEqualTo("john@example.com");
		verify(serviceMock).findByEmail("john@example.com");
	}

	@Test
	void findByEmailNotFound() {
		when(serviceMock.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

		webTestClient.get()
			.uri(builder -> builder.path(PATH + "/by-email")
				.queryParam("email", "unknown@example.com")
				.build(Map.of("municipalityId", MUNICIPALITY_ID)))
			.exchange()
			.expectStatus().isNotFound();

		verify(serviceMock).findByEmail("unknown@example.com");
	}

	@Test
	void updatePerson() {
		final var body = Person.create().withFirstName("Updated").withLastName("Doe");
		final var updated = Person.create().withFirstName("Updated").withLastName("Doe");
		updated.withId("person-1");

		when(serviceMock.update(eq("person-1"), any(Person.class))).thenReturn(updated);

		final var response = webTestClient.put()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "person-1")))
			.contentType(APPLICATION_JSON)
			.bodyValue(body)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON)
			.expectBody(Person.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo("person-1");
		assertThat(response.getFirstName()).isEqualTo("Updated");
		verify(serviceMock).update(eq("person-1"), any(Person.class));
	}

	@Test
	void deletePerson() {
		webTestClient.delete()
			.uri(builder -> builder.path(PATH + "/{id}").build(Map.of("municipalityId", MUNICIPALITY_ID, "id", "person-1")))
			.exchange()
			.expectStatus().isNoContent();

		verify(serviceMock).delete("person-1");
	}
}
