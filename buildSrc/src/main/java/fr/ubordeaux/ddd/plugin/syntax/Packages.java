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

public final class Packages {
    private final String anticorruption;
    private final String application;
    private final String domain;
    private final String infrastructure;
    private final String presentation;

    private final boolean areAnnotations;

    public Packages(String anticorruption, String application, String domain, String infrastructure,
            String presentation, boolean areAnnotations) {
        this.anticorruption = anticorruption;
        this.application = application;
        this.domain = domain;
        this.infrastructure = infrastructure;
        this.presentation = presentation;
        this.areAnnotations = areAnnotations;
    }

    public String getAnticorruption() {
        return this.anticorruption;
    }

    public String getApplication() {
        return this.application;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getInfrastructure() {
        return this.infrastructure;
    }

    public String getPresentation() {
        return this.presentation;
    }

    public boolean areAnnotations() {
        return this.areAnnotations;
    }

    public String[] getAll() {
        String[] all = {
                this.anticorruption,
                this.application,
                this.domain,
                this.infrastructure,
                this.presentation
        };
        return all;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Packages
                && this.anticorruption.compareTo(((Packages)object).anticorruption) == 0
                && this.application.compareTo(((Packages)object).application) == 0
                && this.domain.compareTo(((Packages)object).domain) == 0
                && this.infrastructure.compareTo(((Packages)object).infrastructure) == 0
                && this.presentation.compareTo(((Packages)object).presentation) == 0
                && this.areAnnotations == ((Packages)object).areAnnotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.anticorruption, this.application, this.domain, this.infrastructure,
                this.presentation, this.areAnnotations);
    }

    @Override
    public String toString() {
        return "\nAnticorruption: " + this.anticorruption
                + "\nApplication: " + this.application
                + "\nDomain: " + this.domain
                + "\nInfrastructure: " + this.infrastructure
                + "\nPresentation: " + this.presentation
                + "\nAre Annotations: " + this.areAnnotations;
    }
}
