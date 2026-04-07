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
import se.sundsvall.systemregister.api.model.Supplier;
import se.sundsvall.systemregister.service.SupplierService;

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
@RequestMapping("/{municipalityId}/suppliers")
@Tag(name = "Suppliers", description = "Supplier management operations")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = {
	Problem.class, ConstraintViolationProblem.class
})))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
class SupplierResource {

	private final SupplierService supplierService;

	SupplierResource(final SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(description = "Create a new supplier", responses = {
		@ApiResponse(responseCode = "201", description = "Created - Successful operation", headers = @Header(name = LOCATION, schema = @Schema(type = "string")), useReturnTypeSchema = true)
	})
	ResponseEntity<Void> createSupplier(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@RequestBody @Valid final Supplier supplier) {

		final Supplier result = supplierService.create(supplier);
		return created(
			fromPath("/{municipalityId}/suppliers/{id}")
				.buildAndExpand(municipalityId, result.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get supplier by ID", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Supplier> getSupplierById(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Supplier ID", example = "supplier-1") @PathVariable final String id) {

		return ok(supplierService.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Get all suppliers", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<List<Supplier>> getAll(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "activeOnly", description = "Return only active suppliers", example = "true") @RequestParam(required = false) final Boolean activeOnly) {

		final List<Supplier> result = activeOnly != null && activeOnly ? supplierService.getAllActive() : supplierService.getAll();
		return ok(result);
	}

	@PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(description = "Update supplier", responses = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation", useReturnTypeSchema = true)
	})
	ResponseEntity<Supplier> updateSupplier(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Supplier ID", example = "supplier-1") @PathVariable final String id,
		@RequestBody @Valid final Supplier supplier) {

		return ok(supplierService.update(id, supplier));
	}

	@DeleteMapping(path = "/{id}")
	@Operation(description = "Delete supplier", responses = {
		@ApiResponse(responseCode = "204", description = "No content - Successful operation")
	})
	ResponseEntity<Void> deleteSupplier(
		@Parameter(name = "municipalityId", description = "Municipality ID", example = "2281") @PathVariable @ValidMunicipalityId final String municipalityId,
		@Parameter(name = "id", description = "Supplier ID", example = "supplier-1") @PathVariable final String id) {

		supplierService.delete(id);
		return noContent().build();
	}
}
