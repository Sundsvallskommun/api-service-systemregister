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
import se.sundsvall.systemregister.api.model.ServiceModel;
import se.sundsvall.systemregister.service.ServiceService;

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
@RequestMapping("/{municipalityId}/services")
@Tag(name = "Services", description = "Service management operations")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class ServiceResource {

	private final ServiceService serviceService;

	ServiceResource(final ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(description = "Create a new service", responses = {
		@ApiResponse(responseCode = "201", description = "Created - Successful operation", headers = @Header(name = LOCATION, schema = @Schema(type = "string")), useReturnTypeSchema = true)
	})
	ResponseEntity<Void> createService(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@RequestBody @Valid final ServiceModel service) {

		final ServiceModel result = serviceService.create(service);
		return created(
			fromPath("/{municipalityId}/services/{id}")
				.buildAndExpand(municipalityId, result.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get service by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<ServiceModel> getServiceById(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Service ID", example = "service-1") @PathVariable final String id) {

		return ok(serviceService.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get all services", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<List<ServiceModel>> getAll(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "serviceType", description = "Filter by service type", example = "REST") @RequestParam(required = false) final String serviceType) {

		final List<ServiceModel> result = serviceType != null ? serviceService.getAllByServiceType(serviceType) : serviceService.getAll();
		return ok(result);
	}

	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Update service", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<ServiceModel> updateService(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Service ID", example = "service-1") @PathVariable final String id,
		@RequestBody @Valid final ServiceModel service) {

		return ok(serviceService.update(id, service));
	}

	@DeleteMapping(path = "/{id}")
	@Operation(description = "Delete service", responses = {
		@ApiResponse(responseCode = "204", description = "No content - Successful operation")
	})
	ResponseEntity<Void> deleteService(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Service ID", example = "service-1") @PathVariable final String id) {

		serviceService.delete(id);
		return noContent().build();
	}
}
