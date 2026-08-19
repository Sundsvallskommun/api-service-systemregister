package se.sundsvall.systemregister.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.dept44.problem.ThrowableProblem;
import se.sundsvall.systemregister.api.model.System;
import se.sundsvall.systemregister.integration.db.SystemRepository;
import se.sundsvall.systemregister.integration.db.model.SystemEntity;
import se.sundsvall.systemregister.integration.db.model.enums.SystemStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemServiceTest {

	@Mock
	private SystemRepository systemRepository;

	@Captor
	private ArgumentCaptor<Specification<SystemEntity>> specCaptor;

	@InjectMocks
	private SystemService systemService;

	@Test
	void create() {
		final var model = System.create()
			.withSystemId("SYS-001")
			.withName("Test System");
		final var savedEntity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Test System");
		savedEntity.withId("id-1");

		when(systemRepository.save(any())).thenReturn(savedEntity);

		final var result = systemService.create(model);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getSystemId()).isEqualTo("SYS-001");
		assertThat(result.getName()).isEqualTo("Test System");
		verify(systemRepository).save(any(SystemEntity.class));
	}

	@Test
	void getByIdFound() {
		final var entity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Test System");
		entity.withId("id-1");

		when(systemRepository.findById("id-1")).thenReturn(Optional.of(entity));

		final var result = systemService.getById("id-1");

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("id-1");
		assertThat(result.getSystemId()).isEqualTo("SYS-001");
		verify(systemRepository).findById("id-1");
	}

	@Test
	void getByIdNotFound() {
		when(systemRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> systemService.getById("id-1"))
			.isInstanceOf(ThrowableProblem.class);
		verify(systemRepository).findById("id-1");
	}

	@Test
	void searchSpecificationWithStatusAndSearch() {
		final var pageRequest = PageRequest.of(0, 20, Sort.by("name"));
		final var page = new PageImpl<>(List.<SystemEntity>of(), pageRequest, 0);

		when(systemRepository.findAll(specCaptor.capture(), eq(pageRequest))).thenReturn(page);

		systemService.search("PRODUCTION", "HR", 1, 20);

		// Execute the captured specification to cover the lambda body
		final var spec = specCaptor.getValue();
		final Root<SystemEntity> root = mock();
		final CriteriaQuery<?> query = mock();
		final CriteriaBuilder cb = mock();
		final Path<Object> statusPath = mock();
		final Path<String> namePath = mock();
		final Path<String> systemIdPath = mock();
		final Expression<String> lowerName = mock();
		final Expression<String> lowerId = mock();
		final Predicate statusPred = mock();
		final Predicate namePred = mock();
		final Predicate idPred = mock();
		final Predicate orPred = mock();
		final Predicate andPred = mock();

		doReturn(statusPath).when(root).get("status");
		doReturn(namePath).when(root).get("name");
		doReturn(systemIdPath).when(root).get("systemId");
		when(cb.equal(statusPath, SystemStatus.PRODUCTION)).thenReturn(statusPred);
		when(cb.lower(namePath)).thenReturn(lowerName);
		when(cb.lower(systemIdPath)).thenReturn(lowerId);
		when(cb.like(lowerName, "%hr%")).thenReturn(namePred);
		when(cb.like(lowerId, "%hr%")).thenReturn(idPred);
		when(cb.or(namePred, idPred)).thenReturn(orPred);
		when(cb.and(any(Predicate[].class))).thenReturn(andPred);

		final var result = spec.toPredicate(root, query, cb);
		assertThat(result).isEqualTo(andPred);
	}

	@Test
	void searchWithDefaults() {
		final var entity1 = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Alpha System");
		entity1.withId("id-1");
		final var entity2 = SystemEntity.create()
			.withSystemId("SYS-002")
			.withName("Beta System");
		entity2.withId("id-2");

		final var pageRequest = PageRequest.of(0, 20, Sort.by("name"));
		final var page = new PageImpl<>(List.of(entity1, entity2), pageRequest, 2);

		when(systemRepository.findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest))).thenReturn(page);

		final var result = systemService.search(null, null, 1, 20);

		assertThat(result).isNotNull();
		assertThat(result.getSystems()).hasSize(2);
		assertThat(result.getMetadata().getPage()).isEqualTo(1);
		assertThat(result.getMetadata().getLimit()).isEqualTo(20);
		assertThat(result.getMetadata().getTotalRecords()).isEqualTo(2);
		assertThat(result.getMetadata().getTotalPages()).isEqualTo(1);
		verify(systemRepository).findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest));
	}

	@Test
	void searchWithStatusFilter() {
		final var entity1 = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Alpha System")
			.withStatus(SystemStatus.PRODUCTION);
		entity1.withId("id-1");

		final var pageRequest = PageRequest.of(0, 10, Sort.by("name"));
		final var page = new PageImpl<>(List.of(entity1), pageRequest, 1);

		when(systemRepository.findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest))).thenReturn(page);

		final var result = systemService.search("PRODUCTION", null, 1, 10);

		assertThat(result).isNotNull();
		assertThat(result.getSystems()).hasSize(1);
		assertThat(result.getSystems().getFirst().getSystemId()).isEqualTo("SYS-001");
		verify(systemRepository).findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest));
	}

	@Test
	void searchWithSearchTerm() {
		final var entity1 = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("HR Management");
		entity1.withId("id-1");

		final var pageRequest = PageRequest.of(0, 20, Sort.by("name"));
		final var page = new PageImpl<>(List.of(entity1), pageRequest, 1);

		when(systemRepository.findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest))).thenReturn(page);

		final var result = systemService.search(null, "HR", 1, 20);

		assertThat(result).isNotNull();
		assertThat(result.getSystems()).hasSize(1);
		assertThat(result.getSystems().getFirst().getName()).isEqualTo("HR Management");
		verify(systemRepository).findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest));
	}

	@Test
	void searchWithPagination() {
		final var entity1 = SystemEntity.create()
			.withSystemId("SYS-011")
			.withName("System 11");
		entity1.withId("id-11");

		final var pageRequest = PageRequest.of(1, 10, Sort.by("name"));
		final var page = new PageImpl<>(List.of(entity1), pageRequest, 11);

		when(systemRepository.findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest))).thenReturn(page);

		final var result = systemService.search(null, null, 2, 10);

		assertThat(result).isNotNull();
		assertThat(result.getSystems()).hasSize(1);
		assertThat(result.getMetadata().getPage()).isEqualTo(2);
		assertThat(result.getMetadata().getLimit()).isEqualTo(10);
		assertThat(result.getMetadata().getTotalRecords()).isEqualTo(11);
		assertThat(result.getMetadata().getTotalPages()).isEqualTo(2);
		verify(systemRepository).findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest));
	}

	@Test
	void searchEmpty() {
		final var pageRequest = PageRequest.of(0, 20, Sort.by("name"));
		final var page = new PageImpl<SystemEntity>(List.of(), pageRequest, 0);

		when(systemRepository.findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest))).thenReturn(page);

		final var result = systemService.search(null, null, 1, 20);

		assertThat(result).isNotNull();
		assertThat(result.getSystems()).isEmpty();
		assertThat(result.getMetadata().getTotalRecords()).isZero();
		assertThat(result.getMetadata().getTotalPages()).isZero();
		verify(systemRepository).findAll(ArgumentMatchers.<Specification<SystemEntity>>any(), eq(pageRequest));
	}

	@Test
	void updateFound() {
		final var existingEntity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("Old Name");
		existingEntity.withId("id-1");
		final var updateModel = System.create()
			.withName("New Name")
			.withDescription("Updated description");
		final var savedEntity = SystemEntity.create()
			.withSystemId("SYS-001")
			.withName("New Name")
			.withDescription("Updated description");
		savedEntity.withId("id-1");

		when(systemRepository.findById("id-1")).thenReturn(Optional.of(existingEntity));
		when(systemRepository.save(any())).thenReturn(savedEntity);

		final var result = systemService.update("id-1", updateModel);

		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("New Name");
		assertThat(result.getDescription()).isEqualTo("Updated description");
		verify(systemRepository).findById("id-1");
		verify(systemRepository).save(any(SystemEntity.class));
	}

	@Test
	void updateNotFound() {
		final var updateModel = System.create()
			.withName("New Name");

		when(systemRepository.findById("id-1")).thenReturn(Optional.empty());

		assertThatThrownBy(() -> systemService.update("id-1", updateModel))
			.isInstanceOf(ThrowableProblem.class);
		verify(systemRepository).findById("id-1");
	}

	@Test
	void deleteFound() {
		when(systemRepository.existsById("id-1")).thenReturn(true);

		systemService.delete("id-1");

		verify(systemRepository).existsById("id-1");
		verify(systemRepository).deleteById("id-1");
	}

	@Test
	void deleteNotFound() {
		when(systemRepository.existsById("id-1")).thenReturn(false);

		assertThatThrownBy(() -> systemService.delete("id-1"))
			.isInstanceOf(ThrowableProblem.class);
		verify(systemRepository).existsById("id-1");
	}
}
