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

package fr.ubordeaux.ddd.plugin.project;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public final class ProjectImporter {
    private final Project project;

    public ProjectImporter(Project project) {
        this.project = project;
    }

    public void importProject() {
        JavaClasses classes = new ClassFileImporter().importPath(this.project.getDirectoryPath());
        if (this.project.getPackages().length != 0) {
            DescribedPredicate<JavaClass> predicate =
                    JavaClass.Predicates.resideInAPackage(this.project.getPackages()[0] + "..");
            for (int index = 1; index < this.project.getPackages().length; ++index) {
                predicate = predicate.or(
                        JavaClass.Predicates.resideInAPackage(this.project.getPackages()[index] + ".."));
            }
            classes = classes.that(predicate);
        }
        this.project.setClasses(classes);
    }

    public void clearProject() {
        this.project.setClasses(null);
    }
}
