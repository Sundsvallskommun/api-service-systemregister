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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.Handlingstyp;
import se.sundsvall.systemregister.service.HandlingstypService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/informationshanteringsplaner/{ihPlanId}/handlingstyper")
@Tag(name = "Handlingstyp", description = "Document types")
class HandlingstypResource {

	private final HandlingstypService service;

	HandlingstypResource(final HandlingstypService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Handlingstyp")
	ResponseEntity<Handlingstyp> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String ihPlanId,
		@Valid @RequestBody final Handlingstyp body) {
		body.withIhPlanId(ihPlanId);
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Handlingstyp by ID")
	ResponseEntity<Handlingstyp> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String ihPlanId,
		@PathVariable final String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Handlingstyper for IH-plan")
	ResponseEntity<List<Handlingstyp>> getByIhPlanId(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String ihPlanId) {
		return ResponseEntity.ok(this.service.getByIhPlanId(ihPlanId));
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Handlingstyp")
	ResponseEntity<Handlingstyp> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String ihPlanId,
		@PathVariable final String id,
		@Valid @RequestBody final Handlingstyp body) {
		body.withIhPlanId(ihPlanId);
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Handlingstyp")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String ihPlanId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
