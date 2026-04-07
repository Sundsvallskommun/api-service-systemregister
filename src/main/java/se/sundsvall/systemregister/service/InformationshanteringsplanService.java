package se.sundsvall.systemregister.service;

import java.util.List;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.Informationshanteringsplan;
import se.sundsvall.systemregister.integration.db.InformationshanteringsplanRepository;
import se.sundsvall.systemregister.integration.db.model.enums.IHPlanStatus;
import se.sundsvall.systemregister.service.mapper.InformationshanteringsplanMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class InformationshanteringsplanService {

	private final InformationshanteringsplanRepository repository;

	public InformationshanteringsplanService(final InformationshanteringsplanRepository repository) {
		this.repository = repository;
	}

	public Informationshanteringsplan create(final Informationshanteringsplan model) {
		final var entity = InformationshanteringsplanMapper.toEntity(model);
		final var savedEntity = this.repository.save(entity);
		return InformationshanteringsplanMapper.toModel(savedEntity);
	}

	public Informationshanteringsplan getById(final String id) {
		return this.repository.findById(id)
			.map(InformationshanteringsplanMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, "Informationshanteringsplan not found"));
	}

	public List<Informationshanteringsplan> getAll() {
		return this.repository.findAll().stream()
			.map(InformationshanteringsplanMapper::toModel)
			.toList();
	}

	public List<Informationshanteringsplan> getByOrganisationId(final String organisationId) {
		return this.repository.findByOrganisationId(organisationId).stream()
			.map(InformationshanteringsplanMapper::toModel)
			.toList();
	}

	public List<Informationshanteringsplan> getByStatus(final String status) {
		return this.repository.findByStatus(IHPlanStatus.valueOf(status)).stream()
			.map(InformationshanteringsplanMapper::toModel)
			.toList();
	}

	public Informationshanteringsplan update(final String id, final Informationshanteringsplan model) {
		final var entity = this.repository.findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, "Informationshanteringsplan not found"));

		final var updatedEntity = InformationshanteringsplanMapper.updateEntity(entity, model);
		final var savedEntity = this.repository.save(updatedEntity);
		return InformationshanteringsplanMapper.toModel(savedEntity);
	}

	public void delete(final String id) {
		this.repository.deleteById(id);
	}
}
