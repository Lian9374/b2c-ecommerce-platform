package com.enterprisemall.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

/**
 * 架构测试 — 使用 ArchUnit 强制执行 DDD 分层依赖规则
 */
public class ArchitectureTest {

    private static JavaClasses importedClasses;

    @BeforeAll
    static void setUp() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.enterprisemall");
    }

    @Test
    @DisplayName("common 模块不应依赖其他业务模块")
    void commonShouldNotDependOnOtherModules() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.enterprisemall.common..")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage(
                        "java..",
                        "jakarta..",
                        "org.springframework..",
                        "com.fasterxml..",
                        "com.baomidou..",
                        "cn.hutool..",
                        "org.mapstruct..",
                        "lombok..",
                        "io.swagger..",
                        "com.enterprisemall.common.."
                );
        rule.check(importedClasses);
    }

    @Test
    @DisplayName("领域层不应依赖基础设施层和 API 层")
    void domainShouldNotDependOnInfrastructure() {
        ArchRule rule = classes()
                .that().resideInAPackage("..domain..")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage(
                        "java..",
                        "jakarta..",
                        "com.enterprisemall.common..",
                        "..domain.."
                );
        rule.check(importedClasses);
    }

    @Test
    @DisplayName("模块应无循环依赖")
    void noCyclicDependencies() {
        slices().matching("com.enterprisemall.(*)..")
                .should().beFreeOfCycles()
                .check(importedClasses);
    }
}
