package se.sundsvall.systemregister.api.model;

import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class SystemDependencyTest {

	@Test
	void testBean() {
		assertThat(SystemDependency.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var id = "id";
		final var sourceSystemId = "sourceSystemId";
		final var targetSystemId = "targetSystemId";
		final var dependencyType = "dependencyType";
		final var description = "description";
		final var isCritical = true;
		final var documentationUrl = "documentationUrl";
		final var contractFilePath = "contractFilePath";

		final var result = SystemDependency.create()
			.withId(id)
			.withSourceSystemId(sourceSystemId)
			.withTargetSystemId(targetSystemId)
			.withDependencyType(dependencyType)
			.withDescription(description)
			.withIsCritical(isCritical)
			.withDocumentationUrl(documentationUrl)
			.withContractFilePath(contractFilePath);

		assertThat(result).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(result.getId()).isEqualTo(id);
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
		assertThat(SystemDependency.create()).hasAllNullFieldsOrProperties();
		assertThat(new SystemDependency()).hasAllNullFieldsOrProperties();
	}
}
