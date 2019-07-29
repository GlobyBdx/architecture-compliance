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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public final class ClassUtils {
    public static boolean isAnnotatedWith(JavaClass owner, String annotation) {
        if (PluginConfiguration.getDescription() == null) {
            return owner.isAnnotatedWith(annotation);
        }
        if (PluginConfiguration.getDescription().getClasses().containsKey(annotation)) {
            return PluginConfiguration.getDescription().getClasses().get(annotation).contains(owner.getFullName());
        }
        return false;
    }

    public static boolean isNamedWith(JavaClass owner, String name) {
        return owner.getSimpleName().contains(name);
    }

    public static DescribedPredicate<JavaClass> areAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaClass>("are annotated with " + annotation) {
            @Override
            public boolean apply(JavaClass item) {
                return isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaClass> areNotAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaClass>("are not annotated with " + annotation) {
            @Override
            public boolean apply(JavaClass item) {
                return !isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaClass> areNamedWith(String name) {
        return new DescribedPredicate<JavaClass>("are named with " + name) {
            @Override
            public boolean apply(JavaClass item) {
                return isNamedWith(item, name);
            }
        };
    }

    public static DescribedPredicate<JavaClass> areNotNamedWith(String name) {
        return new DescribedPredicate<JavaClass>("are not named with " + name) {
            @Override
            public boolean apply(JavaClass item) {
                return !isNamedWith(item, name);
            }
        };
    }

    public static ArchCondition<JavaClass> beAnnotatedWith(String annotation) {
        return new ArchCondition<JavaClass>("be annotated with " + annotation) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (!isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is not annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaClass> notBeAnnotatedWith(String annotation) {
        return new ArchCondition<JavaClass>("not be annotated with " + annotation) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaClass> beNamedWith(String name) {
        return new ArchCondition<JavaClass>("be named with " + name) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (!isNamedWith(item, name)) {
                    String message = item.getDescription() + " is not named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaClass> notBeNamedWith(String name) {
        return new ArchCondition<JavaClass>("not be named with " + name) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (isNamedWith(item, name)) {
                    String message = item.getDescription() + " is named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static boolean isInAnyPackage(JavaClass item, boolean isAnnotation, String syntax) {
        String[] names = ((isAnnotation) ?
                PackageUtils.getPackagesAnnotatedWith(syntax)
                : PackageUtils.getPackagesNamedWith(syntax));
        for (String name : names) {
            if (item.getPackageName().startsWith(name)) {
                return true;
            }
        }
        return false;
    }

    public static ArchCondition<JavaClass> resideInAPackageAnnotatedWith(String annotation) {
        return new ArchCondition<JavaClass>("reside in a package annotated with " + annotation) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (!isInAnyPackage(item, true, annotation)) {
                    String message = item.getDescription() + " does reside outside of package annotated with "
                            + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaClass> resideOutsideOfPackageAnnotatedWith(String annotation) {
        return new ArchCondition<JavaClass>("reside outside of package annotated with " + annotation) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (isInAnyPackage(item, true, annotation)) {
                    String message = item.getDescription() + " does reside in a package annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static final ArchCondition<JavaClass> haveAtLeastOneField = haveAtLeastOneField(false, new String[0]);

    public static ArchCondition<JavaClass> haveAtLeastOneField(boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("have at least one field"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                if (item.getFields().isEmpty()) {
                    String message = item.getDescription() + " does not have any field";
                    events.add(SimpleConditionEvent.violated(item, message));
                    return;
                }
                if (syntaxes.length == 0) {
                    return;
                }
                for (JavaField field : item.getFields()) {
                    for (String syntax : syntaxes) {
                        if (areAnnotations && FieldUtils.isAnnotatedWith(field, syntax)) {
                            return;
                        }
                        if (!areAnnotations && FieldUtils.isNamedWith(field, syntax)) {
                            return;
                        }
                    }
                }
                String message = item.getDescription() + " does not have any field "
                        + ((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(syntaxes);
                events.add(SimpleConditionEvent.violated(item, message));
            }
        };
    }

    public static final ArchCondition<JavaClass> overrideEqualsMethodAccessingAllFields =
            overrideEqualsMethodAccessingAllFields(false, new String[0]);

    public static ArchCondition<JavaClass> overrideEqualsMethodAccessingAllFields(
            boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("override equals method accessing all fields"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                Set<JavaField> fields = (syntaxes.length == 0) ? item.getFields() : new HashSet<>();
                for (String syntax : syntaxes) {
                    fields.addAll(((areAnnotations) ?
                            FieldUtils.getFieldsAnnotatedWith(item.getFields(), syntax)
                            : FieldUtils.getFieldsNamedWith(item.getFields(), syntax)));
                }
                for (JavaMethod method : item.getMethods()) {
                    if (MethodUtils.isEqualsMethod(method)) {
                        if (fields.isEmpty() || FieldUtils.areAccessedByMethod(fields, method)) {
                            return;
                        }
                        break;
                    }
                }
                String message = item.getDescription() + " does not override equals method accessing all fields"
                        + ((syntaxes.length != 0) ?
                                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                                : "");
                events.add(SimpleConditionEvent.violated(item, message));
            }
        };
    }

    public static final ArchCondition<JavaClass> overrideHashCodeMethodAccessingAllFields =
            overrideHashCodeMethodAccessingAllFields(false, new String[0]);

    public static ArchCondition<JavaClass> overrideHashCodeMethodAccessingAllFields(
            boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("override hashCode method accessing all fields"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                Set<JavaField> fields = (syntaxes.length == 0) ? item.getFields() : new HashSet<>();
                for (String syntax : syntaxes) {
                    fields.addAll(((areAnnotations) ?
                            FieldUtils.getFieldsAnnotatedWith(item.getFields(), syntax)
                            : FieldUtils.getFieldsNamedWith(item.getFields(), syntax)));
                }
                for (JavaMethod method : item.getMethods()) {
                    if (MethodUtils.isHashCodeMethod(method)) {
                        if (fields.isEmpty() || FieldUtils.areAccessedByMethod(fields, method)) {
                            return;
                        }
                        break;
                    }
                }
                String message = item.getDescription() + " does not override hashCode method accessing all fields"
                        + ((syntaxes.length != 0) ?
                                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                                : "");
                events.add(SimpleConditionEvent.violated(item, message));
            }
        };
    }

    public static final ArchCondition<JavaClass> overrideToStringMethodAccessingAllFields =
            overrideToStringMethodAccessingAllFields(false, new String[0]);

    public static ArchCondition<JavaClass> overrideToStringMethodAccessingAllFields(
            boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("override toString method accessing all fields"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                Set<JavaField> fields = (syntaxes.length == 0) ? item.getFields() : new HashSet<>();
                for (String syntax : syntaxes) {
                    fields.addAll(((areAnnotations) ?
                            FieldUtils.getFieldsAnnotatedWith(item.getFields(), syntax)
                            : FieldUtils.getFieldsNamedWith(item.getFields(), syntax)));
                }
                for (JavaMethod method : item.getMethods()) {
                    if (MethodUtils.isToStringMethod(method)) {
                        if (fields.isEmpty() || FieldUtils.areAccessedByMethod(fields, method)) {
                            return;
                        }
                        break;
                    }
                }
                String message = item.getDescription() + " does not override toString method accessing all fields"
                        + ((syntaxes.length != 0) ?
                                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                                : "");
                events.add(SimpleConditionEvent.violated(item, message));
            }
        };
    }

    public static ArchCondition<JavaClass> accessAtLeastOneClass(boolean areAnnotations, String... syntaxes) {
        return accessAtLeastOneMethodFromAnyClass(null, areAnnotations, syntaxes);
    }

    public static ArchCondition<JavaClass> accessAtLeastOneConstructorFromAnyClass(
            boolean areAnnotations, String... syntaxes) {
        return accessAtLeastOneMethodFromAnyClass("<init>", areAnnotations, syntaxes);
    }

    public static ArchCondition<JavaClass> accessAtLeastOneMethodFromAnyClass(
            String name, boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("access at least one class "
                + ((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(syntaxes)
                + ((name != null) ? " and its member " + name : "")) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                for (JavaAccess<?> access : item.getAccessesFromSelf()) {
                    if (!access.getTargetOwner().equals(item)
                            && (name == null || access.getName().compareTo(name) == 0)) {
                        for (String syntax : syntaxes) {
                            if (areAnnotations && isAnnotatedWith(access.getTargetOwner(), syntax)) {
                                return;
                            }
                            if (!areAnnotations && isNamedWith(access.getTargetOwner(), syntax)) {
                                return;
                            }
                        }
                    }
                }
                String message = item.getDescription() + " does not access any class "
                        + ((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(syntaxes)
                        + ((name != null) ? " and its member " + name : "");
                events.add(SimpleConditionEvent.violated(item, message));
            }
        };
    }

    public static final ArchCondition<JavaClass> haveNonPrivateMethodsWithTheSameReturnType =
            new ArchCondition<JavaClass>("have non-private methods with the same return type") {
        @Override
        public void check(JavaClass item, ConditionEvents events) {
            JavaClass type = null;
            for (JavaMethod method : item.getMethods()) {
                if (!method.getModifiers().contains(JavaModifier.PRIVATE)) {
                    if (type == null) {
                        type = method.getRawReturnType();
                    }
                    if (!method.getRawReturnType().equals(type)) {
                        String message = item.getDescription()
                                + " does not have non-private methods with the same return type";
                        events.add(SimpleConditionEvent.violated(item, message));
                        return;
                    }
                }
            }
            if (type == null) {
                String message = item.getDescription() + " does not have any non-private method";
                events.add(SimpleConditionEvent.violated(item, message));
            }
        }
    };

    public static final ArchCondition<JavaClass> implementAnInterfaceInTheSameLayer =
            new ArchCondition<JavaClass>("implement an interface in the same layer") {
        @Override
        public void check(JavaClass item, ConditionEvents events) {
            String syntax = null;
            boolean isAnnotation = PluginConfiguration.getSyntax().getPackages().areAnnotations();
            for (String packageSyntax : PluginConfiguration.getSyntax().getPackages().getAll()) {
                if (isInAnyPackage(item, isAnnotation, packageSyntax)) {
                    syntax = packageSyntax;
                }
            }
            if (syntax == null) {
                String message = item.getDescription() + " does not reside in any layer";
                events.add(SimpleConditionEvent.violated(item, message));
                return;
            }
            implementAnInterfaceInAnyPackage(item, events, isAnnotation, syntax);
        }
    };

    public static ArchCondition<JavaClass> implementAnInterfaceInAnyPackage(
            boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaClass>("implement an interface in any package "
                + ((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(syntaxes)) {
            @Override
            public void check(JavaClass item, ConditionEvents events) {
                implementAnInterfaceInAnyPackage(item, events, areAnnotations, syntaxes);
            }
        };
    }

    public static void implementAnInterfaceInAnyPackage(
            JavaClass item, ConditionEvents events, boolean areAnnotations, String... syntaxes) {
        if (item.getInterfaces().isEmpty()) {
            String message = item.getDescription() + " does not implement any interface";
            events.add(SimpleConditionEvent.violated(item, message));
            return;
        }
        for (JavaClass itemInterface : item.getInterfaces()) {
            for (String syntax : syntaxes) {
                if (isInAnyPackage(itemInterface, areAnnotations, syntax)) {
                    return;
                }
            }
        }
        String message = item.getDescription() + " does not implement an interface in any package "
                + ((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(syntaxes);
        events.add(SimpleConditionEvent.violated(item, message));
    }

    public static boolean isFirstNonPrivateMethodReturnType(JavaClass owner, JavaClass type) {
        for (JavaMethod method : owner.getMethods()) {
            if (!method.getModifiers().contains(JavaModifier.PRIVATE)) {
                return method.getRawReturnType().equals(type);
            }
        }
        return false;
    }
}
