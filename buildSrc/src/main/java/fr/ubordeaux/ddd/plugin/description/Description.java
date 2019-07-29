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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public final class Description {
    private final String[] paths;
    private final boolean fromPackages;
    private final boolean asXml;

    private final Syntax syntax;
    private final DescriptionImporter importer;
    private final Map<String, List<String>> classes;
    private final Map<String, List<String>> fields;
    private final Map<String, List<String>> methods;
    private final Map<String, List<String>> packages;

    public Description(String[] paths, boolean fromPackages, boolean asXml, Syntax syntax) {
        this.paths = paths;
        this.fromPackages = fromPackages;
        this.asXml = asXml;
        this.syntax = syntax;
        this.importer = ((!this.asXml) ?
                new JsonDescriptionImporter(this, this.syntax)
                : new XmlDescriptionImporter(this, this.syntax));
        this.classes = new HashMap<>();
        this.fields = new HashMap<>();
        this.methods = new HashMap<>();
        this.packages = new HashMap<>();
        this.update();
    }

    public String[] getPaths() {
        return this.paths;
    }

    public boolean getFromPackages() {
        return this.fromPackages;
    }

    public boolean getAsXml() {
        return this.asXml;
    }

    public Syntax getSyntax() {
        return this.syntax;
    }

    public Map<String, List<String>> getClasses() {
        return this.classes;
    }

    public Map<String, List<String>> getFields() {
        return this.fields;
    }

    public Map<String, List<String>> getMethods() {
        return this.methods;
    }

    public Map<String, List<String>> getPackages() {
        return this.packages;
    }

    public void update() {
        this.importer.clearDescription();
        this.importer.importDescription();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Description
                && this.paths.equals(((Description)object).paths)
                && this.fromPackages == ((Description)object).fromPackages
                && this.asXml == ((Description)object).asXml;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.paths, this.fromPackages, this.asXml);
    }

    @Override
    public String toString() {
        return "\nPaths: " + Arrays.toString(this.paths)
        + "\nFrom Packages: " + this.fromPackages
        + "\nAs XML: " + this.asXml
        + "\nClasses: " + getMapSize(this.classes)
        + "\nFields: " + getMapSize(this.fields)
        + "\nMethods: " + getMapSize(this.methods)
        + "\nPackages: " + getMapSize(this.packages);
    }

    private static int getMapSize(Map<String, List<String>> map) {
        int size = 0;
        for (List<String> list : map.values()) {
            size += list.size();
        }
        return size;
    }
}
