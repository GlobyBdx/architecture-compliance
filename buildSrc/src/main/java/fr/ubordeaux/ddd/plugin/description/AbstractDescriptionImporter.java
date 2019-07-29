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

package fr.ubordeaux.ddd.plugin.description;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public abstract class AbstractDescriptionImporter implements DescriptionImporter {
    private final Description description;
    private final Syntax syntax;

    public AbstractDescriptionImporter(Description description, Syntax syntax) {
        this.description = description;
        this.syntax = syntax;
    }

    public final void importDescription() {
        for (String path : this.description.getPaths()) {
            File file = new File(path);
            if (!this.description.getFromPackages()) {
                this.importDescriptionFromClasses(file);
            }
            else {
                this.importDescriptionFromPackages(file);
            }
        }
    }

    public abstract void importDescriptionFromClasses(File file);
    public abstract void importDescriptionFromPackages(File file);

    public final void clearDescription() {
        this.description.getClasses().clear();
        this.description.getFields().clear();
        this.description.getMethods().clear();
        this.description.getPackages().clear();
    }

    protected final Map<String, List<String>> getClasses() {
        return this.description.getClasses();
    }

    protected final Map<String, List<String>> getFields() {
        return this.description.getFields();
    }

    protected final Map<String, List<String>> getMethods() {
        return this.description.getMethods();
    }

    protected final Map<String, List<String>> getPackages() {
        return this.description.getPackages();
    }

    protected final void importPackagesFromClasses() {
        for (String annotation : this.syntax.getPackages().getAll()) {
            if (this.description.getClasses().containsKey(annotation)) {
                if (!this.description.getPackages().containsKey(annotation)) {
                    this.description.getPackages().put(annotation, new ArrayList<>());
                }
                for (String annotatedClass : this.description.getClasses().get(annotation)) {
                    this.description.getPackages().get(annotation).add(
                            annotatedClass.substring(0, annotatedClass.lastIndexOf(".")));
                }
            }
        }
    }
}
