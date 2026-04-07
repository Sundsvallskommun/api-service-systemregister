package se.sundsvall.systemregister.integration.db.model;

import java.time.OffsetDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.sundsvall.systemregister.integration.db.model.enums.DependencyType;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class SystemDependencyEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(SystemDependencyEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCodeExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanEqualsExcluding("createdAt", "updatedAt", "createdBy", "updatedBy"),
			hasValidBeanToStringExcluding("createdAt", "updatedAt", "createdBy", "updatedBy")));
	}

	@Test
	void testBuilderMethods() {
		final var sourceSystemId = "sourceSystemId";
		final var targetSystemId = "targetSystemId";
		final var dependencyType = DependencyType.values()[0];
		final var description = "description";
		final var isCritical = true;
		final var documentationUrl = "documentationUrl";
		final var contractFilePath = "contractFilePath";

		final var result = SystemDependencyEntity.create()
			.withSourceSystemId(sourceSystemId)
			.withTargetSystemId(targetSystemId)
			.withDependencyType(dependencyType)
			.withDescription(description)
			.withIsCritical(isCritical)
			.withDocumentationUrl(documentationUrl)
			.withContractFilePath(contractFilePath);

		assertThat(result).isNotNull().hasNoNullFieldsOrPropertiesExcept("id", "createdAt", "updatedAt", "createdBy", "updatedBy");
		assertThat(result.getSourceSystemId()).isEqualTo(sourceSystemId);
		assertThat(result.getTargetSystemId()).isEqualTo(targetSystemId);
		assertThat(result.getDependencyType()).isEqualTo(dependencyType);
		assertThat(result.getDescription()).isEqualTo(description);
		assertThat(result.getIsCritical()).isEqualTo(isCritical);
		assertThat(result.getDocumentationUrl()).isEqualTo(documentationUrl);
		assertThat(result.getContractFilePath()).isEqualTo(contractFilePath);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(SystemDependencyEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new SystemDependencyEntity()).hasAllNullFieldsOrProperties();
	}
}
