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
import com.tngtech.archunit.lang.syntax.elements.GivenMethods;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassRules;
import fr.ubordeaux.ddd.plugin.rules.utils.FieldRules;
import fr.ubordeaux.ddd.plugin.rules.utils.MethodRules;
import fr.ubordeaux.ddd.plugin.rules.utils.MethodUtils;

public class EntityRules {
    public final ArchRule entitiesMustResideInDomainLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getDomain());

    public final ArchRule entitiesMustNotAlsoBeFactories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory());

    public final ArchRule entitiesMustNotAlsoBeRepositories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository());

    public final ArchRule entitiesMustNotAlsoBeServices =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule entitiesMustNotAlsoBeValueObjects =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject());

    public final ArchRule entitiesMustHaveAtLeastOneEntityId =
            ClassRules.shouldHaveAtLeastOneField(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entitiesMustOverrideEqualsMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideEqualsMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entitiesMustOverrideHashCodeMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideHashCodeMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entitiesMustOverrideToStringMethodAccessingAllEntityIds =
            ClassRules.shouldOverrideToStringMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entityIdsMustOnlyBeDeclaredInEntities =
            FieldRules.shouldOnlyBeDeclaredInClasses(
                    ArchRuleDefinition.fields(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule entityIdsMustOnlyBeDeclaredInAggregatesEntities =
            FieldRules.shouldOnlyBeDeclaredInClasses(
                    ArchRuleDefinition.fields(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule entityIdsMustBePrivate =
            FieldRules.shouldBePrivate(
                    ArchRuleDefinition.fields(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entityIdsMustBeFinal =
            FieldRules.shouldBeFinal(
                    ArchRuleDefinition.fields(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entityIdsMustBeImmutables =
            FieldRules.shouldBeImmutable(
                    ArchRuleDefinition.fields(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entityIdsMustHavePrivateSetters =
            MethodRules.shouldBePrivate(
                    (GivenMethods)ArchRuleDefinition.methods()
                    .that(MethodUtils.accessAtLeastOneField(
                            PluginConfiguration.getSyntax().getFields().areAnnotations(),
                            PluginConfiguration.getSyntax().getFields().getEntityId())),
                    PluginConfiguration.getSyntax().getMethods().areAnnotations(),
                    PluginConfiguration.getSyntax().getMethods().getSetter());

    public final ArchRule entityIdsMustHaveSpecificGetters =
            MethodRules.shouldOnlyAccessImmutableFields(
                    (GivenMethods)ArchRuleDefinition.methods()
                    .that(MethodUtils.accessAtLeastOneField(
                            PluginConfiguration.getSyntax().getFields().areAnnotations(),
                            PluginConfiguration.getSyntax().getFields().getEntityId())),
                    PluginConfiguration.getSyntax().getMethods().areAnnotations(),
                    PluginConfiguration.getSyntax().getMethods().getGetter(),
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());

    public final ArchRule entityIdsMustHaveSpecificAccesses =
            MethodRules.shouldOnlyAccessImmutableFields(
                    ((PluginConfiguration.getSyntax().getMethods().areAnnotations()) ?
                            (GivenMethods)ArchRuleDefinition.methods()
                            .that(MethodUtils.areNotAnnotatedWith(
                                    PluginConfiguration.getSyntax().getMethods().getSetter()))
                            .and(MethodUtils.areNotAnnotatedWith(
                                    PluginConfiguration.getSyntax().getMethods().getGetter()))
                            .and(MethodUtils.accessAtLeastOneField(
                                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                                    PluginConfiguration.getSyntax().getFields().getEntityId()))
                            .and(MethodUtils.areNotObjectClassMethods)
                            : (GivenMethods)ArchRuleDefinition.methods()
                            .that(MethodUtils.areNotNamedWith(
                                    PluginConfiguration.getSyntax().getMethods().getSetter()))
                            .and(MethodUtils.areNotNamedWith(
                                    PluginConfiguration.getSyntax().getMethods().getGetter()))
                            .and(MethodUtils.accessAtLeastOneField(
                                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                                    PluginConfiguration.getSyntax().getFields().getEntityId()))
                            .and(MethodUtils.areNotObjectClassMethods)),
                    false,
                    null,
                    PluginConfiguration.getSyntax().getFields().areAnnotations(),
                    PluginConfiguration.getSyntax().getFields().getEntityId());
}
