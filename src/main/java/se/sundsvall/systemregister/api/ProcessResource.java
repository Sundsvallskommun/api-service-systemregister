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
import se.sundsvall.systemregister.api.model.Process;
import se.sundsvall.systemregister.service.ProcessService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("/{municipalityId}/processer")
@Tag(name = "Process", description = "Legacy process definitions")
class ProcessResource {

	private final ProcessService service;

	ProcessResource(final ProcessService service) {
		this.service = service;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new Process")
	ResponseEntity<Process> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@Valid @RequestBody final Process body) {
		return ResponseEntity.status(CREATED).body(this.service.create(body));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Process by ID")
	ResponseEntity<Process> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all Processer")
	ResponseEntity<List<Process>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String processkod) {
		if (processkod != null) {
			return ResponseEntity.ok(List.of(this.service.getByProcesskod(processkod)));
		}
		return ResponseEntity.ok(this.service.getAll());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update Process")
	ResponseEntity<Process> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id,
		@Valid @RequestBody final Process body) {
		return ResponseEntity.ok(this.service.update(id, body));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Process")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
