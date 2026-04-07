package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.KlassaProcessgrupp;
import se.sundsvall.systemregister.integration.db.KlassaProcessgruppRepository;
import se.sundsvall.systemregister.service.mapper.KlassaProcessgruppMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class KlassaProcessgruppService {

	private final KlassaProcessgruppRepository repository;

	public KlassaProcessgruppService(final KlassaProcessgruppRepository repository) {
		this.repository = repository;
	}

	public KlassaProcessgrupp create(final KlassaProcessgrupp model) {
		final var entity = KlassaProcessgruppMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return KlassaProcessgruppMapper.toModel(savedEntity);
	}

	public KlassaProcessgrupp getById(final Integer id) {
		return this.repository.findById(id)
			.map(KlassaProcessgruppMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaProcessgrupp not found"));
	}

	public List<KlassaProcessgrupp> getAll() {
		return this.repository.findAll().stream()
			.map(KlassaProcessgruppMapper::toModel)
			.toList();
	}

	public List<KlassaProcessgrupp> getByVerksamhetsomradeId(final Integer verksamhetsomradeId) {
		return this.repository.findByVerksamhetsomradeId(verksamhetsomradeId).stream()
			.map(KlassaProcessgruppMapper::toModel)
			.toList();
	}

	public KlassaProcessgrupp update(final Integer id, final KlassaProcessgrupp model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "KlassaProcessgrupp not found"));

		final var updatedEntity = KlassaProcessgruppMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return KlassaProcessgruppMapper.toModel(savedEntity);
	}

	public void delete(final Integer id) {
		this.repository.deleteById(id);
	}
}
