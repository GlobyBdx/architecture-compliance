/*
 * Copyright 2019 Benoit Faget. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * @author Benoit Faget
 * @version 0.1.0
 */

package fr.ubordeaux.ddd.plugin.rules;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.GivenClasses;
import com.tngtech.archunit.library.Architectures;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassRules;
import fr.ubordeaux.ddd.plugin.rules.utils.PackageUtils;

public class LayerRules {
    public final ArchRule anticorruptionLayerClassesMustNotResideInAnotherLayer =
            ClassRules.shouldResideOutsideOfPackages(
                    ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                            (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(PackageUtils.getSubPackagesAnnotatedWith(
                                    PluginConfiguration.getSyntax().getPackages().getAnticorruption()))
                            : (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(
                                    ".." + PluginConfiguration.getSyntax().getPackages().getAnticorruption() + "..")),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getApplication(),
                    PluginConfiguration.getSyntax().getPackages().getDomain(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure(),
                    PluginConfiguration.getSyntax().getPackages().getPresentation());

    public final ArchRule applicationLayerClassesMustNotResideInAnotherLayer =
            ClassRules.shouldResideOutsideOfPackages(
                    ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                            (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(PackageUtils.getSubPackagesAnnotatedWith(
                                    PluginConfiguration.getSyntax().getPackages().getApplication()))
                            : (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(
                                    ".." + PluginConfiguration.getSyntax().getPackages().getApplication() + "..")),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getAnticorruption(),
                    PluginConfiguration.getSyntax().getPackages().getDomain(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure(),
                    PluginConfiguration.getSyntax().getPackages().getPresentation());

    public final ArchRule domainLayerClassesMustNotResideInAnotherLayer =
            ClassRules.shouldResideOutsideOfPackages(
                    ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                            (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(PackageUtils.getSubPackagesAnnotatedWith(
                                    PluginConfiguration.getSyntax().getPackages().getDomain()))
                            : (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(
                                    ".." + PluginConfiguration.getSyntax().getPackages().getDomain() + "..")),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getAnticorruption(),
                    PluginConfiguration.getSyntax().getPackages().getApplication(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure(),
                    PluginConfiguration.getSyntax().getPackages().getPresentation());

    public final ArchRule infrastructureLayerClassesMustNotResideInAnotherLayer =
            ClassRules.shouldResideOutsideOfPackages(
                    ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                            (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(PackageUtils.getSubPackagesAnnotatedWith(
                                    PluginConfiguration.getSyntax().getPackages().getInfrastructure()))
                            : (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(
                                    ".." + PluginConfiguration.getSyntax().getPackages().getInfrastructure() + "..")),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getAnticorruption(),
                    PluginConfiguration.getSyntax().getPackages().getApplication(),
                    PluginConfiguration.getSyntax().getPackages().getDomain(),
                    PluginConfiguration.getSyntax().getPackages().getPresentation());

    public final ArchRule presentationLayerClassesMustNotResideInAnotherLayer =
            ClassRules.shouldResideOutsideOfPackages(
                    ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                            (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(PackageUtils.getSubPackagesAnnotatedWith(
                                    PluginConfiguration.getSyntax().getPackages().getPresentation()))
                            : (GivenClasses)ArchRuleDefinition.classes()
                            .that().resideInAnyPackage(
                                    ".." + PluginConfiguration.getSyntax().getPackages().getPresentation() + "..")),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getAnticorruption(),
                    PluginConfiguration.getSyntax().getPackages().getApplication(),
                    PluginConfiguration.getSyntax().getPackages().getDomain(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure());

    public final ArchRule anticorruptionLayerDependenciesMustBeRespected =
            ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                    Architectures.layeredArchitecture()
                    .layer("Anticorruption")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getAnticorruption()))
                    .whereLayer("Anticorruption")
                    .mayNotBeAccessedByAnyLayer()
                    .as("Anticorruption layer dependencies should be respected")
                    : Architectures.layeredArchitecture()
                    .layer("Anticorruption")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getAnticorruption() + "..")
                    .whereLayer("Anticorruption")
                    .mayNotBeAccessedByAnyLayer()
                    .as("Anticorruption layer dependencies should be respected"));

    public final ArchRule applicationLayerDependenciesMustBeRespected =
            ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                    Architectures.layeredArchitecture()
                    .layer("Application")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getApplication()))
                    .layer("Anticorruption")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getAnticorruption()))
                    .layer("Presentation")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getPresentation()))
                    .whereLayer("Application")
                    .mayOnlyBeAccessedByLayers("Anticorruption", "Presentation")
                    .as("Application layer dependencies should be respected")
                    : Architectures.layeredArchitecture()
                    .layer("Application")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getApplication() + "..")
                    .layer("Anticorruption")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getAnticorruption() + "..")
                    .layer("Presentation")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getPresentation() + "..")
                    .whereLayer("Application")
                    .mayOnlyBeAccessedByLayers("Anticorruption", "Presentation")
                    .as("Application layer dependencies should be respected"));

    public final ArchRule infrastructureLayerDependenciesMustBeRespected =
            ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                    Architectures.layeredArchitecture()
                    .layer("Infrastructure")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getInfrastructure()))
                    .layer("Anticorruption")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getAnticorruption()))
                    .layer("Application")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getApplication()))
                    .layer("Presentation")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getPresentation()))
                    .whereLayer("Infrastructure")
                    .mayOnlyBeAccessedByLayers("Anticorruption", "Application", "Presentation")
                    .as("Infrastructure layer dependencies should be respected")
                    : Architectures.layeredArchitecture()
                    .layer("Infrastructure")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getInfrastructure() + "..")
                    .layer("Anticorruption")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getAnticorruption() + "..")
                    .layer("Application")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getApplication() + "..")
                    .layer("Presentation")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getPresentation() + "..")
                    .whereLayer("Infrastructure")
                    .mayOnlyBeAccessedByLayers("Anticorruption", "Application", "Presentation")
                    .as("Infrastructure layer dependencies should be respected"));

    public final ArchRule presentationLayerDependenciesMustBeRespected =
            ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ?
                    Architectures.layeredArchitecture()
                    .layer("Presentation")
                    .definedBy(PackageUtils.getSubPackagesAnnotatedWith(
                            PluginConfiguration.getSyntax().getPackages().getPresentation()))
                    .whereLayer("Presentation")
                    .mayNotBeAccessedByAnyLayer()
                    .as("Presentation layer dependencies should be respected")
                    : Architectures.layeredArchitecture()
                    .layer("Presentation")
                    .definedBy(".." + PluginConfiguration.getSyntax().getPackages().getPresentation() + "..")
                    .whereLayer("Presentation")
                    .mayNotBeAccessedByAnyLayer()
                    .as("Presentation layer dependencies should be respected"));
}
