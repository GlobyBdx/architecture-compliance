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

import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

public class DescriptionExtension {
    public static final String EXTENSION_NAME = "description";

    private String[] paths;
    private Boolean fromPackages;
    private Boolean asXml;

    public DescriptionExtension() {
        this.paths = new String[0];
        this.fromPackages = Boolean.FALSE;
        this.asXml = Boolean.FALSE;
    }

    @Input
    @Optional
    public String[] getPaths() {
        return this.paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    @Input
    @Optional
    public Boolean getFromPackages() {
        return this.fromPackages;
    }

    public void setFromPackages(Boolean fromPackages) {
        this.fromPackages = fromPackages;
    }

    @Input
    @Optional
    public Boolean getAsXml() {
        return this.asXml;
    }

    public void setAsXml(Boolean asXml) {
        this.asXml = asXml;
    }
}
