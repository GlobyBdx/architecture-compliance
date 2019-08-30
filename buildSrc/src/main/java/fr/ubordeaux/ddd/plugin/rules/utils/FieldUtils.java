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

import java.util.HashSet;
import java.util.Set;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public final class FieldUtils {
    public static boolean isAnnotatedWith(JavaField field, String annotation) {
        if (PluginConfiguration.getDescription() == null) {
            return field.isAnnotatedWith(annotation);
        }
        if (PluginConfiguration.getDescription().getFields().containsKey(annotation)) {
            return PluginConfiguration.getDescription().getFields().get(annotation).contains(field.getFullName());
        }
        return false;
    }

    public static boolean isNamedWith(JavaField field, String name) {
        return field.getName().matches(name);
    }

    public static DescribedPredicate<JavaField> areAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaField>("are annotated with " + annotation) {
            @Override
            public boolean apply(JavaField item) {
                return isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaField> areNotAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaField>("are not annotated with " + annotation) {
            @Override
            public boolean apply(JavaField item) {
                return !isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaField> areNamedWith(String name) {
        return new DescribedPredicate<JavaField>("are named with " + name) {
            @Override
            public boolean apply(JavaField item) {
                return isNamedWith(item, name);
            }
        };
    }

    public static DescribedPredicate<JavaField> areNotNamedWith(String name) {
        return new DescribedPredicate<JavaField>("are not named with " + name) {
            @Override
            public boolean apply(JavaField item) {
                return !isNamedWith(item, name);
            }
        };
    }

    public static ArchCondition<JavaField> beAnnotatedWith(String annotation) {
        return new ArchCondition<JavaField>("be annotated with " + annotation) {
            @Override
            public void check(JavaField item, ConditionEvents events) {
                if (!isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is not annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaField> notBeAnnotatedWith(String annotation) {
        return new ArchCondition<JavaField>("not be annotated with " + annotation) {
            @Override
            public void check(JavaField item, ConditionEvents events) {
                if (isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaField> beNamedWith(String name) {
        return new ArchCondition<JavaField>("be named with " + name) {
            @Override
            public void check(JavaField item, ConditionEvents events) {
                if (!isNamedWith(item, name)) {
                    String message = item.getDescription() + " is not named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaField> notBeNamedWith(String name) {
        return new ArchCondition<JavaField>("not be named with " + name) {
            @Override
            public void check(JavaField item, ConditionEvents events) {
                if (isNamedWith(item, name)) {
                    String message = item.getDescription() + " is named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static boolean isImmutable(JavaField field) {
        return ((PluginConfiguration.getSyntax().getFields().areAnnotations()) ?
                isAnnotatedWith(field, PluginConfiguration.getSyntax().getFields().getImmutable())
                : isNamedWith(field, PluginConfiguration.getSyntax().getFields().getImmutable()))
                || ((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                        ClassUtils.isAnnotatedWith(
                                field.getRawType(), PluginConfiguration.getSyntax().getClasses().getEntity())
                        || ClassUtils.isAnnotatedWith(
                                field.getRawType(), PluginConfiguration.getSyntax().getClasses().getValueObject())
                        : ClassUtils.isNamedWith(
                                field.getRawType(), PluginConfiguration.getSyntax().getClasses().getEntity())
                        || ClassUtils.isNamedWith(
                                field.getRawType(), PluginConfiguration.getSyntax().getClasses().getValueObject()))
                || ((PluginConfiguration.getImmutables().contains(field.getRawType().getName())
                        || field.getRawType().isPrimitive()) && field.getModifiers().contains(JavaModifier.FINAL));
    }

    public static final DescribedPredicate<JavaField> areImmutables =
            new DescribedPredicate<JavaField>("are immutable") {
        @Override
        public boolean apply(JavaField item) {
            return isImmutable(item);
        }
    };

    public static final DescribedPredicate<JavaField> areNotImmutables =
            new DescribedPredicate<JavaField>("are not immutable") {
        @Override
        public boolean apply(JavaField item) {
            return !isImmutable(item);
        }
    };

    public static final ArchCondition<JavaField> beImmutables = new ArchCondition<JavaField>("be immutable") {
        @Override
        public void check(JavaField item, ConditionEvents events) {
            if (!isImmutable(item)) {
                String message = item.getDescription() + " is not immutable";
                events.add(SimpleConditionEvent.violated(item, message));
            }
        }
    };

    public static final ArchCondition<JavaField> notBeImmutables = new ArchCondition<JavaField>("not be immutable") {
        @Override
        public void check(JavaField item, ConditionEvents events) {
            if (isImmutable(item)) {
                String message = item.getDescription() + " is immutable";
                events.add(SimpleConditionEvent.violated(item, message));
            }
        }
    };

    public static final ArchCondition<JavaField> notBeExternallyAccessed =
            new ArchCondition<JavaField>("not be externally accessed") {
        @Override
        public void check(JavaField item, ConditionEvents events) {
            Set<JavaClass> internalClasses = getInternalClasses(item);
            for (JavaClass internalClass : internalClasses) {
                for (JavaAccess<?> access : internalClass.getAccessesToSelf()) {
                    if (!internalClasses.contains(access.getOriginOwner())
                            && !access.getOriginOwner().equals(item.getOwner())) {
                        String message = item.getDescription() + " internal class " + internalClass.getFullName()
                        + " is externally accessed by class " + access.getOriginOwner().getFullName();
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                }
            }
        }
    };

    public static final ArchCondition<JavaField> notBeExternallyInstantiated =
            new ArchCondition<JavaField>("not be externally instantiated") {
        @Override
        public void check(JavaField item, ConditionEvents events) {
            Set<JavaClass> internalClasses = getInternalClasses(item);
            for (JavaClass internalClass : internalClasses) {
                for (JavaConstructor constructor : internalClass.getConstructors()) {
                    for (JavaAccess<?> access : constructor.getAccessesToSelf()) {
                        if (!internalClasses.contains(access.getOriginOwner())
                                && !access.getOriginOwner().equals(item.getOwner())
                                && (!((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                                        ClassUtils.isAnnotatedWith(
                                                access.getOriginOwner(),
                                                PluginConfiguration.getSyntax().getClasses().getFactory())
                                        : ClassUtils.isNamedWith(
                                                access.getOriginOwner(),
                                                PluginConfiguration.getSyntax().getClasses().getFactory()))
                                        || !ClassUtils.isFirstNonPrivateMethodReturnType(
                                                access.getOriginOwner(), item.getOwner()))) {
                            String message = item.getDescription() + " internal class " + internalClass.getFullName()
                            + " is externally instantiated by class " + access.getOriginOwner().getFullName();
                            events.add(SimpleConditionEvent.violated(item, message));
                        }
                    }
                }
            }
        }
    };

    public static boolean isAccessedByMethod(JavaField field, JavaMethod method) {
        for (JavaFieldAccess access : field.getAccessesToSelf()) {
            for (JavaFieldAccess methodAccess : method.getFieldAccesses()) {
                if (access.equals(methodAccess)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean areAccessedByMethod(Set<JavaField> fields, JavaMethod method) {
        for (JavaField field : fields) {
            if (!isAccessedByMethod(field, method)) {
                return false;
            }
        }
        return true;
    }

    public static Set<JavaField> getFieldsAnnotatedWith(Set<JavaField> fields, String annotation) {
        Set<JavaField> annotatedFields = new HashSet<>();
        for (JavaField field : fields) {
            if (isAnnotatedWith(field, annotation)) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }

    public static Set<JavaField> getFieldsNamedWith(Set<JavaField> fields, String name) {
        Set<JavaField> namedFields = new HashSet<>();
        for (JavaField field : fields) {
            if (isNamedWith(field, name)) {
                namedFields.add(field);
            }
        }
        return namedFields;
    }

    public static Set<JavaClass> getInternalClasses(JavaField field) {
        Set<JavaClass> internalClasses = new HashSet<>();
        Set<JavaClass> temporaryClasses = new HashSet<>();
        Set<JavaClass> inspectedClasses = new HashSet<>();
        inspectedClasses.add(field.getRawType());
        boolean check;
        do {
            check = false;
            temporaryClasses.clear();
            for (JavaClass inspectedClass : inspectedClasses) {
                if (!((PluginConfiguration.getSyntax().getClasses().areAnnotations()) ?
                        ClassUtils.isAnnotatedWith(
                                inspectedClass, PluginConfiguration.getSyntax().getClasses().getAggregate())
                        : ClassUtils.isNamedWith(
                                inspectedClass, PluginConfiguration.getSyntax().getClasses().getAggregate()))
                        && internalClasses.add(inspectedClass)) {
                    for (JavaField inspectedField : inspectedClass.getFields()) {
                        if (isImmutable(inspectedField) && !inspectedField.getRawType().isPrimitive()) {
                            check |= temporaryClasses.add(inspectedField.getRawType());
                        }
                    }
                }
            }
            inspectedClasses.clear();
            inspectedClasses.addAll(temporaryClasses);
        } while (check);
        return internalClasses;
    }
}
