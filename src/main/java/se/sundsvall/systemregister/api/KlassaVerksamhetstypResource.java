package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.KlassaVerksamhetstyp;
import se.sundsvall.systemregister.service.KlassaVerksamhetstypService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/klassa/verksamhetstyper")
@Tag(name = "KLASSA Verksamhetstyp", description = "KLASSA Activity types (read-only)")
class KlassaVerksamhetstypResource {

	private final KlassaVerksamhetstypService service;

	KlassaVerksamhetstypResource(final KlassaVerksamhetstypService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Verksamhetstyp by ID")
	ResponseEntity<KlassaVerksamhetstyp> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final Integer id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Verksamhetstyper")
	ResponseEntity<List<KlassaVerksamhetstyp>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId) {
		return ResponseEntity.ok(this.service.getAll());
	}
}
