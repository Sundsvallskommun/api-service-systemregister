package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import se.sundsvall.systemregister.api.model.CodeList;
import se.sundsvall.systemregister.service.CodeListService;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@Validated
@RequestMapping("/{municipalityId}/kodtabeller/{tableName}")
@Tag(name = "CodeList", description = "Generic code lists and reference tables")
class CodeListResource {

	private final CodeListService service;

	CodeListResource(final CodeListService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get code list item by ID")
	ResponseEntity<CodeList> getById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String tableName,
		@PathVariable final Integer id) {
		return ResponseEntity.ok(this.service.getByTableNameAndId(tableName, id));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Get all code list items")
	ResponseEntity<List<CodeList>> getAll(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String tableName) {
		return ResponseEntity.ok(this.service.getAllByTableName(tableName));
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = ALL_VALUE)
	@Operation(summary = "Create code list item", responses = {
		@ApiResponse(responseCode = "201", description = "Created", headers = @Header(name = LOCATION, schema = @Schema(type = "string")))
	})
	ResponseEntity<Void> create(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String tableName,
		@RequestBody @Valid final CodeList model) {
		final var created = this.service.createByTableName(tableName, model);
		return ResponseEntity.created(
			fromPath("/{municipalityId}/kodtabeller/{tableName}/{id}")
				.buildAndExpand(municipalityId, tableName, created.getId())
				.toUri())
			.header(CONTENT_TYPE, ALL_VALUE)
			.build();
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "Update code list item")
	ResponseEntity<CodeList> update(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String tableName,
		@PathVariable final Integer id,
		@RequestBody @Valid final CodeList model) {
		return ResponseEntity.ok(this.service.updateByTableName(tableName, id, model));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete code list item")
	ResponseEntity<Void> delete(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String tableName,
		@PathVariable final Integer id) {
		this.service.deleteByTableName(tableName, id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
