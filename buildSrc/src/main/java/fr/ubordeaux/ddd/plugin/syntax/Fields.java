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

public final class Fields {
    private final String entityId;
    private final String immutable;

    private final boolean areAnnotations;

    public Fields(String entityId, String immutable, boolean areAnnotations) {
        this.entityId = entityId;
        this.immutable = immutable;
        this.areAnnotations = areAnnotations;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getImmutable() {
        return this.immutable;
    }

    public boolean areAnnotations() {
        return this.areAnnotations;
    }

    public String[] getAll() {
        String[] all = {
                this.entityId,
                this.immutable
        };
        return all;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Fields
                && this.entityId.compareTo(((Fields)object).entityId) == 0
                && this.immutable.compareTo(((Fields)object).immutable) == 0
                && this.areAnnotations == ((Fields)object).areAnnotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entityId, this.immutable, this.areAnnotations);
    }

    @Override
    public String toString() {
        return "\nEntity ID: " + this.entityId
                + "\nImmutable: " + this.immutable
                + "\nAre Annotations: " + this.areAnnotations;
    }
}
