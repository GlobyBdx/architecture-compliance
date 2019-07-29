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

package fr.ubordeaux.ddd.plugin.conformance;

import java.util.ArrayList;
import java.util.List;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;

import fr.ubordeaux.ddd.plugin.description.Description;
import fr.ubordeaux.ddd.plugin.project.Project;

public final class ConformanceChecker {
    private final Description description;
    private final Project project;

    public ConformanceChecker(Description description, Project project) {
        this.description = description;
        this.project = project;
    }

    public List<String> checkClasses() {
        List<String> classes = new ArrayList<>();
        for (String annotation : this.description.getClasses().keySet()) {
            for (String annotatedClass : this.description.getClasses().get(annotation)) {
                if (!annotatedClass.endsWith("package-info")) {
                    if (!this.project.getClasses().contain(annotatedClass)) {
                        classes.add(annotatedClass);
                    }
                }
            }
        }
        return classes;
    }

    public List<String> checkFields() {
        List<String> fields = new ArrayList<>();
        for (String annotation : this.description.getFields().keySet()) {
            for (String annotatedField : this.description.getFields().get(annotation)) {
                boolean found = false;
                for (JavaClass importedClass : this.project.getClasses()) {
                    for (JavaField field : importedClass.getFields()) {
                        if (field.getFullName().compareTo(annotatedField) == 0) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    fields.add(annotatedField);
                }
            }
        }
        return fields;
    }

    public List<String> checkMethods() {
        List<String> methods = new ArrayList<>();
        for (String annotation : this.description.getMethods().keySet()) {
            for (String annotatedMethod : this.description.getMethods().get(annotation)) {
                boolean found = false;
                for (JavaClass importedClass : this.project.getClasses()) {
                    for (JavaMethod method : importedClass.getMethods()) {
                        if (method.getFullName().compareTo(annotatedMethod) == 0) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    methods.add(annotatedMethod);
                }
            }
        }
        return methods;
    }
}
