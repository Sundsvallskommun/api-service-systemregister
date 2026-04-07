package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import se.sundsvall.systemregister.api.model.HandlingstypAnsvarig;
import se.sundsvall.systemregister.service.HandlingstypAnsvarigService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/handlingstyper/{handlingstypId}/ansvariga")
@Tag(name = "HandlingstypAnsvarig", description = "Document type responsible parties")
class HandlingstypAnsvarigResource {

	private final HandlingstypAnsvarigService service;

	HandlingstypAnsvarigResource(final HandlingstypAnsvarigService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Add responsible party to Handlingstyp")
	ResponseEntity<HandlingstypAnsvarig> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId,
		@Valid @RequestBody final HandlingstypAnsvarig body) {
		body.withHandlingstypId(handlingstypId);
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all responsible parties for Handlingstyp")
	ResponseEntity<List<HandlingstypAnsvarig>> getByHandlingstypId(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId) {
		return ResponseEntity.ok(this.service.getByHandlingstypId(handlingstypId));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete responsible party")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
