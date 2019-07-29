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

package fr.ubordeaux.ddd.plugin;

import org.gradle.api.Project;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.Optional;

import fr.ubordeaux.ddd.plugin.description.DescriptionExtension;
import fr.ubordeaux.ddd.plugin.project.ProjectExtension;
import fr.ubordeaux.ddd.plugin.syntax.SyntaxExtension;

public class PluginExtension {
    public static final String EXTENSION_NAME = "compliance";

    private final SyntaxExtension syntax;
    private final DescriptionExtension description;
    private final ProjectExtension project;

    private String[] rules;
    private Boolean fixes;

    public PluginExtension(Project project) {
        this.syntax = project.getExtensions().create(SyntaxExtension.EXTENSION_NAME, SyntaxExtension.class, project);
        this.description = project.getExtensions().create(
                DescriptionExtension.EXTENSION_NAME, DescriptionExtension.class);
        this.project = project.getExtensions().create(ProjectExtension.EXTENSION_NAME, ProjectExtension.class);
        this.rules = new String[0];
        this.fixes = Boolean.FALSE;
    }

    @Input
    @Nested
    @Optional
    public SyntaxExtension getSyntax() {
        return this.syntax;
    }

    @Input
    @Nested
    @Optional
    public DescriptionExtension getDescription() {
        return this.description;
    }

    @Input
    @Nested
    public ProjectExtension getProject() {
        return this.project;
    }

    @Input
    @Optional
    public String[] getRules() {
        return this.rules;
    }

    public void setRules(String[] rules) {
        this.rules = rules;
    }

    @Input
    @Optional
    public Boolean getFixes() {
        return this.fixes;
    }

    public void setFixes(Boolean fixes) {
        this.fixes = fixes;
    }
}
