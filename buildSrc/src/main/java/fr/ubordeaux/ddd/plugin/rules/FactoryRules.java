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

public class FactoryRules {
    public final ArchRule factoriesMustResideInDomainLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getDomain());

    public final ArchRule factoriesMustNotAlsoBeAggregates =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate());

    public final ArchRule factoriesMustNotAlsoBeEntities =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule factoriesMustNotAlsoBeRepositories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository());

    public final ArchRule factoriesMustNotAlsoBeServices =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule factoriesMustNotAlsoBeValueObjects =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject());

    public final ArchRule factoriesMustHaveNonPrivateMethodsWithSameReturnType =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.classes()
                    .that(ClassUtils.areAnnotatedWith(PluginConfiguration.getSyntax().getClasses().getFactory()))
                    .should(ClassUtils.haveNonPrivateMethodsWithTheSameReturnType)
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getFactory()
                            + " should have non-private methods with the same return type")
                    : ArchRuleDefinition.classes()
                    .that(ClassUtils.areNamedWith(PluginConfiguration.getSyntax().getClasses().getFactory()))
                    .should(ClassUtils.haveNonPrivateMethodsWithTheSameReturnType)
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getFactory()
                            + " should have non-private methods with the same return type"));

    public final ArchRule factoriesMustAccessAtLeastOneConstructorFromEntityValueObject =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.classes()
                    .that(ClassUtils.areAnnotatedWith(PluginConfiguration.getSyntax().getClasses().getFactory()))
                    .should(ClassUtils.accessAtLeastOneConstructorFromAnyClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getEntity()))
                    .orShould(ClassUtils.accessAtLeastOneConstructorFromAnyClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getFactory()
                            + " should access at least one constructor from a class annotated with "
                            + PluginConfiguration.getSyntax().getClasses().getEntity() + " or "
                            + PluginConfiguration.getSyntax().getClasses().getValueObject())
                    : ArchRuleDefinition.classes()
                    .that(ClassUtils.areNamedWith(PluginConfiguration.getSyntax().getClasses().getFactory()))
                    .should(ClassUtils.accessAtLeastOneConstructorFromAnyClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getEntity()))
                    .orShould(ClassUtils.accessAtLeastOneConstructorFromAnyClass(
                            PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getFactory()
                            + " should access at least one constructor from a class named with "
                            + PluginConfiguration.getSyntax().getClasses().getEntity() + " or "
                            + PluginConfiguration.getSyntax().getClasses().getValueObject()));
}
