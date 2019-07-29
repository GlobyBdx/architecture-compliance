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

package fr.ubordeaux.ddd.plugin.rules.utils;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenClasses;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;

import static fr.ubordeaux.ddd.plugin.rules.utils.ClassUtils.*;

import java.util.Arrays;

public final class ClassRules {
    public static String getThatDescription(boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder();
        description.append("Classes");
        if (classSyntax != null) {
            description.append(" that are " + ((isAnnotation) ? "annotated" : "named") + " with " + classSyntax);
        }
        return description.toString();
    }

    public static GivenClassesConjunction thatAre(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        if (classSyntax == null) {
            return (GivenClassesConjunction)classes;
        }
        if (!isAnnotation) {
            return classes.that(areNamedWith(classSyntax));
        }
        return classes.that(areAnnotatedWith(classSyntax));
    }

    public static ArchRule shouldBePublic(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be public");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().bePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePublic(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be public");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notBePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeProtected(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be protected");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().beProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeProtected(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be protected");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notBeProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldBePrivate(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be private");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().bePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePrivate(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be private");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notBePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeFinal(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be final");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().haveModifier(JavaModifier.FINAL);
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeFinal(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be final");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notHaveModifier(JavaModifier.FINAL);
        return should.as(description.toString());
    }

    public static ArchRule shouldBeAbstract(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be abstract");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().haveModifier(JavaModifier.ABSTRACT);
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeAbstract(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be abstract");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notHaveModifier(JavaModifier.ABSTRACT);
        return should.as(description.toString());
    }

    public static ArchRule shouldBeInterfaces(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should be interfaces");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().beInterfaces();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeInterfaces(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not be interfaces");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().notBeInterfaces();
        return should.as(description.toString());
    }

    public static ArchRule shouldHaveOnlyFinalFields(GivenClasses classes, boolean isAnnotation, String classSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should have only final fields");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should().haveOnlyFinalFields();
        return should.as(description.toString());
    }

    public static ArchRule shouldResideInAnyPackage(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... packageSyntaxes) {
        if (packageSyntaxes.length == 0) return null;
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should reside in " + ((packageSyntaxes.length != 1) ? "any package " : "a package "));
        description.append(((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(packageSyntaxes));
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = ((areAnnotations) ?
                that.should(resideInAPackageAnnotatedWith(packageSyntaxes[0]))
                : that.should().resideInAPackage(".." + packageSyntaxes[0] + ".."));
        for (int index = 1; index < packageSyntaxes.length; ++index) {
            should = ((areAnnotations) ?
                    should.orShould(resideInAPackageAnnotatedWith(packageSyntaxes[index]))
                    : should.orShould().resideInAPackage(".." + packageSyntaxes[index] + ".."));
        }
        return should.as(description.toString());
    }

    public static ArchRule shouldResideOutsideOfPackages(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... packageSyntaxes) {
        if (packageSyntaxes.length == 0) return null;
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should reside outside of " + ((packageSyntaxes.length > 1) ? "packages " : "package "));
        description.append(((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(packageSyntaxes));
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = ((areAnnotations) ?
                that.should(resideOutsideOfPackageAnnotatedWith(packageSyntaxes[0]))
                : that.should().resideOutsideOfPackage(".." + packageSyntaxes[0] + ".."));
        for (int index = 1; index < packageSyntaxes.length; ++index) {
            should = ((areAnnotations) ?
                    should.andShould(resideOutsideOfPackageAnnotatedWith(packageSyntaxes[index]))
                    : should.andShould().resideOutsideOfPackage(".." + packageSyntaxes[index] + ".."));
        }
        return should.as(description.toString());
    }

    public static ArchRule shouldAlsoBe(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... classSyntaxes) {
        if (classSyntaxes.length == 0) return null;
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should also be ");
        description.append(((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(classSyntaxes));
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = ((areAnnotations) ?
                that.should(beAnnotatedWith(classSyntaxes[0]))
                : that.should(beNamedWith(classSyntaxes[0])));
        for (int index = 1; index < classSyntaxes.length; ++index) {
            should = ((areAnnotations) ?
                    should.orShould(beAnnotatedWith(classSyntaxes[index]))
                    : should.orShould(beNamedWith(classSyntaxes[index])));
        }
        return should.as(description.toString());
    }

    public static ArchRule shouldNotAlsoBe(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... classSyntaxes) {
        if (classSyntaxes.length == 0) return null;
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should not also be ");
        description.append(((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(classSyntaxes));
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = ((areAnnotations) ?
                that.should(notBeAnnotatedWith(classSyntaxes[0]))
                : that.should(notBeNamedWith(classSyntaxes[0])));
        for (int index = 1; index < classSyntaxes.length; ++index) {
            should = ((areAnnotations) ?
                    should.andShould(notBeAnnotatedWith(classSyntaxes[index]))
                    : should.andShould(notBeNamedWith(classSyntaxes[index])));
        }
        return should.as(description.toString());
    }

    public static ArchRule shouldHaveAtLeastOneField(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... fieldSyntaxes) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should have at least one field");
        description.append((fieldSyntaxes.length != 0) ?
                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(fieldSyntaxes)
                : "");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = ((fieldSyntaxes.length > 0) ?
                that.should(haveAtLeastOneField(areAnnotations, fieldSyntaxes[0]))
                : that.should(haveAtLeastOneField));
        for (int index = 1; index < fieldSyntaxes.length; ++index) {
            should = should.orShould(haveAtLeastOneField(areAnnotations, fieldSyntaxes[index]));
        }
        return should.as(description.toString());
    }

    public static ArchRule shouldOverrideEqualsMethodAccessingAllFields(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... fieldSyntaxes) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should override equals method accessing all fields");
        description.append((fieldSyntaxes.length != 0) ?
                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(fieldSyntaxes)
                : "");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should(
                overrideEqualsMethodAccessingAllFields(areAnnotations, fieldSyntaxes));
        return should.as(description.toString());
    }

    public static ArchRule shouldOverrideHashCodeMethodAccessingAllFields(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... fieldSyntaxes) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should override hashCode method accessing all fields");
        description.append((fieldSyntaxes.length != 0) ?
                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(fieldSyntaxes)
                : "");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should(
                overrideHashCodeMethodAccessingAllFields(areAnnotations, fieldSyntaxes));
        return should.as(description.toString());
    }

    public static ArchRule shouldOverrideToStringMethodAccessingAllFields(GivenClasses classes,
            boolean isAnnotation, String classSyntax, boolean areAnnotations, String... fieldSyntaxes) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, classSyntax));
        description.append(" should override toString method accessing all fields");
        description.append((fieldSyntaxes.length != 0) ?
                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(fieldSyntaxes)
                : "");
        GivenClassesConjunction that = thatAre(classes, isAnnotation, classSyntax);
        ClassesShouldConjunction should = that.should(
                overrideToStringMethodAccessingAllFields(areAnnotations, fieldSyntaxes));
        return should.as(description.toString());
    }
}
