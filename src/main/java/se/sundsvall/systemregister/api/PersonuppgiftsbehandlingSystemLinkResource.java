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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.PersonuppgiftsbehandlingSystemLink;
import se.sundsvall.systemregister.service.PersonuppgiftsbehandlingSystemLinkService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Validated
@Tag(name = "Personuppgiftsbehandling System Links", description = "Links between processing activities and systems")
@RequestMapping("/{municipalityId}/personuppgiftsbehandlingar/{behandlingId}/system-links")
class PersonuppgiftsbehandlingSystemLinkResource {

	private final PersonuppgiftsbehandlingSystemLinkService service;

	PersonuppgiftsbehandlingSystemLinkResource(final PersonuppgiftsbehandlingSystemLinkService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new system link for a processing activity")
	@ApiResponse(responseCode = "201", description = "System link created", content = @Content(schema = @Schema(implementation = PersonuppgiftsbehandlingSystemLink.class)))
	ResponseEntity<PersonuppgiftsbehandlingSystemLink> createSystemLink(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String behandlingId,
		@RequestBody final PersonuppgiftsbehandlingSystemLink dto) {
		// Ensure the behandlingId in the URL matches the DTO
		dto.withBehandlingId(behandlingId);
		final var created = this.service.create(dto);
		return ResponseEntity.status(CREATED).body(created);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a system link by ID")
	@ApiResponse(responseCode = "200", description = "System link found")
	@ApiResponse(responseCode = "404", description = "System link not found")
	ResponseEntity<PersonuppgiftsbehandlingSystemLink> getSystemLinkById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String behandlingId,
		@PathVariable final String id) {
		final var dto = this.service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Get all system links for a processing activity")
	@ApiResponse(responseCode = "200", description = "List of system links")
	ResponseEntity<List<PersonuppgiftsbehandlingSystemLink>> getSystemLinksForBehandling(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String behandlingId) {
		final var links = this.service.getByBehandlingId(behandlingId);
		return ResponseEntity.ok(links);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a system link")
	@ApiResponse(responseCode = "204", description = "System link deleted")
	@ApiResponse(responseCode = "404", description = "System link not found")
	ResponseEntity<Void> deleteSystemLink(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String behandlingId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
