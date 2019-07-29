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

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public final class MethodUtils {
    public static boolean isAnnotatedWith(JavaMethod method, String annotation) {
        if (PluginConfiguration.getDescription() == null) {
            return method.isAnnotatedWith(annotation);
        }
        if (PluginConfiguration.getDescription().getMethods().containsKey(annotation)) {
            return PluginConfiguration.getDescription().getMethods().get(annotation).contains(method.getFullName());
        }
        return false;
    }

    public static boolean isNamedWith(JavaMethod method, String name) {
        return method.getName().matches(name);
    }

    public static DescribedPredicate<JavaMethod> areAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaMethod>("are annotated with " + annotation) {
            @Override
            public boolean apply(JavaMethod item) {
                return isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaMethod> areNotAnnotatedWith(String annotation) {
        return new DescribedPredicate<JavaMethod>("are not annotated with " + annotation) {
            @Override
            public boolean apply(JavaMethod item) {
                return !isAnnotatedWith(item, annotation);
            }
        };
    }

    public static DescribedPredicate<JavaMethod> areNamedWith(String name) {
        return new DescribedPredicate<JavaMethod>("are named with " + name) {
            @Override
            public boolean apply(JavaMethod item) {
                return isNamedWith(item, name);
            }
        };
    }

    public static DescribedPredicate<JavaMethod> areNotNamedWith(String name) {
        return new DescribedPredicate<JavaMethod>("are not named with " + name) {
            @Override
            public boolean apply(JavaMethod item) {
                return !isNamedWith(item, name);
            }
        };
    }

    public static ArchCondition<JavaMethod> beAnnotatedWith(String annotation) {
        return new ArchCondition<JavaMethod>("be annotated with " + annotation) {
            @Override
            public void check(JavaMethod item, ConditionEvents events) {
                if (!isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is not annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaMethod> notBeAnnotatedWith(String annotation) {
        return new ArchCondition<JavaMethod>("not be annotated with " + annotation) {
            @Override
            public void check(JavaMethod item, ConditionEvents events) {
                if (isAnnotatedWith(item, annotation)) {
                    String message = item.getDescription() + " is annotated with " + annotation;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaMethod> beNamedWith(String name) {
        return new ArchCondition<JavaMethod>("be named with " + name) {
            @Override
            public void check(JavaMethod item, ConditionEvents events) {
                if (!isNamedWith(item, name)) {
                    String message = item.getDescription() + " is not named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static ArchCondition<JavaMethod> notBeNamedWith(String name) {
        return new ArchCondition<JavaMethod>("not be named with " + name) {
            @Override
            public void check(JavaMethod item, ConditionEvents events) {
                if (isNamedWith(item, name)) {
                    String message = item.getDescription() + " is named with " + name;
                    events.add(SimpleConditionEvent.violated(item, message));
                }
            }
        };
    }

    public static final DescribedPredicate<JavaMethod> accessAtLeastOneField =
            accessAtLeastOneField(false, new String[0]);

    public static DescribedPredicate<JavaMethod> accessAtLeastOneField(boolean areAnnotations, String... syntaxes) {
        return new DescribedPredicate<JavaMethod>("access at least one field"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public boolean apply(JavaMethod item) {
                for (JavaField field : item.getOwner().getFields()) {
                    if (FieldUtils.isAccessedByMethod(field, item)) {
                        if (syntaxes.length == 0) {
                            return true;
                        }
                        for (String syntax : syntaxes) {
                            if (areAnnotations && FieldUtils.isAnnotatedWith(field, syntax)) {
                                return true;
                            }
                            if (!areAnnotations && FieldUtils.isNamedWith(field, syntax)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        };
    }

    public static final ArchCondition<JavaMethod> accessOnlyImmutableFields =
            accessOnlyImmutableFields(false, new String[0]);

    public static ArchCondition<JavaMethod> accessOnlyImmutableFields(boolean areAnnotations, String... syntaxes) {
        return new ArchCondition<JavaMethod>("access only immutable fields"
                + ((syntaxes.length != 0) ?
                        ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(syntaxes)
                        : "")) {
            @Override
            public void check(JavaMethod item, ConditionEvents events) {
                for (JavaField field : item.getOwner().getFields()) {
                    if (FieldUtils.isAccessedByMethod(field, item) && !FieldUtils.isImmutable(field)) {
                        String message = item.getDescription() + " does access field " + field.getFullName()
                        + " which may not be immutable";
                        if (syntaxes.length == 0) {
                            events.add(SimpleConditionEvent.violated(item, message));
                        }
                        for (String syntax : syntaxes) {
                            if (areAnnotations && FieldUtils.isAnnotatedWith(field, syntax)) {
                                events.add(SimpleConditionEvent.violated(item, message));
                            }
                            if (!areAnnotations && FieldUtils.isNamedWith(field, syntax)) {
                                events.add(SimpleConditionEvent.violated(item, message));
                            }
                        }
                    }
                }
            }
        };
    }

    public static final DescribedPredicate<JavaMethod> areNotObjectClassMethods =
            new DescribedPredicate<JavaMethod>("are not Object.class methods") {
        @Override
        public boolean apply(JavaMethod method) {
            return !isEqualsMethod(method) && !isHashCodeMethod(method) && !isToStringMethod(method);
        }
    };

    public static boolean isEqualsMethod(JavaMethod method) {
        return method.getName().compareTo("equals") == 0
                && method.getRawParameterTypes().size() == 1
                && method.getRawParameterTypes().get(0).getFullName().compareTo(Object.class.getName()) == 0
                && method.getRawReturnType().getFullName().compareTo(boolean.class.getName()) == 0;
    }

    public static boolean isHashCodeMethod(JavaMethod method) {
        return method.getName().compareTo("hashCode") == 0
                && method.getRawParameterTypes().size() == 0
                && method.getRawReturnType().getFullName().compareTo(int.class.getName()) == 0;
    }

    public static boolean isToStringMethod(JavaMethod method) {
        return method.getName().compareTo("toString") == 0
                && method.getRawParameterTypes().size() == 0
                && method.getRawReturnType().getFullName().compareTo(String.class.getName()) == 0;
    }
}
