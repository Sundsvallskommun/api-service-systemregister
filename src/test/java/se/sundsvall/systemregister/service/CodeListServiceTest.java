package se.sundsvall.systemregister.service;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.dept44.problem.ThrowableProblem;
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
import se.sundsvall.systemregister.integration.db.model.KodFormatEntity;
import se.sundsvall.systemregister.integration.db.model.KodGallringsfristTypEntity;
import se.sundsvall.systemregister.integration.db.model.KodPersonuppgiftEntity;
import se.sundsvall.systemregister.integration.db.model.KodPlanStatusEntity;
import se.sundsvall.systemregister.integration.db.model.KodRegistreringEntity;
import se.sundsvall.systemregister.integration.db.model.KodSekretessLagrumEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeListServiceTest {

	@Mock
	private KodFormatRepository kodFormatRepository;

	@Mock
	private KodBevarandeGallringRepository kodBevarandeGallringRepository;

	@Mock
	private KodGallringsfristTypRepository kodGallringsfristTypRepository;

	@Mock
	private KodBevarandeLagstodRepository kodBevarandeLagstodRepository;

	@Mock
	private KodRegistreringRepository kodRegistreringRepository;

	@Mock
	private KodSekretessLagrumRepository kodSekretessLagrumRepository;

	@Mock
	private KodPersonuppgiftRepository kodPersonuppgiftRepository;

	@Mock
	private KodAnvarstypRepository kodAnvarstypRepository;

	@Mock
	private KodPlanStatusRepository kodPlanStatusRepository;

	private CodeListService service;

	@BeforeEach
	void setUp() {
		service = new CodeListService(
			kodFormatRepository,
			kodBevarandeGallringRepository,
			kodGallringsfristTypRepository,
			kodBevarandeLagstodRepository,
			kodRegistreringRepository,
			kodSekretessLagrumRepository,
			kodPersonuppgiftRepository,
			kodAnvarstypRepository,
			kodPlanStatusRepository);
	}

	@Test
	void getAllByTableName() {
		final var entity = KodFormatEntity.create()
			.withId(1)
			.withKod("FORMAT_001")
			.withNamn("PDF Format")
			.withOrdning(1);

		when(kodFormatRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("format");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getId()).isEqualTo(1);
		assertThat(result.getFirst().getKod()).isEqualTo("FORMAT_001");
		assertThat(result.getFirst().getNamn()).isEqualTo("PDF Format");
		assertThat(result.getFirst().getBeskrivning()).isNull();
		assertThat(result.getFirst().getLagrum()).isNull();
		assertThat(result.getFirst().getGallringsfristAr()).isNull();
		verify(kodFormatRepository).findAll();
	}

	@Test
	void getAllByTableNameEmpty() {
		when(kodFormatRepository.findAll()).thenReturn(List.of());

		final var result = service.getAllByTableName("format");

		assertThat(result).isEmpty();
		verify(kodFormatRepository).findAll();
	}

	@Test
	void getAllByTableNameUnknownTable() {
		assertThatThrownBy(() -> service.getAllByTableName("unknown"))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAllByTableNameWithFullEntity() {
		final var entity = KodBevarandeLagstodEntity.create()
			.withId(1)
			.withKod("BEV_001")
			.withNamn("Bevarande lagstod")
			.withOrdning(1)
			.withGallringsfristAr(10)
			.withLagrum("5 kap 1 par")
			.withBeskrivning("Full description");

		when(kodBevarandeLagstodRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("bevarande-lagstod");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getId()).isEqualTo(1);
		assertThat(result.getFirst().getBeskrivning()).isEqualTo("Full description");
		assertThat(result.getFirst().getLagrum()).isEqualTo("5 kap 1 par");
		assertThat(result.getFirst().getGallringsfristAr()).isEqualTo(10);
		verify(kodBevarandeLagstodRepository).findAll();
	}

	@Test
	void getByTableNameAndId() {
		final var entity = KodFormatEntity.create()
			.withId(1)
			.withKod("FORMAT_001")
			.withNamn("PDF Format")
			.withOrdning(1);

		when(kodFormatRepository.findById(1)).thenReturn(Optional.of(entity));

		final var result = service.getByTableNameAndId("format", 1);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		verify(kodFormatRepository).findById(1);
	}

	@Test
	void getByTableNameAndIdNotFound() {
		when(kodFormatRepository.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getByTableNameAndId("format", 1))
			.isInstanceOf(ThrowableProblem.class);

		verify(kodFormatRepository).findById(1);
	}

	@Test
	void getByTableNameAndIdUnknownTable() {
		assertThatThrownBy(() -> service.getByTableNameAndId("unknown", 1))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void deleteByTableName() {
		service.deleteByTableName("format", 1);

		verify(kodFormatRepository).deleteById(1);
	}

	@Test
	void deleteByTableNameUnknownTable() {
		assertThatThrownBy(() -> service.deleteByTableName("unknown", 1))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void createByTableName() {
		final var model = CodeList.create()
			.withKod("NY_KOD")
			.withNamn("Ny kod")
			.withOrdning(3);
		final var savedEntity = KodFormatEntity.create()
			.withId(10)
			.withKod("NY_KOD")
			.withNamn("Ny kod")
			.withOrdning(3);

		when(kodFormatRepository.save(any(KodFormatEntity.class))).thenReturn(savedEntity);

		final var result = service.createByTableName("format", model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(10);
		assertThat(result.getKod()).isEqualTo("NY_KOD");
		assertThat(result.getNamn()).isEqualTo("Ny kod");
		assertThat(result.getOrdning()).isEqualTo(3);
		verify(kodFormatRepository).save(any(KodFormatEntity.class));
	}

	@Test
	void createByTableNameUnknownTable() {
		final var model = CodeList.create()
			.withKod("NY_KOD")
			.withNamn("Ny kod")
			.withOrdning(1);

		assertThatThrownBy(() -> service.createByTableName("unknown", model))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void createByTableNameWithFullEntity() {
		final var model = CodeList.create()
			.withKod("BEV_NY")
			.withNamn("Ny bevarandelagstod")
			.withOrdning(5)
			.withBeskrivning("Beskrivning")
			.withLagrum("3 kap 2 par")
			.withGallringsfristAr(7);
		final var savedEntity = KodBevarandeLagstodEntity.create()
			.withId(20)
			.withKod("BEV_NY")
			.withNamn("Ny bevarandelagstod")
			.withOrdning(5)
			.withBeskrivning("Beskrivning")
			.withLagrum("3 kap 2 par")
			.withGallringsfristAr(7);

		when(kodBevarandeLagstodRepository.save(any(KodBevarandeLagstodEntity.class))).thenReturn(savedEntity);

		final var result = service.createByTableName("bevarande-lagstod", model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(20);
		assertThat(result.getBeskrivning()).isEqualTo("Beskrivning");
		assertThat(result.getLagrum()).isEqualTo("3 kap 2 par");
		assertThat(result.getGallringsfristAr()).isEqualTo(7);
		verify(kodBevarandeLagstodRepository).save(any(KodBevarandeLagstodEntity.class));
	}

	@Test
	void updateByTableName() {
		final var existingEntity = KodFormatEntity.create()
			.withId(1)
			.withKod("OLD_KOD")
			.withNamn("Gammal kod")
			.withOrdning(1);
		final var model = CodeList.create()
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(2);
		final var savedEntity = KodFormatEntity.create()
			.withId(1)
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(2);

		when(kodFormatRepository.findById(1)).thenReturn(Optional.of(existingEntity));
		when(kodFormatRepository.save(any(KodFormatEntity.class))).thenReturn(savedEntity);

		final var result = service.updateByTableName("format", 1, model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
		assertThat(result.getKod()).isEqualTo("UPD_KOD");
		assertThat(result.getNamn()).isEqualTo("Uppdaterad kod");
		assertThat(result.getOrdning()).isEqualTo(2);
		verify(kodFormatRepository).findById(1);
		verify(kodFormatRepository).save(any(KodFormatEntity.class));
	}

	@Test
	void updateByTableNameNotFound() {
		final var model = CodeList.create()
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(1);

		when(kodFormatRepository.findById(1)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.updateByTableName("format", 1, model))
			.isInstanceOf(ThrowableProblem.class);

		verify(kodFormatRepository).findById(1);
	}

	@Test
	void updateByTableNameUnknownTable() {
		final var model = CodeList.create()
			.withKod("UPD_KOD")
			.withNamn("Uppdaterad kod")
			.withOrdning(1);

		assertThatThrownBy(() -> service.updateByTableName("unknown", 1, model))
			.isInstanceOf(ThrowableProblem.class);
	}

	@Test
	void getAllByTableNameBevarandeGallring() {
		final var entity = KodBevarandeGallringEntity.create().withId(1).withKod("BG1").withNamn("Bevara").withOrdning(1).withBeskrivning("desc");
		when(kodBevarandeGallringRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("bevarande-gallring");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getBeskrivning()).isEqualTo("desc");
		verify(kodBevarandeGallringRepository).findAll();
	}

	@Test
	void getAllByTableNameGallringsfristTyp() {
		final var entity = KodGallringsfristTypEntity.create().withId(1).withKod("GT1").withNamn("Typ").withOrdning(1).withBeskrivning("desc");
		when(kodGallringsfristTypRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("gallringsfrist-typ");

		assertThat(result).hasSize(1);
		verify(kodGallringsfristTypRepository).findAll();
	}

	@Test
	void getAllByTableNameRegistrering() {
		final var entity = KodRegistreringEntity.create().withId(1).withKod("R1").withNamn("Reg").withOrdning(1).withBeskrivning("desc");
		when(kodRegistreringRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("registrering");

		assertThat(result).hasSize(1);
		verify(kodRegistreringRepository).findAll();
	}

	@Test
	void getAllByTableNameSekretessLagrum() {
		final var entity = KodSekretessLagrumEntity.create().withId(1).withKod("SL1").withNamn("Sekretess").withOrdning(1).withBeskrivning("desc").withLagrum("5 kap");
		when(kodSekretessLagrumRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("sekretess-lagrum");

		assertThat(result).hasSize(1);
		assertThat(result.getFirst().getLagrum()).isEqualTo("5 kap");
		verify(kodSekretessLagrumRepository).findAll();
	}

	@Test
	void getAllByTableNamePersonuppgift() {
		final var entity = KodPersonuppgiftEntity.create().withId(1).withKod("P1").withNamn("Personuppgift").withOrdning(1).withBeskrivning("desc");
		when(kodPersonuppgiftRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("personuppgift");

		assertThat(result).hasSize(1);
		verify(kodPersonuppgiftRepository).findAll();
	}

	@Test
	void getAllByTableNameAnsvarstyp() {
		final var entity = KodAnvarstypEntity.create().withId(1).withKod("A1").withNamn("Ansvar").withOrdning(1);
		when(kodAnvarstypRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("ansvarstyp");

		assertThat(result).hasSize(1);
		verify(kodAnvarstypRepository).findAll();
	}

	@Test
	void getAllByTableNamePlanStatus() {
		final var entity = KodPlanStatusEntity.create().withId(1).withKod("PS1").withNamn("Status").withOrdning(1);
		when(kodPlanStatusRepository.findAll()).thenReturn(List.of(entity));

		final var result = service.getAllByTableName("plan-status");

		assertThat(result).hasSize(1);
		verify(kodPlanStatusRepository).findAll();
	}

	@Test
	void createByTableNameSekretessLagrum() {
		final var model = CodeList.create().withKod("SL1").withNamn("Sekretess").withOrdning(1).withBeskrivning("desc").withLagrum("5 kap");
		final var saved = KodSekretessLagrumEntity.create().withId(1).withKod("SL1").withNamn("Sekretess").withOrdning(1).withBeskrivning("desc").withLagrum("5 kap");
		when(kodSekretessLagrumRepository.save(any(KodSekretessLagrumEntity.class))).thenReturn(saved);

		final var result = service.createByTableName("sekretess-lagrum", model);

		assertThat(result).isNotNull();
		assertThat(result.getLagrum()).isEqualTo("5 kap");
		verify(kodSekretessLagrumRepository).save(any(KodSekretessLagrumEntity.class));
	}
}
