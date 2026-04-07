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
import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.service.InformationshanteringsplanService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/informationshanteringsplaner")
@Tag(name = "Informationshanteringsplan (IH-plan)", description = "Information management plans")
class InformationshanteringsplanResource {

	private final InformationshanteringsplanService service;

	InformationshanteringsplanResource(final InformationshanteringsplanService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new IH-plan")
	ResponseEntity<Informationshanteringsplan> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@Valid @RequestBody final Informationshanteringsplan body) {
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get IH-plan by ID")
	ResponseEntity<Informationshanteringsplan> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all IH-plans")
	ResponseEntity<List<Informationshanteringsplan>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String organisationId,
		@RequestParam(required = false) final String status) {
		if (organisationId != null) {
			return ResponseEntity.ok(this.service.getByOrganisationId(organisationId));
		}
		if (status != null) {
			return ResponseEntity.ok(this.service.getByStatus(status));
		}
		return ResponseEntity.ok(this.service.getAll());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update IH-plan")
	ResponseEntity<Informationshanteringsplan> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@Valid @RequestBody final Informationshanteringsplan body) {
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete IH-plan")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
