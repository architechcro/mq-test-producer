package hr.architech.mqtestproducer;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("hr.architech.mqtestproducer");

        noClasses()
            .that()
            .resideInAnyPackage("hr.architech.mqtestproducer.service..")
            .or()
            .resideInAnyPackage("hr.architech.mqtestproducer.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..hr.architech.mqtestproducer.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
