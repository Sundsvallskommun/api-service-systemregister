package se.sundsvall.systemregister.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.sundsvall.dept44.common.validators.annotation.ValidMunicipalityId;
import se.sundsvall.systemregister.api.model.EventLog;
import se.sundsvall.systemregister.service.EventLogService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@Validated
@Tag(name = "Event Logs", description = "Audit event logs")
@RequestMapping("/{municipalityId}/event-logs")
class EventLogResource {

	private final EventLogService service;

	EventLogResource(final EventLogService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new event log entry")
	@ApiResponse(responseCode = "201", description = "Event log entry created", content = @Content(schema = @Schema(implementation = EventLog.class)))
	ResponseEntity<EventLog> createEventLog(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestBody final EventLog dto) {
		final var created = this.service.create(dto);
		return ResponseEntity.status(CREATED).body(created);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get an event log entry by ID")
	@ApiResponse(responseCode = "200", description = "Event log entry found")
	@ApiResponse(responseCode = "404", description = "Event log entry not found")
	ResponseEntity<EventLog> getEventLogById(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		final var dto = this.service.getById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Get event log entries, optionally filtered by entity type and ID")
	@ApiResponse(responseCode = "200", description = "List of event log entries")
	ResponseEntity<List<EventLog>> getEventLogs(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@RequestParam(required = false) final String entityType,
		@RequestParam(required = false) final String entityId) {
		List<EventLog> logs;
		if (entityType != null && entityId != null) {
			logs = this.service.getByEntityTypeAndEntityId(entityType, entityId);
		} else {
			logs = this.service.getAll();
		}
		return ResponseEntity.ok(logs);
	}

	@PatchMapping("/{id}/acknowledge")
	@Operation(summary = "Acknowledge an event log entry")
	@ApiResponse(responseCode = "200", description = "Event log entry acknowledged")
	@ApiResponse(responseCode = "404", description = "Event log entry not found")
	ResponseEntity<EventLog> acknowledgeEventLog(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		final var updated = this.service.acknowledge(id);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete an event log entry")
	@ApiResponse(responseCode = "204", description = "Event log entry deleted")
	@ApiResponse(responseCode = "404", description = "Event log entry not found")
	ResponseEntity<Void> deleteEventLog(
		@ValidMunicipalityId @PathVariable final String municipalityId,
		@PathVariable final String id) {
		this.service.delete(id);
		return ResponseEntity.status(NO_CONTENT).build();
	}
}
