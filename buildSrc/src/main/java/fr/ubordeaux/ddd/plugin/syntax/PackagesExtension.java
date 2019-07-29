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

import fr.ubordeaux.ddd.annotations.packages.Anticorruption;
import fr.ubordeaux.ddd.annotations.packages.Application;
import fr.ubordeaux.ddd.annotations.packages.Domain;
import fr.ubordeaux.ddd.annotations.packages.Infrastructure;
import fr.ubordeaux.ddd.annotations.packages.Presentation;

public class PackagesExtension {
    public static final String EXTENSION_NAME = "packages";

    private String anticorruption;
    private String application;
    private String domain;
    private String infrastructure;
    private String presentation;

    private Boolean areAnnotations;

    public PackagesExtension() {
        this.anticorruption = Anticorruption.class.getName();
        this.application = Application.class.getName();
        this.domain = Domain.class.getName();
        this.infrastructure = Infrastructure.class.getName();
        this.presentation = Presentation.class.getName();
        this.areAnnotations = Boolean.TRUE;
    }

    @Input
    @Optional
    public String getAnticorruption() {
        return this.anticorruption;
    }

    public void setAnticorruption(String anticorruption) {
        this.anticorruption = anticorruption;
    }

    @Input
    @Optional
    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Input
    @Optional
    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Input
    @Optional
    public String getInfrastructure() {
        return this.infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    @Input
    @Optional
    public String getPresentation() {
        return this.presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
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
