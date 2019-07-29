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

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.fields.Immutable;

public class FieldsExtension {
    public static final String EXTENSION_NAME = "fields";

    private String entityId;
    private String immutable;

    private Boolean areAnnotations;

    public FieldsExtension() {
        this.entityId = EntityID.class.getName();
        this.immutable = Immutable.class.getName();
        this.areAnnotations = Boolean.TRUE;
    }

    @Input
    @Optional
    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Input
    @Optional
    public String getImmutable() {
        return this.immutable;
    }

    public void setImmutable(String immutable) {
        this.immutable = immutable;
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
