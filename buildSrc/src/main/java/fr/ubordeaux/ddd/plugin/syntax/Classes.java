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

public final class Classes {
    private final String aggregate;
    private final String entity;
    private final String factory;
    private final String repository;
    private final String service;
    private final String valueObject;

    private final boolean areAnnotations;

    public Classes(String aggregate, String entity, String factory, String repository, String service,
            String valueObject, boolean areAnnotations) {
        this.aggregate = aggregate;
        this.entity = entity;
        this.factory = factory;
        this.repository = repository;
        this.service = service;
        this.valueObject = valueObject;
        this.areAnnotations = areAnnotations;
    }

    public String getAggregate() {
        return this.aggregate;
    }

    public String getEntity() {
        return this.entity;
    }

    public String getFactory() {
        return this.factory;
    }

    public String getRepository() {
        return this.repository;
    }

    public String getService() {
        return this.service;
    }

    public String getValueObject() {
        return this.valueObject;
    }

    public boolean areAnnotations() {
        return this.areAnnotations;
    }

    public String[] getAll() {
        String[] all = {
                this.aggregate,
                this.entity,
                this.factory,
                this.repository,
                this.service,
                this.valueObject
        };
        return all;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Classes
                && this.aggregate.compareTo(((Classes)object).aggregate) == 0
                && this.entity.compareTo(((Classes)object).entity) == 0
                && this.factory.compareTo(((Classes)object).factory) == 0
                && this.repository.compareTo(((Classes)object).repository) == 0
                && this.service.compareTo(((Classes)object).service) == 0
                && this.valueObject.compareTo(((Classes)object).valueObject) == 0
                && this.areAnnotations == ((Classes)object).areAnnotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.aggregate, this.entity, this.factory, this.repository, this.service,
                this.valueObject, this.areAnnotations);
    }

    @Override
    public String toString() {
        return "\nAggregate: " + this.aggregate
                + "\nEntity: " + this.entity
                + "\nFactory: " + this.factory
                + "\nRepository: " + this.repository
                + "\nService: " + this.service
                + "\nValue Object: " + this.valueObject
                + "\nAre Annotations: " + this.areAnnotations;
    }
}
