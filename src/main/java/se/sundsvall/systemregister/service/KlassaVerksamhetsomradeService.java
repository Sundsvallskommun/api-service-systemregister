package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.KlassaVerksamhetsomrade;
import se.sundsvall.systemregister.integration.db.KlassaVerksamhetsomradeRepository;
import se.sundsvall.systemregister.service.mapper.KlassaVerksamhetsomradeMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class KlassaVerksamhetsomradeService {

	private final KlassaVerksamhetsomradeRepository repository;

	public KlassaVerksamhetsomradeService(final KlassaVerksamhetsomradeRepository repository) {
		this.repository = repository;
	}

	public KlassaVerksamhetsomrade create(final KlassaVerksamhetsomrade model) {
		final var entity = KlassaVerksamhetsomradeMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return KlassaVerksamhetsomradeMapper.toModel(savedEntity);
	}

	public KlassaVerksamhetsomrade getById(final Integer id) {
		return this.repository.findById(id)
			.map(KlassaVerksamhetsomradeMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaVerksamhetsomrade not found"));
	}

	public List<KlassaVerksamhetsomrade> getAll() {
		return this.repository.findAll().stream()
			.map(KlassaVerksamhetsomradeMapper::toModel)
			.toList();
	}

	public List<KlassaVerksamhetsomrade> getByVerksamhetstypId(final Integer verksamhetstypId) {
		return this.repository.findByVerksamhetstypId(verksamhetstypId).stream()
			.map(KlassaVerksamhetsomradeMapper::toModel)
			.toList();
	}

	public KlassaVerksamhetsomrade update(final Integer id, final KlassaVerksamhetsomrade model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaVerksamhetsomrade not found"));

		final var updatedEntity = KlassaVerksamhetsomradeMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return KlassaVerksamhetsomradeMapper.toModel(savedEntity);
	}

	public void delete(final Integer id) {
		this.repository.deleteById(id);
	}
}
