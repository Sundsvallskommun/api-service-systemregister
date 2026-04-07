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
import se.sundsvall.systemregister.api.model.AiApplication;
import se.sundsvall.systemregister.service.AiApplicationService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Validated
@Tag(name = "AI Applications", description = "AI Act Applications")
@RequestMapping("/{municipalityId}/ai-applications")
class AiApplicationResource {

	private final AiApplicationService service;

	AiApplicationResource(final AiApplicationService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new AI application")
	@ApiResponse(responseCode = "201", description = "AI application created", content = @Content(schema = @Schema(implementation = AiApplication.class)))
	ResponseEntity<AiApplication> createAiApplication(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestBody final AiApplication dto) {
		final var created = this.service.create(dto);
		return ResponseEntity.status(CREATED).body(created);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get AI application by ID")
	@ApiResponse(responseCode = "200", description = "AI application found")
	@ApiResponse(responseCode = "404", description = "AI application not found")
	ResponseEntity<AiApplication> getAiApplicationById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		final var dto = this.service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Get all AI applications (optionally filtered by status or risk category)")
	@ApiResponse(responseCode = "200", description = "List of AI applications")
	ResponseEntity<List<AiApplication>> getAiApplications(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String status,
		@RequestParam(required = false) final String riskCategory) {
		final List<AiApplication> result;
		if (status != null) {
			result = this.service.getAllByStatus(status);
		} else if (riskCategory != null) {
			result = this.service.getAllByRiskCategory(riskCategory);
		} else {
			result = this.service.getAll();
		}
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing AI application")
	@ApiResponse(responseCode = "200", description = "AI application updated")
	@ApiResponse(responseCode = "404", description = "AI application not found")
	ResponseEntity<AiApplication> updateAiApplication(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@RequestBody final AiApplication dto) {
		final var updated = this.service.update(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete an AI application")
	@ApiResponse(responseCode = "204", description = "AI application deleted")
	@ApiResponse(responseCode = "404", description = "AI application not found")
	ResponseEntity<Void> deleteAiApplication(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
