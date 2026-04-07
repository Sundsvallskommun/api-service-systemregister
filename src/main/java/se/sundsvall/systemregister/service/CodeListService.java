package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import se.sundsvall.systemregister.api.model.CodeList;
import se.sundsvall.systemregister.integration.db.KodAnvarstypRepository;
import se.sundsvall.systemregister.integration.db.KodBevarandeGallringRepository;
import se.sundsvall.systemregister.integration.db.KodBevarandeLagstodRepository;
import se.sundsvall.systemregister.integration.db.KodFormatRepository;
import se.sundsvall.systemregister.integration.db.KodGallringsfristTypRepository;
import se.sundsvall.systemregister.integration.db.KodPersonuppgiftRepository;
import se.sundsvall.systemregister.integration.db.KodPlanStatusRepository;
import se.sundsvall.systemregister.integration.db.KodRegistreringRepository;
import se.sundsvall.systemregister.integration.db.KodSekretessLagrumRepository;
import se.sundsvall.systemregister.integration.db.model.KodAnvarstypEntity;
import se.sundsvall.systemregister.integration.db.model.KodBevarandeGallringEntity;
import se.sundsvall.systemregister.integration.db.model.KodBevarandeLagstodEntity;
import se.sundsvall.systemregister.integration.db.model.KodEntity;
import se.sundsvall.systemregister.integration.db.model.KodFormatEntity;
import se.sundsvall.systemregister.integration.db.model.KodGallringsfristTypEntity;
import se.sundsvall.systemregister.integration.db.model.KodPersonuppgiftEntity;
import se.sundsvall.systemregister.integration.db.model.KodPlanStatusEntity;
import se.sundsvall.systemregister.integration.db.model.KodRegistreringEntity;
import se.sundsvall.systemregister.integration.db.model.KodSekretessLagrumEntity;
import se.sundsvall.systemregister.service.mapper.CodeListMapper;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.dept44.problem.Problem.valueOf;

@Service
public class CodeListService {

	private static final String TABLE_NOT_FOUND = "Code list table not found";
	private static final String ITEM_NOT_FOUND = "Code list item not found";

	private final Map<String, TableBinding<? extends KodEntity>> bindings;

	public CodeListService(final KodFormatRepository kodFormat,
		final KodBevarandeGallringRepository kodBevarandeGallring,
		final KodGallringsfristTypRepository kodGallringsfristTyp,
		final KodBevarandeLagstodRepository kodBevarandeLagstod,
		final KodRegistreringRepository kodRegistrering,
		final KodSekretessLagrumRepository kodSekretessLagrum,
		final KodPersonuppgiftRepository kodPersonuppgift,
		final KodAnvarstypRepository kodAnsvartyp,
		final KodPlanStatusRepository kodPlanStatus) {

		this.bindings = Map.of(
			"format", new TableBinding<>(kodFormat, KodFormatEntity::create),
			"bevarande-gallring", new TableBinding<>(kodBevarandeGallring, KodBevarandeGallringEntity::create),
			"gallringsfrist-typ", new TableBinding<>(kodGallringsfristTyp, KodGallringsfristTypEntity::create),
			"bevarande-lagstod", new TableBinding<>(kodBevarandeLagstod, KodBevarandeLagstodEntity::create),
			"registrering", new TableBinding<>(kodRegistrering, KodRegistreringEntity::create),
			"sekretess-lagrum", new TableBinding<>(kodSekretessLagrum, KodSekretessLagrumEntity::create),
			"personuppgift", new TableBinding<>(kodPersonuppgift, KodPersonuppgiftEntity::create),
			"ansvarstyp", new TableBinding<>(kodAnsvartyp, KodAnvarstypEntity::create),
			"plan-status", new TableBinding<>(kodPlanStatus, KodPlanStatusEntity::create));
	}

	public List<CodeList> getAllByTableName(final String tableName) {
		final var binding = getBinding(tableName);
		return binding.repository().findAll().stream()
			.map(CodeListMapper::toModel)
			.toList();
	}

	public CodeList getByTableNameAndId(final String tableName, final Integer id) {
		final var binding = getBinding(tableName);
		return binding.repository().findById(id)
			.map(CodeListMapper::toModel)
			.orElseThrow(() -> valueOf(NOT_FOUND, ITEM_NOT_FOUND));
	}

	@SuppressWarnings("unchecked")
	public CodeList createByTableName(final String tableName, final CodeList model) {
		final var binding = getBinding(tableName);
		final var entity = binding.factory().get();
		CodeListMapper.updateEntity(entity, model);
		final var saved = ((JpaRepository<KodEntity, Integer>) binding.repository()).save(entity);
		return CodeListMapper.toModel(saved);
	}

	@SuppressWarnings("unchecked")
	public CodeList updateByTableName(final String tableName, final Integer id, final CodeList model) {
		final var binding = getBinding(tableName);
		final var entity = binding.repository().findById(id)
			.orElseThrow(() -> valueOf(NOT_FOUND, ITEM_NOT_FOUND));
		CodeListMapper.updateEntity(entity, model);
		final var saved = ((JpaRepository<KodEntity, Integer>) binding.repository()).save(entity);
		return CodeListMapper.toModel(saved);
	}

	public void deleteByTableName(final String tableName, final Integer id) {
		getBinding(tableName).repository().deleteById(id);
	}

	private TableBinding<? extends KodEntity> getBinding(final String tableName) {
		final var binding = this.bindings.get(tableName);
		if (binding == null) {
			throw valueOf(NOT_FOUND, TABLE_NOT_FOUND);
		}
		return binding;
	}

	private record TableBinding<T extends KodEntity>(JpaRepository<T, Integer> repository, Supplier<T> factory) {}
}
