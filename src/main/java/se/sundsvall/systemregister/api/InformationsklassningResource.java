package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.Informationsklassning;
import se.sundsvall.systemregister.service.InformationsklassningService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/handlingstyper/{handlingstypId}/klassning")
@Tag(name = "Informationsklassning", description = "Information classification")
class InformationsklassningResource {

	private final InformationsklassningService service;

	InformationsklassningResource(final InformationsklassningService service) {
		this.service = service;
	}

	@PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create or update Informationsklassning")
	ResponseEntity<Informationsklassning> createOrUpdate(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId,
		@Valid @RequestBody final Informationsklassning body) {
		return ResponseEntity.status(CREATED).body(this.service.createOrUpdate(handlingstypId, body));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Informationsklassning by Handlingstyp ID")
	ResponseEntity<Informationsklassning> getByHandlingstypId(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId) {
		return ResponseEntity.ok(this.service.getByHandlingstypId(handlingstypId));
	}

	@DeleteMapping
	@Operation(summary = "Delete Informationsklassning")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String handlingstypId) {
		this.service.delete(handlingstypId);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
