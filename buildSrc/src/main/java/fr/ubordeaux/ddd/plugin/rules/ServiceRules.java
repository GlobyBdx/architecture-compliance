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

public class ServiceRules {
    public final ArchRule servicesMustResideInApplicationDomainInfrastructureLayer =
            ClassRules.shouldResideInAnyPackage(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getPackages().areAnnotations(),
                    PluginConfiguration.getSyntax().getPackages().getApplication(),
                    PluginConfiguration.getSyntax().getPackages().getDomain(),
                    PluginConfiguration.getSyntax().getPackages().getInfrastructure());

    public final ArchRule servicesMustNotAlsoBeAggregates =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getAggregate());

    public final ArchRule servicesMustNotAlsoBeEntities =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getEntity());

    public final ArchRule servicesMustNotAlsoBeFactories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getFactory());

    public final ArchRule servicesMustNotAlsoBeRepositories =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getRepository());

    public final ArchRule servicesMustNotAlsoBeValueObjects =
            ClassRules.shouldNotAlsoBe(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getValueObject());

    public final ArchRule servicesFieldsMustBeFinal =
            ClassRules.shouldHaveOnlyFinalFields(
                    ArchRuleDefinition.classes(),
                    PluginConfiguration.getSyntax().getClasses().areAnnotations(),
                    PluginConfiguration.getSyntax().getClasses().getService());

    public final ArchRule servicesMustImplementInterfaceInSameLayer =
            ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                    ArchRuleDefinition.classes()
                    .that(ClassUtils.areAnnotatedWith(PluginConfiguration.getSyntax().getClasses().getService()))
                    .should(ClassUtils.implementAnInterfaceInTheSameLayer)
                    .as("Classes annotated with " + PluginConfiguration.getSyntax().getClasses().getService()
                            + " should implement an interface which reside in the same layer")
                    : ArchRuleDefinition.classes()
                    .that(ClassUtils.areNamedWith(PluginConfiguration.getSyntax().getClasses().getService()))
                    .should(ClassUtils.implementAnInterfaceInTheSameLayer)
                    .as("Classes named with " + PluginConfiguration.getSyntax().getClasses().getService()
                            + " should implement an interface which reside in the same layer"));
}
