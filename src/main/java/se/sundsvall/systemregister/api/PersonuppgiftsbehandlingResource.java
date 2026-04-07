package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import se.sundsvall.systemregister.api.model.Personuppgiftsbehandling;
import se.sundsvall.systemregister.service.PersonuppgiftsbehandlingService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Validated
@Tag(name = "Personuppgiftsbehandling", description = "GDPR Processing Activities")
@RequestMapping("/{municipalityId}/personuppgiftsbehandlingar")
class PersonuppgiftsbehandlingResource {

	private final PersonuppgiftsbehandlingService service;

	PersonuppgiftsbehandlingResource(final PersonuppgiftsbehandlingService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new processing activity")
	@ApiResponse(responseCode = "201", description = "Processing activity created", content = @Content(schema = @Schema(implementation = Personuppgiftsbehandling.class)))
	ResponseEntity<Personuppgiftsbehandling> createPersonuppgiftsbehandling(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestBody final Personuppgiftsbehandling dto) {
		final var created = this.service.create(dto);
		return ResponseEntity.status(CREATED).body(created);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get processing activity by ID")
	@ApiResponse(responseCode = "200", description = "Processing activity found")
	@ApiResponse(responseCode = "404", description = "Processing activity not found")
	ResponseEntity<Personuppgiftsbehandling> getPersonuppgiftsbehandlingById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		final var dto = this.service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Get all processing activities (optionally filtered by status)")
	@ApiResponse(responseCode = "200", description = "List of processing activities")
	ResponseEntity<List<Personuppgiftsbehandling>> getPersonuppgiftsbehandlingar(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String status) {
		final List<Personuppgiftsbehandling> result;
		if (status != null) {
			result = this.service.getAllByStatus(status);
		} else {
			result = this.service.getAll();
		}
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing processing activity")
	@ApiResponse(responseCode = "200", description = "Processing activity updated")
	@ApiResponse(responseCode = "404", description = "Processing activity not found")
	ResponseEntity<Personuppgiftsbehandling> updatePersonuppgiftsbehandling(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@RequestBody final Personuppgiftsbehandling dto) {
		final var updated = this.service.update(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a processing activity")
	@ApiResponse(responseCode = "204", description = "Processing activity deleted")
	@ApiResponse(responseCode = "404", description = "Processing activity not found")
	ResponseEntity<Void> deletePersonuppgiftsbehandling(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
