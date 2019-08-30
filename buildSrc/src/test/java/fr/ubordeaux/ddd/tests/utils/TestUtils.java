/*
 * @author Benoit Faget
 */

package fr.ubordeaux.ddd.tests.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;

import com.google.common.collect.Sets;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import fr.ubordeaux.ddd.plugin.syntax.Classes;
import fr.ubordeaux.ddd.plugin.syntax.Fields;
import fr.ubordeaux.ddd.plugin.syntax.Methods;
import fr.ubordeaux.ddd.plugin.syntax.Packages;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public class TestUtils {
    public static List<Syntax> generateSyntaxes() {
        List<Syntax> syntaxes = new ArrayList<Syntax>();
        boolean[] areAnnotations = {true, false};
        for (int classes = 0; classes < areAnnotations.length; ++classes) {
            for (int fields = 0; fields < areAnnotations.length; ++fields) {
                for (int methods = 0; methods < areAnnotations.length; ++methods) {
                    for (int packages = 0; packages < areAnnotations.length; ++packages) {
                        syntaxes.add(generateSyntax(areAnnotations[classes], areAnnotations[fields],
                                areAnnotations[methods], areAnnotations[packages]));
                    }
                }
            }
        }
        return syntaxes;
    }

    public static Syntax generateSyntax(boolean areClassesAnnotations, boolean areFieldsAnnotations,
            boolean areMethodsAnnotations, boolean arePackagesAnnotations) {
        return new Syntax(
                ((areClassesAnnotations) ?
                        new Classes(
                                "fr.ubordeaux.ddd.annotations.classes.Aggregate",
                                "fr.ubordeaux.ddd.annotations.classes.Entity",
                                "fr.ubordeaux.ddd.annotations.classes.Factory",
                                "fr.ubordeaux.ddd.annotations.classes.Repository",
                                "fr.ubordeaux.ddd.annotations.classes.Service",
                                "fr.ubordeaux.ddd.annotations.classes.ValueObject",
                                areClassesAnnotations)
                        : new Classes(
                                "Aggregate",
                                "Entity",
                                "Factory",
                                "Repository",
                                "Service",
                                "ValueObject",
                                areClassesAnnotations)),
                ((areFieldsAnnotations) ?
                        new Fields(
                                "fr.ubordeaux.ddd.annotations.fields.EntityID",
                                "fr.ubordeaux.ddd.annotations.fields.Immutable",
                                areFieldsAnnotations)
                        : new Fields(
                                "[a-zA-Z]*Id",
                                "[a-zA-Z]*Imm",
                                areFieldsAnnotations)),
                ((areMethodsAnnotations) ?
                        new Methods(
                                "fr.ubordeaux.ddd.annotations.methods.Getter",
                                "fr.ubordeaux.ddd.annotations.methods.Setter",
                                areMethodsAnnotations)
                        : new Methods(
                                "get[a-zA-Z]*",
                                "set[a-zA-Z]*",
                                areMethodsAnnotations)),
                ((arePackagesAnnotations) ?
                        new Packages(
                                "fr.ubordeaux.ddd.annotations.packages.Anticorruption",
                                "fr.ubordeaux.ddd.annotations.packages.Application",
                                "fr.ubordeaux.ddd.annotations.packages.Domain",
                                "fr.ubordeaux.ddd.annotations.packages.Infrastructure",
                                "fr.ubordeaux.ddd.annotations.packages.Presentation",
                                arePackagesAnnotations)
                        : new Packages(
                                "anticorruption",
                                "application",
                                "domain",
                                "infrastructure",
                                "presentation",
                                arePackagesAnnotations)));
    }

    public static void assertRule(ArchRule rule, Class<?>[] classes, Class<?>... exceptions) {
        TestUtils.assertThatCodeExcept(rule, classes, exceptions);
        Assertions.assertThat(Assertions.catchThrowable(() -> {
            rule.check(TestUtils.computeClasses(exceptions));
        })).isNotNull();
    }

    public static void assertThatCodeExcept(ArchRule rule, Class<?>[] classes, Class<?>... exceptions) {
        Assertions.assertThatCode(() -> rule.check(computeClassesExcept(classes, exceptions)))
        .doesNotThrowAnyException();
    }

    public static JavaClasses computeClassesExcept(Class<?>[] classes, Class<?>... exceptions) {
        Set<Class<?>> classesSet = Sets.newHashSet(classes);
        Set<Class<?>> exceptionsSet = Sets.newHashSet(exceptions);
        classesSet.removeAll(exceptionsSet);
        return computeClasses(classesSet);
    }

    public static JavaClasses computeClasses(Class<?>... classes) {
        Set<Class<?>> classesSet = Sets.newHashSet(classes);
        return computeClasses(classesSet);
    }

    public static JavaClasses computeClasses(Set<Class<?>> classesSet) {
        return new ClassFileImporter().importClasses(classesSet);
    }
}
