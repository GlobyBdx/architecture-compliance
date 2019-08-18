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

import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassRules;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassUtils;

public class RepositoryRules {
    public final ArchRule repositoriesMustResideInInfrastructureLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure());

    public final ArchRule repositoriesMustNotAlsoBeAggregates =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate());

    public final ArchRule repositoriesMustNotAlsoBeEntities =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule repositoriesMustNotAlsoBeFactories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory());

    public final ArchRule repositoriesMustNotAlsoBeServices =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule repositoriesMustNotAlsoBeValueObjects =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject());

    public final ArchRule repositoriesMustImplementInterfaceInDomainLayer =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.classes()
                    .that(ClassUtils.areAnnotatedWith(PluginConfiguration.getSyntax().getClasses().getRepository()))
                    .should(ClassUtils.implementAnInterfaceInAnyPackage(
                            PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                            PluginConfiguration.getSyntax().getPackages().getDomain()))
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getRepository()
                            + " should implement an interface which reside in a package "
                            + ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ? "annotated" : "named")
                            + " with " + PluginConfiguration.getSyntax().getPackages().getDomain())
                    : ArchRuleDefinition.classes()
                    .that(ClassUtils.areNamedWith(PluginConfiguration.getSyntax().getClasses().getRepository()))
                    .should(ClassUtils.implementAnInterfaceInAnyPackage(
                            PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                            PluginConfiguration.getSyntax().getPackages().getDomain()))
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getRepository()
                            + " should implement an interface which reside in a package "
                            + ((PluginConfiguration.getSyntax().getPackages().areAnnotations()) ? "annotated" : "named")
                            + " with " + PluginConfiguration.getSyntax().getPackages().getDomain()));

    public final ArchRule repositoriesMustAccessAtLeastOneAggregateEntityValueObject =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.classes()
                    .that(ClassUtils.areAnnotatedWith(PluginConfiguration.getSyntax().getClasses().getRepository()))
                    .should(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .orShould(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getEntity()))
                    .orShould(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getRepository()
                            + " should access at least one class annotated with "
                            + PluginConfiguration.getSyntax().getClasses().getEntity() + " or "
                            + PluginConfiguration.getSyntax().getClasses().getValueObject())
                    : ArchRuleDefinition.classes()
                    .that(ClassUtils.areNamedWith(PluginConfiguration.getSyntax().getClasses().getRepository()))
                    .should(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .orShould(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getEntity()))
                    .orShould(ClassUtils.accessAtLeastOneClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getRepository()
                            + " should access at least one class named with "
                            + PluginConfiguration.getSyntax().getClasses().getEntity() + " or "
                            + PluginConfiguration.getSyntax().getClasses().getValueObject()));
}
