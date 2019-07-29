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

import java.util.Objects;

public final class Syntax {
    private final Classes classes;
    private final Fields fields;
    private final Methods methods;
    private final Packages packages;

    public Syntax(Classes classLevel, Fields fieldLevel, Methods methodLevel, Packages packageLevel) {
        this.classes = classLevel;
        this.fields = fieldLevel;
        this.methods = methodLevel;
        this.packages = packageLevel;
    }

    public Classes getClasses() {
        return this.classes;
    }

    public Fields getFields() {
        return this.fields;
    }

    public Methods getMethods() {
        return this.methods;
    }

    public Packages getPackages() {
        return this.packages;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Syntax
                && this.classes.equals(((Syntax)object).classes)
                && this.fields.equals(((Syntax)object).fields)
                && this.methods.equals(((Syntax)object).methods)
                && this.packages.equals(((Syntax)object).packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.classes, this.fields, this.methods, this.packages);
    }

    @Override
    public String toString() {
        return "\nClasses: " + this.classes.toString()
        + "\n----------------------------------------"
        + "\nFields: " + this.fields.toString()
        + "\n----------------------------------------"
        + "\nMethods: " + this.methods.toString()
        + "\n----------------------------------------"
        + "\nPackages: " + this.packages.toString();
    }
}
