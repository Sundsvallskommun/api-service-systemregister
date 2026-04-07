package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.dept44.problem.Problem;
import se.sundsvall.dept44.problem.violations.ConstraintViolationProblem;
import se.sundsvall.systemregister.api.model.Person;
import se.sundsvall.systemregister.service.PersonService;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@Validated
@RequestMapping("/{municipalityId}/persons")
@Tag(name = "Persons", description = "Person management operations")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class PersonResource {

	private final PersonService personService;

	PersonResource(final PersonService personService) {
		this.personService = personService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(description = "Create a new person", responses = {
		@ApiResponse(responseCode = "201", description = "Created - Successful operation", headers = @Header(name = LOCATION, schema = @Schema(type = "string")), useReturnTypeSchema = true)
	})
	ResponseEntity<Void> createPerson(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@RequestBody @Valid final Person person) {

		final Person result = personService.create(person);
		return created(
			fromPath("/{municipalityId}/persons/{id}")
				.buildAndExpand(municipalityId, result.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get person by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Person> getPersonById(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Person ID", example = "person-1") @PathVariable final String id) {

		return ok(personService.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get all persons", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<List<Person>> getAll(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId) {

		return ok(personService.getAll());
	}

	@GetMapping(path = "/by-email", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Find person by email", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true),
		@ApiResponse(responseCode = "404", description = "Not found")
	})
	ResponseEntity<Person> findByEmail(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "email", description = "Email address", example = "john.doe@example.com") @RequestParam final String email) {

		return personService.findByEmail(email)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Update person", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Person> updatePerson(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Person ID", example = "person-1") @PathVariable final String id,
		@RequestBody @Valid final Person person) {

		return ok(personService.update(id, person));
	}

	@DeleteMapping(path = "/{id}")
	@Operation(description = "Delete person", responses = {
		@ApiResponse(responseCode = "204", description = "No content - Successful operation")
	})
	ResponseEntity<Void> deletePerson(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Person ID", example = "person-1") @PathVariable final String id) {

		personService.delete(id);
		return noContent().build();
	}
}
