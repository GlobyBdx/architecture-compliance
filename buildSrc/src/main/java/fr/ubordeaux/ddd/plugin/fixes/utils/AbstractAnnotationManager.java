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

package fr.ubordeaux.ddd.plugin.fixes.utils;

import java.io.File;

import fr.ubordeaux.ddd.plugin.description.Description;

public abstract class AbstractAnnotationManager {
    private final Description description;

    public AbstractAnnotationManager(Description description) {
        this.description = description;
    }

    public final void manage(String name, String annotation, boolean inclusion) {
        for (String path : this.description.getPaths()) {
            File file = new File(path);
            if (!this.description.getFromPackages()) {
                this.manageArchitectureFromClasses(file, name, annotation, inclusion);
            }
            else {
                this.manageArchitectureFromPackages(file, name, annotation, inclusion);
            }
        }
    }

    public abstract void manageArchitectureFromClasses(File file, String name, String annotation, boolean inclusion);
    public abstract void manageArchitectureFromPackages(File file, String name, String annotation, boolean inclusion);
}
