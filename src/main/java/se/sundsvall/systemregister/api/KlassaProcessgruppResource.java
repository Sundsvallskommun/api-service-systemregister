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
import se.sundsvall.systemregister.api.model.KlassaProcessgrupp;
import se.sundsvall.systemregister.service.KlassaProcessgruppService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/klassa/processgrupper")
@Tag(name = "KLASSA Processgrupp", description = "KLASSA Process groups")
class KlassaProcessgruppResource {

	private final KlassaProcessgruppService service;

	KlassaProcessgruppResource(final KlassaProcessgruppService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Processgrupp")
	ResponseEntity<KlassaProcessgrupp> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@Valid @RequestBody final KlassaProcessgrupp body) {
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Processgrupp by ID")
	ResponseEntity<KlassaProcessgrupp> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Processgrupper")
	ResponseEntity<List<KlassaProcessgrupp>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final Integer verksamhetsomradeId) {
		if (verksamhetsomradeId != null) {
			return ResponseEntity.ok(this.service.getByVerksamhetsomradeId(verksamhetsomradeId));
		}
		return ResponseEntity.ok(this.service.getAll());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Processgrupp")
	ResponseEntity<KlassaProcessgrupp> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id,
		@Valid @RequestBody final KlassaProcessgrupp body) {
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Processgrupp")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
