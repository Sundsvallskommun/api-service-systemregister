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
import se.sundsvall.systemregister.api.model.KlassaVerksamhetsomrade;
import se.sundsvall.systemregister.service.KlassaVerksamhetsomradeService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/klassa/verksamhetsomraden")
@Tag(name = "KLASSA Verksamhetsomrade", description = "KLASSA Activity areas")
class KlassaVerksamhetsomradeResource {

	private final KlassaVerksamhetsomradeService service;

	KlassaVerksamhetsomradeResource(final KlassaVerksamhetsomradeService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Verksamhetsomrade")
	ResponseEntity<KlassaVerksamhetsomrade> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@Valid @RequestBody final KlassaVerksamhetsomrade body) {
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Verksamhetsomrade by ID")
	ResponseEntity<KlassaVerksamhetsomrade> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Verksamhetsomraden")
	ResponseEntity<List<KlassaVerksamhetsomrade>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final Integer verksamhetstypId) {
		if (verksamhetstypId != null) {
			return ResponseEntity.ok(this.service.getByVerksamhetstypId(verksamhetstypId));
		}
		return ResponseEntity.ok(this.service.getAll());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Verksamhetsomrade")
	ResponseEntity<KlassaVerksamhetsomrade> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id,
		@Valid @RequestBody final KlassaVerksamhetsomrade body) {
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Verksamhetsomrade")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
