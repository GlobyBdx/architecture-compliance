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
import com.tngtech.archunit.lang.syntax.elements.GivenFields;
import com.tngtech.archunit.lang.syntax.elements.GivenMethods;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassRules;
import fr.ubordeaux.ddd.plugin.rules.utils.ClassUtils;
import fr.ubordeaux.ddd.plugin.rules.utils.FieldRules;
import fr.ubordeaux.ddd.plugin.rules.utils.MethodRules;
import fr.ubordeaux.ddd.plugin.rules.utils.MethodUtils;

public class ValueObjectRules {
    public final ArchRule valueObjectsMustResideInDomainLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getDomain());

    public final ArchRule valueObjectsMustNotAlsoBeAggregates =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate());

    public final ArchRule valueObjectsMustNotAlsoBeEntities =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule valueObjectsMustNotAlsoBeFactories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory());

    public final ArchRule valueObjectsMustNotAlsoBeRepositories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository());

    public final ArchRule valueObjectsMustNotAlsoBeServices =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule valueObjectsMustHaveAtLeastOneField =
            ClassRules.shouldHaveAtLeastOneField(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    false);

    public final ArchRule valueObjectsMustOverrideEqualsMethodAccessingAllFields =
            ClassRules.shouldOverrideEqualsMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    false);

    public final ArchRule valueObjectsMustOverrideHashCodeMethodAccessingAllFields =
            ClassRules.shouldOverrideHashCodeMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    false);

    public final ArchRule valueObjectsMustOverrideToStringMethodAccessingAllFields =
            ClassRules.shouldOverrideToStringMethodAccessingAllFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject(),
                    false);

    public final ArchRule valueObjectsFieldsMustBePrivate =
            FieldRules.shouldBePrivate(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            : (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))),
                    false,
                    null);

    public final ArchRule valueObjectsFieldsMustBeFinal =
            FieldRules.shouldBeFinal(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            : (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))),
                    false,
                    null);

    public final ArchRule valueObjectsFieldsMustBeImmutables =
            FieldRules.shouldBeImmutable(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            : (GivenFields)ArchRuleDefinition.fields()
                            .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))),
                    false,
                    null);

    public final ArchRule valueObjectsFieldsMustHavePrivateSetters =
            MethodRules.shouldBePrivate(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            (GivenMethods)ArchRuleDefinition.methods()
                            .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            .and(MethodUtils.accessAtLeastOneField(false))
                            : (GivenMethods)ArchRuleDefinition.methods()
                            .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            .and(MethodUtils.accessAtLeastOneField(false))),
                    PluginConfiguration.getSyntax().getMethods().areAnnotations(),
                    PluginConfiguration.getSyntax().getMethods().getSetter());

    public final ArchRule valueObjectsFieldsMustHaveSpecificGetters =
            MethodRules.shouldOnlyAccessImmutableFields(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            (GivenMethods)ArchRuleDefinition.methods()
                            .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            .and(MethodUtils.accessAtLeastOneField(false))
                            : (GivenMethods)ArchRuleDefinition.methods()
                            .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                    PluginConfiguration.getSyntax().getClasses().getValueObject()))
                            .and(MethodUtils.accessAtLeastOneField(false))),
                    PluginConfiguration.getSyntax().getMethods().areAnnotations(),
                    PluginConfiguration.getSyntax().getMethods().getGetter(),
                    false);

    public final ArchRule valueObjectsFieldsMustHaveSpecificAccesses =
            MethodRules.shouldOnlyAccessImmutableFields(
                    ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                            ((PluginConfiguration.getSyntax().getMethods().areAnnotations()) ?
                                    (GivenMethods)ArchRuleDefinition.methods()
                                    .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                                    .and(MethodUtils.areNotAnnotatedWith(
                                            PluginConfiguration.getSyntax().getMethods().getSetter()))
                                    .and(MethodUtils.areNotAnnotatedWith(
                                            PluginConfiguration.getSyntax().getMethods().getGetter()))
                                    .and(MethodUtils.accessAtLeastOneField(false))
                                    .and(MethodUtils.areNotObjectClassMethods)
                                    : (GivenMethods)ArchRuleDefinition.methods()
                                    .that().areDeclaredInClassesThat(ClassUtils.areAnnotatedWith(
                                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                                    .and(MethodUtils.areNotNamedWith(
                                            PluginConfiguration.getSyntax().getMethods().getSetter()))
                                    .and(MethodUtils.areNotNamedWith(
                                            PluginConfiguration.getSyntax().getMethods().getGetter()))
                                    .and(MethodUtils.accessAtLeastOneField(false))
                                    .and(MethodUtils.areNotObjectClassMethods))
                            : ((PluginConfiguration.getSyntax().getMethods().areAnnotations()) ?
                                    (GivenMethods)ArchRuleDefinition.methods()
                                    .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                                    .and(MethodUtils.areNotAnnotatedWith(
                                            PluginConfiguration.getSyntax().getMethods().getSetter()))
                                    .and(MethodUtils.areNotAnnotatedWith(
                                            PluginConfiguration.getSyntax().getMethods().getGetter()))
                                    .and(MethodUtils.accessAtLeastOneField(false))
                                    .and(MethodUtils.areNotObjectClassMethods)
                                    : (GivenMethods)ArchRuleDefinition.methods()
                                    .that().areDeclaredInClassesThat(ClassUtils.areNamedWith(
                                            PluginConfiguration.getSyntax().getClasses().getValueObject()))
                                    .and(MethodUtils.areNotNamedWith(
                                            PluginConfiguration.getSyntax().getMethods().getSetter()))
                                    .and(MethodUtils.areNotNamedWith(
                                            PluginConfiguration.getSyntax().getMethods().getGetter()))
                                    .and(MethodUtils.accessAtLeastOneField(false))
                                    .and(MethodUtils.areNotObjectClassMethods))),
                    false,
                    null,
                    false);
}
