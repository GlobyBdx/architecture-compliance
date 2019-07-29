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

import fr.ubordeaux.ddd.annotations.classes.Aggregate;
import fr.ubordeaux.ddd.annotations.classes.Entity;
import fr.ubordeaux.ddd.annotations.classes.Factory;
import fr.ubordeaux.ddd.annotations.classes.Repository;
import fr.ubordeaux.ddd.annotations.classes.Service;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

public class ClassesExtension {
    public static final String EXTENSION_NAME = "classes";

    private String aggregate;
    private String entity;
    private String factory;
    private String repository;
    private String service;
    private String valueObject;

    private Boolean areAnnotations;

    public ClassesExtension() {
        this.aggregate = Aggregate.class.getName();
        this.entity = Entity.class.getName();
        this.factory = Factory.class.getName();
        this.repository = Repository.class.getName();
        this.service = Service.class.getName();
        this.valueObject = ValueObject.class.getName();
        this.areAnnotations = Boolean.TRUE;
    }

    @Input
    @Optional
    public String getAggregate() {
        return this.aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    @Input
    @Optional
    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Input
    @Optional
    public String getFactory() {
        return this.factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    @Input
    @Optional
    public String getRepository() {
        return this.repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Input
    @Optional
    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Input
    @Optional
    public String getValueObject() {
        return this.valueObject;
    }

    public void setValueObject(String valueObject) {
        this.valueObject = valueObject;
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
