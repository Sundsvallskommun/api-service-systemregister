package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import se.sundsvall.systemregister.api.model.PagedSystemsResponse;
import se.sundsvall.systemregister.api.model.System;
import se.sundsvall.systemregister.service.SystemService;

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
@RequestMapping("/{municipalityId}/systems")
@Tag(name = "Systems", description = "System management operations")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class SystemResource {

	private final SystemService systemService;

	SystemResource(final SystemService systemService) {
		this.systemService = systemService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(description = "Create a new system", responses = {
		@ApiResponse(responseCode = "201", description = "Created - Successful operation", headers = @Header(name = LOCATION, schema = @Schema(type = "string")), useReturnTypeSchema = true)
	})
	ResponseEntity<Void> createSystem(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@RequestBody @Valid final System system) {

		final System result = systemService.create(system);
		return created(
			fromPath("/{municipalityId}/systems/{id}")
				.buildAndExpand(municipalityId, result.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get system by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<System> getSystemById(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "System ID", example = "system-1") @PathVariable final String id) {

		return ok(systemService.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get all systems with optional filtering and pagination", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<PagedSystemsResponse> getAll(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "status", description = "Filter by status", example = "PRODUCTION") @RequestParam(required = false) final String status,
		@Parameter(name = "search", description = "Search by name or system ID", example = "HR") @RequestParam(required = false) final String search,
		@Parameter(name = "page", description = "Page number (1-based)", example = "1") @RequestParam(defaultValue = "1") final int page,
		@Parameter(name = "limit", description = "Number of items per page", example = "20") @RequestParam(defaultValue = "20") final int limit) {

		return ok(systemService.search(status, search, page, limit));
	}

	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Update system", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<System> updateSystem(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "System ID", example = "system-1") @PathVariable final String id,
		@RequestBody @Valid final System system) {

		return ok(systemService.update(id, system));
	}

	@DeleteMapping(path = "/{id}")
	@Operation(description = "Delete system", responses = {
		@ApiResponse(responseCode = "204", description = "No content - Successful operation")
	})
	ResponseEntity<Void> deleteSystem(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "System ID", example = "system-1") @PathVariable final String id) {

		systemService.delete(id);
		return noContent().build();
	}
}
