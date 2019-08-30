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
import fr.ubordeaux.ddd.plugin.rules.utils.FieldUtils;

public class AggregateRules {
    public final ArchRule aggregatesMustAlsoBeEntities =
            ClassRules.shouldAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule aggregatesMustResideInDomainLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getDomain());

    public final ArchRule aggregatesMustNotAlsoBeFactories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory());

    public final ArchRule aggregatesMustNotAlsoBeRepositories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository());

    public final ArchRule aggregatesMustNotAlsoBeServices =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule aggregatesMustNotAlsoBeValueObjects =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject());

    public final ArchRule aggregatesMustHaveAtLeastOneEntityId =
            ClassRules.shouldHaveAtLeastOneField(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule aggregatesMustOverrideEqualsMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideEqualsMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule aggregatesMustOverrideHashCodeMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideHashCodeMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule aggregatesMustOverrideToStringMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideToStringMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule aggregatesInternalObjectsMustOnlyBeExternallyAccessedFromOwnerAggregate =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.fields()
                    .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .and(FieldUtils.areImmutables)
                    .should(FieldUtils.notBeExternallyAccessed)
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getAggregate()
                            + " internal objects should only be externally accessed from owner class")
                    : ArchRuleDefinition.fields()
                    .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .and(FieldUtils.areImmutables)
                    .should(FieldUtils.notBeExternallyAccessed)
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getAggregate()
                            + " internal objects should only be externally accessed from owner class"));

    public final ArchRule aggregatesInternalObjectsMustOnlyBeExternallyInstantiatedByOwnerAggregateFactory =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.fields()
                    .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .and(FieldUtils.areImmutables)
                    .should(FieldUtils.notBeExternallyInstantiated)
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getAggregate()
                            + " internal objects should only be externally instantiated by owner class factory")
                    : ArchRuleDefinition.fields()
                    .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                            PluginConfiguration.getSyntax().getClasses().getAggregate()))
                    .and(FieldUtils.areImmutables)
                    .should(FieldUtils.notBeExternallyInstantiated)
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getAggregate()
                            + " internal objects should only be externally instantiated by owner class factory"));
}
