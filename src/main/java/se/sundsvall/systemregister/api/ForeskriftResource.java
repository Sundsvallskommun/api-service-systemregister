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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.Foreskrift;
import se.sundsvall.systemregister.service.ForeskriftService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/foreskrifter")
@Tag(name = "Foreskrift", description = "Regulations and statutes")
class ForeskriftResource {

	private final ForeskriftService service;

	ForeskriftResource(final ForeskriftService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Foreskrift")
	ResponseEntity<Foreskrift> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@Valid @RequestBody final Foreskrift body) {
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Foreskrift by ID")
	ResponseEntity<Foreskrift> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Foreskrifter")
	ResponseEntity<List<Foreskrift>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String beteckning) {
		if (beteckning != null) {
			return ResponseEntity.ok(List.of(this.service.getByBeteckning(beteckning)));
		}
		return ResponseEntity.ok(this.service.getAll());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Foreskrift")
	ResponseEntity<Foreskrift> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@Valid @RequestBody final Foreskrift body) {
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Foreskrift")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
