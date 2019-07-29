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

package fr.ubordeaux.ddd.plugin.syntax;

import org.gradle.api.Project;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.Optional;

public class SyntaxExtension {
    public static final String EXTENSION_NAME = "syntax";

    private final ClassesExtension classes;
    private final FieldsExtension fields;
    private final MethodsExtension methods;
    private final PackagesExtension packages;

    public SyntaxExtension(Project project) {
        this.classes = project.getExtensions().create(ClassesExtension.EXTENSION_NAME, ClassesExtension.class);
        this.fields = project.getExtensions().create(FieldsExtension.EXTENSION_NAME, FieldsExtension.class);
        this.methods = project.getExtensions().create(MethodsExtension.EXTENSION_NAME, MethodsExtension.class);
        this.packages = project.getExtensions().create(PackagesExtension.EXTENSION_NAME, PackagesExtension.class);
    }

    @Input
    @Nested
    @Optional
    public ClassesExtension getClasses() {
        return this.classes;
    }

    @Input
    @Nested
    @Optional
    public FieldsExtension getFields() {
        return this.fields;
    }

    @Input
    @Nested
    @Optional
    public MethodsExtension getMethods() {
        return this.methods;
    }

    @Input
    @Nested
    @Optional
    public PackagesExtension getPackages() {
        return this.packages;
    }
}
