package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.KlassaVerksamhetstyp;
import se.sundsvall.systemregister.integration.db.KlassaVerksamhetstypRepository;
import se.sundsvall.systemregister.service.mapper.KlassaVerksamhetstypMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class KlassaVerksamhetstypService {

	private final KlassaVerksamhetstypRepository repository;

	public KlassaVerksamhetstypService(final KlassaVerksamhetstypRepository repository) {
		this.repository = repository;
	}

	public KlassaVerksamhetstyp getById(final Integer id) {
		return this.repository.findById(id)
			.map(KlassaVerksamhetstypMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaVerksamhetstyp not found"));
	}

	public List<KlassaVerksamhetstyp> getAll() {
		return this.repository.findAll().stream()
			.map(KlassaVerksamhetstypMapper::toModel)
			.toList();
	}
}
