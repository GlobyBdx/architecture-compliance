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

import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.annotations.methods.Setter;

public class MethodsExtension {
    public static final String EXTENSION_NAME = "methods";

    private String getter;
    private String setter;

    private Boolean areAnnotations;

    public MethodsExtension() {
        this.getter = Getter.class.getName();
        this.setter = Setter.class.getName();
        this.areAnnotations = Boolean.TRUE;
    }

    @Input
    @Optional
    public String getGetter() {
        return this.getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    @Input
    @Optional
    public String getSetter() {
        return this.setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    @Input
    @Optional
    public Boolean getAreAnnotations() {
        return this.areAnnotations;
    }

    public void setAreAnnotations(Boolean areAnnotations) {
        this.areAnnotations = areAnnotations;
    }
}
