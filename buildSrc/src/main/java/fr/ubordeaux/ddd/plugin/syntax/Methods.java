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

public final class Methods {
    private final String getter;
    private final String setter;

    private final boolean areAnnotations;

    public Methods(String getter, String setter, boolean areAnnotations) {
        this.getter = getter;
        this.setter = setter;
        this.areAnnotations = areAnnotations;
    }

    public String getGetter() {
        return this.getter;
    }

    public String getSetter() {
        return this.setter;
    }

    public boolean areAnnotations() {
        return this.areAnnotations;
    }

    public String[] getAll() {
        String[] all = {
                this.getter,
                this.setter
        };
        return all;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Methods
                && this.getter.compareTo(((Methods)object).getter) == 0
                && this.setter.compareTo(((Methods)object).setter) == 0
                && this.areAnnotations == ((Methods)object).areAnnotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getter, this.setter, this.areAnnotations);
    }

    @Override
    public String toString() {
        return "\nGetter: " + this.getter
                + "\nSetter: " + this.setter
                + "\nAre Annotations: " + this.areAnnotations;
    }
}
