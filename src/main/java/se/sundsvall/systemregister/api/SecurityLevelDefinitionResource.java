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
import se.sundsvall.systemregister.api.model.SecurityLevelDefinition;
import se.sundsvall.systemregister.service.SecurityLevelDefinitionService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Validated
@Tag(name = "Security Level Definitions", description = "Security level definitions for K, R, T dimensions")
@RequestMapping("/{municipalityId}/security-level-definitions")
class SecurityLevelDefinitionResource {

	private final SecurityLevelDefinitionService service;

	SecurityLevelDefinitionResource(final SecurityLevelDefinitionService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new security level definition")
	@ApiResponse(responseCode = "201", description = "Security level definition created", content = @Content(schema = @Schema(implementation = SecurityLevelDefinition.class)))
	ResponseEntity<SecurityLevelDefinition> createSecurityLevelDefinition(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestBody final SecurityLevelDefinition dto) {
		final var created = this.service.create(dto);
		return ResponseEntity.status(CREATED).body(created);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a security level definition by ID")
	@ApiResponse(responseCode = "200", description = "Security level definition found")
	@ApiResponse(responseCode = "404", description = "Security level definition not found")
	ResponseEntity<SecurityLevelDefinition> getSecurityLevelDefinitionById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		final var dto = this.service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Get security level definitions, optionally filtered by dimension and/or level")
	@ApiResponse(responseCode = "200", description = "List of security level definitions")
	ResponseEntity<List<SecurityLevelDefinition>> getSecurityLevelDefinitions(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String dimension,
		@RequestParam(required = false) final Integer level) {
		List<SecurityLevelDefinition> definitions;
		if (dimension != null && level != null) {
			definitions = this.service.getByDimensionAndLevel(dimension, level);
		} else if (dimension != null) {
			definitions = this.service.getByDimension(dimension);
		} else {
			definitions = this.service.getAll();
		}
		return ResponseEntity.ok(definitions);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a security level definition")
	@ApiResponse(responseCode = "200", description = "Security level definition updated")
	@ApiResponse(responseCode = "404", description = "Security level definition not found")
	ResponseEntity<SecurityLevelDefinition> updateSecurityLevelDefinition(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@RequestBody final SecurityLevelDefinition dto) {
		final var updated = this.service.update(id, dto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a security level definition")
	@ApiResponse(responseCode = "204", description = "Security level definition deleted")
	@ApiResponse(responseCode = "404", description = "Security level definition not found")
	ResponseEntity<Void> deleteSecurityLevelDefinition(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
