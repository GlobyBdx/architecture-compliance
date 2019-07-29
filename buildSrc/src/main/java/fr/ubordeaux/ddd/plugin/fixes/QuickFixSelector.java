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

package fr.ubordeaux.ddd.plugin.fixes;

import java.util.HashMap;
import java.util.Map;

import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public final class QuickFixSelector {
    private final Syntax syntax;
    private final Map<String, QuickFix[]> sets;

    public QuickFixSelector(Syntax syntax) {
        this.syntax = syntax;
        this.sets = new HashMap<>();
        this.update();
    }

    public QuickFix[] select(String rule) {
        return this.sets.get(rule);
    }

    public void update() {
        this.sets.clear();

        this.sets.put("aggregatesMustAlsoBeEntities",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationInclusion(this.syntax.getClasses().getEntity()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                        : this.group());

        this.sets.put("aggregatesMustResideInDomainLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                                : this.group(new PackageAnnotationInclusion(this.syntax.getPackages().getDomain())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                                : this.group());

        this.sets.put("aggregatesMustNotAlsoBeFactories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                        : this.group());

        this.sets.put("aggregatesMustNotAlsoBeRepositories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                        : this.group());

        this.sets.put("aggregatesMustNotAlsoBeServices",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                        : this.group());

        this.sets.put("aggregatesMustNotAlsoBeValueObjects",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()))
                        : this.group());

        this.sets.put("entitiesMustResideInDomainLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                                : this.group(new PackageAnnotationInclusion(this.syntax.getPackages().getDomain())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                                : this.group());

        this.sets.put("entitiesMustNotAlsoBeFactories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                        : this.group());

        this.sets.put("entitiesMustNotAlsoBeRepositories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                        : this.group());

        this.sets.put("entitiesMustNotAlsoBeServices",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                        : this.group());

        this.sets.put("entitiesMustNotAlsoBeValueObjects",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()))
                        : this.group());

        this.sets.put("entityIdsMustOnlyBeDeclaredInEntities",
                (this.syntax.getFields().areAnnotations()) ?
                        this.group(new FieldAnnotationRemoval(this.syntax.getFields().getEntityId()))
                        : this.group());

        this.sets.put("entityIdsMustBePrivate",
                (this.syntax.getFields().areAnnotations()) ?
                        this.group(
                                new PrivateFieldModifier(),
                                new FieldAnnotationRemoval(this.syntax.getFields().getEntityId()))
                        : this.group(new PrivateFieldModifier()));

        this.sets.put("entityIdsMustBeFinal",
                (this.syntax.getFields().areAnnotations()) ?
                        this.group(
                                new FinalFieldModifier(),
                                new FieldAnnotationRemoval(this.syntax.getFields().getEntityId()))
                        : this.group(new FinalFieldModifier()));

        this.sets.put("entityIdsMustBeImmutables",
                (this.syntax.getFields().areAnnotations()) ?
                        this.group(
                                new FieldAnnotationInclusion(this.syntax.getFields().getImmutable()),
                                new FieldAnnotationRemoval(this.syntax.getFields().getEntityId()))
                        : this.group());

        this.sets.put("entityIdsMustHavePrivateSetters",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationRemoval(this.syntax.getMethods().getSetter()))
                        : this.group(new PrivateMethodModifier()));

        this.sets.put("entityIdsMustHaveSpecificGetters",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationRemoval(this.syntax.getMethods().getGetter()))
                        : this.group(new PrivateMethodModifier()));

        this.sets.put("entityIdsMustHaveSpecificAccesses",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationInclusion(this.syntax.getMethods().getSetter()),
                                new MethodAnnotationInclusion(this.syntax.getMethods().getGetter()))
                        : this.group(new PrivateMethodModifier()));

        this.sets.put("factoriesMustResideInDomainLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                                : this.group(new PackageAnnotationInclusion(this.syntax.getPackages().getDomain())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                                : this.group());

        this.sets.put("factoriesMustNotAlsoBeAggregates",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                        : this.group());

        this.sets.put("factoriesMustNotAlsoBeEntities",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                        : this.group());

        this.sets.put("factoriesMustNotAlsoBeRepositories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                        : this.group());

        this.sets.put("factoriesMustNotAlsoBeServices",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                        : this.group());

        this.sets.put("factoriesMustNotAlsoBeValueObjects",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()))
                        : this.group());

        this.sets.put("repositoriesMustResideInInfrastructureLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getInfrastructure()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                                : this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getInfrastructure())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                                : this.group());

        this.sets.put("repositoriesMustNotAlsoBeAggregates",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                        : this.group());

        this.sets.put("repositoriesMustNotAlsoBeEntities",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                        : this.group());

        this.sets.put("repositoriesMustNotAlsoBeFactories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                        : this.group());

        this.sets.put("repositoriesMustNotAlsoBeServices",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                        : this.group());

        this.sets.put("repositoriesMustNotAlsoBeValueObjects",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()))
                        : this.group());

        this.sets.put("servicesMustResideInApplicationDomainInfrastructureLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getApplication()),
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getInfrastructure()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                                : this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getApplication()),
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getInfrastructure())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                                : this.group());

        this.sets.put("servicesMustNotAlsoBeAggregates",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                        : this.group());

        this.sets.put("servicesMustNotAlsoBeEntities",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                        : this.group());

        this.sets.put("servicesMustNotAlsoBeFactories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                        : this.group());

        this.sets.put("servicesMustNotAlsoBeRepositories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                        : this.group());

        this.sets.put("servicesMustNotAlsoBeValueObjects",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()))
                        : this.group());

        this.sets.put("servicesFieldsMustBeFinal", this.group(new FinalFieldsModifier()));

        this.sets.put("valueObjectsMustResideInDomainLayer",
                (this.syntax.getPackages().areAnnotations()) ?
                        ((this.syntax.getClasses().areAnnotations()) ?
                                this.group(
                                        new PackageAnnotationInclusion(this.syntax.getPackages().getDomain()),
                                        new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                                : this.group(new PackageAnnotationInclusion(this.syntax.getPackages().getDomain())))
                        : (this.syntax.getClasses().areAnnotations()) ?
                                this.group(new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                                : this.group());

        this.sets.put("valueObjectsMustNotAlsoBeAggregates",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getAggregate()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                        : this.group());

        this.sets.put("valueObjectsMustNotAlsoBeEntities",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getEntity()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                        : this.group());

        this.sets.put("valueObjectsMustNotAlsoBeFactories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getFactory()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                        : this.group());

        this.sets.put("valueObjectsMustNotAlsoBeRepositories",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getRepository()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                        : this.group());

        this.sets.put("valueObjectsMustNotAlsoBeServices",
                (this.syntax.getClasses().areAnnotations()) ?
                        this.group(
                                new ClassAnnotationRemoval(this.syntax.getClasses().getService()),
                                new ClassAnnotationRemoval(this.syntax.getClasses().getValueObject()))
                        : this.group());

        this.sets.put("valueObjectsFieldsMustBePrivate", this.group(new PrivateFieldModifier()));

        this.sets.put("valueObjectsFieldsMustBeFinal", this.group(new FinalFieldModifier()));

        this.sets.put("valueObjectsFieldsMustBeImmutables",
                (this.syntax.getFields().areAnnotations()) ?
                        this.group(new FieldAnnotationInclusion(this.syntax.getFields().getImmutable()))
                        : this.group());

        this.sets.put("valueObjectsFieldsMustHavePrivateSetters",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationRemoval(this.syntax.getMethods().getSetter()))
                        : this.group(new PrivateMethodModifier()));

        this.sets.put("valueObjectsFieldsMustHaveSpecificGetters",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationRemoval(this.syntax.getMethods().getGetter()))
                        : this.group(new PrivateMethodModifier()));

        this.sets.put("valueObjectsFieldsMustHaveSpecificAccesses",
                (this.syntax.getMethods().areAnnotations()) ?
                        this.group(
                                new PrivateMethodModifier(),
                                new MethodAnnotationInclusion(this.syntax.getMethods().getSetter()),
                                new MethodAnnotationInclusion(this.syntax.getMethods().getGetter()))
                        : this.group(new PrivateMethodModifier()));
    }

    private QuickFix[] group(QuickFix... fixes) {
        return fixes;
    }
}
