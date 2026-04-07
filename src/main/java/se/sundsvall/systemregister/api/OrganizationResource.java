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
import se.sundsvall.systemregister.api.model.Organization;
import se.sundsvall.systemregister.service.OrganizationService;

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
@RequestMapping("/{municipalityId}/organizations")
@Tag(name = "Organizations", description = "Organization management operations")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class OrganizationResource {

	private final OrganizationService organizationService;

	OrganizationResource(final OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(description = "Create a new organization", responses = {
		@ApiResponse(responseCode = "201", description = "Created - Successful operation", headers = @Header(name = LOCATION, schema = @Schema(type = "string")), useReturnTypeSchema = true)
	})
	ResponseEntity<Void> createOrganization(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@RequestBody @Valid final Organization organization) {

		final Organization result = organizationService.create(organization);
		return created(
			fromPath("/{municipalityId}/organizations/{id}")
				.buildAndExpand(municipalityId, result.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get organization by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Organization> getOrganizationById(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Organization ID", example = "org-1") @PathVariable final String id) {

		return ok(organizationService.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get all organizations", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<List<Organization>> getAll(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "level", description = "Filter by level", example = "1") @RequestParam(required = false) final Integer level) {

		final List<Organization> result = level != null ? organizationService.getAllByLevel(level) : organizationService.getAll();
		return ok(result);
	}

	@GetMapping(path = "/{id}/children", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get children organizations", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<List<Organization>> getChildren(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Parent organization ID", example = "org-1") @PathVariable final String id) {

		return ok(organizationService.getChildren(id));
	}

	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Update organization", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Organization> updateOrganization(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Organization ID", example = "org-1") @PathVariable final String id,
		@RequestBody @Valid final Organization organization) {

		return ok(organizationService.update(id, organization));
	}

	@DeleteMapping(path = "/{id}")
	@Operation(description = "Delete organization", responses = {
		@ApiResponse(responseCode = "204", description = "No content - Successful operation")
	})
	ResponseEntity<Void> deleteOrganization(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Organization ID", example = "org-1") @PathVariable final String id) {

		organizationService.delete(id);
		return noContent().build();
	}
}
